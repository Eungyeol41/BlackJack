package com.team.app.service;

public interface Player {
	public void getCard(CardDeck cardDeck);
	public void openCard();
	public Integer sumPoint();	
	public Boolean pSelect();
	public void charge();
	public void betting();
	public void saveMoney();
	public void loadMoney();
}
