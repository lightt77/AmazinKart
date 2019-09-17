package com.abhishek.AmazinKart;

import com.abhishek.AmazinKart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmazinKartApplication implements CommandLineRunner
{
    @Autowired
    private ProductService productService;

    public static void main(String[] args)
    {
        SpringApplication app = new SpringApplication(AmazinKartApplication.class);
        if (args.length > 0)
            app.run(args[0]);
        else
            app.run("promotionSetA");       // defaults to set A
    }

    @Override
    public void run(String... args) throws Exception
    {
        productService.generateDiscountedProducts();
    }
}
