package ir.fardup.product.config;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.correlation.CorrelationDataProvider;
import org.axonframework.messaging.correlation.MessageOriginProvider;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class HttpRequestCorrelationDataProvider implements CorrelationDataProvider {


    @Override
    public Map<String, ?> correlationDataFor(Message<?> message) {
        Map<String, Object> correlationData = new HashMap<>();
        correlationData.put(MessageOriginProvider.getDefaultCorrelationKey(), message.getIdentifier());
        correlationData.put(MessageOriginProvider.getDefaultTraceKey(), message.getMetaData().getOrDefault(MessageOriginProvider.getDefaultTraceKey(), message.getIdentifier()));
        if (message instanceof CommandMessage<?>) {
            if (message.getMetaData().containsKey("processUUID")) {
                correlationData.put("processUUID", message.getMetaData().get("processUUID"));
                correlationData.put("requestInfo", message.getMetaData().get("requestInfo"));
            }
        }
        return correlationData;
    }
}
