package myshop;

import java.io.IOException;

import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import it.sauronsoftware.junique.MessageHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import myshop.dbhandler.DBQuries;
import myshop.view.MainViewController;
import myshop.view.NewDealerWindowController;
import myshop.view.ProductController;
import myshop.view.PurchaseBillEditFormViewController;
import myshop.view.SalesInvoiceController;
import myshop.view.purchaseBillController;
public class Main extends Application {
	
	private static Stage primaryStage;
	private static BorderPane mainLayout;
	public static boolean isPurchaseClicked,isSalesClicked;
	public static boolean isPayableClicked,isRecievableClicked;
	public static boolean isPurchaseEditClick,isSalesEditClick;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Main.primaryStage = primaryStage;
		Main.primaryStage.setTitle("New Jilani Autos");
		showMainView();
		showMainItems();
		/*
		 StageManager.getInstance().setPrimaryStage(primaryStage);
		 //Main.primaryStage=primaryStage;
		 Main.primaryStage=StageManager.getInstance().getPrimaryStage();
		 Main.primaryStage.setTitle("My Shop App");
		 showMainView();
		 showMainItems();*/
	}
	
	
	///////////////////////////////////My Main Window Scenes //////////////////////////////////////
	
	private void showMainView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainView.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout, 1040,680);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void showMainItems() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainItems.fxml"));
		BorderPane mainItems = loader.load();
		mainLayout.setCenter(mainItems);
	}
	
	public static void showInventoryScene() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("inventory/Inventory.fxml"));
		BorderPane inventory = loader.load();
		mainLayout.setCenter(inventory);
	}
	
	public static void showPurchaseHistoryScene() throws IOException{
		isPurchaseClicked=true;
		isSalesClicked=false;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/PurchaseHistoryView.fxml"));
		BorderPane purchaseHistoryScene = loader.load();
		mainLayout.setCenter(purchaseHistoryScene);
	}
	
	public static void showSalesHistoryScene() throws IOException{
		isPurchaseClicked=false;
		isSalesClicked=true;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/PurchaseHistoryView.fxml"));
		BorderPane salesHistoryScene = loader.load();
		mainLayout.setCenter(salesHistoryScene);
	}
	
	public static void showAcountPaybleScene() throws IOException{
		isPayableClicked=true;
		isRecievableClicked=false;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/AccountPayableView.fxml"));
		BorderPane accountPaybleViewScene = loader.load();
		mainLayout.setCenter(accountPaybleViewScene);
	}
	

	public static void showAcountRecievableScene() throws IOException{
		isPayableClicked=false;
		isRecievableClicked=true;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/AccountPayableView.fxml"));
		BorderPane accountRecievableViewScene = loader.load();
		mainLayout.setCenter(accountRecievableViewScene);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////////////// Sale and Purchase Window Of myShop ////////////////////////////
	
	public static void showPurchaseBillWindow() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/PurchaseBillEntryForm.fxml"));
		BorderPane addNewProduct = loader.load();
		purchaseBillController controller = (purchaseBillController)loader.getController();
		System.out.println("khol window");
		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("Purchase Bill Entry");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		
		Scene scene = new Scene(addNewProduct);
		controller.setScene(scene);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
	}
	
	public static void showSalesInvoiceWindow() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/SalesInvoiceEntryForm.fxml"));
		BorderPane addNewProduct = loader.load();
		
		SalesInvoiceController controller = (SalesInvoiceController)loader.getController();
		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("Sales Invoice Entry");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		
		Scene scene = new Scene(addNewProduct);
		controller.setScene(scene);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
	}
	
	/////////////////////////////////Bills Details of sales And Purchases ///////////////////////////////////
	
	public static void showPurchaseBillDetail() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/PurchaseBillDetail.fxml"));
		BorderPane billDetail = loader.load();
		
		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("Bill Details");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		
		Scene scene = new Scene(billDetail);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
				
	}
	
	public static  PurchaseBillEditFormViewController showPurchaseBillDetailWithEdit() throws IOException
	{
		isPurchaseEditClick = true;
		isSalesEditClick = false;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/PurchaseBillEditForm.fxml"));
		BorderPane addNewProduct = loader.load();
		
		PurchaseBillEditFormViewController controller = (PurchaseBillEditFormViewController)loader.getController();
		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("Sales Invoice Entry");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		
		Scene scene = new Scene(addNewProduct);
		//controller.setScene(scene);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
		return controller;
	}

	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////////////// Account Payable Detail Window Of myShop ////////////////////
			
		public static void showAccountDetailsOfAPersonWindow() throws IOException{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/AccountDetailsOfAPerson.fxml"));
			BorderPane showDetailWindow = loader.load();
			
			Stage addDialogStage = new Stage();
			addDialogStage.setTitle("Purchase Bill Entry");
			addDialogStage.initModality(Modality.WINDOW_MODAL);
			addDialogStage.initOwner(primaryStage);
			
			Scene scene = new Scene(showDetailWindow);
			addDialogStage.setScene(scene);
			addDialogStage.showAndWait();
		}
		
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////////////// Dealers Of myShop /////////////////////////////////////////////
	
	public static void showNewDealerWindow() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/NewDealerWindow.fxml"));
		BorderPane addNewProduct = loader.load();
		
		NewDealerWindowController controller = (NewDealerWindowController)loader.getController();
		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("New Dealer Form");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		
		Scene scene = new Scene(addNewProduct);
		controller.setScene(scene);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
	}
	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////////////// Products Of myShop /////////////////////////////////////////////
	
	
	public static void showAddProdWindow() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		
		loader.setLocation(Main.class.getResource("view/NewProdWindow.fxml"));
		BorderPane addNewProduct = loader.load();
		//// For Cancel Button
		ProductController controller = (ProductController)loader.getController();
		
		Stage addDialogStage = new Stage();
		addDialogStage.setTitle("Add New Product");
		addDialogStage.initModality(Modality.WINDOW_MODAL);
		addDialogStage.initOwner(primaryStage);
		
		Scene scene = new Scene(addNewProduct);
		controller.setScene(scene);
		addDialogStage.setScene(scene);
		addDialogStage.showAndWait();
				
	}
	
	
	////////////////////////////// Dialog Boxes
	
	public static void faillureDialogBox(String message){
		Alert fail= new Alert(AlertType.ERROR);
        fail.setHeaderText("failure");
        fail.setContentText(message);
        fail.showAndWait();
	}
	
	public static void successDialogBox(){
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("Succes");
        alert.setContentText("Record Added !");
        alert.showAndWait();
    
	}
	
	public static void promptDialogBox(String message){
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		System.out.println(confirm.getButtonTypes());
		confirm.setHeaderText("Confirm");
		confirm.setContentText(message);
		confirm.showAndWait();
	}

	
	//////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		//launch(args);
		String appId = "New Jilani Autos Prototype";
		boolean alreadyRunning;
		try {
			JUnique.acquireLock(appId, new MessageHandler() {
				@SuppressWarnings("deprecation")
				public String handle(String message) {
					// A brand new argument received! Handle it!
					
					System.out.println("message is "+message);
					
					return null;
				}
			});
			alreadyRunning = false;
		} catch (AlreadyLockedException e) {
			alreadyRunning = true;
		}
		if (!alreadyRunning) {
			// Start sequence here
			launch(args);
			MainViewController m = new MainViewController();
			m.stop();
			System.out.println("band hogai");
			}
		else {
			for (int i = 0; i < args.length; i++) {
				JUnique.sendMessage(appId, args[0]);
				
				
			}
		}
	}

	
	
	
	
}
