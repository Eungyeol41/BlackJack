package com.team.app.model;

public class CardVO {

	private String cardPattern;
	private String cardNumber;
	public String getCardPattern() {
		return cardPattern;
	}
	public void setCardPattern(String cardPattern) {
		this.cardPattern = cardPattern;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	@Override
	public String toString() {
		return "[cardPattern=" + cardPattern + ", cardNumber=" + cardNumber + "]";
	}
	
}
