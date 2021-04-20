package com.team.app.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.team.app.model.CardVO;
import com.team.app.service.CardDeck;
import com.team.app.service.Gamer;

public class PlayerV2 implements Gamer {

	protected List<CardVO> pCardList;

	protected CardDeck cardDeck;
	protected Scanner scan;
	protected Dealer dealer;

	protected Integer pMoney;
	protected Integer intBet;

	public PlayerV2() {

		pCardList = new ArrayList<CardVO>();


		cardDeck = new CardDeckImpl();
		scan = new Scanner(System.in);
		
		this.money();

	}


	public CardVO overLap(CardVO cardVO) {
		// 중복검사 method

		int i = 0;
		int j = 0;

		int pSize = pCardList.size();
		int dSize = dealer.dCardList.size();

		for (j = 0; j < pSize; j++) {
			if (cardVO.getCardPattern().equals(pCardList.get(j).getCardPattern())) {
				if (cardVO.getCardNumber().equals(pCardList.get(j).getCardNumber())) {
					break;
				}
			}
		}
		for (i = 0; i < dSize; i++) {
			if (cardVO.getCardPattern().equals(dealer.dCardList.get(i).getCardPattern())) {
				if (cardVO.getCardNumber().equals(dealer.dCardList.get(i).getCardNumber())) {
					break;
				}
			}
		}
		if (j < pSize || i < dSize) {
			return null;
		}

		return cardVO;
	}

	public Integer burst(int score) {

		if (score > 21) {
			System.out.println("합계가 21을 초과하였습니다!!");
			return score;
		}
		return null;
	}

	@Override
	public void getCard() {
		// 카드 1장 뽑기

		CardVO cardVO = new CardVO();

		while (true) {
			cardVO = cardDeck.hit();
			this.overLap(cardVO);
			if (this.overLap(cardVO) == null) {
				continue;
			}
			break;
		}
		pCardList.add(cardVO);
	}

	@Override
	public Integer openCard() {
		// Enter를 누르면 카드 1장을 뽑고
		// sumPoint() method를 이용해 점수 저장

		CardVO cardVO = new CardVO();
		int pSize = pCardList.size();
		Integer pSum = null;

		for (int i = 0; i < pSize; i++) {
			cardVO = pCardList.get(i);
			pSum = this.sumPoint();
			System.out.println("플레이어의 카드 : " + cardVO.getCardPattern() + "\t" + cardVO.getCardNumber());

		}

		System.out.println("-".repeat(50));
		System.out.println("카드 점수 : " + pSum);
		System.out.println("-".repeat(50));

		return pSum;

	}

	public Integer pSelect() {
		// 플레이어가 카드를 2장(이상) 뽑았을때 hit할것인지 stand할 것인지 ?

		while (true) {

			System.out.println("히트 하시겠습니까?");
			System.out.println("Hit : H // Stand : S");
			System.out.print(">> ");
			String hit = scan.nextLine();
			System.out.println("-".repeat(50));
			if (hit.equalsIgnoreCase("H")) {
				return 0;
			} else if (hit.equalsIgnoreCase("S")) {
				return null;
			} else {
				System.out.println("H 또는 S만 입력하세요 !!!");
				continue;
			}
		}

	}

	@Override
	public Integer sumPoint() {
		// 뽑은 카드의 점수계산 method
		// vo.getCardNumber에 들어있는 문자열을 정수형으로 변환하여 score에 저장 후
		// pSum 변수에 저장
		// vo.getCardNumber에 있는 문자열이 알파벳일 경우 조건문을 이용하여
		// pSum 변수에 알맞은 점수를 저장하고 continue

		int pSum = 0;
		int pSize = pCardList.size();

		for (int i = 0; i < pSize; i++) {
			CardVO vo = pCardList.get(i);
			if (vo.getCardNumber().equals("K") || vo.getCardNumber().equals("Q") || vo.getCardNumber().equals("J")) {
				pSum += 10;
				continue;
			} else if (vo.getCardNumber().equals("A")) {
				pSum++;
				continue;
			}
			Integer score = Integer.valueOf(vo.getCardNumber());
			pSum += score;
		}

		return pSum;
	}
	
	// 베팅
	
	public Integer getpMoney() {
		return pMoney;
	}

	public void setpMoney(Integer pMoney) {
		this.pMoney = pMoney;
	}

	public Integer getIntBet() {
		return intBet;
	}

	public void setIntBet(Integer intBet) {
		this.intBet = intBet;
	}
	

	public void money() {
		// 돈이 0원이거나 처음 시작하는 경우 10000원 지급

		if (pMoney == null || pMoney == 0) {
			pMoney = 10000;
		}

	}

	public void betting() {


		while (true) {

			System.out.println("베팅할 액수를 입력해주세요");
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
			}
			pMoney -= intBet;
			break;
		}

		System.out.println("베팅 금액 : " + intBet);
		System.out.println("소지 금액 : " + pMoney);

	}

	public void saveMoney() {

		FileWriter fileW;
		PrintWriter out;

		System.out.println("저장할 파일 이름을 입력하세요");
		System.out.print(">> ");
		String strName = scan.nextLine();

		String fileName = "src/com/team/app/" + strName + ".txt";

		try {
			fileW = new FileWriter(fileName);
			out = new PrintWriter(fileW);

			out.print(pMoney);

			out.flush();
			out.close();

			System.out.println("저장 완료 !");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadMoney() {

		FileReader fileR;
		BufferedReader buffer;

		System.out.println("불러올 파일 이름을 입력하세요");
		System.out.print(">> ");
		String strName = scan.nextLine();

		String fileName = "src/com/team/app/" + strName + ".txt";

		try {
			fileR = new FileReader(fileName);
			buffer = new BufferedReader(fileR);

			String reader = buffer.readLine();

			pMoney = Integer.valueOf(reader);

			buffer.close();

			System.out.println(fileName + " 파일을 불러왔습니다");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
