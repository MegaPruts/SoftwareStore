package com.ff2.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XmlElement implements IXmlElement {
	private XmlDocument xmlDocument;
	private String tagName;
	private List<XmlAttribute> attributes = new ArrayList<XmlAttribute>();
	private List<IXmlElementContent> content = new ArrayList<IXmlElementContent>();

	public XmlElement(String tagName, XmlDocument xmlDocument) {
		this.tagName = tagName;
		this.xmlDocument = xmlDocument;
	}


	public String getNsTagName() {
		String prefix = getNamespace().getNamespacePrefix();
		return prefix == null || prefix.length() == 0 ? tagName : prefix + ":"
				+ tagName;
	}

	private Namespace getNamespace() {
		return xmlDocument.getNamespace();
	}

	public Iterator<IXmlElementContent> getContent() {
		return content.iterator();
	}

}
