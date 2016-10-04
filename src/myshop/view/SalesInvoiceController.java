package myshop.view;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import myshop.Main;
import myshop.dbhandler.ConnectToDB;
import myshop.dbhandler.DBQuries;

public class SalesInvoiceController implements Initializable{

	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField recieptNoTextField;
	@FXML
	private ComboBox<String> customerNameComboBox;
	@FXML
	private ComboBox<String> prodNameComboBox;
	@FXML
	private TextField quantityTextField;
	@FXML
	private TextField rateTextField;
	@FXML
	private TextField amountTextField;
	@FXML
	private  Label availableQuantityLabel;
	@FXML
	private Button addButton;
	private ObservableList<String> customerNameList;
	private ObservableList<String> productNameList;
	String ds;
	
	Scene scene;
	DBQuries query = new DBQuries();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ResultSet rset1 = query.getAllProductsToSell();
		
		ResultSet rset2 = query.getAllCustomersName();
		
		customerNameList = FXCollections.observableArrayList();
		productNameList = FXCollections.observableArrayList();
		try {
			while(rset1.next()){
				productNameList.add(rset1.getString("product_Id")+"_"+rset1.getString("product_Name"));
				//System.out.println(rset1.getInt("quantity"));
			}
			
			while(rset2.next()){
				customerNameList.add(rset2.getString("name")+"_"+rset2.getString("mobile_No_1"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prodNameComboBox.setItems(productNameList);
		customerNameComboBox.setItems(customerNameList);
}
		
	/////////////////////////// Controlling Methods //////////////////////////////////////
		
	@FXML
	public void addButonPressed(){
		if(areAllFieldsSelected()){
			if(!(Integer.parseInt(availableQuantityLabel.getText())>=0))
			{
				Main.faillureDialogBox("You do not have sufficient stock ");
				return;
			}
			
			LocalDate currDate = datePicker.getValue();
			
			int year= currDate.getYear();
			int month=currDate.getMonthValue();
			int date =currDate.getDayOfMonth();
			
			String dateFormat = date+"/"+month+"/"+year;
			String recieptNo = recieptNoTextField.getText();
			String quantity = quantityTextField.getText();
			String rate = rateTextField.getText();
			String amount = amountTextField.getText();
			String customer = customerNameComboBox.getValue();
			String products = prodNameComboBox.getValue();
			String[] str =customer.split("_");
			String[] str1 =products.split("_");

			DBQuries query1 = new DBQuries();
			int result = query1.insertIntoSalesHistory(dateFormat, str[0], str[1], recieptNo, str1[1], str1[0],Integer.parseInt(quantity) , Float.parseFloat(rate),Float.parseFloat(amount));
			if(result == 1){
				Main.successDialogBox();
				datePicker.getEditor().setText("");
				recieptNoTextField.setText("");
				customerNameComboBox.setValue("");
				prodNameComboBox.setValue("");
				quantityTextField.setText("");
				rateTextField.setText("");
				amountTextField.setText("");
				availableQuantityLabel.setText("");
				//System.out.println("Which error yarrrrrr");
			}
			else{
				Main.faillureDialogBox("Query Failed to Execute ");
			}
		}
		else{
			Main.faillureDialogBox("Please fill all the required fields");
		}
}
	
	@FXML
	public void cancelButtonPressed(){
		scene.getWindow().hide();
	}
		
	@FXML
	public void productSelected() throws SQLException{ 
		
		//// Available Quantitty k label p action laga wa H uski wajah se error aarha H shyd resolve krwana H Ali se
		if(prodNameComboBox.getValue().length() >0)
		{
		String str = prodNameComboBox.getValue();
		String[] str1 = str.split("_");
		String product_Id = str1[0];
		String product_Name = str1[1];
		ResultSet rset=query.getAllQuantityAvailable(product_Id, product_Name);
		ds=null;
		while(rset.next()){
			 ds = rset.getString("quantity");
		}
		availableQuantityLabel.setText(ds);
		
		}
	}
	@FXML
	public  void quantityLeft() throws SQLException{
		
		keyPressed();
		if(quantityTextField.getText().length() >0)	{
			//System.out.println("im qty left" );
			
			//String n1 = availableQuantityLabel.getText();
			String n1=ds;
			int pn1 = Integer.parseInt(n1);
		
			String n2 = quantityTextField.getText();
			int pn2 = Integer.parseInt(n2);
			int res = pn1-pn2;
			String str =Integer.toString(res);
			
			//System.out.println(pn1  +" "+pn2 );
			
			availableQuantityLabel.setText(str);
		}
		else
			availableQuantityLabel.setText(ds);
	}
	
	private boolean areAllFieldsSelected() {
		return datePicker.getValue()!=null && recieptNoTextField.getText().length()>0 && customerNameComboBox.getValue()!=null 
				   && prodNameComboBox.getValue()!=null && quantityTextField.getText().length()>0 && rateTextField.getText().length()>0 && amountTextField.getText().length()>0;
	}
	
	///////////////////////zafar
	
	@FXML
	public void mouseDraggedOnAmountTextField(){
		Float rate = Float.parseFloat(rateTextField.getText());
		int quantity= Integer.parseInt(quantityTextField.getText());
		Float amount = rate * quantity;
		amountTextField.setText(amount+"");
	}
	@FXML
	public void keyPressed(){
		if(quantityTextField.getText().length() > 0 && rateTextField.getText().length() > 0){
			Float rate = Float.parseFloat(rateTextField.getText());
			int quantity= Integer.parseInt(quantityTextField.getText());
			Float amount = rate * quantity;
			amountTextField.setText(amount+"");
		}
		else
			amountTextField.setText(0+"");
	}

	
	////////////////////////////

	public void setScene(Scene scene) {
		this.scene = scene;
		scene.setOnKeyPressed(
				event->{
					switch(event.getCode()){
					case ENTER:
						System.out.println("manhoos add ");
					case ESCAPE:
						cancelButtonPressed();
					default:
						break;
					}
				}
			);
	}
	
}


