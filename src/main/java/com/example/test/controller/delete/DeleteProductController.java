package com.example.test.controller.delete;

import com.example.test.ConnectionDB;
import com.example.test.DataExtractor;
import com.example.test.ProductExtractor;
import com.example.test.ProductModel;
import com.example.test.controller.OperationPreparatorController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteProductController {
    private final ObservableList<ProductModel> products = FXCollections.observableArrayList();
    private static final String WATCH_PRODUCTS = "select * from product";

    @FXML
    private TableColumn<ProductModel, String> categoryColumn;

    @FXML
    private Button delete_product_button;

    @FXML
    private TableColumn<ProductModel, Long> idColumn;

    @FXML
    private TextField id_field;

    @FXML
    private TableColumn<ProductModel, String> nameColumn;

    @FXML
    private TableColumn<ProductModel, Integer> priceColumn;

    @FXML
    private TableView<ProductModel> productTable;

    @FXML
    void initialize() {

        pushProducts();
        pushTable();

        delete_product_button.setOnAction(Event -> {
            String query = String.format("delete from product where id = %s",id_field.getText());
            ConnectionDB.executeUpdate(query,null, OperationPreparatorController.DELETE_PRODUCT);
        });
    }

    private void pushTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable.setItems(products);
    }

    private void pushProducts() {
        productTable.getItems().clear();
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
    }

}
