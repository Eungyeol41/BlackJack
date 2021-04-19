package com.team.app.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Betting {

	protected Scanner scan;
	protected Integer pMoney;
	protected Integer intBet;

	public Betting() {

		scan = new Scanner(System.in);

		this.money();
		
	}

	public void money() {
		// 돈이 0원이거나 처음 시작하는 경우 10000원 지급

		if (pMoney == null || pMoney == 0) {
			pMoney = 10000;
		}
		
	}

	public void betting() {

		intBet = null;
		
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
