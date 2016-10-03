package myshop.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import myshop.Main;
import myshop.dbhandler.DBQuries;

public class NewDealerWindowController implements Initializable{

	@FXML
	public  TextField nameTextField;
	@FXML
	public  TextField uniqueIdTextField;
	@FXML
	public  TextField mobileNo1TextField;
	@FXML
	public  TextField mobileNo2TextField;
	@FXML
	public  TextField landLineNoTextField;
	@FXML
	public  TextField emailTextField;
	@FXML
	public  TextField nicNoTextField;
	@FXML
	public  TextField refferedByTextField;
	@FXML 
	public  TextArea notesTextArea;
	@FXML 
	public  TextArea addressTextArea;
	@FXML
	public  CheckBox supplierCheckBox;
	@FXML
	public  CheckBox customerCheckBox;
	@FXML
	public Label uidLabel;

	
	String dealer=null;

	Scene scene;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		uniqueIdTextField.textProperty().bind(mobileNo1TextField.textProperty());
	}

	
	@FXML
	public  void customerSelected(){
		if(customerCheckBox.isSelected())
			supplierCheckBox.setSelected(false);
		if(!customerCheckBox.isSelected() && !supplierCheckBox.isSelected())
			customerCheckBox.setSelected(true);	
			uidLabel.setText("Customer ID");
			dealer="Customer";
			System.out.println(dealer);
	}
	
	@FXML
	public void supplierSelected(){
		if(supplierCheckBox.isSelected())
			customerCheckBox.setSelected(false);
		if(!customerCheckBox.isSelected() && !supplierCheckBox.isSelected())
			supplierCheckBox.setSelected(true);	
			uidLabel.setText("Supplier ID");
			dealer="Supplier";
			System.out.println(dealer);
	}
	
	@FXML
	public void addDealerBtnPressed(){
		String uniqueId = uniqueIdTextField.getText();
		String name = nameTextField.getText();
		String mobileNo1 = mobileNo1TextField.getText();
		String mobileNo2 = mobileNo2TextField.getText();
		String landLineNo = landLineNoTextField.getText();
		String email = emailTextField.getText();
		String nicNo = nicNoTextField.getText();
		String refferedBy = refferedByTextField.getText();
		String notes = notesTextArea.getText();
		String address = addressTextArea.getText();
			if(uniqueId.length() >0 && name.length() > 0 && mobileNo1.length() > 0 && dealer.length() > 0){
				
				DBQuries query = new DBQuries();
				int result = query.insertIntoDealers(uniqueId,name,mobileNo1,mobileNo2,landLineNo,email,nicNo,refferedBy,notes,address,dealer);
				System.out.println("button daba hai");
				//int result = Main.addDealer(uniqueId, name, mobileNo1, mobileNo2, landLineNo, email, nicNo, refferedBy, notes, address, dealer);
			    if(result == 1){
			    	Main.successDialogBox();
			    	uniqueIdTextField.setText("");
			    	nameTextField.setText("");
			    	mobileNo1TextField.setText("");
			    	//supplierCheckBox.setText("");
			    	//customerCheckBox.setText("");
			    }
			    else{
			    	Main.faillureDialogBox("Query failed to Execute");
			    	
			    }
			}
			else{
				Main.faillureDialogBox("Please fill all the required fields");
			}
				
	}
	
	

	public void setScene(Scene scene) {
		// TODO Auto-generated method stub
		this.scene = scene;
	}
	
	@FXML
	public void CancelBtnPressed(){
		scene.getWindow().hide();
	}


	
}
