package com.sales.salesTaxes.config;

import com.sales.salesTaxes.service.BasicTaxCalculator;
import com.sales.salesTaxes.service.ImportedTaxCalculator;
import com.sales.salesTaxes.helper.TaxCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaxConfig {
    @Bean
    public TaxCalculator basicTaxCalculator() {
        return new BasicTaxCalculator();
    }
    @Bean
    public TaxCalculator importedTaxCalculator(){
        return new ImportedTaxCalculator();
    }
}
