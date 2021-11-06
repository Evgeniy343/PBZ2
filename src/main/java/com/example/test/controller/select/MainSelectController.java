package com.example.test.controller.select;

import com.example.test.ConnectionDB;
import com.example.test.DataExtractor;
import com.example.test.ProductExtractor;
import com.example.test.ProductModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainSelectController implements Initializable {
    private final ObservableList<ProductModel> products = FXCollections.observableArrayList();
    private final static String MAIN_SELECT = """
            select product.id,product.p_name,product.category,product.price
            from product
            join factory_to_product ftp on product.id = ftp.product_id
            join factory f on f.id = ftp.factory_id
            where f.f_name = ?""";

    @FXML
    private TableColumn<ProductModel, String> categoryColumn;

    @FXML
    private TextField factoryNameField;

    @FXML
    private TableColumn<ProductModel, Long> idColumn;

    @FXML
    private TableColumn<ProductModel, String> nameColumn;

    @FXML
    private TableColumn<ProductModel, Integer> priceColumn;

    @FXML
    private TableView<ProductModel> productTable;

    @FXML
    private Button sendButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        sendButton.setOnAction(Event -> {
            productTable.getItems().clear();
            pushProducts();
            pushTable();
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
        ResultSet resultProducts = ConnectionDB.executeRequest(MAIN_SELECT,factoryNameField.getText());
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
