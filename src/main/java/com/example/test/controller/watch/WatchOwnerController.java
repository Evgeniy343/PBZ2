package com.example.test.controller.watch;

import com.example.test.ConnectionDB;
import com.example.test.DataExtractor;
import com.example.test.OwnerExtractor;
import com.example.test.OwnerModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WatchOwnerController {
    private static final String WATCH_OWNERS = "select * from owner";
    private final ObservableList<OwnerModel> owners = FXCollections.observableArrayList();

    @FXML
    private TableView<OwnerModel> ownerTable;

    @FXML
    private TableColumn<OwnerModel, String> emailColumn;

    @FXML
    private TableColumn<OwnerModel, String> fioColumn;

    @FXML
    private TableColumn<OwnerModel, Long> idColumn;

    @FXML
    private TableColumn<OwnerModel, String> telephoneColumn;

    @FXML
    void initialize() {
        ResultSet resultOwners = ConnectionDB.executeRequest(WATCH_OWNERS,null);
        DataExtractor<OwnerModel> extractor = new OwnerExtractor();
        try {
            while (resultOwners.next()) {
                OwnerModel owner = extractor.extract(resultOwners);
                owners.add(owner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        fioColumn.setCellValueFactory(new PropertyValueFactory<>("fio"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        ownerTable.setItems(owners);
    }

}
