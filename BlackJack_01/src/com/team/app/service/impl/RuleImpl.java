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
		
		// dealer burst 승리
		if (dPoint > 21) {
			System.out.println("Player Burst");
			this.calculateResult("win");
			
		// player burst 패배
		} else if(dPoint > 21) {
			System.out.println("Dealer Burst");
			this.calculateResult("lose");
			
		// player 승리
		} else if (dPoint < pPoint) {
			System.out.println("You're Winner!!!");
			this.calculateResult("win");
			
		// player 패배
		} else if (dPoint > pPoint) {
			System.out.println("You Lose!!!");
			this.calculateResult("lose");
			
		// 무승부
		} else if (dPoint == pPoint) {
			System.out.println("Draw!!!");
			this.calculateResult("draw");
		}
		System.out.println();
	}
	
	protected void calculateResult(String result) {
		// TODO 결과에 따라 배팅금액 정산
		Integer pMoney = playerV2.getpMoney();
		Integer intBet = playerV2.getIntBet();
		
		if(result.equals("win")) {
			pMoney += (intBet * 2);
			System.out.println((intBet * 2) + "원을 획득했습니다.");
			System.out.println("소지 금액 : " + pMoney);			
		}else if(result.equals("lose")) {
			// 효율을 위해서 패배를 먼저 if문에 작성
			System.out.println(intBet + "원을 잃었습니다.");
			System.out.println("소지 금액 : " + pMoney);
		}else if(result.equals("draw")) {
			pMoney += intBet;
			System.out.println(intBet + "원을 돌려받았습니다");
			System.out.println("소지 금액 : " + pMoney);
		}
		
		playerV2.setpMoney(pMoney);
	}
}
