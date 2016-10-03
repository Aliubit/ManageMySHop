package myshop.view;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import myshop.Main;
import myshop.dbhandler.DBQuries;
import myshop.models.MyPurchaseBillModel;
import myshop.models.PurchaseHistoryModel;



public class PurchaseHistoryViewController implements Initializable {

	@FXML
	public DatePicker datePicker;
	@FXML
	public Button searchButton;
	@FXML 
	public  TableColumn<PurchaseHistoryModel,String> dateColumn;
	@FXML 
	public  TableColumn<PurchaseHistoryModel,String> billNoColumn;
	@FXML 
	public TableColumn<PurchaseHistoryModel,String> supplierIdColumn;
	@FXML 
	public  TableColumn<PurchaseHistoryModel,String> supplierNameColumn;
	@FXML
	public TableColumn<PurchaseHistoryModel,String> billedAmountColumn;
	@FXML
	public  TableView<PurchaseHistoryModel> table;
	
	public static String clickedBillNo,clickedName,clickedId,clickedDate,clickedAmount;
	
	public PurchaseHistoryModel model;
	
	public static ObservableList<PurchaseHistoryModel> list;
	
	String columnOfBill,columnOfpartyName,columnOfpartyId,columnOfAmount,columnOfDate;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		
		//System.out.println();
		

		/*table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		   
			
			System.out.println(" Obs is ");
			System.out.println("Selected Bill is "+newSelection.billNo +"Selected Name is "+newSelection.name+"Selected ID Is "+newSelection.id);
			clickedBillNo=newSelection.billNo;
			clickedName=newSelection.name;
			clickedId=newSelection.id;
			clickedDate=newSelection.date;
			clickedAmount=newSelection.amount;

			
			try {
				Main.showPurchaseBillDetail();
			} catch (Exception e) {
				//e.printStackTrace();
			}
			
		});*/
		
		DBQuries dbQueries = new DBQuries();
		ResultSet rset;
		if(Main.isPurchaseClicked)
			rset = dbQueries.getPurchaseHistoryWithDate();
		else
		{
			rset = dbQueries.getSalesHistoryWithDate();
		}
			
		if(Main.isPurchaseClicked)
		{
			columnOfBill="bill_No";
			columnOfpartyId="supplier_Id";
			columnOfpartyName="supplier_Name";
			columnOfAmount="Amount";
			columnOfDate="purchase_Date";
		}
		else
		{
			columnOfBill="reciept_No";
			columnOfpartyId="customer_Id";
			columnOfpartyName="customer_Name";
			columnOfAmount="amount";
			columnOfDate="sale_Date";
		}

		setDataForTable(rset);
	}

	@FXML
	void searchButtonPressed()
	{
		
		LocalDate currDate = datePicker.getValue();
		
		table.setEditable(true);
		int year= currDate.getYear();
		int month=currDate.getMonthValue();
		int date =currDate.getDayOfMonth();
		
		String dateFormat = date+"/"+month+"/"+year;
		
		//System.out.println(dateFormat);
		
		DBQuries dbQueries = new DBQuries();
		ResultSet rset;
		if(Main.isPurchaseClicked)
			rset = dbQueries.getPurchaseHistoryWithDate(dateFormat);
		else
		{
			rset = dbQueries.getSalesHistoryWithDate(dateFormat);
		}
		
		setDataForTable(rset);
	}
	
	void setDataForTable(ResultSet rset)
	{
		list = FXCollections.observableArrayList();
		
		try {
			while(rset.next())
			{
				//System.out.println("in WHile");
				if(!list.isEmpty())
				{
					//System.out.println("in if");
					boolean found=false;
					
					for(PurchaseHistoryModel model : list)
					{
						if(model.billNo.equals(rset.getString(columnOfBill)) && model.name.equals(rset.getString(columnOfpartyName)) && model.id.equals(rset.getString(columnOfpartyId)))
						{
							Float amountFromdb = rset.getFloat(columnOfAmount);
							String amountFromList = model.amount;
							Float totalAmount = Float.parseFloat(amountFromList)+amountFromdb;
							model.amount=totalAmount+"";
							found=true;
						}
					}
					if(!found)
					{
						list.add(new PurchaseHistoryModel(rset.getString(columnOfDate),rset.getString(columnOfBill),rset.getString(columnOfpartyName),rset.getString(columnOfpartyId),rset.getFloat(columnOfAmount)+""));
					    found=false;
					}
					
				}
				else
				{
					//System.out.println("in else");
					list.add(new PurchaseHistoryModel(rset.getString(columnOfDate),rset.getString(columnOfBill),rset.getString(columnOfpartyName),rset.getString(columnOfpartyId),rset.getFloat(columnOfAmount)+""));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(Main.isSalesClicked)
		{
		supplierIdColumn.setText("Customer ID");
		supplierNameColumn.setText("Customer Name");
		billedAmountColumn.setText("Invoice Amount");
		billNoColumn.setText("Reciept No");
		}
		
		  	 dateColumn.setCellValueFactory(new PropertyValueFactory<PurchaseHistoryModel,String>("date"));
	         billNoColumn.setCellValueFactory(new PropertyValueFactory<PurchaseHistoryModel,String>("billNo"));
	         supplierIdColumn.setCellValueFactory(new PropertyValueFactory<PurchaseHistoryModel,String>("Id"));
	         supplierNameColumn.setCellValueFactory(new PropertyValueFactory<PurchaseHistoryModel,String>("name"));
		     billedAmountColumn.setCellValueFactory(new PropertyValueFactory<PurchaseHistoryModel,String>("amount"));
		
		     table.setItems(list);
		     
		     
		     table.setRowFactory(tv -> {
		    	    TableRow<PurchaseHistoryModel> row = new TableRow<>();
		    	    row.setOnMouseClicked(event -> {
		    	        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
		    	             && event.getClickCount() == 2) {

		    	        	PurchaseHistoryModel clickedRow = row.getItem();
		    	        	
		    	        	clickedBillNo=clickedRow.billNo;
		    				clickedName=clickedRow.name;
		    				clickedId=clickedRow.id;
		    				clickedDate=clickedRow.date;
		    				clickedAmount=clickedRow.amount;

		    				
		    				try {
		    					Main.showPurchaseBillDetail();
		    				} catch (Exception e) {
		    					//e.printStackTrace();
		    				}
		    	          
		    	        }
		    	        if(! row.isEmpty() &&event.getButton() == MouseButton.SECONDARY)
		    	        {
		    	        	
		    	        }
		    	        if(event.getButton() == MouseButton.PRIMARY&& event.getClickCount() == 1){
		    	        	
		    	        }
		    	    });
		    	    return row ;
		    	});
	}
	
	
}
