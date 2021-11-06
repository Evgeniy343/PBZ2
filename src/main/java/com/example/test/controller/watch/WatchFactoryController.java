package com.example.test.controller.watch;

import com.example.test.ConnectionDB;
import com.example.test.DataExtractor;
import com.example.test.FactoryExtractor;
import com.example.test.FactoryModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class WatchFactoryController {

    private static final String WATCH_FACTORIES = "select * from factory";
    private final ObservableList<FactoryModel> factories = FXCollections.observableArrayList();

    @FXML
    private TableView<FactoryModel> factoryTable;

    @FXML
    private TableColumn<FactoryModel, Long> idColumn;

    @FXML
    private TableColumn<FactoryModel, String> nameColumn;

    @FXML
    private TableColumn<FactoryModel, Date> foundationColumn;

    @FXML
    private TableColumn<FactoryModel, Integer> amountWorkerColumn;

    @FXML
    private void initialize() {

        ResultSet resultFactory = ConnectionDB.executeRequest(WATCH_FACTORIES,null);
        DataExtractor<FactoryModel> extractor = new FactoryExtractor();
        try {
            while (resultFactory.next()) {
                FactoryModel factory = extractor.extract(resultFactory);
                factories.add(factory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        foundationColumn.setCellValueFactory(new PropertyValueFactory<>("foundation"));
        amountWorkerColumn.setCellValueFactory(new PropertyValueFactory<>("amountWorker"));

        factoryTable.setItems(factories);
    }

}
