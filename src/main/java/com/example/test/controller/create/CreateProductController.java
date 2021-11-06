package com.example.test.controller.create;

import com.example.test.*;
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

public class CreateProductController {
    private final ObservableList<ProductModel> products = FXCollections.observableArrayList();
    private static final String WATCH_PRODUCTS = "select * from product";

    @FXML
    private TableColumn<ProductModel, String> categoryColumn;

    @FXML
    private TextField categoryField;

    @FXML
    private Button createButton;

    @FXML
    private TableColumn<ProductModel, Long> idColumn;

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

        createButton.setOnAction(Event -> {
            Integer id = products.size() + 1;
            String productName = nameField.getText();
            String category = categoryField.getText();
            Integer price = Integer.parseInt(priceField.getText());

            CreateContext productContext = new CreateContext(id,null,null,0,
                    productName,category,price);

            String query = "insert into product(id,p_name,category,price) values (?,?,?,?)";
            ConnectionDB.executeUpdate(query,productContext, OperationPreparatorController.CREATE_PRODUCT);
        });
    }

    private void pushTable() {
        productTable.getItems().clear();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable.setItems(products);
    }

    private void pushProducts() {
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
