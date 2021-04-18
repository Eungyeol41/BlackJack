package com.team.app.service.impl;

import java.util.Scanner;

import com.team.app.service.Gamer;
import com.team.app.service.Rule;

public class GameImpl implements com.team.app.service.Game {

	protected Dealer dealer;
	protected PlayerV2 player;
	protected Rule rule;
	protected Scanner scan;
	

	public GameImpl() {
		dealer = new Dealer();
		player = new PlayerV2();
		rule = new RuleImpl();
		scan = new Scanner(System.in);
	}
	
	@Override
	public void selectMenu() {
		// TODO 블랙잭 메뉴 선
		
	}

	@Override
	public void playGame() {
		// TODO 플레이어 딜러 게임 플레이
		System.out.println("게임을 시작합니다.");
		// 2장씩 받고
		this.starGame();
		// player card check check
		player.openCard();
		
		// player select
		// 		Hit get one card
		if(player.pSelect() == 0) {
			player.getCard();
		}
		// 		Stand nothing
		
		// dealer point under 16
		// 		get one card
		if(dealer.sumPoint() < 16) {
			dealer.getCard();
		}
		
		// open dealer cards
		dealer.openCard();
		// open player cards
		player.openCard();
		
		// if dealer or player burst >> game end
		if( checkBurst(dealer) || checkBurst(player)) {
			rule.printResult(dealer.sumPoint(),player.sumPoint());
		} else {
		
		// player Hit 			>> get one card
		// 		  Stand (null) 	>> game end
		while(player.pSelect() != null) {
			player.getCard();
			
			// if player burst >> game end
			if(checkBurst(player)) break;
		}

		rule.printResult(dealer.sumPoint(),player.sumPoint());
		}


	}

	private void starGame() {
		// TODO 딜러 2장 플레이어 2장 받기
		dealer.getCard();
		dealer.getCard();

		player.getCard();
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
