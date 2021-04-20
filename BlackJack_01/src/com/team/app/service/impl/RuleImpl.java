package com.team.app.service.impl;

import com.team.app.service.Rule;

public class RuleImpl implements Rule {
	
	PlayerV2 playerV2;
	
	public RuleImpl() {
		playerV2 = new PlayerV2();
	}

	@Override
	public void printResult(int dPoint, int pPoint) {
		// TODO 승패를 판별하고 결과 출력

		System.out.println("=".repeat(50));
		System.out.println("::Game Result::");
		System.out.println("Dealer Score : " + dPoint);
		System.out.println("Player Score : " + pPoint);
		System.out.println("");
		// player 승리
		if (dPoint < pPoint) {
			System.out.println("You're Winner!!!");
			
		// player 패배
		} else if (dPoint > pPoint) {

			System.out.println("You Lose!!!");

		// 무승부
		} else if (dPoint == pPoint) {
			System.out.println("Draw!!!");
		}
		System.out.println();
	}
	
	public void calculateResult() {
		// TODO 결과에 따라 배팅금액 정산
		Integer intBet = playerV2.intBet;
		Integer pMoney = playerV2.pMoney;
		
	}
}
