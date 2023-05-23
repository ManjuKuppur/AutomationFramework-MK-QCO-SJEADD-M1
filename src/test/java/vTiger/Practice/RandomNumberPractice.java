package vTiger.Practice;

import java.util.Random;

public class RandomNumberPractice {

	public static void main(String[] args) {
		Random ran = new Random();
		int random = ran.nextInt(1000);
		System.out.println(random);

	}

}
