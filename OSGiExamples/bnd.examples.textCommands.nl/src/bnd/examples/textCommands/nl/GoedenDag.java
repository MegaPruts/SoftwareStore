package bnd.examples.textCommands.nl;

import org.osgi.service.component.annotations.Component;

import bnd.example.textCommand.api.ExampleTextCommand;

@Component
public class GoedenDag implements ExampleTextCommand{

	@Override
	public String runCommand(String name) {
		return String.format("Goedenmiddag meneer/mevrouw %s,  hoe maakt u het ?", name);
	}

}
