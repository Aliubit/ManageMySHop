package myshop.view;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import myshop.Main;

public class MainViewController implements Initializable{
	
	
	////////////////////// left pane labels
	
	@FXML
	private Label addNewProductLabel;
	@FXML
	private Label addNewCustomerOrSellerLabel;
	@FXML
	private Label viewCustomersLabel;
	@FXML
	private Label viewSuppliersLabel;
	@FXML
	private Label viewAllProducts;
	///////////////////////////////////////
	
	//////////////////////  right pane Labels
	@FXML
	private Label accountPayableViewLabel;
	@FXML
	private Label accountReceivableViewLabel;
	@FXML
	private Label purchaseHistoryLabel;
	@FXML
	private Label salesHistoryLabel;
	@FXML 
	private Label cashBookLabel;
	@FXML
	private Label inventoryLabel;
	/////////////////////////////////////////
	
	@FXML
	private Label cuurDateLabel;
	
	////////////////////////////top////////////////////////////
	
	
	
public void clock(){
		Thread clock = new Thread(){
			public void run(){
				
				try {
					while(true){
						
					
											
						Platform.runLater(new Runnable() {
						    @Override public void run() {
						    	Calendar cal = new GregorianCalendar();
								
								int day = cal.get(Calendar.DAY_OF_MONTH);
								int month= cal.get(Calendar.MONTH);
								int year = cal.get(Calendar.YEAR);
								
								int sec = cal.get(Calendar.SECOND);
								int mins = cal.get(Calendar.MINUTE);
								int hrs = cal.get(Calendar.HOUR);
						    	
						    	cuurDateLabel.setText("Date: "+day+"/"+month+"/"+year+ " Time: "+hrs+":"+mins+":"+sec);
						    }
						});
						
					sleep(1000);
					}
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		};
			
		clock.start();
	}
			public void stop()
			{
				System.exit(0);
			}

	

	

	
		
				
		 
				
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		clock();
		
		
	}
	
	////////////////////////////Left //////////////////////////
	@FXML
	private void openInventoryLabel() throws IOException{
		Main.showInventoryScene();
	}
	@FXML 
	private void goAddNewProdLabel() throws IOException{
		Main.showAddProdWindow();
	}
	@FXML
	private void goAddDealerNCustomerLabel() throws IOException{
		Main.showNewDealerWindow();
	}	
	////////////////////////////Right //////////////////////////}
		
	@FXML
	public void openPurchaseHistory() throws IOException{
		Main.showPurchaseHistoryScene();
	}
	@FXML
	public void openSalesHistory() throws IOException{
		Main.showSalesHistoryScene();
	}
	@FXML
	private void openAccountPayable() throws IOException{
		Main.showAcountPaybleScene();
	}
	@FXML
	private void openAccountRecievable() throws IOException{
		Main.showAcountRecievableScene();
	}
	////////////////////////////bottom //////////////////////////
	@FXML
	private void goHomeBtn() throws IOException{
		Main.showMainItems();
	}
	
	///////////////////////////*********/////////////////////////
	
	////////////////////////// hovering Methods for Right Pane/////////////////
			@FXML	
			public void onHoverAccPayableLabel(){
					accountPayableViewLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: GREY;");
					accountPayableViewLabel.setEffect(new Glow());
					accountPayableViewLabel.setMaxWidth(250);
					accountPayableViewLabel.setWrapText(true);
				}
			@FXML	
			public void onHoverAccReceivableLabel(){
					accountReceivableViewLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: GREY;");
					accountReceivableViewLabel.setEffect(new Glow());
					accountReceivableViewLabel.setMaxWidth(250);
					accountReceivableViewLabel.setWrapText(true);
				}
			
			@FXML	
			public void onHoverPurchaseHistoryLabel(){
					purchaseHistoryLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: GREY;");
					purchaseHistoryLabel.setEffect(new Glow());
					purchaseHistoryLabel.setMaxWidth(250);
					purchaseHistoryLabel.setWrapText(true);
				}
			
			@FXML	
			public void onHoverSalesHistoryLabel(){
					salesHistoryLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: GREY;");
					salesHistoryLabel.setEffect(new Glow());
					salesHistoryLabel.setMaxWidth(250);
					salesHistoryLabel.setWrapText(true);
				}
			
			@FXML	
			public void onHoverCashBookLabel(){
					cashBookLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: GREY;");
					cashBookLabel.setEffect(new Glow());
					cashBookLabel.setMaxWidth(250);
					cashBookLabel.setWrapText(true);
				}
			
			@FXML	
			public void onHoverInventotyLabel(){
					inventoryLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: GREY;");
					inventoryLabel.setEffect(new Glow());
					inventoryLabel.setMaxWidth(250);
					inventoryLabel.setWrapText(true);
				}
				
			//////////////////////////////////*******************/////////////////////////////////
			
			////////////////////////////////////// for Left Pane Hovering Labels//////////////////
			
			@FXML	
			public void onHoverAddNewProductLabel(){
					addNewProductLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: GREY;");
					addNewProductLabel.setEffect(new Glow());
					addNewProductLabel.setMaxWidth(250);
					addNewProductLabel.setWrapText(true);
				}
			
			@FXML	
			public void onHoverAddNewCustomerOrSellerLabel(){
					addNewCustomerOrSellerLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: GREY;");
					addNewCustomerOrSellerLabel.setEffect(new Glow());
					addNewCustomerOrSellerLabel.setMaxWidth(250);
					addNewCustomerOrSellerLabel.setWrapText(true);
				}
			
			@FXML	
			public void onHoverViewSellerLabel(){
					viewSuppliersLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: GREY;");
					viewSuppliersLabel.setEffect(new Glow());
					viewSuppliersLabel.setMaxWidth(250);
					viewSuppliersLabel.setWrapText(true);
				}
			
			@FXML	
			public void onHoverViewCustomerLabel(){
					viewCustomersLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: GREY;");
					viewCustomersLabel.setEffect(new Glow());
					viewCustomersLabel.setMaxWidth(250);
					viewCustomersLabel.setWrapText(true);
				}
			
			@FXML	
			public void onHoverViewAllProductsLabel(){
					viewAllProducts.setStyle("-fx-font-size: 16px; -fx-text-fill: GREY;");
					viewAllProducts.setEffect(new Glow());
					viewAllProducts.setMaxWidth(250);
					viewAllProducts.setWrapText(true);
				}
		
			//////////////////////////////////*******************/////////////////////////////////
			
			///////////////////////// Right Pane closing Hover Label//////////////////////////////
	
	
			@FXML	
			public void onCloseHoveraccpayableLabel(){
				accountPayableViewLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: WHITE;");
				accountPayableViewLabel.setEffect(null);
			}
			
			@FXML	
			public void onCloseHoveraccReceivableLabel(){
				accountReceivableViewLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: WHITE;");
				accountReceivableViewLabel.setEffect(null);
			}
			
			@FXML	
			public void onCloseHoverPurchaseHistoryLabel(){
				purchaseHistoryLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: WHITE;");
				purchaseHistoryLabel.setEffect(null);
			}
			
			@FXML	
			public void onCloseHoverSalesHistoryLabel(){
				salesHistoryLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: WHITE;");
				salesHistoryLabel.setEffect(null);
			}
			
			@FXML	
			public void onCloseHoverCashBookLabel(){
				cashBookLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: WHITE;");
				cashBookLabel.setEffect(null);
			}
			
			@FXML	
			public void onCloseHoverinventoryLabel(){
				inventoryLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: WHITE;");
				inventoryLabel.setEffect(null);
			}
			
			//////////////////////////////******************//////////////////////////////
			
			///////////////////////// Left Pane Hover Label//////////////////////////////
			@FXML	
			public void onCloseHoverAddNewProductLabel(){
				addNewProductLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: WHITE;");
				addNewProductLabel.setEffect(null);
			}
			
			@FXML	
			public void onCloseHoverAddNewCustomerOrSellerLabel(){
				addNewCustomerOrSellerLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: WHITE;");
				addNewCustomerOrSellerLabel.setEffect(null);
				
			}
			
			@FXML	
			public void onCloseHoverViewCustomerLabel(){
				viewCustomersLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: WHITE;");
				viewCustomersLabel.setEffect(null);
			}
			
			@FXML	
			public void onCloseHoverViewSupplierLabel(){
				viewSuppliersLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: WHITE;");
				viewSuppliersLabel.setEffect(null);
			}
			
			@FXML	
			public void onCloseHoverViewAllProductLabel(){
				viewAllProducts.setStyle("-fx-font-size: 15px; -fx-text-fill: WHITE;");
				viewAllProducts.setEffect(null);
			}
		
			//////////////////////////////******************//////////////////////////////
		
		
	
		
}
