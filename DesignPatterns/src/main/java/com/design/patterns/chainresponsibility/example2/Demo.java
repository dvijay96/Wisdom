package com.design.patterns.chainresponsibility.example2;

import java.util.concurrent.ThreadLocalRandom;

public class Demo {

	public static void main(String[] args) {
		AuthenticationProcessor oauth = new OAuthProcessor();
		AuthenticationProcessor userPass = new UserNamePasswordProcessor();

		oauth.setNext(userPass);
//		userPass.setNext(oauth);     // creates cycle

		String[] authTypes = { "oauth", "userPass", "JWT", "basic", "temp" };

		ThreadLocalRandom random = ThreadLocalRandom.current();

		for (int i = 0; i < 5; i++) {
			int type = random.nextInt(0, authTypes.length);
			System.out.println(
					"Auth Type: " + authTypes[type] + " \t authorized? " + oauth.isAuthorized(authTypes[type]));
		}
	}

}
