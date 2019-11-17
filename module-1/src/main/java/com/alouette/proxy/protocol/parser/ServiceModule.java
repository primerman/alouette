package com.alouette.proxy.protocol.parser;

import java.util.*;

/**
 * Created by zm on 2019/11/17.
 */
public class ServiceModule {
    String interfaceDesc;
    String version;
    Map<String, String> fieldsMap;
    Map<String, ArrayList<String>> methodsMap;

    public ServiceModule() {
        fieldsMap = new LinkedHashMap<String, String>();
        methodsMap = new LinkedHashMap<String, ArrayList<String>>();
    }

    public String getInterfaceDesc() {
        return interfaceDesc;
    }

    public void setInterfaceDesc(String interfaceDesc) {
        this.interfaceDesc = interfaceDesc;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, String> getFieldsMap() {
        return fieldsMap;
    }

    public Map<String, ArrayList<String>> getMethodsMap() {
        return methodsMap;
    }
}
