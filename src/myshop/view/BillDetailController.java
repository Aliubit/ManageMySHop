package myshop.view;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.w3c.dom.events.MouseEvent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import myshop.Main;
import myshop.dbhandler.DBQuries;
import myshop.models.MyPurchaseBillModel;

public class BillDetailController implements Initializable{

	@FXML
	private Label billNoLabel;
	
	@FXML
	private Label dateLabel;
	
	@FXML
	private Label supplierIdLabel;
	
	@FXML
	private Label supplierNameLabel;
	
	@FXML
	private Label transcriptLabel;
	
	@FXML
	private Label totalAmountLabel;
	
	@FXML
	private Label idLabel;
	
	@FXML
	private Label nameLabel;
	
	@FXML
	private Label billHeaderLabel;
	
	@FXML
	private Button editBtn;
	
	@FXML 
	public  TableColumn<MyPurchaseBillModel,String> serialNoColumn;
	@FXML 
	public  TableColumn<MyPurchaseBillModel,String> productIdColumn;
	@FXML 
	public  TableColumn<MyPurchaseBillModel,String> productNameColumn;
	@FXML
	public TableColumn<MyPurchaseBillModel,String> productQuantityColumn;
	@FXML
	public TableColumn<MyPurchaseBillModel,String> productRateColumn;
	@FXML
	public TableColumn<MyPurchaseBillModel,String> productAmountColumn;
	@FXML
	public  TableView<MyPurchaseBillModel> table;
	
	public ObservableList<MyPurchaseBillModel> list;
	
	String columnOfBill,columnOfpartyName,columnOfpartyId,columnOfAmount,columnOfDate,columnOfItemName,columnOfItemId;
	

	 public static String dateLabelText;
	 public static String billNoLabelText;
	 public static String supplierIdNameLabelText;
	 public static String productIdNameLabelText;
	 public static String amountLabelText;
	 public static String quantityLabelText;
	 public	static String rateLabelText;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		list = FXCollections.observableArrayList();
		
		  ContextMenu cm = new ContextMenu();
		     MenuItem mi1 = new MenuItem("Edit ");
		     cm.getItems().add(mi1);
		     MenuItem mi2 = new MenuItem("Delete");
		     cm.getItems().add(mi2);
	
