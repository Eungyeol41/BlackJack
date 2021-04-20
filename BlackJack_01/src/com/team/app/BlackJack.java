package com.team.app;

import com.team.app.service.impl.GameImpl;

public class BlackJack {

	public static void main(String[] args) {
		
		GameImpl gameImpl = new GameImpl();
		gameImpl.playGame();

	}
}
