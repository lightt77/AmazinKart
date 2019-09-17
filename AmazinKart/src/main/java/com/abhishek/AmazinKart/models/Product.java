package com.abhishek.AmazinKart.models;

public class Product
{
    private String category;
    private int inventory;
    private String arrival;
    private int rating;
    private String currency;
    private int price;
    private String origin;
    private String product;
    private Discount discount;

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public int getInventory()
    {
        return inventory;
    }

    public void setInventory(int inventory)
    {
        this.inventory = inventory;
    }

    public String getArrival()
    {
        return arrival;
    }

    public void setArrival(String arrival)
    {
        this.arrival = arrival;
    }

    public int getRating()
    {
        return rating;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public String getOrigin()
    {
        return origin;
    }

    public void setOrigin(String origin)
    {
        this.origin = origin;
    }

    public String getProduct()
    {
        return product;
    }

    public void setProduct(String product)
    {
        this.product = product;
    }

    public Discount getDiscount()
    {
        return discount;
    }

    public void setDiscount(Discount discount)
    {
        this.discount = discount;
    }
}
