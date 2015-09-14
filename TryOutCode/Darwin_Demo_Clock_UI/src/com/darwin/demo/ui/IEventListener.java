package com.darwin.demo.ui;


public interface IEventListener {

	public void eventFired(IProcessEvent processEvent);

	String getID();

	public void setContext(ProcessContext context);

}
