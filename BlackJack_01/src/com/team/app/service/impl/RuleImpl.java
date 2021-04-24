package com.team.app.service.impl;

import com.team.app.model.PlayerVO;
import com.team.app.service.Rule;

public class RuleImpl implements Rule {

		
	@Override
	public void printResult(int dPoint, int pPoint, PlayerVO playerVO) {
		// TODO 승패를 판별하고 결과 출력

		System.out.println("=".repeat(50));
		System.out.println("::게임 결과::");
		System.out.println("딜러 점수 : " + dPoint);
		System.out.println("플레이어 점수 : " + pPoint);
		System.out.println("");
			
		// dealer burst 승리
		if (dPoint > 21) {
			System.out.println("!!! 이겼습니다 !!!");
			System.out.println("Dealer Bust");
			this.calculateResult("win", playerVO);

		// player burst 패배
		} else if (pPoint > 21) {
			System.out.println("!!! 졌습니다 !!!");
			System.out.println("Player Bust");
			this.calculateResult("lose", playerVO);

		// player 승리
		} else if (dPoint < pPoint) {
			System.out.println("!!! 이겼습니다 !!!");
			this.calculateResult("win", playerVO);

		// player 패배
		} else if (dPoint > pPoint) {
			System.out.println("!!! 졌습니다 !!!");
			this.calculateResult("lose", playerVO);

		// 무승부
		} else if (dPoint == pPoint) {
			System.out.println("!!! 무승부 !!!");
			this.calculateResult("draw", playerVO);
		}
		System.out.println();

	}

	protected void calculateResult(String result, PlayerVO playerVO) {
		// TODO 결과에 따라 배팅금액 정산

		Integer pMoney = playerVO.getpMoney();
		Integer pBet = playerVO.getpBet();

		if (result.equals("win")) {
			pMoney += (pBet * 2);
			System.out.println((pBet * 2) + "원을 획득했습니다.");
			System.out.println("소지 금액 : " + pMoney);
		} else if (result.equals("lose")) {
			// 효율을 위해서 패배를 먼저 if문에 작성
			System.out.println(pBet + "원을 잃었습니다.");
			System.out.println("소지 금액 : " + pMoney);
		} else if (result.equals("draw")) {
			pMoney += pBet;
			System.out.println(pBet + "원을 돌려받았습니다");
			System.out.println("소지 금액 : " + pMoney);

		}
		playerVO.setpMoney(pMoney);
	}
}
