package com.example.test;

import com.example.test.controller.CreateContext;
import com.example.test.controller.OperationPreparatorController;

import java.sql.*;
import java.util.Enumeration;

public class ConnectionDB {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "27112001jeka";
    private static Connection connection;

    public static ResultSet executeRequest(String request,String factoryName) {
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = connectToBD(request);
            if(factoryName != null){
                statement.setString(1,factoryName);
            }
            resultSet = statement.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void executeUpdate(String request, CreateContext context, OperationPreparatorController type) {
        try {
            PreparedStatement statement = connectToBD(request);
            switch (type) {
                case CREATE_FACTORY -> factoryPreparator(context, statement);
                case CREATE_PRODUCT -> productPreparator(context, statement);
                case EDIT_FACTORY -> factoryEditPreparator(context,statement);
                case EDIT_PRODUCT -> productEditPreparator(context,statement);
                case DELETE_FACTORY,DELETE_PRODUCT -> System.out.println();
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void factoryPreparator(CreateContext context, PreparedStatement statement) throws SQLException {
        statement.setInt(1, context.getId());
        statement.setString(2, context.getFactoryName());
        statement.setString(3, context.getFoundation());
        statement.setInt(4, context.getAmountWorker());
    }

    private static void factoryEditPreparator(CreateContext context, PreparedStatement statement) throws SQLException {
        statement.setString(1, context.getFactoryName());
        statement.setString(2, context.getFoundation());
        statement.setInt(3, context.getAmountWorker());
        statement.setInt(4, context.getId());
    }

    private static void productPreparator(CreateContext context, PreparedStatement statement) throws SQLException {
        statement.setInt(1, context.getId());
        statement.setString(2, context.getProductName());
        statement.setString(3, context.getCategory());
        statement.setInt(4, context.getPrice());
    }

    private static void productEditPreparator(CreateContext context, PreparedStatement statement) throws SQLException {
        statement.setString(1, context.getProductName());
        statement.setString(2, context.getCategory());
        statement.setInt(3, context.getPrice());
        statement.setInt(4, context.getId());
    }


    private static PreparedStatement connectToBD(String request) {
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            statement = connection.prepareStatement(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }


    public static void registerDrivers() {
        try {
            DriverManager.registerDriver(DriverManager.getDriver(DB_URL));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deregisterDrivers() {
        final Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
