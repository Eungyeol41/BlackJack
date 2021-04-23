package com.team.app.service.impl;

import com.team.app.service.Rule;

public class RuleImpl implements Rule {

		
	@Override
	public void printResult(int dPoint, int pPoint, Player player) {
		// TODO 승패를 판별하고 결과 출력

		System.out.println("=".repeat(50));
		System.out.println("::Game Result::");
		System.out.println("Dealer Score : " + dPoint);
		System.out.println("Player Score : " + pPoint);
		System.out.println("");

		// dealer와 player 모두 21이 나왔을 때
		if(pPoint == 21 && dPoint ==21) {
			System.out.println("!! Draw !!");
			this.calculateResult("draw", player);
			
		// dealer BlackJack 패배
		} else if (pPoint == 21) {
			System.out.println("!!! Black Jack !!!");
			System.out.println("Player BlackJack");
			this.calculateResult("Bwin", player);
			
		// player BlackJack 승리
		} else if (dPoint == 21) {
			System.out.println("!!! Black Jack !!!");
			System.out.println("Dealer BlackJack");
			//이것도 변경해야함 ( 베팅 관련 ★★★★)
			this.calculateResult("Blose", player);
			
		// dealer burst 승리
		} else if (dPoint > 21) {
			System.out.println("!!! You Win !!!");
			System.out.println("Dealer Burst");
			this.calculateResult("win", player);

		// player burst 패배
		} else if (pPoint > 21) {
			System.out.println("!!! You Lose !!!");
			System.out.println("Player Burst");
			this.calculateResult("lose", player);

		// player 승리
		} else if (dPoint < pPoint) {
			System.out.println("!!! You Win !!!");
			this.calculateResult("win", player);

		// player 패배
		} else if (dPoint > pPoint) {
			System.out.println("!!! You Lose !!!");
			this.calculateResult("lose", player);

		// 무승부
		} else if (dPoint == pPoint) {
			System.out.println("!!! Draw !!!");
			this.calculateResult("draw", player);
		}
		System.out.println();

	}

	protected void calculateResult(String result, Player player) {
		// TODO 결과에 따라 배팅금액 정산

		Integer pMoney = player.getpMoney();
		Integer intBet = player.getIntBet();
		Integer bBet = player.getbBet();

		if (result.equals("win")) {
			pMoney += (intBet * 2);
			System.out.println((intBet * 2) + "원을 획득했습니다.");
			System.out.println("소지 금액 : " + pMoney);
		} else if (result.equals("lose")) {
			// 효율을 위해서 패배를 먼저 if문에 작성
			System.out.println(intBet + "원을 잃었습니다.");
			System.out.println("소지 금액 : " + pMoney);
		} else if (result.equals("draw")) {
			pMoney += intBet;
			System.out.println(intBet + "원을 돌려받았습니다");
			System.out.println("소지 금액 : " + pMoney);
		} else if (result.equals("Bwin")) {
			pMoney += ((intBet + bBet) * 3);
			System.out.println(((intBet + bBet) * 3) + "원을 획득했습니다.");
			System.out.println("소지 금액 : " + pMoney);
		} else if (result.equals("Blose")) {
			pMoney -= ((intBet + bBet) * 2);
			System.out.println(((intBet + bBet) * 2) + "원을 잃었습니다.");
			System.out.println("소지 금액 : " + pMoney);
			if (pMoney < 0) {
				System.out.println("금액이 부족합니다.");
				pMoney = 0;
				System.out.println("소지금액이 0원으로 바꼈습니다.");
				return; // 테스트 필요★★★★★★★★
			}
		}
		player.setpMoney(pMoney);
	}
}
