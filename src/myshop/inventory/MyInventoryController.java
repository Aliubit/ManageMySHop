package myshop.inventory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import myshop.dbhandler.ConnectToDB;
import myshop.dbhandler.DBQuries;
import myshop.models.MyInventoryModel;



public class MyInventoryController implements Initializable {

	private static  List<MyInventoryModel> inventoryObject;
	
	@FXML
	public  TableView<MyInventoryModel> table;
	private static ObservableList<MyInventoryModel> data;
	@FXML 
	public  TableColumn<MyInventoryModel,String> nameColumn;
	@FXML 
	public  TableColumn<MyInventoryModel,String> prodColumn;
	@FXML 
	public TableColumn<MyInventoryModel,String> quantityColumn;
	@FXML 
	public  TableColumn<MyInventoryModel,String> rateColumn;
	@FXML
	public TableColumn<MyInventoryModel,String> totalAmountColumn;
	//@FXML
	//public Label totalCostOfInventory;
	  
	float totalSum;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		inventoryObject = new ArrayList<MyInventoryModel>();
		
		DBQuries query = new DBQuries();
		ResultSet rset;
		rset=query.selectionOfInventory();
		
		
		
		
		System.out.println(rset==null);
		data = FXCollections.observableArrayList();
	
		try {
				while (rset.next()) {
					MyInventoryModel model = new MyInventoryModel(rset.getString("product_Id"),rset.getString("product_Name"),rset.getInt("quantity")+"",rset.getFloat("rate")+"");
					totalSum += Float.parseFloat(model.getTotalProductAmount());
					data.add( model);
				}
			} 
		catch (SQLException e1) {
					e1.printStackTrace();
			}
	         
	    for(MyInventoryModel e : data){
	        	 System.out.println(e.getProductId()+" ," + e.getProductName() + " ," + e.getProductQuantity()+ ", "+ e.getProductRate());
	         }
	  	  	  
         prodColumn.setCellValueFactory(new PropertyValueFactory<MyInventoryModel,String>("productId"));
         nameColumn.setCellValueFactory(new PropertyValueFactory<MyInventoryModel,String>("productName"));
         quantityColumn.setCellValueFactory(new PropertyValueFactory<MyInventoryModel,String>("productQuantity"));
         rateColumn.setCellValueFactory(new PropertyValueFactory<MyInventoryModel,String>("productRate"));
	     totalAmountColumn.setCellValueFactory(new PropertyValueFactory<MyInventoryModel,String>("totalProductAmount"));
	     table.setItems(data);
	     
	     System.out.println(totalSum);
	     
	     try {
			ConnectToDB.conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	         
	  }
}

