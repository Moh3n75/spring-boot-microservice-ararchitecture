package ir.fardup.product.config;

import com.fardup.msutility.axon.RequestInfo;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class CommandInterceptor implements MessageHandlerInterceptor<CommandMessage<?>> {

    @Override
    public Object handle(@NotNull UnitOfWork<? extends CommandMessage<?>> unitOfWork, @NotNull InterceptorChain interceptorChain) throws Exception {
        unitOfWork.transformMessage(message ->
                message.andMetaData(Map.of("httpServletRequest", RequestInfo.getRequest())));
        return interceptorChain.proceed();
    }
}
