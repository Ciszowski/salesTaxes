package com.sales.salesTaxes.model;
import java.util.List;

public class Receipt {
    private List<String> items;
    private double totalPrice;
    private double totalSalesTaxes;
    private String message;

    public Receipt() {}

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<String> getItems() {
        return items;
    }
    public void setItems(List<String> items) {
        this.items = items;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public double getTotalSalesTaxes() {
        return this.totalSalesTaxes;
    }
    public void setTotalSalesTaxes(double totalSalesTaxes) {
        this.totalSalesTaxes = totalSalesTaxes;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
