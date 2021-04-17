package com.team.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.team.app.model.CardVO;
import com.team.app.service.Gamer;

public class Player implements Gamer {

	List<CardVO> pCardList;
	
	public Player() {
		pCardList = new ArrayList<CardVO>();
	}
			
	@Override
	public void getCard() {
		// TODO Auto-generated method stub

	}

	@Override
	public void openCard() {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer sumPoint() {
		// TODO Auto-generated method stub
		return null;
	}

}
