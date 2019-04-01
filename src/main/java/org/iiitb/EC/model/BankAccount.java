package org.iiitb.EC.model;

public class BankAccount {
	private int holderID;
	private long accountNumber;
	private long currentBalance;
	
	public long getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(long currentBalance) {
		this.currentBalance = currentBalance;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getHolderID() {
		return holderID;
	}
	public void setHolderID(int holderID) {
		this.holderID = holderID;
	}
}
