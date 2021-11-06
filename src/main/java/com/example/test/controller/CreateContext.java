package com.example.test.controller;

import java.util.Objects;

public class CreateContext {
    Integer id;
    String factoryName;
    String productName;
    Integer amountWorker;
    String foundation;
    String category;
    Integer price;

    public CreateContext(Integer id, String factoryName, String foundation, Integer amountWorker,
                         String productName, String category, Integer price) {
        this.id = id;
        this.factoryName = factoryName;
        this.productName = productName;
        this.amountWorker = amountWorker;
        this.foundation = foundation;
        this.category = category;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getAmountWorker() {
        return amountWorker;
    }

    public String getFoundation() {
        return foundation;
    }

    public String getCategory() {
        return category;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateContext that = (CreateContext) o;
        return Objects.equals(id, that.id) && Objects.equals(factoryName, that.factoryName) && Objects.equals(productName, that.productName) && Objects.equals(amountWorker, that.amountWorker) && Objects.equals(foundation, that.foundation) && Objects.equals(category, that.category) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, factoryName, productName, amountWorker, foundation, category, price);
    }

    @Override
    public String toString() {
        return "CreateContext{" +
                "id=" + id +
                ", factoryName='" + factoryName + '\'' +
                ", productName='" + productName + '\'' +
                ", amountWorker=" + amountWorker +
                ", foundation='" + foundation + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
