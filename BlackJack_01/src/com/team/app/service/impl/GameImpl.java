package com.team.app.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.team.app.model.PlayerVO;
import com.team.app.service.CardDeck;
import com.team.app.service.Dealer;
import com.team.app.service.Player;

public class GameImpl implements com.team.app.service.Game {

	protected Dealer dealer;
	protected Player player;
	protected PlayerVO playerVO;

	protected Scanner scan;
	protected CardDeck cardDeck;

	public GameImpl() {
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
			System.out.println("메뉴는 1 ~ 5, Exit만 입력할 수 있습니다.");
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
			this.loadMoney();
		} else if (intInput == 3) {
			this.charge();
		} else if (intInput == 4) {
			this.saveMoney();
		} else if (intInput == 5) {
			this.gameRule();
		}
	}
	
	private void playGame() {
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
			// 딜러가 버스트라면 return
			if (checkBust()) {
				return;
			}
		}
		this.printResult();
	}

	private void dealerPlay() {
		// 그다음 딜러가 16점 이하이면 카드 한장 받기
		if (dealer.sumPoint() <= 16) {
			System.out.println("-".repeat(50));
			System.out.println("딜러가 한장을 가져갑니다.");
			dealer.getCard(cardDeck);
			this.open();
		}
	}

	private Boolean checkBust() {
		// TODO 플레이중에 버스트 체크 및 결과 출력
		boolean result = false;
		if (player.sumPoint() > 21 || dealer.sumPoint() > 21) {
			this.printResult();
			result = true;
		}
		return result;
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
		this.betting();

		// 딜러와 플레이어 카드 2장씩 받기
		dealer.getCard(cardDeck);
		player.getCard(cardDeck);

		dealer.getCard(cardDeck);
		player.getCard(cardDeck);
	}

	private void betting() {

		Integer intBet = null;
		Integer pMoney = playerVO.getpMoney();

		while (true) {
			System.out.println("-".repeat(50));
			System.out.println("베팅할 액수를 입력해주세요");
			System.out.println("소지 금액 : " + pMoney);
			System.out.print(">> ");
			String strBet = scan.nextLine();

			try {
				intBet = Integer.valueOf(strBet);
			} catch (NumberFormatException e) {
				System.out.println("정수만 입력하세요 !!");
				continue;
			}

			if (intBet > pMoney) {
				System.out.println("입력한 액수가 소지금액보다 큽니다 !!");
				continue;
			} else if (intBet < 0) {
				System.out.println("0보다 큰 액수를 입력하세요 !!");
				continue;
			}

			pMoney -= intBet;
			playerVO.setpBet(intBet);
			playerVO.setpMoney(pMoney);
			break;
		}
		System.out.println("-".repeat(50));
		System.out.println("베팅 금액 : " + intBet);
		System.out.println("소지 금액 : " + pMoney);
	}
	
	private void loadMoney() {
		// TODO 2. 불러오기
		
		Integer pMoney = 0;
		
		while (true) {
			System.out.println("불러올 유저의 이름을 입력하세요");
			System.out.println("메뉴로 돌아가기 : QUIT");
			System.out.print(">> ");
			String strName = scan.nextLine();
			if (strName.equals("")) {
				System.out.println("유저의 이름은 꼭 입력하세요 !!!");
				continue;
			}
			if (strName.equals("QUIT")) return;

			String fileName = "src/com/team/app/" + strName + ".txt";

			FileReader fileR;
			BufferedReader buffer;

			try {
				fileR = new FileReader(fileName);
				buffer = new BufferedReader(fileR);

				String reader = buffer.readLine();
				pMoney = Integer.valueOf(reader);

				buffer.close();
				
				playerVO.setpMoney(pMoney);

				System.out.println(strName + " 유저의 정보를 불러왔습니다");
				System.out.println("소지 금액 : " + pMoney);
				return;

			} catch (FileNotFoundException e) {
				System.out.println("파일을 찾을 수 없습니다 !!");
				continue;
			} catch (IOException e) {
				System.out.println("파일을 읽을 수 없습니다 !!");
			}
		}
	}
	
	private void charge() {
		// TODO 3. 충전하기
		if (playerVO.getpMoney() == null) {
			System.out.println("새로 게임을 시작하면 10000원이 자동으로 충전됩니다");
			return;
		}
		if (playerVO.getpMoney() != 0) {
			System.out.println("잔액이 0원인 경우만 충전이 가능합니다 !!");
			return;
		}
		System.out.println("10000원이 충전되었습니다 !");
		playerVO.setpMoney(10000);
	}

	private void saveMoney() {
		// TODO 4. 저장하기
		FileWriter fileW;
		PrintWriter out;
		
		Integer pMoney = playerVO.getpMoney();

		while (pMoney != null) {
			System.out.println("소지 금액 " + pMoney + " 원을 저장합니다");
			System.out.println("저장할 유저의 이름을 입력하세요");
			System.out.println("메뉴로 돌아가기 : QUIT");
			System.out.print(">> ");
			String strName = scan.nextLine();
			if (strName.equals("")) {
				System.out.println("유저의 이름은 꼭 입력하세요 !!!");
				continue;
			}
			if (strName.equals("QUIT")) return;
			
			String fileName = "src/com/team/app/" + strName + ".txt";

			try {
				fileW = new FileWriter(fileName);
				out = new PrintWriter(fileW);

				out.print(pMoney);

				out.flush();
				out.close();

				System.out.println("저장 완료 !");
				return;

			} catch (IOException e) {
				System.out.println("파일 생성 오류 !!");
			}
		}
		System.out.println("저장할 데이터가 없습니다");
		System.out.println("게임을 플레이해주세요");
	}
	

	private void printResult() {
		// TODO 승패를 판별하고 결과 출력
		int dPoint = dealer.sumPoint();
		int pPoint = player.sumPoint();

		System.out.println("=".repeat(50));
		System.out.println("::게임 결과::");
		System.out.println("딜러 점수 : " + dPoint);
		System.out.println("플레이어 점수 : " + pPoint);
		System.out.println("");

		// dealer burst 승리
		if (dPoint > 21) {
			System.out.println("!!! 이겼습니다 !!!");
			System.out.println("Dealer Bust");
			this.calculateResult("win");

			// player burst 패배
		} else if (pPoint > 21) {
			System.out.println("!!! 졌습니다 !!!");
			System.out.println("Player Bust");
			this.calculateResult("lose");

			// player 승리
		} else if (dPoint < pPoint) {
			System.out.println("!!! 이겼습니다 !!!");
			this.calculateResult("win");

			// player 패배
		} else if (dPoint > pPoint) {
			System.out.println("!!! 졌습니다 !!!");
			this.calculateResult("lose");

			// 무승부
		} else if (dPoint == pPoint) {
			System.out.println("!!! 무승부 !!!");
			this.calculateResult("draw");
		}
		System.out.println();
	}

	private void calculateResult(String result) {
		// TODO 결과에 따라 배팅금액 정산

		Integer pMoney = playerVO.getpMoney();
		Integer pBet = playerVO.getpBet();

		if (result.equals("win")) {
			pMoney += (pBet * 2);
			System.out.println((pBet * 2) + "원을 획득했습니다.");
			System.out.println("소지 금액 : " + pMoney);
		} else if (result.equals("lose")) {
			System.out.println(pBet + "원을 잃었습니다.");
			System.out.println("소지 금액 : " + pMoney);
		} else if (result.equals("draw")) {
			pMoney += pBet;
			System.out.println(pBet + "원을 돌려받았습니다");
			System.out.println("소지 금액 : " + pMoney);

		}
		playerVO.setpMoney(pMoney);
	}

	private void gameRule() {

		

		System.out.println("Game Rule");
		System.out.println("*".repeat(50));
		scan.nextLine();
		System.out.println("▶ 블랙잭은 Dealer와 Player가 경기를 진행합니다");
		scan.nextLine();
		System.out.println("▶ 카드는 조커를 제외한 52장입니다");
		System.out.println();
		System.out.println("▶ 카드는 ♥, ♣, ◆, ♠ 무늬를 가진\n" + " A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K로 이루어져\n" + " 있습니다");
		scan.nextLine();
		System.out.println("▶ A는 1점, 2 ~ 10까지는 카드에 표시된 숫자를 점수로\n" + " J, Q, K는 10점으로 계산합니다");
		scan.nextLine();
		System.out.println("▶ 『Hit』는 Player가 한 장의 카드를 뽑는 것입니다.");
		scan.nextLine();
		System.out.println("▶ 『Stand』는 Player가 카드를 그만 뽑고 본인의 카드를\n" + " 오픈해서 게임의 결과를 확인합니다.");
		scan.nextLine();
		System.out.println("▶ 『Bust』는 카드의 합계가 21을 넘었을 때를 의미하며\n" + "『Bust』가 나오는 쪽은 게임에서 지게됩니다.");
		scan.nextLine();
		System.out.println("▶ 지금부터 게임의 규칙을 설명하겠습니다.");
		scan.nextLine();
		System.out.println("▶ 카드를 받기 전에 소지금 내에서 원하는 금액을 베팅합니다.");
		scan.nextLine();
		System.out.println("▶ Dealer와 Player는 순차적으로 카드를 하나씩 뽑아\n" + " 각자 2개의 카드를 소지합니다");
		scan.nextLine();
		System.out.println("▶ 딜러의 카드 1장을 공개합니다.");
		scan.nextLine();
		System.out.println("▶ Player는 Hit과 Stand 중 선택하게 됩니다.");
		scan.nextLine();
		System.out.println("▶ Player는 합이 21이 넘지 않는 내에서 본인의 선택에 따라\n" + " 얼마든지 카드를 추가로 뽑을 수 있습니다.");
		scan.nextLine();
		System.out.println("▶ Dealer는 처음에 뽑은 두 카드의 합계가 16점 이하이면\n" + " 반드시 한 장을 추가로 뽑아야 하고 17점 이상이면\n" + " 추가할 수 없습니다");
		scan.nextLine();
		System.out.println("▶ Dealer와 Player의 추가 뽑기가 완료되면 게임이 종료되고\n" + " 점수를 계산합니다.");
		scan.nextLine();
		System.out.println("▶ Dealer와 Player 중 소유한 카드의 합이 21에 제일 가까운 쪽이\n" + "승리합니다");
		scan.nextLine();
		System.out.println("▶ 21을 초과하는 쪽이 게임에서 지게 됩니다.");
		scan.nextLine();
		System.out.println("▶ 이제 게임을 즐겨주세요! 건투를 빕니다!");
	}
}
