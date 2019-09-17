package com.abhishek.AmazinKart.services;

import com.abhishek.AmazinKart.models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService
{
    @Autowired
    private RestTemplate restTemplate;
    @Value("${productDetailsUrl}")
    private String PRODUCT_DETAILS_URL;
    @Autowired
    private ExchangeRatesService exchangeRatesService;
    @Autowired
    private DiscountService discountService;

    public void generateDiscountedProducts(String promotionSet) throws IOException
    {
        List<Product> products = getProducts();
        products.forEach(product -> {
            product.setPrice(exchangeRatesService.convertToINR(product.getPrice(), product.getCurrency()));
            product.setCurrency("INR");
            discountService.applyDiscounts(product, promotionSet);
        });

        (new ObjectMapper()).writeValue(new File("./output/output.json"), products);
    }

    public List<Product> getProducts()
    {
        return Arrays.stream((Objects.requireNonNull(restTemplate.getForEntity(PRODUCT_DETAILS_URL, Product[].class).getBody()))).collect(Collectors.toList());
    }
}
