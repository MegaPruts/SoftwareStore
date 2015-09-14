package com.darwin.demo.ui;


public abstract class BaseComponent implements IBaseComponent {
	// private static Log log = Log.getLog(BaseComponent.class);

	public String getID() {
		final String METHOD_SIGNATURE = "getID()";
		// log.enteringMethod(METHOD_SIGNATURE);
		String retValue = getClass().getName();
		// log.leavingMethod(METHOD_SIGNATURE);
		return retValue;
	}
}
