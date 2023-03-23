package com.design.systems.loadbalancer;

import java.util.Map;
import java.util.Set;

public abstract class LoadBalancer {

	Map<RequestType, Service> serviceMap;

	public void register(RequestType requestType, Service service) {
		serviceMap.put(requestType, service);
	}

	public Set<Destination> getDestinations(Request request) {
		Service service = serviceMap.get(request.getRequestType());
		return service.destinations;
	}

	abstract Destination balanceLoad(Request request);
}
