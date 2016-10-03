package myshop.dbhandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import myshop.view.BillDetailController;
import myshop.view.PurchaseBillEditFormViewController;

public class DBQuries {

	ConnectToDB conn ;
	String s="('";
	String bw="', '";
	String e="')";
	public DBQuries() {
		conn = new ConnectToDB() ;
	}
	
	//////////////////////////// Inventory /////////////////////////////////////
	
	public void insertIntoInventory(String prodId,String prodName){
		String query= "INSERT INTO inventory(product_id, product_Name)" + "VALUES"+ "('"+prodId+"','"+prodName+"')";
      	conn.queryHolder(query);	 
	}
	
	public ResultSet selectionOfInventory(){
		String query = "select * from inventory where quantity > 0";
		ResultSet rset= conn.dbRetrival(query);
		return rset;
	}
	
	
	
	//////////////////////*******************//////////////////////////////////
	
	
	/////////////////////////// Products ///////////////////////////////////////
	
	public int insertIntoProducts(String prodId,String prodName){
			
		ResultSet rs = getAllProducts();
		int flag=0;
		try {
			while(rs.next()){
				System.out.println(rs.getString("product_Id") +" " + rs.getString("product_Name"));
				if(rs.getString("product_Id").equals(prodId) && rs.getString("product_Name").equals(prodName)){
					flag++;	
					System.out.println("No query Here /t/t record hai");
					break;
				}
			}
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
			if(flag==0){
				String query= "INSERT INTO products(product_id, product_Name)" + "VALUES"+ "('"+prodId+"','"+prodName+"')";
		      	insertIntoInventory(prodId, prodName);
		      	return conn.queryHolder(query);
		      	
				
			}
			else
			{
				return -1;
			}
			
	}
	
	public ResultSet getAllProducts(){
		String query = "select * from products";
		ResultSet rset= conn.dbRetrival(query);
		return rset;
		
	}
	
	
	
	///////////////////////////////
	///////////////// update inventory Edit by purchase Form VIew Controller
	
	
	public void updateInventoryAfterEdit(String prodId,String prodName,int quantity,Float rate,Boolean isIncrement,int prevqnty){
		
		String query1 ="select quantity from inventory WHERE product_Id='"+prodId+"' AND product_Name='"+prodName+"'";
		ResultSet rset= conn.dbRetrival(query1);
		
		int currentQuantity=0;
	
		try {
			while(rset.next()){
					currentQuantity = rset.getInt("quantity");
					if(isIncrement)
					{
						currentQuantity+=(quantity-prevqnty);
						String updationOfInventory = "UPDATE inventory SET quantity='"+currentQuantity+"',rate='"+rate+"' WHERE product_Id='"+prodId+"' AND product_Name='"+prodName+"'";
						conn.queryHolder(updationOfInventory);
					}
					else
					{
						currentQuantity-=(prevqnty-quantity);
						String updationOfInventory = "UPDATE inventory SET quantity='"+currentQuantity+"',rate='"+rate+"' WHERE product_Id='"+prodId+"' AND product_Name='"+prodName+"'";
						conn.queryHolder(updationOfInventory);
					}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/////////////////////////////////   update inventory Edit by sales Form VIew Controller
	
public void updateInventoryAftersalesEdit(String prodId,String prodName,int quantity,Float rate,Boolean isIncrement,int prevqnty){
		
		String query1 ="select quantity from inventory WHERE product_Id='"+prodId+"' AND product_Name='"+prodName+"'";
		ResultSet rset= conn.dbRetrival(query1);
		
		int currentQuantity=0;
	
		try {
			while(rset.next()){
					currentQuantity = rset.getInt("quantity");
					if(isIncrement)
					{
						currentQuantity+=(quantity-prevqnty);
						String updationOfInventory = "UPDATE inventory SET quantity='"+currentQuantity+"',rate='"+rate+"' WHERE product_Id='"+prodId+"' AND product_Name='"+prodName+"'";
						conn.queryHolder(updationOfInventory);
					}
					else
					{
						currentQuantity-=(quantity-prevqnty);
						String updationOfInventory = "UPDATE inventory SET quantity='"+currentQuantity+"',rate='"+rate+"' WHERE product_Id='"+prodId+"' AND product_Name='"+prodName+"'";
						conn.queryHolder(updationOfInventory);
					}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	////////////////////////////////
	
	//////////////////////// End of Trying
	//////////////////////*******************//////////////////////////////////
	
	///////////////////////////////////////////update edit purchase bill
	
	public void updatePurchaseBillAfterEdit(String Date,String nameOfSupplier,String idOfSupplier,String billNo,String nameOfProd,String idOfProduct
            ,int quantityOfProd,Float rateOfProd,Float amountOfProd){
		
		String query1 ="select quantity from purchase_history WHERE item_Id='"+idOfProduct+"' AND item_Name='"+nameOfProd+"' AND purchase_Date='"+Date+"' AND supplier_Id='"+idOfSupplier+"' AND supplier_Name='"+nameOfSupplier+"' AND bill_No='"+billNo+"'" ;
		ResultSet rset= conn.dbRetrival(query1);
		
	String newAmount="";
		try {
			while(rset.next()){
				amountOfProd = quantityOfProd * rateOfProd;
				newAmount = Float.toString(amountOfProd);
				String updationOfInventory = "UPDATE purchase_history SET quantity='"+quantityOfProd+"',rate='"+rateOfProd+"',amount='"+newAmount+"' WHERE item_Id='"+idOfProduct+"' AND item_Name='"+nameOfProd+"' AND purchase_Date='"+Date+"' AND supplier_Id='"+idOfSupplier+"' AND supplier_Name='"+nameOfSupplier+"' AND bill_No='"+billNo+"'" ;
				conn.queryHolder(updationOfInventory);
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	///////////////////////////////////////////end purchase bill
	
	//////////////////////// Purchase Bill Working ////////////////////////////
	
	public int insertIntoPurchaseHistory(String Date,String nameOfSupplier,String idOfSupplier,String billNo,String nameOfProd,String idOfProcuct
			                              ,int quantityOfProd,float rateOfProd,float amountOfProd){
		int count=0;
		String query = "INSERT INTO purchase_history(purchase_Date,bill_No,supplier_Id,supplier_Name,item_Id,item_Name,quantity,rate,Amount)"
						+ "VALUES" + "('"+Date+"', '"+billNo+"', '"+idOfSupplier+"', '"+nameOfSupplier+"', '"+idOfProcuct+"', '"+nameOfProd+"', '"+quantityOfProd+"', '"+rateOfProd+"', '"+amountOfProd+"')";
		System.out.println("database se p bill"+ idOfProcuct + "Name Of Product "+ nameOfProd);
		count = conn.queryHolder(query);
		
			
		String query1 ="select * from inventory WHERE product_Id='"+idOfProcuct+"' AND product_Name='"+nameOfProd+"'";
		System.out.println("database se  p bill"+ idOfProcuct + "Name Of Product "+ nameOfProd);
		ResultSet rest2= conn.dbRetrival(query1);
		
		int quantity=quantityOfProd;
		int currentQuantity=0;
		int newQuantity=0 ;
		
		Float rate=rateOfProd;
		Float currentRate=(float)0.0;
		Float newRate=(float) 0.0;
		
		try {
			while(rest2.next())
			{
				currentQuantity = rest2.getInt("quantity");
				currentRate =  rest2.getFloat("rate");
				newQuantity=quantity+currentQuantity;
				System.out.println("yeh lo meri new quantity"+newQuantity);
				if(currentRate.intValue()!=0)
					newRate=(currentRate+rate)/2;
				else
					newRate = rate;
				System.out.println("rate after zero found"+newRate);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("database se "+ idOfProcuct + "Name Of Product "+ nameOfProd);
		String updationOfInventory = "UPDATE inventory SET quantity='"+newQuantity+"',rate='"+newRate+"' WHERE product_Id='"+idOfProcuct+"' AND product_Name='"+nameOfProd+"'";
		conn.queryHolder(updationOfInventory);
		
		return count;
	
	}
	
	
		//////////////////////*******************//////////////////////////////////
			
			
		//////////////////////// Sales Bill Working ////////////////////////////
	
	public int insertIntoSalesHistory(String Date,String nameOfCustomer,String idOfCustomer,String recieptNo,String nameOfProd,String idOfProcuct
            ,int quantityOfProd,float rateOfProd,float amountOfProd){

		int count=0,flag = 0;
		if(flag == 0){
			String query = "INSERT INTO sales_history(sale_Date,reciept_No,customer_Id,customer_Name,product_Id,product_Name,quantity,rate,amount)"
								+ "VALUES" + "('"+Date+"', '"+recieptNo+"', '"+idOfCustomer+"', '"+nameOfCustomer+"', '"+idOfProcuct+"', '"+nameOfProd+"', '"+quantityOfProd+"', '"+rateOfProd+"', '"+amountOfProd+"')";
			count = conn.queryHolder(query);
			
		}
		
		//System.out.println("code yahan aaya kia ???");
		String query1 ="select * from inventory WHERE product_Id='"+idOfProcuct+"' AND product_Name='"+nameOfProd+"'";
		ResultSet rest2= conn.dbRetrival(query1);
		
		int quantity=quantityOfProd;
		int currentQuantity=0;
		int newQuantity=0 ;
		
		try {
			while(rest2.next()){
				currentQuantity = rest2.getInt("quantity");
				newQuantity=currentQuantity-quantity;
				}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		if(newQuantity==0){
			String updationOfInventory = "UPDATE inventory SET rate = 0 WHERE product_Id='"+idOfProcuct+"' AND product_Name='"+nameOfProd+"'";
			count = conn.queryHolder(updationOfInventory);
			//return count;
			//System.out.println("Count value if newQuantity is 0 : "+count);
		}
		else{
			System.out.println("Update k else mai aayaaaa");
			String updationOfInventory = "UPDATE inventory SET quantity='"+newQuantity+"' WHERE product_Id='"+idOfProcuct+"' AND product_Name='"+nameOfProd+"'";
			conn.queryHolder(updationOfInventory);
			//return count;
			//System.out.println("Count value if newQuantity is not 0 : "+count);
		}
		return count;
		
	}
	
	
	public ResultSet getAllProductsToSell(){
		String query = "select * from inventory where quantity > 0 ";
		ResultSet rset= conn.dbRetrival(query);
		return rset;
	}
	
	public ResultSet getAllQuantityAvailable(String product_Id,String product_Name){
		
		String query = "select quantity from inventory where product_Id ='"+product_Id+"' AND product_Name='"+product_Name+"'";
		System.out.println(query);
		ResultSet rset= conn.dbRetrival(query);
		return rset;
	}
	
	//////////////////////*******************//////////////////////////////////
	
	
	
	////////////////////// Dealer and Suppliers ///////////////////////////////
	
	public int insertIntoDealers(String uniqueId, String name, String mobileNo1, String mobileNo2, String landLineNo,
			String email, String nicNo, String refferedBy, String notes, String address, String dealer) {
		
		String query = "INSERT INTO dealers(uniqueId,name,mobile_No_1,mobile_No_2,landLineNo,email,nicNo,refferedBy,notes,address,role)"
				+ "VALUES" + "('"+uniqueId+"', '"+name+"', '"+mobileNo1+"', '"+mobileNo2+"', '"+landLineNo+"', '"+email+"', '"+nicNo+"', '"+refferedBy+"', '"+notes+"', '"+address+"', '"+dealer+"')";
		return conn.queryHolder(query);
	}

	public ResultSet getAllSuppliersName(){
		String supplier = "Supplier";
		String query = "select * from dealers WHERE role = '"+supplier+"'" ;
		ResultSet rset1= conn.dbRetrival(query);
		return rset1;
	}

	public ResultSet getAllCustomersName(){
		String customer = "Customer";
		String query = "select * from dealers WHERE role = '"+customer+"'" ;
		ResultSet rset1= conn.dbRetrival(query);
		return rset1;
	}
	
	///////Get All Purchase History with date
	
	public ResultSet getPurchaseHistoryWithDate(String dateFormat)
	{
		String query = "select * from purchase_history WHERE purchase_Date = '"+dateFormat+"'" ;
		ResultSet rset1= conn.dbRetrival(query);
		return rset1;
	}
	public ResultSet getSalesHistoryWithDate(String dateFormat)
	{
		String query = "select * from sales_history WHERE sale_Date = '"+dateFormat+"'" ;
		ResultSet rset1= conn.dbRetrival(query);
		return rset1;
	}
	public ResultSet getPurchaseHistoryWithDate()
	{
		String query = "select * from purchase_history" ;
		ResultSet rset1= conn.dbRetrival(query);
		return rset1;
	}
	
	public ResultSet getSalesHistoryWithDate()
	{
		String query = "select * from sales_history" ;
		ResultSet rset1= conn.dbRetrival(query);
		return rset1;
	}
	
	/////
	
	//get quantity,rate,amount of certain billNo
	
	
	public ResultSet getPurchaseBillDetails(String billNo,String name,String id)
	{
		
		String query = "select item_Id,item_Name,quantity,rate,Amount from purchase_history where supplier_Id ='"+id+"' AND supplier_Name='"+name+"'"+" AND bill_No='"+billNo+"'";
		ResultSet rset1= conn.dbRetrival(query);
		return rset1;	
	}
	
	public ResultSet getSalesBillDetails(String billNo,String name,String id)
	{
		
		String query = "select product_Id,product_Name,quantity,rate,amount from sales_history where customer_Id ='"+id+"' AND customer_Name='"+name+"'"+" AND reciept_No='"+billNo+"'";
		//String query = "select * from purchase_history WHERE purchase_Date = '"+dateFormat+"'" ;
		ResultSet rset1= conn.dbRetrival(query);
		return rset1;	
	}
	
	public ResultSet getAccountsOfAllsuppliersFromPurchaseHistory()
	{
		
		String query = "select * from purchase_history";
		ResultSet rset = conn.dbRetrival(query);
		return rset;
		
	}
	
	public ResultSet getAllSuppliersWithLeisure()
	{
		String sp="Supplier";
		String query = "select name,mobile_No_1 from dealers where role='"+sp+"'";
		ResultSet rset = conn.dbRetrival(query);
		return rset;
		
	}
	 public ResultSet getdebitOfDealerFromPurchaseHistory(String Name,String Id)
	 {
		 
		 String query = "select Amount from purchase_history where supplier_Name='"+Name+"' AND supplier_Id='"+Id+"'";
		 ResultSet rset = conn.dbRetrival(query);
		 return rset;
		 
	 }
	 
	 public ResultSet getcreditOfDealerFromAccountPayable(String Name,String Id)
	 {
		 
		  String query = "select amount from accountpayable where supplier_Name='"+Name+"' AND supplier_Id='"+Id+"'";
		  
		 ResultSet rset = conn.dbRetrival(query);
		 return rset;
		 
	 }
	///
	
	 
	 ////////////// Zafar And Umais
	 
		public ResultSet getAccountsOfAllCustomersFromSalesHistory()
		{
			
			String query = "select * from sales_history";
			ResultSet rset = conn.dbRetrival(query);
			return rset;
			
		}

		
		public ResultSet getAllCustomersWithLeisure()
		{
			String cs="Customer";
			String query = "select name,mobile_No_1 from dealers where role='"+cs+"'";
			ResultSet rset = conn.dbRetrival(query);
			return rset;
			
		}
		
		public ResultSet getCreditOfDealerFromSalesHistory(String Name,String Id)
		 {
			String query = "select amount from sales_history where customer_Name='"+Name+"' AND customer_Id='"+Id+"'";
			ResultSet rset = conn.dbRetrival(query);
			return rset;
			 
		 }
		
		public ResultSet getDebitOfDealerFromAccountRecievable(String Name,String Id)
		 {
			 
			 String query = "select amount from accountsrecievable where customer_Name='"+Name+"' AND customer_Id='"+Id+"'";
			 ResultSet rset = conn.dbRetrival(query);
			 return rset;
			 
		 }

		//////////////////////*******************//////////////////////////////////
}
