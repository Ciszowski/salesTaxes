package com.sales.salesTaxes.helper;

import com.sales.salesTaxes.exception.ShoppingException;
import com.sales.salesTaxes.model.Item;
import com.sales.salesTaxes.model.Receipt;

import java.util.List;

public interface ShoppingService {
    Receipt generateReceipt(List<Item> items) throws ShoppingException;
}
