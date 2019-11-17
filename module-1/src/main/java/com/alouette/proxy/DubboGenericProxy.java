package com.alouette.proxy;

import com.alouette.proxy.protocol.parser.ProtocolParserBuilder;
import com.alouette.proxy.protocol.parser.ServiceModule;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * 实现泛化调用机制,调用端无需上线即可实现接口动态调用
 * 功能:实现服务端服务暴露机制
 */
public class DubboGenericProxy  {

    public static void main(String... args) throws Exception {
        ProtocolParserBuilder builder = new ProtocolParserBuilder();
        Map<String,ServiceModule> map = builder.parse("protocol/service.xml");
    }

}
