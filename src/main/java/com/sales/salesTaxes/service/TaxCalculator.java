package com.sales.salesTaxes.service;

import com.sales.salesTaxes.model.Item;

public interface TaxCalculator {
    double calculateTax(Item item);
}