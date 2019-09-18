package com.abhishek.AmazinKart.services;

import com.abhishek.AmazinKart.models.Discount;
import com.abhishek.AmazinKart.models.Product;
import org.springframework.stereotype.Service;

@Service
public class DiscountService
{
    public void applyDiscounts(Product product, String promotionSet)
    {
        if (promotionSet.equals("promotionSetA"))
            applyDiscountsFromPromotionSetA(product);
        else if (promotionSet.equals("promotionSetB"))
            applyDiscountsFromPromotionSetB(product);

        applyCommonDiscounts(product);
    }

    private void applyDiscountsFromPromotionSetA(Product product)
    {
        double currentDiscountAmount = 0d;
        String currentDiscountTag = "";

        if (product.getOrigin().equals("Africa"))
        {
            currentDiscountAmount = 0.07d * product.getPrice();
            currentDiscountTag = "get 7% off";
        }

        if (product.getRating() == 2 && currentDiscountAmount < 0.04d * product.getPrice())
        {
            currentDiscountAmount = 0.04d * product.getPrice();
            currentDiscountTag = "get 4% off";
        }

        if (product.getRating() < 2 && currentDiscountAmount < 0.08d * product.getPrice())
        {
            currentDiscountAmount = 0.08d * product.getPrice();
            currentDiscountTag = "get 8% off";
        }

        if (currentDiscountAmount < 100
                && (product.getCategory().equals("electronics") || product.getCategory().equals("furnishing"))
                && product.getPrice() >= 500)
        {
            currentDiscountAmount = 100d;
            currentDiscountTag = "get Rs 100 off";
        }

        if (currentDiscountAmount != 0d)
        {
            Discount discount = new Discount();
            discount.setAmount(Double.toString(currentDiscountAmount));
            discount.setDiscountTag(currentDiscountTag);
            product.setDiscount(discount);
            product.setPrice(product.getPrice() - (int)currentDiscountAmount);
        }
    }

    private void applyDiscountsFromPromotionSetB(Product product)
    {
        double currentDiscountAmount = 0d;
        String currentDiscountTag = "";

        if (product.getInventory() > 20)
        {
            currentDiscountAmount = 0.12d * product.getPrice();
            currentDiscountTag = "get 12% off";
        }

        if (product.getArrival().equals("NEW") && 0.07d * product.getPrice() > currentDiscountAmount)
        {
            currentDiscountAmount = 0.07d * product.getPrice();
            currentDiscountTag = "get 7% off";
        }

        if(currentDiscountAmount != 0d)
        {
            Discount discount = new Discount();
            discount.setAmount(Double.toString(currentDiscountAmount));
            discount.setDiscountTag(currentDiscountTag);
            product.setDiscount(discount);
            product.setPrice(product.getPrice() - (int)currentDiscountAmount);
        }
    }

    private void applyCommonDiscounts(Product product)
    {
        if (product.getPrice() > 1000 && product.getDiscount() == null)
        {
            Discount discount = new Discount();
            double discountAmount = product.getPrice() * 0.02d;
            discount.setAmount(Double.toString(discountAmount));
            discount.setDiscountTag("get 2% off");
            product.setDiscount(discount);
            product.setPrice(product.getPrice() - (int)discountAmount);
        }
    }
}
