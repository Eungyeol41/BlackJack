package com.team.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Teacher {

	public static void main(String[] args) {
		
		List<Integer> cardList = new ArrayList<Integer>();
		Random rnd = new Random();
		
		for(int i = 0 ; i < 50 ; i++) {
			cardList.add(i + 1);
		}
		
		
		while(true) {
			
			int nSize = cardList.size();
			if(nSize < 1) break;
			
			int index = rnd.nextInt( nSize );
			System.out.println(cardList.get(index));
			
			cardList.remove(index);
			
		}
		
		
		
		 
		
		
	}
	
	
}
