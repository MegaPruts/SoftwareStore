package bnd.examples.textCommands.en;

import org.osgi.service.component.annotations.Component;

import bnd.example.textCommand.api.ExampleTextCommand;

@Component
public class GoodDay implements ExampleTextCommand{

	@Override
	public String runCommand(String name) {
		return String.format("Good afternoon sir/madam %s,  how are you doing ?", name);
	}

}
