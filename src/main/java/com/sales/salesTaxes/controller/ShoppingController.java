package com.sales.salesTaxes.controller;

import com.sales.salesTaxes.model.Item;
import com.sales.salesTaxes.model.Receipt;
import com.sales.salesTaxes.service.ShoppingService;
import com.sales.salesTaxes.utils.ReceiptGenerator;
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
        ReceiptGenerator receiptGenerator = new ReceiptGenerator(shoppingService);
        return receiptGenerator.generateReceipt(stringItems);
    };
}
