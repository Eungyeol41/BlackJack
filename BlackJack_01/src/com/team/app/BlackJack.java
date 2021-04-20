package com.team.app;

import com.team.app.service.impl.GameImpl;
import com.team.app.service.impl.PlayerV2;

public class BlackJack {

	public static void main(String[] args) {
		
		GameImpl gameImpl = new GameImpl();
		gameImpl.playGame();

	}
}
