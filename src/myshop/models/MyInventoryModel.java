package myshop.models;

public class MyInventoryModel {
	
	 String productId;
	 String productName;
	 String productQuantity;
	 String productRate;
	 String totalProductAmount;
	
	public MyInventoryModel(String prodId,String prodName,String prodQuantity,String productRate)
	{
		this.productId=prodId;
		this.productName=prodName;
		this.productQuantity=prodQuantity;
		this.productRate=productRate;	
		this.settotalProductAmount();
	}
	
	public void setProductName(String productName){
		this.productName = productName;
	}
	
	public void setProductId(String productId){
		this.productId = productId;
	}
	
	public void setProductQuantity(String productQuantity){
		this.productQuantity = productQuantity;
	}
	
	public void setProductRate(String productRate){
		this.productRate = productRate;
	}
	
	public void settotalProductAmount(){
		this.totalProductAmount = (Integer.parseInt(this.productQuantity) * Float.parseFloat(this.productRate)) + "";
	}
	public String getProductName(){
		return this.productName;
	}
	
	public String getProductId(){
		return this.productId; 
	}
	
	public String getProductQuantity(){
		return this.productQuantity;
	}
	
	public String getProductRate(){
		return this.productRate;
	}
	
	public String getTotalProductAmount(){
		return this.totalProductAmount;
	}
	
	
}
