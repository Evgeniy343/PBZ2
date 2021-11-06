package com.example.test.controller.delete;

import com.example.test.ConnectionDB;
import com.example.test.DataExtractor;
import com.example.test.FactoryExtractor;
import com.example.test.FactoryModel;
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

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteFactoryController {
    private static final String WATCH_FACTORIES = "select * from factory";
    private final ObservableList<FactoryModel> factories = FXCollections.observableArrayList();

    @FXML
    private TableColumn<FactoryModel, Integer> amountWorkerColumn;

    @FXML
    private Button delete_factory_button;

    @FXML
    private TableView<FactoryModel> factoryTable;

    @FXML
    private TableColumn<FactoryModel, Date> foundationColumn;

    @FXML
    private TableColumn<FactoryModel, Long> idColumn;

    @FXML
    private TextField id_field;

    @FXML
    private TableColumn<FactoryModel, String> nameColumn;

    @FXML
    void initialize() {

        pushFactories();
        pushTable();

        delete_factory_button.setOnAction(Event -> {
            String query = String.format("delete from factory where id = %s",id_field.getText());
            ConnectionDB.executeUpdate(query,null, OperationPreparatorController.DELETE_FACTORY);
        });
    }

    private void pushTable() {
        factoryTable.getItems().clear();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        foundationColumn.setCellValueFactory(new PropertyValueFactory<>("foundation"));
        amountWorkerColumn.setCellValueFactory(new PropertyValueFactory<>("amountWorker"));

        factoryTable.setItems(factories);
    }

    private void pushFactories() {
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
    }

}
