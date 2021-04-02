package bnd.example.gogoCommands;

import org.apache.felix.service.command.CommandProcessor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import bnd.example.textCommand.api.ExampleTextCommand;

@Component(property = { CommandProcessor.COMMAND_SCOPE + "=bnd.example",
		CommandProcessor.COMMAND_FUNCTION + "=nowEnters" }, service = NowEnters.class)
public class NowEnters {

	private ExampleTextCommand commandService;

	@Reference
	public void set(ExampleTextCommand commandService) {
		this.commandService = commandService;
	}

	public void nowEnters(String name) {
		System.out.println(commandService.runCommand(name));
	}
}
