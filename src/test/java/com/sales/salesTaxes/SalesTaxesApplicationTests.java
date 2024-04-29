package com.sales.salesTaxes;

import com.sales.salesTaxes.exception.ShoppingException;
import com.sales.salesTaxes.helper.ShoppingService;
import com.sales.salesTaxes.model.Item;
import com.sales.salesTaxes.model.Receipt;
import com.sales.salesTaxes.service.BasicTaxCalculator;
import com.sales.salesTaxes.service.ImportedTaxCalculator;
import com.sales.salesTaxes.service.ShoppingServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class SalesTaxesApplicationTests {
	@Autowired
	private ShoppingService shoppingService;

	@Test
	void testGenerateReceipt_First() throws ShoppingException {

		// creation obj Item from inputs
		List<Item> expectedItems = Arrays.asList(
				new Item("1 book", 12.49, false),
				new Item("1 music CD", 14.99, false),
				new Item("1 chocolate bar", 0.85, false)
		);
		Receipt receipt = shoppingService.generateReceipt(expectedItems);

		// check if not null
		assertNotNull(receipt);

		// Definition <> results
		Receipt expectedResult = new Receipt();
		expectedResult.setItems(Arrays.asList(
				"1 book: 12,49",
				"1 music CD: 16,49",
				"1 chocolate bar: 0,85"
		));
		expectedResult.setTotalPrice(29.83);
		expectedResult.setTotalSalesTaxes(1.5);

		Assertions.assertEquals(expectedResult.getItems(), receipt.getItems());
		Assertions.assertEquals(expectedResult.getTotalPrice(), receipt.getTotalPrice());
		Assertions.assertEquals(expectedResult.getTotalSalesTaxes(), receipt.getTotalSalesTaxes());
	}
	@Test
	void testGenerateReceipt_Second() throws ShoppingException {

		// creation obj Item from inputs
		List<Item> expectedItems = Arrays.asList(
				new Item("1 imported box of chocolates", 10.00, true),
				new Item("1 imported bottle of perfume", 47.50, true)
		);
		Receipt receipt = shoppingService.generateReceipt(expectedItems);

		// check if not null
		assertNotNull(receipt);

		// Definition <> results
		Receipt expectedResult = new Receipt();
		expectedResult.setItems(Arrays.asList(
				"1 imported box of chocolates: 10,50",
				"1 imported bottle of perfume: 54,65"
		));
		expectedResult.setTotalPrice(65.15);
		expectedResult.setTotalSalesTaxes(7.65);

		Assertions.assertEquals(expectedResult.getItems(), receipt.getItems());
		Assertions.assertEquals(expectedResult.getTotalPrice(), receipt.getTotalPrice());
		Assertions.assertEquals(expectedResult.getTotalSalesTaxes(), receipt.getTotalSalesTaxes());
	}

	@Test
	void testGenerateReceipt_Third() throws ShoppingException {
		// creation obj Item from inputs
		List<Item> expectedItems = Arrays.asList(
				new Item("1 imported bottle of perfume", 27.99, true),
				new Item("1 bottle of perfume", 18.99, false),
				new Item("1 packet of headache pills", 9.75, false),
				new Item("1 box of imported chocolates", 11.25, true)
		);
		Receipt receipt = shoppingService.generateReceipt(expectedItems);

		// check if not null
		assertNotNull(receipt);

		// Definition <> results
		Receipt expectedResult = new Receipt();
		expectedResult.setItems(Arrays.asList(
				"1 imported bottle of perfume: 32,19",
				"1 bottle of perfume: 20,89",
				"1 packet of headache pills: 9,75",
				"1 box of imported chocolates: 11,85"
		));
		expectedResult.setTotalPrice(74.68);
		expectedResult.setTotalSalesTaxes(6.7);

		Assertions.assertEquals(expectedResult.getItems(), receipt.getItems());
		Assertions.assertEquals(expectedResult.getTotalPrice(), receipt.getTotalPrice());
		Assertions.assertEquals(expectedResult.getTotalSalesTaxes(), receipt.getTotalSalesTaxes());
	}

}
