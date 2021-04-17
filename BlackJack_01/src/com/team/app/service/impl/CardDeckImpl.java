package com.team.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.team.app.model.CardVO;
import com.team.app.service.CardDeck;

public class CardDeckImpl implements CardDeck {

	protected Random rnd;
	protected List<CardVO> cardList;
	
	public CardDeckImpl() {
		rnd = new Random();
		cardList = new ArrayList<CardVO>();
	}
	
	@Override
	public void makeDeck() {
		// TODO 카드 덱  만들기
		String[] pattern = new String[] {"♥","♣","◆","♠"};
		String[] number = new String[] {"A","2","3","4","5","6","7","8","9","10","K","Q","J"};
		
		for(int i = 0 ; i < pattern.length ; i++) {
			for(int j = 0 ; j < number.length ; j++) {
				
				CardVO vo = new CardVO();
				vo.setCardPattern(pattern[i]);
				vo.setCardNumber(number[j]);
				
				cardList.add(vo);
				
			}
		}
	} 
		
	@Override
	public CardVO hit() {
		// TODO 카드값 리턴
		
		int nSize = cardList.size();
		int num = rnd.nextInt(nSize); // 0 ~ (nSize - 1) 중의

		CardVO vo = cardList.get(num);
		
		return vo;
		
	}
	

}
