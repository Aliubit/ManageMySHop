package myshop.models;

public class MyAccountPaybleModel {

	 public String serialNo;
	 public String supplierId;
	 public String supplierName;
	 public String credit;
	 public String debit;
	 public String Balance;
	
	 public MyAccountPaybleModel(String counter, String supplierId, String supplierName , String debit,String credit,String balance) {
		this.serialNo=counter;
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.credit=credit;
		this.debit = debit;
		this.Balance=balance;
	
	}


	public String getSerialNo() {
		return serialNo;
	}


	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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
