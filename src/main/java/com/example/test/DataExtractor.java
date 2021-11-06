package com.example.test;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataExtractor<T> {
    T extract(ResultSet resultSet) throws SQLException;
}
