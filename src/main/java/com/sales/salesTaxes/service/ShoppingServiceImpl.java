package com.sales.salesTaxes.service;

import com.sales.salesTaxes.model.Item;
import com.sales.salesTaxes.model.Receipt;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ShoppingServiceImpl implements ShoppingService {
    @Override
    public Receipt generateReceipt(List<Item> items) {
        // logic;
        double totalSalesTaxes = 0;
        double totalPrice = 0;
        List<String> receiptLines = new ArrayList<>();


        for (Item item: items) {
            double itemPrice = item.getPrice();
            double salesTax = calculateSalesTax(item);
            totalSalesTaxes += salesTax;

            double taxedPrice = itemPrice * salesTax;
            totalPrice += taxedPrice;

            String receiptLine = String.format("%d %s: %.2f", 1, item.getName(), taxedPrice);
            receiptLines.add(receiptLine);
        }
        Receipt receipt = new Receipt();
        receipt.setItems(receiptLines);
        receipt.setTotalSalesTaxes(roundSalesTax(totalSalesTaxes));
        receipt.setTotalPrice(roundPrice(totalPrice));
        return receipt;
    }
    double calculateSalesTax(Item item) {
       double salesTaxRate = 0.1;
       double importTaxRate = 0.05;
       double salesTax = 0;

       // Applique Basic tax except pour les exempt items
        if(isExemptTax(item)) {
          salesTax += item.getPrice() * salesTaxRate;
        }

        // Applique duty tax except pour les exempt items
        if(isExemptDutyTax(item)) {
            salesTax += item.getPrice() * importTaxRate;
        }
        return salesTax;
    }
    boolean isExemptTax(Item item) {
        // check if l item est exempt des taxes basic
        String itemName = item.getName().toLowerCase();
        return !itemName.contains("book") && !itemName.contains("food") && !itemName.contains("medical");
    }
    boolean isExemptDutyTax(Item item) {
        return item.getImported();
    }
    double roundSalesTax(double tax) {
        // Round au plus pres de 0.05
        return Math.round(tax * 20) / 20.0;
    }
    double roundPrice(double price) {
        // Round a la 2nd decimal
        return Math.round(price * 100) / 100.0;
    }
}