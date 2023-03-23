package com.design.systems.loadbalancer;

import java.util.HashSet;
import java.util.Set;

public class LoadBalancerFactory {

	private static Set<String> factorySet;

	static {
		factorySet = new HashSet<>();
		factorySet.add("round-robin");
		factorySet.add("least-connection");
		factorySet.add("routed-load");
	}

	public LoadBalancer createLoadBalancer(String loadBalancerType) {
		if (loadBalancerType == null || !factorySet.contains(loadBalancerType.toLowerCase())) {
			throw new IllegalArgumentException("No such Load Balancer present.");
		}
		loadBalancerType = loadBalancerType.toLowerCase();

		if (loadBalancerType.equals("round-robin")) {
			return new RoundRobinLoadBalancer();
		} else if (loadBalancerType.equals("least-connection")) {
			return new LeastConnectionLoadBalancer();
		} else {
			return new RoutedLoadBalancer();
		}
	}
}
