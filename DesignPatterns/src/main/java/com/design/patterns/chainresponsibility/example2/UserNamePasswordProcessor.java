package com.design.patterns.chainresponsibility.example2;

public class UserNamePasswordProcessor extends AuthenticationProcessor {

	@Override
	public boolean isAuthorized(String authType) {
		if (authType != null && authType.equalsIgnoreCase("userPass"))
			return true;
		else if (next != null)
			next.isAuthorized(authType);
		return false;
	}

}
