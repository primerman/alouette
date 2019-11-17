package com.alouette.proxy.protocol.parser;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathFactory;
import java.util.Map;

public class ProtocolParserBuilder {
    DocumentBuilderFactory documentBuilderFactory = null;
    XPathFactory xPathFactory = null;
    XPathProtocolParser xPathProtocolParser;

    public ProtocolParserBuilder() {
        xPathFactory = XPathFactory.newInstance();
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
    }

    public Map<String, ServiceModule> parse(String path) throws Exception {
        xPathProtocolParser = new XPathProtocolParser(xPathFactory, documentBuilderFactory);
        return xPathProtocolParser.parse(path);
    }
}
