package com.example.test.controller;

import com.example.test.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EditController {
    private static final String VIEW_EDIT_FACTORY_FXML = "/com/example/test/edit-factory.fxml";
    private static final String VIEW_EDIT_PRODUCT_FXML = "/com/example/test/edit-product.fxml";

    @FXML
    private Button edit_factory_button;

    @FXML
    private Button edit_factory_product;

    @FXML
    void initialize() {

        ViewLoader loader = ViewLoader.getInstance();

        edit_factory_button.setOnAction(Event -> loader.moveToAnotherView(VIEW_EDIT_FACTORY_FXML));

        edit_factory_product.setOnAction(Event -> loader.moveToAnotherView(VIEW_EDIT_PRODUCT_FXML));
    }

}
