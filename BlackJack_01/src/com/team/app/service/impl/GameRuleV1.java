package com.team.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.team.app.model.CardVO;
import com.team.app.service.GameRule;

public class GameRuleV1 implements GameRule {

	protected List<CardVO> dCardList;
	protected List<CardVO> pCardList;
	protected Scanner scan;
	
	public GameRuleV1() {
		dCardList = new ArrayList<CardVO>();
		pCardList = new ArrayList<CardVO>();
		scan = new Scanner(System.in);
	}
	
	@Override
	public void getCard() {
		// TODO CardDeck에서 hit 호출해서 CardVO 리턴받기
		// VO리스트에 리턴값 추가

	}

	@Override
	public void openCard() {
		// TODO 딜러 1장, 게이머 2장 보여줌
		// 조건 생성 1. 딜러와 플레이어가 버스트인가?
		// 			2. 딜러가 16이하인가?
	}

	@Override
	public void dealerSelect() {
		// TODO 16이하면 hit하기 

	}

	@Override
	public void playerSelect() {
		// TODO hit할 것인가 stand 할 것인가 선택 받고 
		// hit하면 hit 하고, stand하면 종료

	}

	@Override
	public void burst() {
		// TODO Auto-generated method stub

	}

}
