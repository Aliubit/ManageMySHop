package myshop.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import myshop.Main;

public class MainItemsController {

	@FXML
	private void purchaseEntryBtn() throws IOException{
		Main.showPurchaseBillWindow();
	}
	
	@FXML
	private void salesInvoiceBtn() throws IOException{
		Main.showSalesInvoiceWindow();
	}
	
}
