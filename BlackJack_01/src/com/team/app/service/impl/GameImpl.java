package com.team.app.service.impl;

// 소연 : 문제발생! 게임을 한판하고 이겨서 얻은 돈이 유지가안됨.
// 			경우 1) 10000원 베팅후 이겨서 20000원이 된 후에
//					다시 1. 새게임 눌렀더니 10000원 초과된 금액이 전부 
//					소지금보다 크다고 나옴!
//			경우 2) 3000원 베팅후 져서 7000원이 된 후에
//					다시 1. 새게임 눌렀더니...10000원으로 돌아감...
//		-> 게임한판하고 다시 한판 더하고 싶을때마다 저장해야하나...?
//		 while문 넣어서 계속 더하거나 한판더하겠냐고 물어보는게 낫지않음?
//		-> 아니면 playGame() method에 매개변수 받아서
//			진짜 처음하는 경우랑 한판하고 메뉴로 돌아온 경우를 구별할수도..
// 	아니다 1. 새게임 2. 이어하기 3. 불러오기 4. 저장하기 5. 충전하기로 만들어서
//  게임하고 또하는 2. 이어하기와 / 파일불러오는 3. 불러오기 로 나누는 것도 좋을
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
		// TODO 블랙잭 메뉴 선택
		
		while (true) {
			System.out.println("=".repeat(50));
			System.out.println("BlackJack");
			System.out.println("=".repeat(50));
			System.out.println("1. 새게임");
			System.out.println("2. 이어하기");
			System.out.println("3. 충전하기");
			System.out.println("4. 저장하기");
			System.out.println("5. 게임 규칙 설명");
			System.out.println("Exit. 그만하기");
			System.out.println("-".repeat(50));
			System.out.print(">> ");
			String strInput = scan.nextLine();
			if (strInput.equals("Exit"))
				return;
			this.selectMenu(strInput);
		}//while end
	}
	
	private void selectMenu(String strInput) {
		Integer intInput = null;
		try {
			intInput = Integer.valueOf(strInput);
		} catch (NumberFormatException e) {
			System.out.println("메뉴나 Exit를 선택해주세요");
			return;
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
		}else if(intInput == 5) {
			
		}
	}

	@Override
	public void playGame() {
		// TODO 플레이어 딜러 게임 플레이

		// startGame으로 보냅니다
//		player.pCardList.removeAll(player.pCardList);
//		dealer.dCardList.removeAll(dealer.dCardList);

		System.out.println("게임을 시작합니다.");
//		player.betting();

		// 카드리스트 초기화, 배팅, 카드 받기
		this.startGame();
		// 딜러와 플레이어 카드 확인
		player.openCard();
		dealer.openCard();
		
		// 먼저 플레이어 선택 true이면 Hit하고 카드 확인
		if( player.pSelect() ) {
			player.getCard();
			this.open();
//			player.openCard();
//			dealer.openCard(); // 소연 : 딜러것도 보여주는게 좋을해서 추가함
		} // 영진 : @@@@@@@@@@@@@@ 이 코드 필요한거임???? @@@@@@@@@@@@@@@@
		// 소연: Hit하면 0리턴이고 Stand하면 null 리턴이라 판별하려고한듯
		//		근데 이거 그냥 코드만보면 1도모르겠다.. 
		//		좀더 알아보기쉽게 수정안되나?
	
		
		// 플레이어 버스트이면 결과보여주고 끝
		if( checkBurst(player) ) {
			// result
			rule.printResult(dealer.sumPoint(),player.sumPoint(),  player);
			return;
		}
		
		// 그다음 딜러가 16점 이하이면
		// 		카드 한장 받기
		if(dealer.sumPoint() < 16) {
			System.out.println("딜러가 한장을 가져갑니다.");
			dealer.getCard();
		}
		// 딜러가 버스트라면 결과보여주고 끝
		if( checkBurst(dealer) ) {
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
		
		// 플레이어 선택이 	true이면 카드를 받고 
		// 		  			false라면 stand로 결과 출력
		while( player.pSelect() ) { // 여기 null에서 1로 바꾸면 시작하자마자 S했을때 오류뜨던거 해결
			player.getCard();
			this.open();
//			player.openCard();
//			dealer.openCard(); // 소연 : 딜러것도 보여주는게 좋을해서 추가함
			
			// 플레이어가 버스트라면 break하고 결과출력
			if(checkBurst(player)) break;
		} // while end (player)
		rule.printResult(dealer.sumPoint(),player.sumPoint(), player);
//		}
	}
	
	private void open() {
		// 딜러와 플레이어 카드 출력
		player.openCard();
		dealer.openCard();
	}

	private void startGame() {
		// 딜러와 플레이어 카드 리스트 초기화
		player.pCardList.removeAll(player.pCardList);
		dealer.dCardList.removeAll(dealer.dCardList);
		
		// 플레이어 배팅
		player.betting();
		
		// 딜러와 플레이어 카드 2장씩 받기
		dealer.getCard();
		player.getCard();
		
		dealer.getCard();
		player.getCard();
	}

	@Override
	public Boolean checkBurst(Gamer player1) {
		// TODO 플레이중에 버스트 체크
		boolean result = false;

		if (player1.sumPoint() > 21) {
			System.out.println("Burst!");
			result = true;
		}

		return result;
	}

}
