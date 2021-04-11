package plwtz.temp.service.impl;

import org.osgi.service.component.annotations.Component;

import plwtz.temp.service.Temp;


@Component
public class TempService implements Temp {

	@Override
	public String read() {
		return "25.3";
	}

}
