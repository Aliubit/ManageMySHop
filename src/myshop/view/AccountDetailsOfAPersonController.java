package myshop.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import myshop.models.MyAccountPaybleModel;

public class AccountDetailsOfAPersonController implements Initializable {

	@FXML
	public TableView<MyAccountPaybleModel> table;
	@FXML 
	public TableColumn<MyAccountPaybleModel,String> dateColumn;
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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
}
