package com.team.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.team.app.model.CardVO;
import com.team.app.service.CardDeck;
import com.team.app.service.Gamer;

public class Player implements Gamer {

	protected List<CardVO> pCardList;
	protected List<CardVO> dCardList;
	protected CardDeck cardDeck;
	protected Scanner scan;
	protected Dealer dealer;

	public Player() {

		pCardList = new ArrayList<CardVO>();
		dCardList = new ArrayList<CardVO>();

		cardDeck = new CardDeckImpl();
		scan = new Scanner(System.in);

		cardDeck.makeDeck();

	}

	public CardVO overLap(CardVO cardVO) {
		// 중복검사 method

		int num = 0;
		int i = 0;
		int j = 0;

		int pSize = pCardList.size();
		int dSize = dCardList.size();

		for (j = 0; j < pSize; j++) {
			if (cardVO.getCardPattern().equals(pCardList.get(j).getCardPattern())) {
				if (cardVO.getCardNumber().equals(pCardList.get(j).getCardNumber())) {
					break;
				}
			}
		}
		for (i = 0; i < dSize; i++) {
			if (cardVO.getCardPattern().equals(dealer.dCardList.get(i).getCardPattern())) {
				if (cardVO.getCardNumber().equals(dealer.dCardList.get(i))) {
					break;
				}
			}
		}
		if (j < pSize | i < dSize) {
			return null;
		}

		return cardVO;
	}

	@Override
	public void getCard() {
		// 카드 1장 뽑기

		CardVO cardVO = new CardVO();

		while (true) {
			cardVO = cardDeck.hit();
			this.overLap(cardVO);
			if (this.overLap(cardVO) == null) {
				continue;
			}
			break;
		}
		pCardList.add(cardVO);
	}

	@Override
	public void openCard() {
		// Enter를 누르면 카드 1장을 뽑고 
		// sumpoint() method를 이용해 점수 저장

		CardVO cardVO = new CardVO();
		Integer score = null;

		while (true) {

			System.out.print(">> ");
			String str = scan.nextLine();
			int pSize = 0;
			if (str.equals("")) {

				this.getCard();
				pSize = pCardList.size();
				score = this.sumPoint();
				
				if (score >= 21) {
					System.out.println("블랙잭 or 버스트");
					break;
				}

				for (int i = 0; i < pSize; i++) {
					cardVO = pCardList.get(i);
					System.out.println("보유 카드 : " + cardVO.getCardPattern() + "\t" + cardVO.getCardNumber());
				}
				System.out.println("현재 점수 합계 : " + score);
//				continue;
			}
			
			Integer hit = this.hit(pSize);
			if(hit == null) {
				continue;
			} else {
				break;
			}
		}
		System.out.println("카드 합산 점수 : " + score);
	}

	public Integer hit(int pSize) {
		// 플레이어가 카드를 2장(이상) 뽑았을때 hit할것인지 stand할 것인지 ?

		while (true) {
			
			if (pSize >= 2) {
				System.out.println("히트 하시겠습니까?");
				System.out.println("Hit : H // Stand : S");
				System.out.print(">> ");
				String hit = scan.nextLine();
				if (hit.equalsIgnoreCase("H")) {
					return null;
				} else if (hit.equalsIgnoreCase("S")) {
					return 0;
				} else {
					System.out.println("H 또는 S만 입력하세요 !!!");
					continue;
				}
			}
		}
	}

	@Override
	public Integer sumPoint() {
		// 뽑은 카드의 점수계산 method
		// vo.getCardNumber에 들어있는 문자열을 정수형으로 변환하여 score에 저장 후 
		// pSum 변수에 저장
		// vo.getCardNumber에 있는 문자열이 알파벳일 경우 조건문을 이용하여 
		// pSum 변수에 알맞은 점수를 저장하고 continue 
		 

		int pSum = 0;
		int pSize = pCardList.size();

		for (int i = 0; i < pSize; i++) {
			CardVO vo = pCardList.get(i);
			if (vo.getCardNumber().equals("K") | vo.getCardNumber().equals("Q") | vo.getCardNumber().equals("J")) {
				pSum += 10;
				continue;
			} else if (vo.getCardNumber().equals("A")) {
				pSum++;
				continue;
			}
			Integer score = Integer.valueOf(vo.getCardNumber());
			pSum += score;
		}

		return pSum;
	}

}
