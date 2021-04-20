package com.team.app;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Frame extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private Image introBackground;
	private final int SCREEN_WIDTH = 1280;
	private final int SCREEN_HEIGHT = 720;

	public Frame() {

		setTitle("Black Jack");

		// 프레임 고정 true로 설정하면 움직일 수 있음
		setResizable(false);
		// 프레임 보이게 설정
		setVisible(true);
		// 사이즈 설정
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		// 가운데에서 실행하게 설정
		setLocationRelativeTo(null);
		// 프로그램을 종료하면 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		introBackground = new ImageIcon(BlackJack.class.getResource("../image/introBackground.jpg")).getImage();

	}

	public void paint(Graphics g) {
		// 프로그램 크기만큼 이미지 넣어주기
		screenImage = createImage(SCREEN_WIDTH, SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);

		// 스크린 이미지 만들기
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);
		this.repaint();
		// 게임이 끝날때까지 계속 이미지가 띄워지게 만들기
	}

}
