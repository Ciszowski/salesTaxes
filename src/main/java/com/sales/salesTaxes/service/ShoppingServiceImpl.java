package com.sales.salesTaxes.service;
import com.sales.salesTaxes.exception.ShoppingException;
import com.sales.salesTaxes.helper.ShoppingService;
import com.sales.salesTaxes.helper.TaxCalculator;
import com.sales.salesTaxes.model.Item;
import com.sales.salesTaxes.model.Receipt;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShoppingServiceImpl implements ShoppingService {
    private final List<TaxCalculator> taxCalculators;
    public ShoppingServiceImpl(List<TaxCalculator> taxCalculators) {
        this.taxCalculators = taxCalculators;
    }

    @Override
    public Receipt generateReceipt(List<Item> items) throws ShoppingException {
        if (items == null || items.isEmpty()) {
            throw new ShoppingException("This list of items is null or empty");
        }
        // logic;
        double totalSalesTaxes = 0;
        double totalPrice = 0;
        List<String> receiptLines = new ArrayList<>();

        for (Item item: items) {
            double itemPrice = item.getPrice();
            double salesTax = calculateSalesTax(item);
            totalSalesTaxes += salesTax;

            double taxedPrice = itemPrice + salesTax;
            totalPrice += taxedPrice;

            String receiptLine = String.format("%s: %.2f", item.getName(), taxedPrice);
            receiptLines.add(receiptLine);
        }
        Receipt receipt = new Receipt();
        receipt.setItems(receiptLines);
        receipt.setTotalSalesTaxes(roundPrice(totalSalesTaxes));
        receipt.setTotalPrice(roundPrice(totalPrice));
        return receipt;
    }
    double calculateSalesTax(Item item) {
        double totalTax = 0;
        for (TaxCalculator calculator: taxCalculators) {
            totalTax += calculator.calculateTax(item);
        }
        return totalTax;
    }

    private double roundPrice(double price) {
        // Round a la 2nd decimal
        return Math.round(price * 100) / 100.0;
    }
}