package plwtz.osgi.ds.inverter.impl;

import org.osgi.service.component.annotations.Component;

import plwtz.osgi.ds.inverter.StringInverter;

@Component
public class StringInverterImpl implements StringInverter {

	@Override
	public String invert(String inputString) {
		return new StringBuffer(inputString).reverse().toString();
	}

}
