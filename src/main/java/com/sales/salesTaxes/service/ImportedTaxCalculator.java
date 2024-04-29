package com.sales.salesTaxes.service;

import com.sales.salesTaxes.helper.TaxCalculator;
import com.sales.salesTaxes.model.Item;

public class ImportedTaxCalculator  implements TaxCalculator {
    private static final double IMPORTED_TAX_RATE = 0.05;

    @Override
    public double calculateTax(Item item){
        if (!item.getImported()) {
            return 0;
        }
        return roundSalesTaxes(item.getPrice() * IMPORTED_TAX_RATE);
    }

    private double roundSalesTaxes(double tax){
        return Math.ceil(tax * 20) / 20.0;
    }
}
