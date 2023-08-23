package com.example.managestaff.controller;

import com.example.managestaff.model.services.SalarySearchModel;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalarySearchController {

    @FXML
    private TableView<SalarySearchModel>;
    @FXML
    private TableColumn<SalarySearchModel, Integer>;
    @FXML
    private TableColumn<SalarySearchModel, String>;
    @FXML
    private TableColumn<SalarySearchModel, String>;
    @FXML
    private TableColumn<SalarySearchModel, Integer>;

    ObservableList<SalarySearchModel> = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection = connectNow.getDBConnection();

        String timekeepingViewQuery = "SELECT id, present, absent, staff_id FROM timekeeping";

        try{

            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(timekeepingViewQuery);

            while (queryOutput.next()){

                Integer queryid = queryOutput.getInt("id");
                String querypresent = queryOutput.getString("present");
                String queryabsent = queryOutput.getString("absent");
                String querystaff_id = queryOutput.getString("staff_id");

                SalarySearchModelObservableList.add(new SalarySearchModel(queryid, querypresent, queryabsent, querystaff_id));

            }

            idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            presentTableColumn.setCellValueFactory(new PropertyValueFactory<>("present"));
            absentColumn.setCellValueFactory(new PropertyValueFactory<>("absent"));
            staff_idTableColumn.setCellValueFactory(new PropertyValueFactory<>("staff_id"));

            timekeepingTableView.setItems(SalarySearchModelObservableList);

            FilteredList<SalarySearchModel> filteredData = new FilteredList<>(SalarySearchModelObservableList, b ->true);

            keywordTextField.textProperty().addListener(observable, oldValue, newValue) -> {
                filteredData.setPredicate(SalarySearchModel) -> {

                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (SalarySearchModel.getid().toString().indexOf(searchKeyword) > -1){
                        return true;
                    } else if (SalarySearchModel.getpresent().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (SalarySearchModel.getabsent().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if (SalarySearchModel.getstaff_id().toString().indexOf(searchKeyword) > -1){
                        return true;
                    } else
                        return false;

                };
            };

            SortedList<SalarySearchModel> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(timekeepingTableView.comparatorProperty());

            timekeepingTableView.setItems(sortedData);

        } catch(SQLException e) {
            Logger.getLogger(SalarySearchController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

}
