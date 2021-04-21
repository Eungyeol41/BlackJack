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
	public void openCard() {
		// TODO dealer가 뽑은 카드 중 1장 오픈하고 dealer의 점수 저장
		// 소연: 여기도 Integer method 였는데 void로 바꿈
		// 		게임클래스에서 이거 리턴값이아니라 sum가져가길래
		int dSize = dCardList.size();
		CardVO cardVO = new CardVO();
		//Integer dSum = null;
		
		// 소연 : for문 필요없지않나? 어차피 계속 [0]번째만 보여주면되잖아
		// for(int i = 0; i < dSize; i++) {
			//cardVO = dCardList.get(i);
			//dSum = this.sumPoint();
		//}
		
		cardVO = dCardList.get(0);
		System.out.println("딜러의 카드 : " 
				+ cardVO.getCardPattern() 
				+ " " 
				+ cardVO.getCardNumber());
		
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
