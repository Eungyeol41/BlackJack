package com.team.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.team.app.model.CardVO;
import com.team.app.service.CardDeck;
import com.team.app.service.Player;

public class PlayerImpl implements Player {

	protected List<CardVO> pCardList;

	protected Scanner scan;
	protected CardVO cardVO;

	public PlayerImpl() {
		pCardList = new ArrayList<CardVO>();
		scan = new Scanner(System.in);
		cardVO = new CardVO();
	}

	public List<CardVO> getPCardList(){
		return this.pCardList;
	}
	
	@Override
	public void getCard(CardDeck cardDeck) {
		// 카드 1장 뽑기
		cardVO = cardDeck.hit();
		pCardList.add(cardVO);
	}

	@Override
	public void openCard() {

		int pSize = pCardList.size();

		System.out.print("플레이어\t");

		for (int i = 0; i < pSize; i++) {
			if (i == 3) {
				System.out.printf("\n\t\t");
			} 
				cardVO = pCardList.get(i);
				System.out.printf("[ %s %2s ]  ", cardVO.getCardPattern(), cardVO.getCardNumber());
		}
		System.out.println();
	}

	@Override
	public Integer sumPoint() {
		// TODO player의 카드 점수 합산

		int pSum = 0;
		int pSize = pCardList.size();

		for (int i = 0; i < pSize; i++) {

			CardVO cardVO = pCardList.get(i);
			String number = cardVO.getCardNumber();

			if (number.equals("K") || number.equals("Q") || number.equals("J")) {
				pSum += 10;
				continue;
			} else if (number.equals("A")) {
				pSum++;
				continue;
			}
			Integer score = Integer.valueOf(number);
			pSum += score;
		}
		return pSum;
	}

	@Override	
	public Boolean pSelect() {

		while (true) {

			Integer pSum = this.sumPoint();
			System.out.println("-".repeat(50));
			System.out.println("카드 점수합 : " + pSum);
			System.out.println("-".repeat(50));

			System.out.println("히트나 스탠드를 선택해주세요");
			System.out.println("[ Hit : H ] [ Stand : S ]");
			System.out.print(">> ");
			String hit = scan.nextLine();
			if (hit.equalsIgnoreCase("H")) {
				return true;
			} else if (hit.equalsIgnoreCase("S")) {
				return false;
			}
			System.out.println("H 또는 S만 입력하세요 !!!");
			continue;
		}
	}
}
