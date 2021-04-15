package com.team.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.team.app.model.CardVO;
import com.team.app.service.CardDeck;

public class CardDeckV1 implements CardDeck {

	protected Random rnd;
	protected List<CardVO> cardList;
	
	public CardDeckV1() {
		rnd = new Random();
		cardList = new ArrayList<CardVO>();
	}
	
	@Override
	public void makeDeck() {
		// TODO Auto-generated method stub

	}

	@Override
	public CardVO hit() {
		// TODO 중복검사, 카드값 리턴 
		return null;
	}

}
