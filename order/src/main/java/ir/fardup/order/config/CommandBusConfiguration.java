package ir.fardup.order.config;


import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.PropagatingErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CommandBusConfiguration {


    @Autowired
    public void configure(EventProcessingConfigurer configurer) {
        configurer
                .usingSubscribingEventProcessors()
                .registerDefaultListenerInvocationErrorHandler(conf -> PropagatingErrorHandler.instance());
    }

//    @Bean
//    public CommandBus configureCommandBus() {
//        CommandBus commandBus = SimpleCommandBus.builder().build();
//        commandBus.registerHandlerInterceptor(new CommandInterceptor());
//        return commandBus;
//
//    }

    /*@Autowired
    public CommandBus configureCommandBus(CommandBus commandBus){
//        commandBus.registerHandlerInterceptor(new CommandInterceptor());
        return commandBus;
    }*/

    @Autowired
    public void registerCreateProductCommandInterceptor(CommandBus commandBus, CommandInterceptor commandInterceptor) {
        commandBus.registerDispatchInterceptor(commandInterceptor);
    }
}
