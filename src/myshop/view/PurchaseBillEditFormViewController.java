package myshop.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import myshop.dbhandler.DBQuries;
import myshop.models.MyPurchaseBillModel;

public class PurchaseBillEditFormViewController implements Initializable{

	
	 public PurchaseBillEditFormViewController() {
	}
	
		
	
	
	
	@FXML
	public Label dateLabel;
	
	@FXML
    public Label billNoLabel;
	
	@FXML
	public Label supplierIdNameLabel;
	
	@FXML
	public Label productIdNameLabel;
	
	@FXML
	public Label amountLabel;
	
	@FXML
	public TextField quantityTextField;
	
	@FXML
	public TextField rateTextField;
	
	public boolean isPurchaseEditClicked=true;
	

	
	
	@FXML
	private void saveBtnPressed(){
		
		if(isPurchaseEditClicked){
		DBQuries db = new DBQuries();
		String str1[] = BillDetailController.supplierIdNameLabelText.split("_");
		String str2[] = BillDetailController.productIdNameLabelText.split("_");
		int currqty= Integer.parseInt(this.quantityTextField.getText());
		int prevqty =Integer.parseInt( BillDetailController.quantityLabelText);
		
		db.updatePurchaseBillAfterEdit(BillDetailController.dateLabelText, str1[0], str1[1], BillDetailController.billNoLabelText, str2[1], str2[0], currqty, Float.parseFloat(this.rateTextField.getText()), Float.parseFloat(this.amountLabel.getText()));
		
		
		String str[] = this.productIdNameLabel.getText().split("_");
		boolean bool;
		if(currqty > prevqty)
		{
			bool=true;
		}
		else
		{
			bool=false;
		}
		db.updateInventoryAfterEdit(str[0],str[1], currqty, Float.parseFloat(this.rateTextField.getText()), bool, prevqty);
		
		}/*
		else{
			DBQuries db = new DBQuries();
			String str1[] = BillDetailController.supplierIdNameLabelText.split("_");
			String str2[] = BillDetailController.productIdNameLabelText.split("_");
			int currqty= Integer.parseInt(this.quantityTextField.getText());
			int prevqty =Integer.parseInt( BillDetailController.quantityLabelText);
			
			db.updatePurchaseBillAfterEdit(BillDetailController.dateLabelText, str1[0], str1[1], BillDetailController.billNoLabelText, str2[1], str2[0], currqty, Float.parseFloat(this.rateTextField.getText()), Float.parseFloat(this.amountLabel.getText()));
			
			
			String str[] = this.productIdNameLabel.getText().split("_");
			boolean bool;
			if(currqty > prevqty)
			{
				bool=true;
			}
			else
			{
				bool=false;
			}
			db.updateInventoryAfterEdit(str[0],str[1], currqty, Float.parseFloat(this.rateTextField.getText()), bool, prevqty);
			
			
		}
	*/}
	
	@FXML
	private void deleteBtnPressed(){
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		this.dateLabel.setText(BillDetailController.dateLabelText);
		this.supplierIdNameLabel.setText(BillDetailController.supplierIdNameLabelText);
		this.billNoLabel.setText(BillDetailController.billNoLabelText);
		this.productIdNameLabel.setText(BillDetailController.productIdNameLabelText);
		this.quantityTextField.setText(BillDetailController.quantityLabelText);
		this.rateTextField.setText(BillDetailController.rateLabelText);
		Float product = Float.parseFloat(this.rateTextField.getText()) * Integer.parseInt(this.quantityTextField.getText()); 
		String newProduct = Float.toString(product);
				
		this.amountLabel.setText(newProduct);
		
		//System.out.println("Selected Quantity is  "+BillDetailController.quantityLabelText);
	}
	
	@FXML
	public void keyPressedAfterEdit(){
		if(quantityTextField.getText().length() > 0 && rateTextField.getText().length() > 0){
			Float rate = Float.parseFloat(rateTextField.getText());
			int quantity= Integer.parseInt(quantityTextField.getText());
			Float amount = rate * quantity;
			amountLabel.setText(amount+"");
		}
		else
			amountLabel.setText(0+"");
	}

	
}
