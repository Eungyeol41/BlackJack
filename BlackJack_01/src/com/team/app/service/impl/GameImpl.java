package com.team.app.service.impl;

// 언니.. 딜러나 플레이어 ... 초기화 안하면... 우리.... 버스트 코인타고...
// 부자돼요....
import java.util.Scanner;

import com.team.app.service.Gamer;
import com.team.app.service.Rule;

public class GameImpl implements com.team.app.service.Game {

	protected Dealer dealer;
	protected Player player;
	protected Rule rule;
	protected Scanner scan;
	
	

	public GameImpl() {
		rule = new RuleImpl();
		scan = new Scanner(System.in);
		dealer = new Dealer();
		player = new Player();
	}
	
	@Override
	public void selectMenu() {
		// TODO 블랙잭 메뉴 선
		
		while (true) {
			System.out.println("=".repeat(50));
			System.out.println("BlackJack");
			System.out.println("=".repeat(50));
			System.out.println("1. 새게임");
			System.out.println("2. 이어하기");
			System.out.println("3. 충전하기");
			System.out.println("4. 저장하기");
			System.out.println("Exit. 그만하기");
			System.out.println("-".repeat(50));
			System.out.print(">> ");
			String strInput = scan.nextLine();
			if (strInput.equals("Exit"))
				return;
			this.selectMenu(strInput);
		}
	}
	
	private void selectMenu(String strInput) {
		Integer intInput = null;
		try {
			intInput = Integer.valueOf(strInput);
		} catch (NumberFormatException e) {
			System.out.println("메뉴나 Exit를 선택해주세요");
		}
		
		if(intInput == 1) {
			player.money();
			this.playGame();
		}else if(intInput == 2) {
			player.loadMoney();
			this.playGame();
		}else if(intInput == 3) {
			//충전하기
			player.charge();
		}else if(intInput == 4) {
			player.saveMoney();
		}
	}

	@Override
	public void playGame() {
		// TODO 플레이어 딜러 게임 플레이

		player.pCardList.removeAll(player.pCardList);
		dealer.dCardList.removeAll(dealer.dCardList);

		
		System.out.println("게임을 시작합니다.");
		player.betting();
		// 2장씩 받고
		this.startGame();
		// dealer player card check check
		player.openCard();
		dealer.openCard();
		
		// player select
		// 		Hit get one card
		if(player.pSelect() == 0) {
			player.getCard();
			player.openCard();
		} // @@@@@@@@@@@@@@ 이 코드 필요한거임???? @@@@@@@@@@@@@@@@
	
		
		// if player burst
		if( checkBurst(player) ) {
			// result
			rule.printResult(dealer.sumPoint(),player.sumPoint(),  player);
			return;
		}
		// 		Stand nothing
		
		// dealer point under 16
		// 		get one card
		if(dealer.sumPoint() < 16) {
			dealer.getCard();
		}
		// if dealer burst
		if( checkBurst(dealer) ) {
			// result
			rule.printResult(dealer.sumPoint(),player.sumPoint(), player);
			return;
		}
		
		
		/*
		// open dealer cards
		dealer.openCard();
		// open player cards
		player.openCard();
		*/
		
		// if dealer or player burst >> game end
//		if( checkBurst(dealer) || checkBurst(player)) {
//			rule.printResult(dealer.sumPoint(),player.sumPoint());
//			
//		} else {
		
		// player Hit 			>> get one card
		// 		  Stand (null) 	>> game end
		while(player.pSelect() != null) {
			player.getCard();
			player.openCard();
			// if player burst >> game end
			if(checkBurst(player)) break;
		}

		rule.printResult(dealer.sumPoint(),player.sumPoint(), player);

//		}


	}

	private void startGame() {
		// TODO 딜러 2장 플레이어 2장 받기
		dealer.getCard();
		player.getCard();
		
		dealer.getCard();
		player.getCard();
		
		
	}

	@Override
	public Boolean checkBurst(Gamer player1) {
		// TODO Auto-generated method stub
		boolean result = false;

		if (player1.sumPoint() > 21) {
			System.out.println("Burst!");
			result = true;
		}

		return result;
	}

}
