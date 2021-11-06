package com.example.test.controller;

import com.example.test.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeleteController {
    private static final String VIEW_DELETE_FACTORY_FXML = "/com/example/test/delete-factory.fxml";
    private static final String VIEW_DELETE_PRODUCT_FXML = "/com/example/test/delete-product.fxml";

    @FXML
    private Button delete_factory_button;

    @FXML
    private Button delete_product_button;

    @FXML
    void initialize() {

        ViewLoader loader = ViewLoader.getInstance();

        delete_factory_button.setOnAction(Event -> loader.moveToAnotherView(VIEW_DELETE_FACTORY_FXML));

        delete_product_button.setOnAction(Event -> loader.moveToAnotherView(VIEW_DELETE_PRODUCT_FXML));
    }

}
