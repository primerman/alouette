package com.alouette.proxy.protocol.parser;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by zm on 2019/11/17.
 */
public class ProtocolParserBuilderTest {


    @Test
    public void testParser() throws Exception {

        ProtocolParserBuilder builder = new ProtocolParserBuilder();
        Map<String,ServiceModule> map = builder.parse("protocol/service.xml");


    }



}