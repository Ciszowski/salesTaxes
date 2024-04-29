package com.sales.salesTaxes.controller;

import com.sales.salesTaxes.model.Item;
import com.sales.salesTaxes.model.Receipt;
import com.sales.salesTaxes.service.ShoppingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ShoppingController {
    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping("/receipt")
    public ResponseEntity<Receipt> generateReceipt(@RequestBody(required = false) List<String> stringItems) {
        if (stringItems == null || stringItems.isEmpty()) {
            Receipt errorReceipt = new Receipt();
            errorReceipt.setMessage("the body is missing or is empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorReceipt);
        }
        List<Item> itemList = new ArrayList<>();
        for (String stringItem : stringItems) {
            Item item = mapToItem(stringItem);
            itemList.add(item);
        }
        Receipt receipt = shoppingService.generateReceipt(itemList);
        return ResponseEntity.ok(receipt);
    };

    Item mapToItem(String stringItem) {
        String[] parts = stringItem.split(" at ");
        String name = parts[0];
        double price = Double.parseDouble(parts[1]);
        boolean imported = name.toLowerCase().contains("imported");
        return new Item(name, price, imported);
    };
}
