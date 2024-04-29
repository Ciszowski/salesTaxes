package com.sales.salesTaxes.utils;

import com.sales.salesTaxes.exception.ItemException;
import com.sales.salesTaxes.exception.ShoppingException;
import com.sales.salesTaxes.helper.ItemFactory;
import com.sales.salesTaxes.model.Item;
import com.sales.salesTaxes.model.Receipt;
import com.sales.salesTaxes.helper.ShoppingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;

public class ReceiptGenerator implements ItemFactory {
    private final ShoppingService shoppingService;

    public ReceiptGenerator(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    public ResponseEntity<Receipt> generateReceipt(List<String> stringItems) throws ShoppingException, ItemException {
        if (stringItems == null || stringItems.isEmpty()) {
            Receipt errorReceipt = new Receipt();
            errorReceipt.setMessage("the body is missing or is empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorReceipt);
        }
        List<Item> itemList = mapToItems(stringItems);
        return ResponseEntity.ok(shoppingService.generateReceipt(itemList));
    }

     public List<Item> mapToItems(List<String> stringItems) throws ItemException {
        List<Item> itemList = new ArrayList<>();
        for (String stringItem : stringItems) {
            Item item = mapToItem(stringItem);
            itemList.add(item);
        }
        return itemList;
    }

    private Item mapToItem(String stringItem) throws ItemException {
        try {
            String[] parts = stringItem.split(" at ");
            String name = parts[0];
            double price = Double.parseDouble(parts[1]);
            boolean imported = name.toLowerCase().contains("imported");
            return new Item(name, price, imported);
        } catch (Exception e) {
            throw new ItemException("Invalid item format");
        }
    };
}
