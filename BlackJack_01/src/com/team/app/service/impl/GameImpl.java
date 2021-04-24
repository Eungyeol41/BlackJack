package com.team.app.service.impl;

import java.util.Scanner;

import com.team.app.model.PlayerVO;
import com.team.app.service.CardDeck;
import com.team.app.service.Dealer;
import com.team.app.service.Player;
import com.team.app.service.Rule;

public class GameImpl implements com.team.app.service.Game {
	
	protected Dealer dealer;
	protected Player player;
	protected PlayerVO playerVO;

	protected Rule rule;
	protected Scanner scan;
	protected CardDeck cardDeck;

	public GameImpl() {
		rule = new RuleImpl();
		scan = new Scanner(System.in);

		dealer = new DealerImpl();
		player = new PlayerImpl();
		playerVO = new PlayerVO();
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
		if (intInput < 1 || intInput > 5) {
			System.out.println("메뉴는 1 ~ 5, QUIT만 입력할 수 있습니다.");
			return;
		}
		if (intInput == 1) {
			if (playerVO.getpMoney() == null) {
				System.out.println("10000원이 충전되었습니다");
				playerVO.setpMoney(10000);
			} else if (playerVO.getpMoney() == 0) {
				System.out.println("소지 금액이 0원입니다.");
				System.out.println("금액을 충전하고 오세요");
				return;
			}
			this.playGame();
		} else if (intInput == 2) {
			player.loadMoney(playerVO);
		} else if (intInput == 3) {
			// 충전하기
			player.charge(playerVO);
		} else if (intInput == 4) {
			player.saveMoney(playerVO);
		} else if (intInput == 5) {
			this.gameRule();
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
			// player Hit

			player.getCard(cardDeck);
			this.open();

			// 플레이어 버스트이면 결과보여주고 끝
			if (checkBust()) {
				return;
			}

			// dealer play
			this.dealerPlay();

			// 플레이어 선택
			// - true이면 카드를 받고
			// - false라면 stand로 결과 출력
			while (player.pSelect()) {
				player.getCard(cardDeck);
				this.open();
				// 플레이어가 버스트라면 return
				if (checkBust())
					return;
			} // while end (player)
		} else {
			// player Stand

			// dealer play
			this.dealerPlay();
		}
		rule.printResult(dealer.sumPoint(), player.sumPoint(), playerVO);
	}

	private void dealerPlay() {
		// 그다음 딜러가 16점 이하이면 카드 한장 받기
		if (dealer.sumPoint() <= 16) {
			System.out.println("-".repeat(50));
			System.out.println("딜러가 한장을 가져갑니다.");
			dealer.getCard(cardDeck);
			this.open();

			// 딜러가 버스트라면 결과보여주고 끝
			if (checkBust()) {
				return;
			}
		}
	}

	private void open() {
		// 딜러와 플레이어 카드 출력
		dealer.openCard();
		player.openCard();
	}

	private void startGame() {
		// 딜러와 플레이어 카드 리스트 초기화
		player.getPCardList().removeAll(player.getPCardList());
		dealer.getDCardList().removeAll(dealer.getDCardList());
		playerVO.setpBet(0);

		// 카드덱 생성
		cardDeck = new CardDeckImpl();

		// 플레이어 배팅
		player.betting(playerVO);

		// 딜러와 플레이어 카드 2장씩 받기
		dealer.getCard(cardDeck);
		player.getCard(cardDeck);

		dealer.getCard(cardDeck);
		player.getCard(cardDeck);
	}
	
	private Boolean checkBust() {
		// TODO 플레이중에 버스트 체크 및 결과 출력
		boolean result = false;
		if(player.sumPoint() > 21 || dealer.sumPoint() > 21) {
			rule.printResult(dealer.sumPoint(), player.sumPoint(), playerVO);
			result = true;
		}
		return result;
	}
	
	private void gameRule() {
		
		String str;
		
		System.out.println("Game Rule");
		System.out.println("*".repeat(50));
		str = scan.nextLine();
		System.out.println("▶ 블랙잭은 Dealer와 Player가 경기를 진행합니다");
		str = scan.nextLine();
		System.out.println("▶ 카드는 조커를 제외한 52장입니다");
		System.out.println();
		System.out.println("▶ 카드는 ♥, ♣, ◆, ♠ 무늬를 가진\n"  
							+ " A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K로 이루어져\n" 
							+ " 있습니다");
		str = scan.nextLine();
		System.out.println("▶ A는 1점, 2 ~ 10까지는 카드에 표시된 숫자를 점수로\n" 
							+ " J, Q, K는 10점으로 계산합니다");
		str = scan.nextLine();
		System.out.println("▶ 『Hit』는 Player가 한 장의 카드를 뽑는 것입니다.");
		str = scan.nextLine();
		System.out.println("▶ 『Stand』는 Player가 카드를 그만 뽑고 본인의 카드를\n" 
							+ " 오픈해서 게임의 결과를 확인합니다.");
		str = scan.nextLine();
		System.out.println("▶ 『Bust』는 카드의 합계가 21을 넘었을 때를 의미하며\n" 
							+ "『Bust』가 나오는 쪽은 게임에서 지게됩니다.");
		str = scan.nextLine();
		System.out.println("▶ 지금부터 게임의 규칙을 설명하겠습니다.");
		str = scan.nextLine();
		System.out.println("▶ 카드를 받기 전에 소지금 내에서 원하는 금액을 베팅합니다.");
		str = scan.nextLine();
		System.out.println("▶ Dealer와 Player는 순차적으로 카드를 하나씩 뽑아\n" 
							+ " 각자 2개의 카드를 소지합니다");
		str = scan.nextLine();
		System.out.println("▶ 딜러의 카드 1장을 공개합니다.");
		str = scan.nextLine();
		System.out.println("▶ Player는 Hit과 Stand 중 선택하게 됩니다.");
		str = scan.nextLine();
    
		System.out.println("▶ Player는 합이 21이 넘지 않는 내에서 본인의 선택에 따라\n" 
							+ " 얼마든지 카드를 추가로 뽑을 수 있습니다.");
		str = scan.nextLine();
		System.out.println("▶ Dealer는 처음에 뽑은 두 카드의 합계가 16점 이하이면\n" 
							+ " 반드시 한 장을 추가로 뽑아야 하고 17점 이상이면\n" 
							+ " 추가할 수 없습니다");
		str = scan.nextLine();
		System.out.println("▶ Dealer와 Player의 추가 뽑기가 완료되면 게임이 종료되고\n" 
							+ " 점수를 계산합니다.");
		str = scan.nextLine();
		System.out.println("▶ Dealer와 Player 중 소유한 카드의 합이 21에 제일 가까운 쪽이\n" 
							+ "승리합니다");
		str = scan.nextLine();
		System.out.println("▶ 21을 초과하는 쪽이 게임에서 지게 됩니다.");
		str = scan.nextLine();
		System.out.println("▶ 이제 게임을 즐겨주세요! 건투를 빕니다!");
	}
}
