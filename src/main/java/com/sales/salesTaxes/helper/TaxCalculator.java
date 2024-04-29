package com.sales.salesTaxes.helper;

import com.sales.salesTaxes.model.Item;

public interface TaxCalculator {
    double calculateTax(Item item);
}