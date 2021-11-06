package com.example.test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FactoryExtractor implements DataExtractor<FactoryModel> {

    public static final String ID = "id";
    public static final String FACTORY_NAME = "f_name";
    public static final String FOUNDATION = "foundation";
    public static final String AMOUNT_WORKER = "amount_worker";

    @Override
    public FactoryModel extract(ResultSet resultSet) throws SQLException {
        return new FactoryModel(
                resultSet.getLong(ID),
                resultSet.getString(FACTORY_NAME),
                resultSet.getDate(FOUNDATION),
                resultSet.getInt(AMOUNT_WORKER)
        );
    }
}
