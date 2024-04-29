package com.sales.salesTaxes.helper;

import com.sales.salesTaxes.exception.ItemException;
import com.sales.salesTaxes.model.Item;

import java.util.List;

public interface ItemFactory {
    List<Item> mapToItems(List<String> stringItems) throws ItemException;
}
