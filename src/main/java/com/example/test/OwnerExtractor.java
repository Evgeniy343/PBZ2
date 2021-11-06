package com.example.test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerExtractor implements DataExtractor<OwnerModel>{

    public static final String ID = "id";
    public static final String FIO = "fio";
    public static final String EMAIL = "email";
    public static final String TELEPHONE = "telephone";

    @Override
    public OwnerModel extract(ResultSet resultSet) throws SQLException {
        return new OwnerModel(
                resultSet.getLong(ID),
                resultSet.getString(FIO),
                resultSet.getString(EMAIL),
                resultSet.getString(TELEPHONE)
        );
    }
}
