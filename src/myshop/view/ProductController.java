package myshop.view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import myshop.Main;
import myshop.dbhandler.DBQuries;

public class ProductController {

	@FXML
	private TextField prodNameTextField;
	@FXML 
	private TextField prodIdTextField;
	@FXML 
	private Button cancelBtn;
	
	Scene scene;
	
	@FXML
	public void addButtonPressed(){
		
		String prodname = prodNameTextField.getText();
		String prodId =  prodIdTextField.getText();
		
		if(prodname.length() >0 && prodId.length() > 0){
			DBQuries query = new DBQuries();
			int result = query.insertIntoProducts(prodId, prodname);
			if(result==1)
			{
				Main.successDialogBox();
				prodNameTextField.setText("");
				prodIdTextField.setText("");
			}
			else
				Main.faillureDialogBox("Item with same name and id already exists");
		}
		
		else
		{
			Main.promptDialogBox("Please fill all the required fields");
		}
		/////Nahi HOrha yR
		/*if(ButtonType.YES.equals(ButtonData.YES)){
			System.out.println("Ok Pressed");
		}
		else if(ButtonType.CANCEL != null && ButtonData.CANCEL_CLOSE != null){
			System.out.println("Cancel Pressed");
		}*/
	}
	
/*	public void faillureDialogBox(String message){
		Alert fail= new Alert(AlertType.INFORMATION);
        fail.setHeaderText("failure");
        fail.setContentText(message);
        fail.showAndWait();
	}
	
	public void successDialogBox(){
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("Succes");
        alert.setContentText("Record Added !");
        alert.showAndWait();
    
	}
*/
	public void setScene(Scene scene) { 
		this.scene = scene; 
	}
	
	@FXML
	public void cancelButtonPressed(){
		scene.getWindow().hide();
		
	}
	
}
