package com.darwin.demo.ui;


public abstract class ProcessComponent extends BaseComponent {
	// private static Log log = Log.getLog(ProcessComponent.class);

	protected ProcessContext context;

	public void setContext(ProcessContext context) {
		// final String METHOD_SIGNATURE = "setContext(ProcessContext context)";
		// log.enteringMethod(METHOD_SIGNATURE);
		this.context = context;
		// log.leavingMethod(METHOD_SIGNATURE);
	}

}
