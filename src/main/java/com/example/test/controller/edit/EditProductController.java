package com.example.test.controller.edit;

import com.example.test.ConnectionDB;
import com.example.test.DataExtractor;
import com.example.test.ProductExtractor;
import com.example.test.ProductModel;
import com.example.test.controller.CreateContext;
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

public class EditProductController {
    //String query = String.format("update product set %s = %d",a,b);
    private final ObservableList<ProductModel> products = FXCollections.observableArrayList();
    private static final String WATCH_PRODUCTS = "select * from product";

    @FXML
    private TableColumn<ProductModel, String> categoryColumn;

    @FXML
    private TextField categoryField;

    @FXML
    private Button editButton;

    @FXML
    private TableColumn<ProductModel, Long> idColumn;

    @FXML
    private TextField idField;

    @FXML
    private TableColumn<ProductModel, String> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TableColumn<ProductModel, Integer> priceColumn;

    @FXML
    private TextField priceField;

    @FXML
    private TableView<ProductModel> productTable;

    @FXML
    void initialize() {
        pushProducts();
        pushTable();

        editButton.setOnAction(Event -> {
            String query = "update product set p_name = ?, category = ?, price = ? where id = ?";
            CreateContext context = new CreateContext(Integer.parseInt(idField.getText()),null,null
                    ,0,nameField.getText(),categoryField.getText(),Integer.parseInt(priceField.getText()));
            ConnectionDB.executeUpdate(query,context, OperationPreparatorController.EDIT_PRODUCT);
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
