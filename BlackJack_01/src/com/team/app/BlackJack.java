package com.team.app;

import com.team.app.service.GamePlay;
import com.team.app.service.impl.GamePlayV1;

public class BlackJack {

	public static void main(String[] args) {
		GamePlay gpV1 = new GamePlayV1();
		gpV1.selectMenu();
	}

}
