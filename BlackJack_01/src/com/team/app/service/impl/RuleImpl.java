package com.team.app.service.impl;

import com.team.app.service.Rule;

public class RuleImpl implements Rule {

	@Override
	public void printResult(int dScore, int pScore) {
		// TODO Auto-generated method stub

		System.out.println("=".repeat(50));
		System.out.println("::Game Result::");
		System.out.println("Dealer Score : " + dScore);
		System.out.println("Player Score : " + pScore);
		System.out.println("");
		// player 승리
		if (dScore < pScore) {
			System.out.println("You're Winner!!!");
			
		// player 패배
		} else if (dScore > pScore) {

			System.out.println("You Lose!!!");

		// 무승부
		} else if (dScore == pScore) {
			System.out.println("Draw!!!");
		}
		System.out.println();
	}

}
