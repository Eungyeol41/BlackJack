package com.team.app.service;

import java.util.List;

import com.team.app.model.CardVO;

public interface Dealer {
	public List<CardVO> getDCardList();
	public void getCard(CardDeck cardDeck);
	public void openCard();
	public Integer sumPoint();	
}
