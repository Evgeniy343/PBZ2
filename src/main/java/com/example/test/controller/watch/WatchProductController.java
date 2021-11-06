package com.example.test.controller.watch;

import com.example.test.ConnectionDB;
import com.example.test.DataExtractor;
import com.example.test.ProductExtractor;
import com.example.test.ProductModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WatchProductController {
    private static final String WATCH_PRODUCTS = "select * from product";
    private final ObservableList<ProductModel> products = FXCollections.observableArrayList();

    @FXML
    private TableView<ProductModel> productTable;

    @FXML
    private TableColumn<ProductModel, String> categoryColumn;

    @FXML
    private TableColumn<ProductModel, Long> idColumn;

    @FXML
    private TableColumn<ProductModel, String> nameColumn;

    @FXML
    private TableColumn<ProductModel, Integer> priceColumn;

    @FXML
    void initialize() {
        ResultSet resultProducts = ConnectionDB.executeRequest(WATCH_PRODUCTS,null);
        DataExtractor<ProductModel> extractor = new ProductExtractor();
        try {
            while (resultProducts.next()) {
                ProductModel product = extractor.extract(resultProducts);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable.setItems(products);
    }

}
