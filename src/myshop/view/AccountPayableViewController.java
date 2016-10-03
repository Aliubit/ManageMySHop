package myshop.view;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import myshop.Main;
import myshop.dbhandler.DBQuries;
import myshop.models.MyAccountPaybleModel;

public class AccountPayableViewController implements Initializable {

	@FXML
	public Label accountRecievableLabel;
	@FXML
	public TableView<MyAccountPaybleModel> table;
	@FXML 
	public TableColumn<MyAccountPaybleModel,String> serialColumn;
	@FXML 
	public TableColumn<MyAccountPaybleModel,String> supplierIdColumn;
	@FXML 
	public TableColumn<MyAccountPaybleModel,String> supplierNameColumn;
	@FXML 
	public TableColumn<MyAccountPaybleModel,String> debitColumn;
	@FXML
	public TableColumn<MyAccountPaybleModel,String> creditColumn;
	@FXML
	public TableColumn<MyAccountPaybleModel,String> balanceColumn;
	
	//public static String clickedId,clickedName,clickedDebit,clickedCredit,clickedBalance;
	
	public MyAccountPaybleModel model;
	
	ObservableList<MyAccountPaybleModel> list;
	
	String columnOfSerialNo,columnOfDealerName,columnOfDealerId,columnOfDebit,columnOfCredit,columnOfBalance;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		list=FXCollections.observableArrayList();
		
		////////// Zafar And Umais
		
		if(Main.isPayableClicked){
			columnOfSerialNo="Serial_No";
			columnOfDealerId="Supplier_Id";
			columnOfDealerName="Supplier_Name";
			columnOfDebit="Debit";
			columnOfCredit="Credit";
			columnOfBalance="Balance";
		
		}
		else{
			columnOfSerialNo="Serial_No";
			columnOfDealerId="Customer_Id";
			columnOfDealerName="Customer_Name";
			columnOfDebit="Credit";
			columnOfCredit="Debit";
			columnOfBalance="Balance";
		
		}

		
		/////////// End
		
	DBQuries db = new DBQuries();
	
	if(Main.isPayableClicked){
		
	
		ResultSet rset = db.getAllSuppliersWithLeisure();
		
		int counter=0;
		try {
			while(rset.next())
			{
				counter++;
				columnOfDealerName=rset.getString("name");
				columnOfDealerId=rset.getString("mobile_No_1");
				ResultSet rset1= db.getdebitOfDealerFromPurchaseHistory(columnOfDealerName,columnOfDealerId);
				Float debit=0f,credit=0f;
				while(rset1.next())
				{
					debit+=rset1.getFloat("Amount");
				}
				
				ResultSet rset2=db.getcreditOfDealerFromAccountPayable(columnOfDealerName, columnOfDealerId);
				while(rset2.next())
				{
					credit+=rset2.getFloat("amount");
				}
				
				list.add(new MyAccountPaybleModel(counter+"",columnOfDealerId, columnOfDealerName, debit+"",credit+"",(debit-credit)+""));
			}
			
			serialColumn.setCellValueFactory(new PropertyValueFactory< MyAccountPaybleModel,String>("serialNo"));
			supplierNameColumn.setCellValueFactory(new PropertyValueFactory<MyAccountPaybleModel,String>("supplierName"));
			debitColumn.setCellValueFactory(new PropertyValueFactory<MyAccountPaybleModel,String>("debit"));
			creditColumn.setCellValueFactory(new PropertyValueFactory<MyAccountPaybleModel,String>("credit"));
			balanceColumn.setCellValueFactory(new PropertyValueFactory<MyAccountPaybleModel,String>("Balance"));
			 supplierIdColumn.setCellValueFactory(new PropertyValueFactory<MyAccountPaybleModel,String>("supplierId"));
			
			table.setItems(list);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	else{
		ResultSet rset = db.getAllCustomersWithLeisure();
		System.out.println("else of acc payble");
		int counter=0;
		try {
			while(rset.next())
			{
				counter++;
				columnOfDealerName=rset.getString("name");
				columnOfDealerId=rset.getString("mobile_No_1");
				ResultSet rset1= db.getCreditOfDealerFromSalesHistory(columnOfDealerName, columnOfDealerId);
				Float debit=0f,credit=0f;
				while(rset1.next())
				{
					credit+=rset1.getFloat("amount");
				}
				
				ResultSet rset2=db.getDebitOfDealerFromAccountRecievable(columnOfDealerName, columnOfDealerId);
				while(rset2.next())
				{
					debit+=rset2.getFloat("amount");
				}
				
				list.add(new MyAccountPaybleModel(counter+"",columnOfDealerId, columnOfDealerName,credit+"",debit+"",(credit-debit)+""));
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	if(Main.isRecievableClicked){
		supplierIdColumn.setText("Customer ID");
		supplierNameColumn.setText("Customer Name");
		debitColumn.setText("Credit");
		creditColumn.setText("Debit");
	}
	
	serialColumn.setCellValueFactory(new PropertyValueFactory< MyAccountPaybleModel,String>("serialNo"));
	supplierNameColumn.setCellValueFactory(new PropertyValueFactory<MyAccountPaybleModel,String>("supplierName"));
	debitColumn.setCellValueFactory(new PropertyValueFactory<MyAccountPaybleModel,String>("debit"));
	creditColumn.setCellValueFactory(new PropertyValueFactory<MyAccountPaybleModel,String>("credit"));
	balanceColumn.setCellValueFactory(new PropertyValueFactory<MyAccountPaybleModel,String>("Balance"));
	supplierIdColumn.setCellValueFactory(new PropertyValueFactory<MyAccountPaybleModel,String>("supplierId"));
	
	table.setItems(list);

	
		
	
		}
	}

