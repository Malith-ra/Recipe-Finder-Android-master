package com.saneth.recipes;

public class ProductCard {
    private String name1,description1,availability1;
    private double weight1, price1;

    public ProductCard(String name1, String description1, String availability1, double weight1, double price1) {
        this.name1 = name1;
        this.description1 = description1;
        this.availability1 = availability1;
        this.weight1 = weight1;
        this.price1 = price1;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getAvailability1() {
        return availability1;
    }

    public void setAvailability1(String availability1) {
        this.availability1 = availability1;
    }

    public double getWeight1() {
        return weight1;
    }

    public void setWeight1(double weight1) {
        this.weight1 = weight1;
    }

    public double getPrice1() {
        return price1;
    }

    public void setPrice1(double price1) {
        this.price1 = price1;
    }
}
