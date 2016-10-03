package myshop.models;

public class MyPurchaseBillModel {

	public String serialNo;
	public String productId;
	public String productName;
	public String productQuantity;
	public String productRate;
	public String productAmount;
	
	public MyPurchaseBillModel(String serialNo,String productId,String productName,String productQuantity,String productRate,String productAmount){
		this.serialNo = serialNo;
		this.productId=productId;
		this.productName=productName;
		this.productQuantity=productQuantity;
		this.productRate=productRate;
		this.productAmount=productAmount;
	}
	
	
	public void setSerialNo(String serialNo){
		this.serialNo = serialNo;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductQuantity() {
		return productQuantity;
	}


	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}


	public String getProductRate() {
		return productRate;
	}


	public void setProductRate(String productRate) {
		this.productRate = productRate;
	}


	public String getProductAmount() {
		return productAmount;
	}


	public void setProductAmount(String productAmount) {
		this.productAmount = productAmount;
	}


	public String getSerialNo() {
		return serialNo;
	}
	
	
}
