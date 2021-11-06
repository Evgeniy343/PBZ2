package com.example.test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductExtractor implements DataExtractor<ProductModel>{

    public static final String ID = "id";
    public static final String PRODUCT_NAME = "p_name";
    public static final String CATEGORY = "category";
    public static final String PRICE = "price";

    @Override
    public ProductModel extract(ResultSet resultSet) throws SQLException {
        return new ProductModel(
                resultSet.getLong(ID),
                resultSet.getString(PRODUCT_NAME),
                resultSet.getString(CATEGORY),
                resultSet.getInt(PRICE)
        );
    }
}
