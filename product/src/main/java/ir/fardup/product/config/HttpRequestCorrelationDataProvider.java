package ir.fardup.product.config;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.correlation.CorrelationDataProvider;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class HttpRequestCorrelationDataProvider implements CorrelationDataProvider {


    @Override
    public Map<String, ?> correlationDataFor(Message<?> message) {
        Map<String, Object> correlationData = new HashMap<>();
        if (message instanceof CommandMessage<?>) {
            if (message.getMetaData().containsKey("processUUID")) {
                correlationData.put("processUUID", message.getMetaData().get("processUUID"));
            }
        }
        return correlationData;
    }
}
