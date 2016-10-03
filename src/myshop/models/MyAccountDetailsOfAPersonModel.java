package myshop.models;

public class MyAccountDetailsOfAPersonModel {

	 String date;
	 String supplierId;
	 String supplierName;
	 String credit;
	 String debit;
	 String Balance;
	public MyAccountDetailsOfAPersonModel(String date, String supplierId, String supplierName, String credit,String debit, String balance) {
		this.date = date;
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.credit = credit;
		this.debit = debit;
		this.Balance = balance;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getDebit() {
		return debit;
	}
	public void setDebit(String debit) {
		this.debit = debit;
	}
	public String getBalance() {
		return Balance;
	}
	public void setBalance(String balance) {
		Balance = balance;
	}
	 
	 
	
}
