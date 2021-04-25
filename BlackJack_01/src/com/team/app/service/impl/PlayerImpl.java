package com.team.app.service.impl;

//import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.team.app.model.CardVO;
import com.team.app.model.PlayerVO;
import com.team.app.service.CardDeck;
import com.team.app.service.Player;

public class PlayerImpl implements Player {

	protected List<CardVO> pCardList;

	protected Scanner scan;
	protected CardVO cardVO;

	public PlayerImpl() {
		pCardList = new ArrayList<CardVO>();
		scan = new Scanner(System.in);
		cardVO = new CardVO();
	}

	public List<CardVO> getPCardList(){
		return this.pCardList;
	}
	
	@Override
	public void getCard(CardDeck cardDeck) {
		// 카드 1장 뽑기
		cardVO = cardDeck.hit();
		pCardList.add(cardVO);
	}

	@Override
	public void openCard() {

		int pSize = pCardList.size();

		System.out.print("플레이어\t");

		for (int i = 0; i < pSize; i++) {
			if (i == 3) {
				System.out.printf("\n\t\t");
			} 
				cardVO = pCardList.get(i);
				System.out.printf("[ %s %2s ]  ", cardVO.getCardPattern(), cardVO.getCardNumber());
		}
		System.out.println();
	}

	@Override
	public Integer sumPoint() {
		// TODO player의 카드 점수 합산

		int pSum = 0;
		int pSize = pCardList.size();

		for (int i = 0; i < pSize; i++) {

			CardVO vo = pCardList.get(i);
			String number = vo.getCardNumber();

			if (number.equals("K") || number.equals("Q") || number.equals("J")) {
				pSum += 10;
				continue;
			} else if (number.equals("A")) {
				pSum++;
				continue;
			}
			Integer score = Integer.valueOf(number);
			pSum += score;
		}
		return pSum;
	}

	@Override	
	public Boolean pSelect() {

		while (true) {

			Integer pSum = this.sumPoint();
			System.out.println("-".repeat(50));
			System.out.println("카드 점수합 : " + pSum);
			System.out.println("-".repeat(50));

			System.out.println("히트나 스탠드를 선택해주세요");
			System.out.println("[ Hit : H ] [ Stand : S ]");
			System.out.print(">> ");
			String hit = scan.nextLine();
			if (hit.equalsIgnoreCase("H")) {
				return true;
			} else if (hit.equalsIgnoreCase("S")) {
				return false;
			}
			System.out.println("H 또는 S만 입력하세요 !!!");
			continue;
		}
	}

//	@Override
//	public void charge(PlayerVO playerVO) {
//		if (playerVO.getpMoney() == null) {
//			System.out.println("새로 게임을 시작하면 10000원이 자동으로 충전됩니다");
//			return;
//		}
//		if (playerVO.getpMoney() != 0) {
//			System.out.println("잔액이 0원인 경우만 충전이 가능합니다 !!");
//			return;
//		}
//		System.out.println("10000원이 충전되었습니다 !");
//		playerVO.setpMoney(10000);
//	}
	
//	@Override
//	public void betting(PlayerVO playerVO) {
//
//		Integer intBet = null;
//		Integer pMoney = playerVO.getpMoney();
//		
//		while (true) {
//			System.out.println("-".repeat(50));
//			System.out.println("베팅할 액수를 입력해주세요");
//			System.out.println("소지 금액 : " + pMoney);
//			System.out.print(">> ");
//			String strBet = scan.nextLine();
//			
//			try {
//				intBet = Integer.valueOf(strBet);
//			} catch (NumberFormatException e) {
//				System.out.println("정수만 입력하세요 !!");
//				continue;
//			}
//
//			if (intBet > pMoney) {
//				System.out.println("입력한 액수가 소지금액보다 큽니다 !!");
//				continue;
//			} else if (intBet < 0) {
//				System.out.println("0보다 큰 액수를 입력하세요 !!");
//				continue;
//			}
//			
//			pMoney -= intBet;
//			playerVO.setpBet(intBet);
//			playerVO.setpMoney(pMoney);
//			break;
//		}
//		System.out.println("-".repeat(50));
//		System.out.println("베팅 금액 : " + intBet);
//		System.out.println("소지 금액 : " + pMoney);
//	}
	
//	@Override
//	public void saveMoney(PlayerVO playerVO) {
//
//		FileWriter fileW;
//		PrintWriter out;
//		
//		Integer pMoney = playerVO.getpMoney();
//
//		while (pMoney != null) {
//			System.out.println("소지 금액 " + pMoney + " 원을 저장합니다");
//			System.out.println("저장할 유저의 이름을 입력하세요");
//			System.out.print(">> ");
//			String strName = scan.nextLine();
//			if (strName.equals("")) {
//				System.out.println("유저의 이름은 꼭 입력하세요 !!!");
//				continue;
//			}
//
//			String fileName = "src/com/team/app/" + strName + ".txt";
//
//			try {
//				fileW = new FileWriter(fileName);
//				out = new PrintWriter(fileW);
//
//				out.print(pMoney);
//
//				out.flush();
//				out.close();
//
//				System.out.println("저장 완료 !");
//				return;
//
//			} catch (IOException e) {
//				System.out.println("파일 생성 오류 !!");
//			}
//		}
//		System.out.println("저장할 데이터가 없습니다");
//		System.out.println("게임을 플레이해주세요");
//	}

//	@Override
//	public void loadMoney(PlayerVO playerVO) {
//
//		Integer pMoney = 0;
//		
//		while (true) {
//			System.out.println("불러올 유저의 이름을 입력하세요");
//			System.out.println("메뉴로 돌아가기 : QUIT");
//			System.out.print(">> ");
//			String strName = scan.nextLine();
//			if (strName.equals("")) {
//				System.out.println("유저의 이름은 꼭 입력하세요 !!!");
//				continue;
//			}
//			if (strName.equals("QUIT"))
//				return;
//
//			String fileName = "src/com/team/app/" + strName + ".txt";
//
//			FileReader fileR;
//			BufferedReader buffer;
//
//			try {
//				fileR = new FileReader(fileName);
//				buffer = new BufferedReader(fileR);
//
//				String reader = buffer.readLine();
//				pMoney = Integer.valueOf(reader);
//
//				buffer.close();
//				
//				playerVO.setpMoney(pMoney);
//
//				System.out.println(strName + " 유저의 정보를 불러왔습니다");
//				System.out.println("소지 금액 : " + pMoney);
//				return;
//
//			} catch (FileNotFoundException e) {
//				System.out.println("파일을 찾을 수 없습니다 !!");
//				continue;
//			} catch (IOException e) {
//				System.out.println("파일을 읽을 수 없습니다 !!");
//			}
//		}
//	}
}