		     mi1.setOnAction(event -> {
	               
		    	 MyPurchaseBillModel model= table.getSelectionModel().getSelectedItem();
		    	 
		    	 dateLabelText=dateLabel.getText();
					supplierIdNameLabelText =this.supplierNameLabel.getText() + "_" + this.supplierIdLabel.getText();
					billNoLabelText=this.billNoLabel.getText();
						productIdNameLabelText= model.productId + "_" + model.productName;
						quantityLabelText=model.productQuantity;
						rateLabelText=model.productRate;
						
						 try {
							Main.showPurchaseBillDetailWithEdit();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    	 
		    	 
		     });
		     
		//System.out.println("Initialize called");
		if(Main.isPurchaseClicked)
		{
			columnOfBill="bill_No";
			columnOfpartyId="supplier_Id";
			columnOfpartyName="supplier_Name";
			columnOfAmount="Amount";
			columnOfDate="purchase_Date";
			columnOfItemId="item_Id";
			idLabel.setText("Supplier ID :");
			nameLabel.setText("Supplier Name :");
			transcriptLabel.setText("Bill No : ");
			columnOfItemName="item_Name";
			
			billHeaderLabel.setText("Purchase Bill");
			//System.out.println("Initialize called if");
			
			//////On CLick Method
			
			
			
			/*table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
				
				try {
					//System.out.println("Haan Bhai");
					//PurchaseBillEditFormViewController controller = Main.showPurchaseBillDetailWithEdit();
					
					System.out.println("1st date "+dateLabel.getText());
					
					dateLabelText=dateLabel.getText();
				supplierIdNameLabelText =this.supplierNameLabel.getText() + "_" + this.supplierIdLabel.getText();
				billNoLabelText=this.billNoLabel.getText();
					productIdNameLabelText= newSelection.productId + "_" + newSelection.productName;
					quantityLabelText=newSelection.productQuantity;
					rateLabelText=newSelection.productRate;
					
					 Main.showPurchaseBillDetailWithEdit();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			});*/
			
			////End On CLick Method
		}
		else
		{
			columnOfBill="reciept_No";
			columnOfpartyId="customer_Id";
			columnOfpartyName="customer_Name";
			columnOfAmount="amount";
			columnOfDate="sale_Date";
			columnOfItemId="product_Id";
			columnOfItemName="product_Name";
			idLabel.setText("Customer ID :");
			nameLabel.setText("Customer Name :");
			billHeaderLabel.setText("Sales Invoice");
			transcriptLabel.setText("Invoice No : ");
			//System.out.println("Initialize called else");
		}

		
		DBQuries db = new DBQuries();
		ResultSet rset;
		if(Main.isPurchaseClicked)
		{
		rset = db.getPurchaseBillDetails(PurchaseHistoryViewController.clickedBillNo, PurchaseHistoryViewController.clickedName,PurchaseHistoryViewController.clickedId);
		}
		else
		{
			rset = db.getSalesBillDetails(PurchaseHistoryViewController.clickedBillNo, PurchaseHistoryViewController.clickedName,PurchaseHistoryViewController.clickedId);
		}
		int counter=0;
		try {
			while(rset.next())
			{
				counter++;
				list.add(new MyPurchaseBillModel(counter+"",rset.getString(columnOfItemId),rset.getString(columnOfItemName), rset.getInt("quantity")+"", rset.getFloat("rate")+"", rset.getFloat(columnOfAmount)+""));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		 billNoLabel.setText(PurchaseHistoryViewController.clickedBillNo);
	     supplierIdLabel.setText(PurchaseHistoryViewController.clickedId);
	     supplierNameLabel.setText(PurchaseHistoryViewController.clickedName);
	     dateLabel.setText(PurchaseHistoryViewController.clickedDate);
	     totalAmountLabel.setText(PurchaseHistoryViewController.clickedAmount);
		
		
		 serialNoColumn.setCellValueFactory(new PropertyValueFactory<MyPurchaseBillModel,String>("serialNo"));
         productIdColumn.setCellValueFactory(new PropertyValueFactory<MyPurchaseBillModel,String>("productId"));
         productNameColumn.setCellValueFactory(new PropertyValueFactory<MyPurchaseBillModel,String>("productName"));
         productQuantityColumn.setCellValueFactory(new PropertyValueFactory<MyPurchaseBillModel,String>("ProductQuantity"));
	     productRateColumn.setCellValueFactory(new PropertyValueFactory<MyPurchaseBillModel,String>("productRate"));
	     productAmountColumn.setCellValueFactory(new PropertyValueFactory<MyPurchaseBillModel,String>("productAmount"));
	     
	     
	     ////
	    
	     table.setRowFactory(tv -> {
	    	    TableRow<MyPurchaseBillModel> row = new TableRow<>();
	    	    row.setOnMouseClicked(event -> {
	    	        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
	    	             && event.getClickCount() == 2) {

	    	        	MyPurchaseBillModel clickedRow = row.getItem();
	    	           //System.out.println(clickedRow.productAmount + " "+clickedRow.productId);
	    	        }
	    	        if(! row.isEmpty() &&event.getButton() == MouseButton.SECONDARY)
	    	        {
	    	        	cm.show(table,event.getScreenX(),event.getScreenY());
	    	        }
	    	        if(event.getButton() == MouseButton.PRIMARY&& event.getClickCount() == 1){
	    	        	cm.hide();
	    	        }
	    	    });
	    	    return row ;
	    	});

	     /////
	
	     table.setItems(list);
	     
	    
	
	}
	
	
	
}
