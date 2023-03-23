package com.design.systems.loadbalancer;

public class Destination {
	private String ipAddress;
	private int requestsBeingServed;
	private int threshold;

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getRequestsBeingServed() {
		return requestsBeingServed;
	}

	public void setRequestsBeingServed(int requestsBeingServed) {
		this.requestsBeingServed = requestsBeingServed;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public boolean acceptRequest(Request request) {
		if (threshold <= requestsBeingServed) {
			requestsBeingServed++;
			return true;
		} else {
			return false;
		}
	}

	protected void completeRequest() {
		requestsBeingServed--;
	}
}
