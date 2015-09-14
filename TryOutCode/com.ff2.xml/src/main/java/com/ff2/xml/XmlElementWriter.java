package com.ff2.xml;

import java.io.PrintWriter;
import java.util.Iterator;

public class XmlElementWriter implements IXmlItemWriter {
	final static public void write(XmlElement xmlElement, PrintWriter writer) {
		new XmlElementWriter(xmlElement).write(writer);
	}

	private XmlElement xmlElement;

	public XmlElementWriter(XmlElement xmlElement) {
		this.xmlElement = xmlElement;
	}

	final public void write(PrintWriter writer) {
		writer.printf("<%s", xmlElement.getNsTagName());
		{
			Iterator<IXmlElementContent> iterator = xmlElement.getContent();
			boolean hasContent = iterator.hasNext();
			while (iterator.hasNext()) {
				IXmlElementContent xmlElementItem = iterator.next();
//				xmlElementItem.getWriter().write(writer);
			}
			if (!hasContent)
				writer.print(">");
		}
		writer.print(">");
		writer.printf("</%s>", xmlElement.getNsTagName());
	}
}
