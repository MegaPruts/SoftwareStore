package org.example.command;

import org.apache.felix.service.command.CommandProcessor;
import org.example.api.Greeting;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = GreetingCommand.class, property = { CommandProcessor.COMMAND_SCOPE + "=" + "example",
		CommandProcessor.COMMAND_FUNCTION + "=" + "greet" })
public class GreetingCommand {
	private Greeting greetingSvc;

	@Reference
	public void setGreeting(Greeting greetingSvc) {
		this.greetingSvc = greetingSvc;
	}

	public void greet(String name) {
		System.out.println(greetingSvc.sayHello(name));
	}
}
