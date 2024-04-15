package com.fardup.order.config;

import com.thoughtworks.xstream.XStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XStreamConfig {

    @Bean
    public XStream xStream() {
        XStream xStream = new XStream();
        xStream.allowTypesByWildcard(new String[]{
                "ir.fardup.**",
                "com.fardup.**",
                "java.base.**",
                "java.util.**",
                "java.nio.**",
                "org.springframework.**"
        });
        return xStream;
    }
}
