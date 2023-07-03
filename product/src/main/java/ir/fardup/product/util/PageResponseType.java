package ir.fardup.product.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.axonframework.common.ReflectionUtils;
import org.axonframework.messaging.responsetypes.AbstractResponseType;
import org.springframework.data.domain.Page;

import java.beans.ConstructorProperties;
import java.lang.reflect.Type;
import java.util.concurrent.Future;

public class PageResponseType<R> extends AbstractResponseType<Page<R>> {


    /**
     * Instantiate a {@link org.axonframework.messaging.responsetypes.ResponseType} with the given {@code expectedResponseType} as the type to be matched against and
     * to which the query response should be converted to, as is or as the contained type for an array/list/etc.
     *
     * @param expectedResponseType the response type which is expected to be matched against and to be returned, as is or as
     *                             the contained type for an array/list/etc
     */
    @JsonCreator
    @ConstructorProperties({"expectedResponseType"})
    public PageResponseType(@JsonProperty("expectedResponseType") Class<R> expectedResponseType) {
        super(expectedResponseType);
    }

    @Override
    public boolean matches(Type responseType) {
        Type unwrapped = ReflectionUtils.unwrapIfType(responseType, Future.class, Page.class);
        return isGenericAssignableFrom(unwrapped) || isAssignableFrom(unwrapped);
    }

    @Override
    public Page<R> convert(Object response) {
        return (Page<R>) response;
    }

    @Override
    public Class responseMessagePayloadType() {
        return Page.class;
    }

    @Override
    public String toString() {
        return "PageResponseType{" + expectedResponseType + "}";
    }

}