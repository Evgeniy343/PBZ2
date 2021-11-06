package com.example.test.controller;

import com.example.test.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class OpportunityController {

    private static final String VIEW_CREATE_FXML = "/com/example/test/view-create.fxml";
    private static final String VIEW_DELETE_FXML = "/com/example/test/view-delete.fxml";
    private static final String VIEW_EDIT_FXML = "/com/example/test/view-edit.fxml";
    private static final String VIEW_WATCH_FXML = "/com/example/test/view-watch.fxml";
    private static final String FACTORY_TO_PRODUCT_FXML = "/com/example/test/watch-connect-factory-to-product.fxml";


    @FXML
    private Button create_button;

    @FXML
    private Button delete_button;

    @FXML
    private Button edit_button;

    @FXML
    private Button watch_button;

    @FXML
    private Button mainButton;

    @FXML
    void initialize() {

        ViewLoader loader = ViewLoader.getInstance();

        create_button.setOnAction(Event -> loader.moveToAnotherView(VIEW_CREATE_FXML));

        delete_button.setOnAction(Event -> loader.moveToAnotherView(VIEW_DELETE_FXML));

        edit_button.setOnAction(Event -> loader.moveToAnotherView(VIEW_EDIT_FXML));

        watch_button.setOnAction(Event -> loader.moveToAnotherView(VIEW_WATCH_FXML));

        mainButton.setOnAction(Event -> loader.moveToAnotherView(FACTORY_TO_PRODUCT_FXML));
    }

}
