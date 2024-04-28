package com.sales.salesTaxes.model;
import java.util.List;

public class Item {
    private String name;
    private double price;
    private final boolean imported;

    public Item(String name, double price, boolean imported) {
        this.name = name;
        this.price = price;
        this.imported = imported;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public boolean getImported() {
        return imported;
    }
}
