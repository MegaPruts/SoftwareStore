package com.darwin.demo.ui;

public interface IEventProducer {

	String getID();

	void setContext(ProcessContext context);

	void clearContext();

}
