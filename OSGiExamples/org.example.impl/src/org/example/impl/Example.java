package org.example.impl;

import org.example.api.Greeting;
import org.osgi.service.component.annotations.Component;

@Component
public class Example implements Greeting {

	@Override
	public String sayHello(String name) {
		return "Hello " + name;
	}

}
