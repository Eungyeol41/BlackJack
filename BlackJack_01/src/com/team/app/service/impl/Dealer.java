package com.team.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.team.app.model.CardVO;
import com.team.app.service.Gamer;

public class Dealer implements Gamer {

	List<CardVO> dCardList;
	
	public Dealer() {
		dCardList = new ArrayList<CardVO>();
	}
	
	@Override
	public void getCard() {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer openCard() {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public Integer sumPoint() {
		// TODO Auto-generated method stub
		return null;
	}

}
