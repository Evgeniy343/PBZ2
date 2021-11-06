package com.example.test.controller.edit;

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

public class EditFactoryController {
    private static final String WATCH_FACTORIES = "select * from factory";
    private final ObservableList<FactoryModel> factories = FXCollections.observableArrayList();

    @FXML
    private TableColumn<FactoryModel, Integer> amountWorkerColumn;

    @FXML
    private TextField amountWorkerField;

    @FXML
    private Button editButton;

    @FXML
    private TableView<FactoryModel> factoryTable;

    @FXML
    private TableColumn<FactoryModel, Date> foundationColumn;

    @FXML
    private TableColumn<FactoryModel, Long> idColumn;

    @FXML
    private TextField idField;

    @FXML
    private TableColumn<FactoryModel, String> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField foundationField;



    @FXML
    void initialize() {

        pushFactories();
        pushTable();

        editButton.setOnAction(Event -> {
            String query = "update factory set f_name = ?, foundation = cast(? as date), amount_worker = ? where id = ?";
            CreateContext context = new CreateContext(Integer.parseInt(idField.getText()),nameField.getText()
                    ,foundationField.getText(),Integer.parseInt(amountWorkerField.getText())
                    ,null,null,0);
            ConnectionDB.executeUpdate(query,context, OperationPreparatorController.EDIT_FACTORY);
        });

    }

    private void pushTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        foundationColumn.setCellValueFactory(new PropertyValueFactory<>("foundation"));
        amountWorkerColumn.setCellValueFactory(new PropertyValueFactory<>("amountWorker"));

        factoryTable.setItems(factories);
    }

    private void pushFactories() {
        factoryTable.getItems().clear();
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
