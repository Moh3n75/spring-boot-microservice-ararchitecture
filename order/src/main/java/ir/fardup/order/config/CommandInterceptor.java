package ir.fardup.order.config;

import com.fardup.msutility.axon.RequestInfo;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

@Slf4j
@Component
public class CommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> /*MessageHandlerInterceptor<CommandMessage<?>>*/ {


    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {
            log.info("Dispatching a command {}.", command);
            return command;
        };
    }

    /*@Override
    public Object handle(@NotNull UnitOfWork<? extends CommandMessage<?>> unitOfWork, @NotNull InterceptorChain interceptorChain) throws Exception {
        unitOfWork.transformMessage(message ->
                message.andMetaData(Map.of("httpServletRequest", RequestInfo.getRequest())));
        return interceptorChain.proceed();
    }*/
}
