package com.design.patterns.chainresponsibility.example2;

public abstract class AuthenticationProcessor {

	protected AuthenticationProcessor next;

	public void setNext(AuthenticationProcessor next) {
		this.next = next;
	}

	public abstract boolean isAuthorized(String authType);
}
