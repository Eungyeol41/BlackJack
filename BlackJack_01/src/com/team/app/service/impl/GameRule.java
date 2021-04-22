package com.team.app.service.impl;

import java.util.Scanner;

public class GameRule {

	public void gameRule(){
		
		Scanner scan = new Scanner(System.in);
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
//		System.out.println("▶ Player는 카드 확인 후 hit또는 stand를 선택 할 수 있습니다. ");
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
