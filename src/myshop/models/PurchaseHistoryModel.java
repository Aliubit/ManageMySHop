package myshop.models;

public class PurchaseHistoryModel {

	public String date;
	public String billNo;
	public String name;
	public String id;
	public String amount;
	
	public PurchaseHistoryModel(String date,String billNo,String name,String id,String amount){
			this.date = date;
			this.billNo=billNo;
			this.name=name;
			this.id=id;
			this.amount=amount;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public void setBillNo(String billNo){
		this.billNo = billNo;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setAmount(String amount){
		this.amount = amount;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public String getBillNo(){
		return this.billNo;
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getAmount(){
		return this.amount;
	}
	
	
}
