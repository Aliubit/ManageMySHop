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
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import myshop.Main;
import myshop.dbhandler.ConnectToDB;
import myshop.dbhandler.DBQuries;

public class purchaseBillController implements Initializable{

	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField billNoTextField;
	@FXML
	private ComboBox<String> supplierNameComboBox;
	@FXML
	private ComboBox<String> prodNameComboBox;
	@FXML
	private TextField quantityTextField;
	@FXML
	private TextField rateTextField;
	@FXML
	private TextField amountTextField;
	@FXML
	private Button addButton;
	public ObservableList<String> supplierNameList;
	public ObservableList<String> productNameList;
	String text;

	Scene scene;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		DBQuries query = new DBQuries();
		ResultSet rset = query.getAllProducts();
		ResultSet rset1 = query.getAllSuppliersName();
		
		
		text= new String("");
		supplierNameList = FXCollections.observableArrayList();
		productNameList = FXCollections.observableArrayList();
		
		try{
			while(rset.next()){
				productNameList.add(rset.getString("product_Id")+"_"+rset.getString("product_Name"));
			}
			
			while(rset1.next()){
				supplierNameList.add(rset1.getString("name")+"_"+rset1.getString("mobile_No_1"));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		prodNameComboBox.setItems(productNameList);
		supplierNameComboBox.setItems(supplierNameList);
	}
	
	public void handle( KeyEvent event ) {
		if(prodNameComboBox.isFocused())
		{
		//System.out.println("Aagaya Text is "+text);
	//	System.out.println("you typed "+event.getCharacter());
        if( event.getCode() == KeyCode.BACK_SPACE)
        {
            text = text.substring( 0, text.length() - 1 );
        }
        else
        {
        	 text += event.getCharacter();
        }
        for( String item: productNameList ) {
        	 if( item.startsWith(text)) 
        		 {
        		 prodNameComboBox.setValue(item);
        		 break;
        		 }
           // if( item.startsWith( text ) ) prodNameComboBox.setEffect( item );
        }
		}
		else
		{
			text="";
		}
        
       
    }
	
	//////////////////////////// Controlling Methods //////////////////////////////////////
	
	@FXML
	private void addButonPressed(){
		if(areAllFieldsSelected() ){
		LocalDate currDate = datePicker.getValue();
		
		int year= currDate.getYear();
		int month=currDate.getMonthValue();
		int date =currDate.getDayOfMonth();
		
		String dateFormat = date+"/"+month+"/"+year;
		String billNo = billNoTextField.getText();
		String quantity = quantityTextField.getText();
		String rate = rateTextField.getText();
		String amount = amountTextField.getText();
		String supplier = supplierNameComboBox.getValue();
		String products = prodNameComboBox.getValue();
//		System.out.println("Product is "+products);
		String[] str =supplier.split("_");
		String[] str1 =products.split("_");
		
		/*prodNameComboBox.setValue("");
		rateTextField.setText("");
		amountTextField.setText("");
		quantityTextField.setText("");
		*/
		DBQuries query = new DBQuries();
		int result = query.insertIntoPurchaseHistory(dateFormat, str[0], str[1], billNo, str1[1], str1[0],Integer.parseInt(quantity) , Float.parseFloat(rate),Float.parseFloat(amount));
		//System.out.println("supplier ID "+str[1]+"Supplier Name "+ str[0]+" P ID:  "+ str1[1]+ " P Name: "+ str1[0]);
		if(result == 1){
			Main.successDialogBox();
			datePicker.getEditor().setText("");
			billNoTextField.setText("");
			supplierNameComboBox.setValue("");
			prodNameComboBox.setValue("");
			quantityTextField.setText("");
			rateTextField.setText("");
			amountTextField.setText("");
		}
		else{
			Main.faillureDialogBox("Query Failed to Execute");
		}
		try {
			ConnectToDB.conn.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
		else{
			Main.faillureDialogBox("Please fill all the required fields");
		}
}
	
	
	
	public boolean areAllFieldsSelected(){
			return datePicker.getValue()!=null && billNoTextField.getText().length()>0 && supplierNameComboBox.getValue()!=null 
				   && prodNameComboBox.getValue()!=null && quantityTextField.getText().length()>0 && rateTextField.getText().length()>0 && amountTextField.getText().length()>0;
	}
	
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

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	@FXML
	public void  cancelButtonPressed(){
		//Main.promptDialogBox("Are you sure you want to cancel this window");
		scene.getWindow().hide();
	}
}
