package com.design.systems.loadbalancer;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RoutedLoadBalancer extends LoadBalancer {

	@Override
	Destination balanceLoad(Request request) {
		Set<Destination> destinations = getDestinations(request);
		List<Destination> list = destinations.stream().collect(Collectors.toList());
		return list.get(request.getId().hashCode() % list.size());
	}

}
