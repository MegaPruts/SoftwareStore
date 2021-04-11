package plwtz.osgi.ds.inverter.gogo.command;

import org.apache.felix.service.command.CommandProcessor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import plwtz.osgi.ds.inverter.StringInverter;

@Component(property = { CommandProcessor.COMMAND_SCOPE + "=" + "bnd.example",
		CommandProcessor.COMMAND_FUNCTION + "=" + "invert" }, service = InvertStringCommand.class)
public class InvertStringCommand {

	private StringInverter inverter;

	@Reference
	public void set(StringInverter inverter) {
		this.inverter = inverter;
	}

	public String invert(String inputString) {
		return inverter == null ? "No inverter available." : inverter.invert(inputString);
	}
}
