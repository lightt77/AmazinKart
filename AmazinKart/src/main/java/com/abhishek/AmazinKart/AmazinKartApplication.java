package com.abhishek.AmazinKart;

import com.abhishek.AmazinKart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class AmazinKartApplication implements CommandLineRunner
{
    @Autowired
    private ProductService productService;
    private static final Logger LOG = Logger.getLogger(AmazinKartApplication.class.getName());

    public static void main(String[] args)
    {
        try
        {
            SpringApplication app = new SpringApplication(AmazinKartApplication.class);
            if (args.length > 0)
                app.run(args[0]);
            else
                app.run("promotionSetA");       // defaults to set A
        }
        catch (Exception ex)
        {
            LOG.log(Level.SEVERE, "Exception", ex);
        }
    }

    @Override
    public void run(String... args) throws Exception
    {
        productService.generateDiscountedProducts(args[0]);
    }
}
