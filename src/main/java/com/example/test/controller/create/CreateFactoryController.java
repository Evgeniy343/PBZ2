package com.example.test.controller.create;

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

public class CreateFactoryController {
    private static final String WATCH_FACTORIES = "select * from factory";
    private final ObservableList<FactoryModel> factories = FXCollections.observableArrayList();

    @FXML
    private TableView<FactoryModel> factoryTable;

    @FXML
    private TableColumn<FactoryModel, Integer> amountWorkerColumn;

    @FXML
    private TextField amountWorkerField;

    @FXML
    private Button createButton;

    @FXML
    private TableColumn<FactoryModel, Date> foundationColumn;

    @FXML
    private TextField foundationField;

    @FXML
    private TableColumn<FactoryModel, Long> idColumn;

    @FXML
    private TableColumn<FactoryModel, String> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    void initialize() {


        pushFactories();
        pushTable();

        createButton.setOnAction(Event -> {
            Integer id = factories.size() + 1;
            String factoryName = nameField.getText();
            String foundation = foundationField.getText();
            Integer amountWorker = Integer.parseInt(amountWorkerField.getText());

            CreateContext factoryContext = new CreateContext(id,factoryName,foundation,amountWorker,
                    null,null,0);

            String query = "insert into factory(id,f_name,foundation,amount_worker) values (?,?,cast(? as date),?)";
            ConnectionDB.executeUpdate(query,factoryContext, OperationPreparatorController.CREATE_FACTORY);

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
