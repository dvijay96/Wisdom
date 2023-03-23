package com.design.systems.loadbalancer;

import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class RoundRobinLoadBalancer extends LoadBalancer {
	Map<RequestType, Queue<Destination>> destinationsForRequest;

	@Override
	public Destination balanceLoad(Request request) {
		if (!destinationsForRequest.containsKey(request.getRequestType())) {
			Set<Destination> destinations = getDestinations(request);
			destinationsForRequest.put(request.getRequestType(), cTq(destinations));
		}
		Destination destination = destinationsForRequest.get(request.getRequestType()).poll();
		destinationsForRequest.get(request.getRequestType()).add(destination);
		return destination;
	}

	private Queue<Destination> cTq(Set<Destination> destinations) {
		return null;
	}

}
