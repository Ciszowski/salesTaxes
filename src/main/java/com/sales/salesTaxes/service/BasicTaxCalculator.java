package com.sales.salesTaxes.service;

import com.sales.salesTaxes.helper.TaxCalculator;
import com.sales.salesTaxes.model.Item;
import org.jetbrains.annotations.NotNull;

public class BasicTaxCalculator implements TaxCalculator {
    private static final double BASIC_TAX_RATE = 0.1;

    @Override
    public double calculateTax(Item item) {
        if (isExemptTax(item)) {
            return 0;
        }
        return roundSalesTax(item.getPrice() * BASIC_TAX_RATE);
    }
    private boolean isExemptTax(@NotNull Item item) {
        String itemName = item.getName().toLowerCase();
        return itemName.contains("book") || itemName.contains("chocolate") || itemName.contains("pill");
    }

    private double roundSalesTax(double tax) {
        return Math.round(tax * 100) / 100.0;
    }
}
