package com.team.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.team.app.model.CardVO;
import com.team.app.service.CardDeck;
import com.team.app.service.Dealer;

public class DealerImpl implements Dealer {

	public List<CardVO> dCardList;
	protected Scanner scan;
	CardVO cardVO;

	public DealerImpl() {
		dCardList = new ArrayList<CardVO>();
		scan = new Scanner(System.in);
		cardVO = new CardVO();
	}
	
	public List<CardVO> getDCardList(){
		return this.dCardList;
	}

	@Override
	public void getCard(CardDeck cardDeck) {
		// TODO dealer 카드 1장 뽑기
		
		cardVO = cardDeck.hit();

		dCardList.add(cardVO);
	} // end getCard()

	@Override
	public void openCard() {
		// TODO dealer가 뽑은 카드 중 1장 오픈하고 dealer의 점수 저장
		int dSize = dCardList.size();
		cardVO = dCardList.get(0);
		System.out.printf("딜러\t\t");
		for(int i = 0 ;i < dSize ; i++) {
			if(i == 0) {
				System.out.printf("[ %s %2s ]  "
				,cardVO.getCardPattern(), cardVO.getCardNumber());
			} else {
				System.out.print("[      ]  ");
			}
		}
		System.out.println();
	} // end openCard()

	@Override
	public Integer sumPoint() {
		// TODO dealer의 카드 점수 합산
		
		int dSum = 0;
		int dSize = dCardList.size();
		
		for(int i = 0; i < dSize; i++) {
			
			CardVO cardVO = dCardList.get(i);
			String number = cardVO.getCardNumber();
			
			if(number.equals("J") || number.equals("Q") || number.equals("K")) {
				dSum += 10;
				continue;
			} else if(number.equals("A")) {
				dSum++;
				continue;
			}
			Integer score = Integer.valueOf(number);
			dSum += score;
		}
		return dSum;
	} // end sumPoint()

}
