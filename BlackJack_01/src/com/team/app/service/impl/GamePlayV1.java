package com.team.app.service.impl;

import java.util.Scanner;

import com.team.app.service.GamePlay;

public class GamePlayV1 implements GamePlay {

	Scanner scan;
	
	public GamePlayV1() {
		scan = new Scanner(System.in);
	}
	
	@Override
	public void selectMenu() {
		// TODO Auto-generated method stub

		System.out.println("1. 게임시작");
		System.out.println("2. 게임설명");
		System.out.println("QUIT. 게임종료");
		System.out.print("메뉴선택 >> ");
		
		String menu = scan.nextLine();
		if(menu.equals("0")) {
			
		}
		
	}

	@Override
	public void printResult() {
		// TODO Auto-generated method stub

	}

}
