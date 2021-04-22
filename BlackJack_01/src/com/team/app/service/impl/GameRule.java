package com.team.app.service.impl;

import java.util.Scanner;

public class GameRule {

	public void gameRule(){
		
		Scanner scan = new Scanner(System.in);
		String str;
		
		System.out.println("Game Rule");
		System.out.println("*".repeat(50));
		str = scan.nextLine();
		System.out.println("블랙잭은 Dealer와 Player가 경기를 진행합니다");
		str = scan.nextLine();
		System.out.println("카드는 조커를 제외한 52장입니다");
		System.out.println("카드는 ♥, ♣, ◆, ♠ 무늬를 가진 A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K로 이루어져 있습니다");
		str = scan.nextLine();
		System.out.println("A는 1점, 2 ~ 10까지는 카드에 나타난 숫자를 점수로 하며 J, Q, K는 10점으로 계산합니다");
		str = scan.nextLine();
		System.out.println("지금부터 게임에 사용되는 용어를 설명해드리겠습니다.");
		System.out.println();
		System.out.println("Hit은 Player가 원할 때 한 장의 카드를 뽑는 것입니다.");
		System.out.println("Stand는 Player가 카드를 그만 뽑고 본인의 카드를 오픈해서 게임의 결과를 확인하는 것입니다.");
		System.out.println("Burst는 카드의 합계가 21을 넘었을 때를 의미하며 Burst가 나오는 쪽은 게임에서 지게됩니다.");
		str = scan.nextLine();
		System.out.println("카드를 받기 전에 소지금 내에서 베팅할 금액을 선택합니다.");
		str = scan.nextLine();
		System.out.println("Dealer와 Player는 순차적으로 카드를 하나씩 뽑아 각자 2개의 카드를 소지합니다");
		str = scan.nextLine();
		System.out.println("딜러의 카드 1장을 공개합니다.");
		str = scan.nextLine();
		System.out.println("Player는 합이 21이 넘지 않는 내에서 본인의 선택에 따라 얼마든지 카드를 추가로 뽑을 수 있습니다.");
		System.out.println("Player는 Hit과 Stand 중 선택하게 됩니다.");
		str = scan.nextLine();
		System.out.println("Dealer는 처음에 뽑은 두 카드의 합계가 16점 이하이면 반드시 한 장을 추가로 뽑아야 하고 17점 이상이면 추가할 수 없습니다");
		str = scan.nextLine();
		System.out.println("Dealer와 Player의 추가 뽑기가 완료되면 게임이 종료되고 점수를 계산합니다.");
		str = scan.nextLine();
		System.out.println("Dealer와 Player 중 소유한 카드의 합이 21에 제일 가까운 쪽이 승리합니다");
		str = scan.nextLine();
		System.out.println("뭔 말을 하고 싶었던 건데요./..... 언니가 뭔 말 할라 그랬쟈나요.... / ");
		System.out.println("21을 초과하면 무조건 초과한 쪽이 게임에서 지게 됩니다.");
		str = scan.nextLine();
		
		
	}
}
