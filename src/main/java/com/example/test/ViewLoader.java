package com.example.test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class ViewLoader {
    private static ViewLoader instance;

    public static ViewLoader getInstance(){
        if(instance == null){
            instance = new ViewLoader();
        }
        return instance;
    }

    public void moveToAnotherView(String fxmlName) {
        try {
            AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlName)));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
