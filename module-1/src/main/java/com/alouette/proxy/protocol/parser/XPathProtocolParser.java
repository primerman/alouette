package com.alouette.proxy.protocol.parser;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XPathProtocolParser {
    private String rootNodeName = "service";
    private String interfaceAttrName = "interface";
    private String versionAttrName = "version";
    private String fieldName = "name";
    private String fieldType = "type";
    private String methodName = "name";
    private String paramTypeName = "type";
    DocumentBuilder builder;
    XPath xpath;

    public XPathProtocolParser(XPathFactory xPathFactory, DocumentBuilderFactory documentBuilderFactory) throws ParserConfigurationException {
        builder = documentBuilderFactory.newDocumentBuilder();
        xpath = xPathFactory.newXPath();

    }

    /**
     * @param path 协议路径 com/alouette/proxy/protocol/service.xml
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public Map<String, ServiceModule> parse(String path) throws Exception {
        Document document = builder.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(path));
        Map<String, ServiceModule> map = new HashMap<String, ServiceModule>();
        ServiceModule serviceModule = new ServiceModule();
        serviceModule.setInterfaceDesc(document.getDocumentElement().getAttribute(interfaceAttrName));
        serviceModule.setVersion(document.getDocumentElement().getAttribute(versionAttrName));
        NodeList nodes = (NodeList) evaluate("/service/fields/field", document, XPathConstants.NODESET);
        if (nodes != null && nodes.getLength() > 0) {
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                serviceModule.getFieldsMap().put(node.getAttributes().getNamedItem(fieldName).getNodeValue(), node.getAttributes().getNamedItem(fieldType).getNodeValue());
            }
        }
        NodeList paramTypes = (NodeList) evaluate("/service/methods/method/paramTypes", document, XPathConstants.NODESET);
        NodeList paramTypeNodeList = (NodeList) evaluate("/service/methods/method/paramTypes/paramType", document, XPathConstants.NODESET);

        if (paramTypes != null && paramTypes.getLength() > 0) {
            for (int i = 0; i < paramTypes.getLength(); i++) {
                Node paramTypesNode = paramTypes.item(i);
                String methodNameAttr = paramTypesNode.getParentNode().getAttributes().getNamedItem(methodName).getNodeValue();
                ArrayList<String> paramTypeList = new ArrayList<String>();
                if(paramTypeNodeList!=null){
                    for (int j = 0; j < paramTypeNodeList.getLength(); j++) {
                        if(paramTypeNodeList.item(j).getParentNode()==paramTypesNode){
                            paramTypeList.add(paramTypeNodeList.item(j).getAttributes().getNamedItem(paramTypeName).getNodeValue());
                        }
                    }
                }
                serviceModule.getMethodsMap().put(methodNameAttr, paramTypeList);
            }
        }
        map.put(String.format("%s_%s", serviceModule.getInterfaceDesc(), serviceModule.getVersion()), serviceModule);
        return map;
    }

    private Object evaluate(String expression, Object root, QName returnType) throws Exception {
        try {
            return xpath.evaluate(expression, root, returnType);
        } catch (Exception e) {
            throw e;
        }
    }

}
