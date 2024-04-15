package ir.fardup.order.config;

import com.fardup.msutility.axon.RequestInfo;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;

@Component
@Slf4j
public class EventInterceptor implements MessageDispatchInterceptor<EventMessage<?>> {

    @Override
    public BiFunction<Integer, EventMessage<?>, EventMessage<?>> handle(
            List<? extends EventMessage<?>> messages) {
        return (index, event) -> {
            log.info("Publishing event: [{}].", event);
            if (event.getMetaData().get("requestInfo") != null)
                RequestInfo.setHeaders((HashMap<String, String>) event.getMetaData().get("requestInfo"));
            log.info("Publishing event: [{}].", RequestInfo.getHeader("PROCESS-UUID"));
            return event;
        };
    }
}
