package com.example.test.controller;

import com.example.test.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WatchController {
    private static final String VIEW_WATCH_FACTORY_FXML = "/com/example/test/watch-factory.fxml";
    private static final String VIEW_WATCH_PRODUCT_FXML = "/com/example/test/watch-product.fxml";
    private static final String VIEW_WATCH_OWNER_FXML = "/com/example/test/watch-owner.fxml";

    @FXML
    private Button watch_factory_button;

    @FXML
    private Button watch_owner_button;

    @FXML
    private Button watch_product_button;

    @FXML
    void initialize() {

        ViewLoader loader = ViewLoader.getInstance();

        watch_factory_button.setOnAction(Event -> loader.moveToAnotherView(VIEW_WATCH_FACTORY_FXML));

        watch_owner_button.setOnAction(Event -> loader.moveToAnotherView(VIEW_WATCH_OWNER_FXML));

        watch_product_button.setOnAction(Event -> loader.moveToAnotherView(VIEW_WATCH_PRODUCT_FXML));
    }

}
