package ir.fardup.product.product.service;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.function.BiFunction;

@Component
public class ProductInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {


    @NotNull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@NotNull List<? extends CommandMessage<?>> messages) {
        return null;
    }
}
