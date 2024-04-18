package ir.fardup.product.config;


import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.PropagatingErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CommandBusConfiguration {


    /*@Autowired
    public void configure(EventProcessingConfigurer configurer) {
        configurer
                .usingSubscribingEventProcessors()
                .registerDefaultListenerInvocationErrorHandler(conf -> PropagatingErrorHandler.instance());
    }*/

    /*@Autowired
    public CommandBus configureCommandBus(CommandBus commandBus, HttpServletRequest httpServletRequest) {
        if (commandBus instanceof SimpleCommandBus){
        }
        commandBus.registerHandlerInterceptor(new CommandInterceptor(httpServletRequest));
        return commandBus;

    }*/

    @Autowired
    public void configureCommandBus(EventBus eventBus) {
        eventBus.registerDispatchInterceptor(new EventInterceptor());
    }

    @Autowired
    public void registerCreateProductCommandInterceptor(CommandBus commandBus, CommandInterceptor commandInterceptor) {
        commandBus.registerDispatchInterceptor(commandInterceptor);
    }
}
