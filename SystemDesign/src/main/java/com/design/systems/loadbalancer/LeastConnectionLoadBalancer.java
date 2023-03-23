package com.design.systems.loadbalancer;

import java.util.Comparator;

public class LeastConnectionLoadBalancer extends LoadBalancer {

	@Override
	public Destination balanceLoad(Request request) {
		return getDestinations(request).stream().min(Comparator.comparingInt(d -> d.getRequestsBeingServed()))
				.orElseThrow(null);
	}

}
