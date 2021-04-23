package com.team.app.service.impl;

import java.util.Scanner;

import com.team.app.service.CardDeck;
import com.team.app.service.Gamer;
import com.team.app.service.Rule;

public class GameImpl implements com.team.app.service.Game {

	protected Dealer dealer;
	protected Player player;
	protected Rule rule;
	protected Scanner scan;
	protected CardDeck cardDeck;
	protected GameRule gameR;

	public GameImpl() {
		rule = new RuleImpl();
		scan = new Scanner(System.in);
		dealer = new Dealer();
		player = new Player();
		gameR = new GameRule();
	}

	@Override
	public void selectMenu() {
		// TODO 블랙잭 메뉴 선택

		while (true) {
			System.out.println("=".repeat(50));
			System.out.println("BlackJack");
			System.out.println("=".repeat(50));
			System.out.println("1. 게임시작");
			System.out.println("2. 불러오기");
			System.out.println("3. 충전하기");
			System.out.println("4. 저장하기");
			System.out.println("5. 게임 규칙 설명");
			System.out.println("Exit. 그만하기");
			System.out.println("-".repeat(50));
			System.out.print(">> ");
			String strInput = scan.nextLine();
			if (strInput.equals("Exit")) {
				System.out.println("게임을 종료합니다");
				return;
			}
			this.selectMenu(strInput);
		} // while end
	}

	private void selectMenu(String strInput) {
		Integer intInput = null;
		try {
			intInput = Integer.valueOf(strInput);
		} catch (NumberFormatException e) {
			System.out.println("메뉴나 Exit를 선택해주세요");
			return;
		}
		if (intInput == 1) {
			// 0과 null 은 비교할 수 없습니다.... 여러분...
			if (player.getpMoney() == null) {
				player.money();
			} else if (player.getpMoney() == 0) {
				System.out.println("소지 금액이 0원입니다.");
				System.out.println("금액을 충전하고 오세요");
				return; // 돈없으면 쫓아냄
			}
			this.playGame();
		} else if (intInput == 2) {
			player.loadMoney();
		} else if (intInput == 3) {
			// 충전하기
			player.charge();
		} else if (intInput == 4) {
			player.saveMoney();
		} else if (intInput == 5) {
			gameR.gameRule();
		}
	}

	@Override
	public void playGame() {
		// TODO 플레이어 딜러 게임 플레이
		System.out.println("게임을 시작합니다.");

		// 카드리스트 초기화, 배팅, 카드 받기
		this.startGame();
		// 딜러와 플레이어 카드 확인
		this.open();

		// 먼저 플레이어 선택 true이면 Hit하고 카드 확인
		if (player.pSelect()) {
			player.getCard(cardDeck);
			this.open();

			// 그다음 딜러가 16점 이하이면 카드 한장 받기
			if (dealer.sumPoint() < 16) {
				System.out.println("딜러가 한장을 가져갑니다.");
				dealer.getCard(cardDeck);
				while (true) {
					System.out.println("딜러가 BlackJack 일 경우 3배를 획득할 수 있습니다.");
					System.out.println("추가 bettig을 하시겠습니까?");
					System.out.println("1. 추가betting  2.그냥 진행");
					System.out.print(">> ");
					String strBet = scan.nextLine();
					if (strBet.equals("1")) {
						player.betting();
						break;
					} else if (strBet.equals("2")) {
						break;
					} else
						continue;
				} // while end

				// 플레이어 버스트이면 결과보여주고 끝
				if (checkBurst(player)) {
					return;
				}

				// 그다음 딜러가 16점 이하이면 카드 한장 받기
				if (dealer.sumPoint() < 16) {
					System.out.println("딜러가 한장을 가져갑니다.");
					dealer.getCard(cardDeck);
				}
				// 딜러가 버스트라면 결과보여주고 끝
				if (checkBurst(dealer)) {
					return;
				}

				// 플레이어 선택
				// - true이면 카드를 받고
				// - false라면 stand로 결과 출력
				while (player.pSelect()) {
					player.getCard(cardDeck);
					this.open();
					// 플레이어가 버스트라면 return
					if (checkBurst(player))
						return;
				} // while end (player)
			}
		}
		rule.printResult(dealer.sumPoint(), player.sumPoint(), player);
	}

	private void open() {
		// 딜러와 플레이어 카드 출력
		dealer.openCard();
		player.openCard();
	}

	private void startGame() {
		// 딜러와 플레이어 카드 리스트 초기화
		player.pCardList.removeAll(player.pCardList);
		dealer.dCardList.removeAll(dealer.dCardList);

		// 카드덱 생성
		cardDeck = new CardDeckImpl();

		// 플레이어 배팅
		player.betting();

		// 딜러와 플레이어 카드 2장씩 받기
		dealer.getCard(cardDeck);
		player.getCard(cardDeck);

		dealer.getCard(cardDeck);
		player.getCard(cardDeck);
	}

	@Override
	public Boolean checkBurst(Gamer player1) {
		// TODO 플레이중에 버스트 체크 및 결과 출력
		boolean result = false;

		if (player1.sumPoint() > 21) {
			System.out.println("Burst!");
			rule.printResult(dealer.sumPoint(), player.sumPoint(), player);
			result = true;
		}
		return result;
	}
}
