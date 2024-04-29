package com.sales.salesTaxes.controller;

import com.sales.salesTaxes.exception.ItemException;
import com.sales.salesTaxes.exception.ShoppingException;
import com.sales.salesTaxes.model.Receipt;
import com.sales.salesTaxes.helper.ShoppingService;
import com.sales.salesTaxes.utils.ReceiptGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShoppingController {
    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping("/receipt")
    public ResponseEntity<Receipt> generateReceipt(@RequestBody(required = false) List<String> stringItems) throws ShoppingException, ItemException {
        ReceiptGenerator receiptGenerator = new ReceiptGenerator(shoppingService);
        return receiptGenerator.generateReceipt(stringItems);
    };
}
