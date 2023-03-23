package com.design.systems.loadbalancer;

import java.util.Set;

public class Service {

	String name;
    Set<Destination> destinations;

    public void addDestination(Destination destination){
        destinations.add(destination);
    }

    public void removeDestination(Destination destination){
        destinations.remove(destination);
    }
}
