package org.iiitb.EC.model;

public class BankAccount {
	private int holderID;
	private String accountNumber;
	private int currentBalance;
	
	public int getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(int currentBalance) {
		this.currentBalance = currentBalance;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getHolderID() {
		return holderID;
	}
	public void setHolderID(int holderID) {
		this.holderID = holderID;
	}
}
