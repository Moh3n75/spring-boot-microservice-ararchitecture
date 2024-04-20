package ir.fardup.order.config;

import com.fardup.msutility.axon.RequestInfo;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;

@Component
@Slf4j
public class EventInterceptor implements MessageHandlerInterceptor<EventMessage<?>> {

    /*@Override
    public BiFunction<Integer, EventMessage<?>, EventMessage<?>> handle(
            List<? extends EventMessage<?>> messages) {
        return (index, event) -> {
            log.info("Publishing event: [{}].", event);
            log.info("Publishing event type: [{}].", event.getPayloadType());
            if (event.getMetaData().get("requestInfo") != null)
                RequestInfo.setHeaders((HashMap<String, String>) event.getMetaData().get("requestInfo"));
            log.info("Publishing event: [{}].", RequestInfo.getHeader("PROCESS-UUID"));
            return event;
        };
    }*/

    @Override
    public Object handle(UnitOfWork<? extends EventMessage<?>> unitOfWork,
                         InterceptorChain interceptorChain) throws Exception {
        EventMessage<?> event = unitOfWork.getMessage();
        if (event.getMetaData().get("requestInfo") != null)
            RequestInfo.setHeaders((HashMap<String, String>) event.getMetaData().get("requestInfo"));
        return interceptorChain.proceed();
    }
}
