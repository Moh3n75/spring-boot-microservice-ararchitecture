package ir.fardup.product.config;


import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.PropagatingErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public CommandBus configureCommandBus() {
        CommandBus commandBus = SimpleCommandBus.builder().build();
        commandBus.registerHandlerInterceptor(new CommandInterceptor());
        return commandBus;

    }
}
