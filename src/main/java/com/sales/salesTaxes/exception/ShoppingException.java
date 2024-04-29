package com.sales.salesTaxes.exception;

import ch.qos.logback.core.hook.ShutdownHook;

public class ShoppingException extends Exception {
    public ShoppingException(String message) {
        super(message);
    }
}
