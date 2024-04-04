package ir.fardup.product.util;

import com.fardup.msutility.customexception.CustomExceptionKey;

public enum BusinessExceptionKeyImpl implements CustomExceptionKey {

    REQUEST_DOESNT_HAVE_CODE("REQUEST_DOESNT_HAVE_CODE"),
    DUPLICATE_TITLE("DUPLICATE_TITLE"),
    NOT_FOND("NOT_FOUND"),
    ;

    private final String value;

    private BusinessExceptionKeyImpl(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
