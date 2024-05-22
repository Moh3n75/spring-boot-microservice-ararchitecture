//package ir.fardup.product.config;
//
//import com.fardup.msutility.axon.RequestInfo;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.axonframework.commandhandling.CommandMessage;
//import org.axonframework.messaging.MessageDispatchInterceptor;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Map;
//import java.util.function.BiFunction;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class CommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> /*MessageHandlerInterceptor<CommandMessage<?>>*/ {
//
//    private final HttpServletRequest httpServletRequest;
//
//    @Override
//    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {
//        return (index, command) -> {
//            log.info("Dispatching a command {}.", command);
//            if (RequestInfo.getHeaderNames().isEmpty()) {
//                RequestInfo.setHeaders(httpServletRequest);
//            }
//            return command
//                    .andMetaData(Map.of("processUUID", RequestInfo.getHeader("PROCESS-UUID")))
//                    .andMetaData(Map.of("requestInfo", RequestInfo.getHeaders()));
//        };
//    }
//
//    /*@Override
//    public Object handle(@NotNull UnitOfWork<? extends CommandMessage<?>> unitOfWork, @NotNull InterceptorChain interceptorChain) throws Exception {
//        unitOfWork.transformMessage(message ->
//                message.andMetaData(Map.of("httpServletRequest", RequestInfo.getRequest())));
//        return interceptorChain.proceed();
//    }*/
//}
