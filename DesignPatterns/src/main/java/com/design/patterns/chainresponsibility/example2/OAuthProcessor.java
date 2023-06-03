package com.design.patterns.chainresponsibility.example2;

public class OAuthProcessor extends AuthenticationProcessor {

	@Override
	public boolean isAuthorized(String authType) {
		if (authType != null && authType.equalsIgnoreCase("Oauth"))
			return true;
		else if (next != null)
			next.isAuthorized(authType);
		return false;
	}

}
