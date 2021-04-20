package com.team.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.team.app.model.CardVO;
import com.team.app.service.CardDeck;
import com.team.app.service.Gamer;

public class Dealer implements Gamer {

	public List<CardVO> dCardList;
	
	protected CardDeck cardDeck;
	protected Scanner scan;

	public Dealer() {
		dCardList = new ArrayList<CardVO>();
		cardDeck = new CardDeckImpl();
		scan = new Scanner(System.in);
	}

	@Override
	public void getCard() {
		// TODO dealer 카드 1장 뽑기
		
		CardVO cardVO = new CardVO();
		cardVO = cardDeck.hit();

		dCardList.add(cardVO);
	} // end getCard()

	@Override
	public Integer openCard() {
		// TODO dealer가 뽑은 카드 중 1장 오픈하고 dealer의 점수 저장
		
		int dSize = dCardList.size();
		CardVO cardVO = new CardVO();
		Integer dSum = null;
		
		for(int i = 0; i < dSize; i++) {
			cardVO = dCardList.get(i);
			dSum = this.sumPoint();
		}
		System.out.println("딜러의 카드 : " + ( cardVO.getCardPattern() + " " + cardVO.getCardNumber() ));
		
		return dSum;
	} // end openCard()

	@Override
	public Integer sumPoint() {
		// TODO dealer의 카드 점수 합산
		
		int dSum = 0;
		int dSize = dCardList.size();
		
		for(int i = 0; i < dSize; i++) {
			CardVO cardVO = dCardList.get(i);
			if(cardVO.getCardNumber().equals("J") || cardVO.getCardNumber().equals("Q") || cardVO.getCardNumber().equals("K")) {
				dSum += 10;
				continue;
			} else if(cardVO.getCardNumber().equals("A")) {
				dSum++;
				continue;
			}
			Integer score = Integer.valueOf(cardVO.getCardNumber());
			dSum += score;
		}
		return dSum;
	} // end sumPoint()

}
