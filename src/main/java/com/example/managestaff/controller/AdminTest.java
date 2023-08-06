package com.example.managestaff.controller;

import com.example.managestaff.model.entity.Staff;
import com.example.managestaff.model.repository.StaffModel;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.control.TableCell;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;


public class AdminTest implements Initializable {

    @FXML
    private Label dashboard_lable1, dashboard_lable2, dashboard_lable3, dashboard_lable4;

    @FXML
    private Label dashboard_totalReport, dashboard_totalRoom, dashboard_totalStaff, username;

    @FXML
    private AnchorPane anchorAddAccount, anchorDashBoard, anchorInfo,
            anchorReport, anchorRoom, anchorSalary,
            anchorSetting, anchorStaff, admin_form;

    @FXML
    private Button btnAccountInfo, btnAddAccount, btnSalary,
            btnDBoard, btnDom, btnReport,
            btnSetting, btnSignOut, btnStaff;

    @FXML
    private Button account_btnAdd, account_btnClear, account_btnDelete,
            account_btnImport, account_btnUpdate;
    @FXML
    private DatePicker account_dob;

    @FXML
    private TableColumn<Staff, String> account_col_deparmentID, account_col_dob, account_col_email,
            account_col_fullName, account_col_gender, account_col_phone,
            account_col_positionID, account_col_staffID, account_col_status, account_col_view;

    @FXML
    private TextField account_email, account_fullName, account_phone,
            account_positonID, account_search, account_staffID, account_deparmentID, account_gender;
    private ComboBox<?> account_status;

    @FXML
    private ImageView account_image;
    @FXML
    private TableView<Staff> account_tableView;
    @FXML
    private Pagination paginationStaff;
    @FXML

    private ObservableList<Staff> addStaffList;
    private ObservableList<String> staffInfo;
    StaffModel dao = new StaffModel();
    AlertMessage alert = new AlertMessage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashboardTotalStaff();
        showInfoInLabels();
        showInforStaff();
        addStaffShowListData();
    }

    public void dashboardTotalStaff() {
        int total = dao.getAll();
        dashboard_totalStaff.setText(String.valueOf(total));
    }

    public void showInforStaff() {
        staffInfo = dao.getFullnames();
        System.out.println(staffInfo);
    }

    // get the names of the last four users
    public void showInfoInLabels() {

        if (staffInfo != null && staffInfo.size() >= 4) {
            String staff1 = staffInfo.get(0);
            String staff2 = staffInfo.get(1);
            String staff3 = staffInfo.get(2);
            String staff4 = staffInfo.get(3);

            dashboard_lable1.setText(staff1);
            dashboard_lable2.setText(staff2);
            dashboard_lable3.setText(staff3);
            dashboard_lable4.setText(staff4);
        } else {
        }
    }

    public void addStaffShowListData() {
        addStaffList = dao.addStaff();
        account_col_staffID.setCellValueFactory(new PropertyValueFactory<>("id"));
        account_col_fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        account_col_dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        account_col_phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        account_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        Staff staff = account_tableView.getSelectionModel().getSelectedItem();
        Callback<TableColumn<Staff, String>, TableCell<Staff, String>> cellFoctory = (TableColumn<Staff, String> param) -> {
            // make cell containing buttons
            final TableCell<Staff, String> cell = new TableCell<Staff, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button editBtn = new Button("Show Detail");
                        editBtn.setStyle(
                                "-fx-cursor:hand;" +
                                        "-fx-background-color:#266fd5;"
                                        + "-fx-text-fill:#ffff;"
                                        + "-fx-font-family:Gill Sans MT ;"
                                        + "-fx-font-size:15px;"
                        );
                        editBtn.setOnAction(event -> {
                            Staff staff = getTableView().getSelectionModel().getSelectedItem();
                            try {
                                Parent parent = FXMLLoader.load(getClass().getResource("/com/example/managestaff/view/staffInfo.fxml"));
                                Scene scene = new Scene(parent);
                                Stage stage = new Stage();
                                stage.setScene(scene);

                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        setGraphic(editBtn);
                        setText(null);
                    }
                }

                ;

            };
            return cell;
        };
        account_col_view.setCellFactory(cellFoctory);
        account_tableView.setItems(addStaffList);
        FilteredList<Staff> filteredList = new FilteredList<>(addStaffList);
        account_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(Staff -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (String.valueOf(Staff.getId()).contains(searchKeyword)) {
                    return true;
                } else if (Staff.getFullName().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (Staff.getDob().toString().contains(searchKeyword)) {
                    return true;
                } else if (Staff.getPhoneNumber().contains(searchKeyword)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Staff> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(account_tableView.comparatorProperty());
        account_tableView.setItems(sortedList);
    }

    @FXML
    private void handleClick(ActionEvent event) {
        anchorDashBoard.setVisible(event.getSource() == btnDBoard);
        anchorStaff.setVisible(event.getSource() == btnStaff);
        anchorReport.setVisible(event.getSource() == btnReport);
        anchorRoom.setVisible(event.getSource() == btnDom);
        anchorAddAccount.setVisible(event.getSource() == btnAddAccount);
        anchorSalary.setVisible(event.getSource() == btnSalary);
        anchorInfo.setVisible(event.getSource() == btnAccountInfo);
        anchorSetting.setVisible(event.getSource() == btnSetting);

    }

    public void exit() {
        System.exit(0);
    }

    private double x = 0;
    private double y = 0;

    public void logout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {
                btnSignOut.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/managestaff/view/login.fxml"));
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                Scene scene = new Scene(root);
                root.setOnMousePressed(event -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });
                root.setOnMouseDragged(event -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
