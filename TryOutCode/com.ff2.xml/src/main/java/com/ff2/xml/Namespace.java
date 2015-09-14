package com.ff2.xml;

public class Namespace {
	private String prefix;
	private String uri;

	public Namespace(String namespace) {
		this(namespace, "");
	}

	public Namespace(String namespace, String prefix) {
		this.uri = namespace;
		this.prefix = prefix;
	}

	public String getNamespacePrefix() {
		return prefix == null ? "" : prefix;
	}

	public String getName() {
		return uri;
	}

	public String getNamespacePrefix(String appendix) {
		return getNamespacePrefix().length() > 0 ? getNamespacePrefix()
				+ appendix : getNamespacePrefix();
	}

	public String getQualifiedName(String itemName) {
		return getNamespacePrefix(":") + itemName;
	}

	/**
	 * xmlns{:<prefix>}="<uri>"
	 * 
	 * @return
	 */
	public String getDeclaration() {
		StringBuffer decl = new StringBuffer("xmlns");
		decl.append(prefix == null || prefix.length() == 0 ? "" : ":" + prefix);
		decl.append("=");
		decl.append("\"" + uri + "\"");
		return decl.toString();
	}
}
