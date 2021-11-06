package com.example.test.controller;

import com.example.test.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CreateController {
    private static final String VIEW_CREATE_FACTORY_FXML = "/com/example/test/create-factory.fxml";
    private static final String VIEW_CREATE_PRODUCT_FXML = "/com/example/test/create-product.fxml";


    @FXML
    private Button add_factory_button;

    @FXML
    private Button add_product_button;

    @FXML
    void initialize() {

        ViewLoader loader = ViewLoader.getInstance();

        add_factory_button.setOnAction(Event -> loader.moveToAnotherView(VIEW_CREATE_FACTORY_FXML));

        add_product_button.setOnAction(Event -> loader.moveToAnotherView(VIEW_CREATE_PRODUCT_FXML));
    }

}
