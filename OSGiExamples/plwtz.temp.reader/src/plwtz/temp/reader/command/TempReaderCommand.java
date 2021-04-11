package plwtz.temp.reader.command;

import org.apache.felix.service.command.CommandProcessor;
import org.osgi.service.component.annotations.*;

import plwtz.temp.service.Temp;

//@GogoCommand
@Component(service = TempReaderCommand.class, property = { CommandProcessor.COMMAND_SCOPE + "=" + "plwtz",
		CommandProcessor.COMMAND_FUNCTION + "=" + "temp" })
public class TempReaderCommand {

	@Reference
	private Temp tempService;

//	@Reference
//	public void set(Temp tempService) {
//		this.tempService = tempService;
//	}

	public String temp() {
		return tempService == null ? "0.0" : tempService.read();
	}
}
