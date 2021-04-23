package com.team.app.service.impl;

public class BlackJack_Betting extends Player {
	
	protected Integer bBet;
	
	protected Integer bBetting() {
		
		
		
		while (true) {
			System.out.println("-".repeat(50));
			System.out.println("베팅할 액수를 입력해주세요");
			System.out.print(">> ");
			String strBet = scan.nextLine();

			try {
				bBet = Integer.valueOf(strBet);
			} catch (NumberFormatException e) {
				System.out.println("정수만 입력하세요 !!");
				continue;
			}

			if (bBet > pMoney) {
				System.out.println("입력한 액수가 소지금액보다 큽니다 !!");
				continue;
			} else if (bBet < 0) {
				System.out.println("0보다 큰 액수를 입력하세요 !!");
				continue;

			}
			pMoney -= bBet;
			break;
		}
		System.out.println("-".repeat(50));
		System.out.println("베팅 금액 : " + bBet);
		System.out.println("소지 금액 : " + pMoney);
		
		return bBet;
	}

	public Integer getbBet() {
		return bBet;
	}

	public void setbBet(Integer bBet) {
		this.bBet = bBet;
	}
	
	

}
