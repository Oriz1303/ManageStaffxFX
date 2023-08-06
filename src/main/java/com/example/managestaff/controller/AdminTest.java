package com.example.managestaff.controller;

import com.example.managestaff.model.entity.Staff;
import com.example.managestaff.model.repository.StaffModel;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import com.example.managestaff.model.services.HandleImageFiles;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import javafx.scene.control.TableCell;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;


public class AdminTest implements Initializable {

    @FXML
    private Label dashboard_lable1, dashboard_lable2, dashboard_lable3, dashboard_lable4;

    @FXML
    private Label dashboard_totalReport, dashboard_totalRoom, dashboard_totalStaff, username;

    @FXML
    private AnchorPane anchorAddAccount, anchorDashBoard, anchorInfo, anchorReport, anchorRoom, anchorSalary, anchorSetting, anchorStaff, admin_form;

    @FXML
    private Button btnAccountInfo, btnAddAccount, btnSalary, btnDBoard, btnDom, btnReport, btnSetting, btnSignOut, btnStaff, btnChooseFile, btnSubmitRegister;

    @FXML
    private TableView<Staff> viewDataStaff;

    @FXML
    private TableColumn<Staff, String> colDepartment, colEdit, colGender, colId, colName, colPhone;

    // VAR REGISTER FORM INITIAL
    @FXML
    private TextField staffEmail, staffName, staffPhoneNumber, staffId;

    @FXML
    private DatePicker staffDob;

    @FXML
    private RadioButton rollAdmin, rollUser, genMale, genFemale;

    @FXML
    private ChoiceBox<String> listDepartments, listPositions;

    @FXML
    private ToggleGroup gender, radioRoll;

    @FXML
    Circle circlePortrait;
    // VAR REGISTER FORM END

    @FXML
    private TextField account_search;

    @FXML
    private Pagination paginationStaff;

    @FXML

    private ObservableList<String> staffInfo;
    private ObservableList<Staff> listStaff;
    StaffModel dao = new StaffModel();
    AlertMessage alert = new AlertMessage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getTotalStaff();
        showInfoInLabels();
        getStaffInfo();
        AtomicReference<String> pathImgRef = new HandleImageFiles().handleBtn(btnChooseFile, circlePortrait);
        btnSubmitRegister.setOnMouseClicked(e -> {
            int id = Integer.parseInt(staffId.getText());
            String name = staffName.getText();
            RadioButton selectRadioGender = (RadioButton) gender.getSelectedToggle();
            String genderValue = selectRadioGender.getText();
            int gender = genderValue == "Male" ? 1 : 0;
            Date getDate = Date.valueOf(staffDob.getValue());
            String phoneNumber = staffPhoneNumber.getText();
            String email = staffEmail.getText();
            String department = listDepartments.getValue();
            int departmentId = department == "HR" ? 1 : department == "BE" ? 2 : department == "FE" ? 3 : department == "SALES" ? 4 : 5;
            String position = listPositions.getValue();
            int positionId = position == "INTERN" ? 1 : position == "FRESHER" ? 2 : position == "JUNIOR" ? 3 : position == "SENIOR" ? 4 : 5;
            RadioButton selectRadioRoll = (RadioButton) radioRoll.getSelectedToggle();
            String rollValue = selectRadioRoll.getText();
            int roll = rollValue == "USER" ? 1 : 2;

            //VALIDATE REGISTER
            if (name.trim().isBlank() || email.trim().isBlank() || phoneNumber.trim().isBlank()) {
                alert.errorMessage("All fieds are necessary to be fileds");
            } else if (isIdDuplicate(id) || id <= 0) {
                alert.errorMessage("ID already exits");
            } else if (!name.matches("^.{8,}$") || name.length() < 8) {
                alert.errorMessage("Names can only contain letters and spaces");
            } else if (!phoneNumber.matches("^0\\d{9}$")) {
                alert.errorMessage("Invalid phone number");
            } else if (!email.matches("^^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
                alert.errorMessage("Invalid Email");
            } else {
                String pathImg;
                if (name != null) {
                    pathImg = pathImgRef.get();
                    Staff staff = new Staff(id, name, gender, getDate, phoneNumber, email, departmentId, positionId, pathImg);
                    StaffModel.add(staff);
                    alert.successMessage("Sucess full");
                }
            }
        });
        loadData();
        ObservableList<String> departments = FXCollections.observableArrayList("HR", "BE", "FE", "SALES", "MANAGER");
        ObservableList<String> positions = FXCollections.observableArrayList("INTERN", "FRESHER", "JUNIOR", "SENIOR", "CEO");
        listDepartments.setItems(departments);
        listDepartments.setValue("HR");
        listPositions.setItems(positions);
        listPositions.setValue("INTERN");
    }

    private boolean isIdDuplicate(int id) {
        for (Staff staff : listStaff) {
            if (staff.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void getTotalStaff() {
        int total = dao.getToTalStaff();
        dashboard_totalStaff.setText(String.valueOf(total));
    }

    public void getStaffInfo() {
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
            if (dashboard_lable1 != null && dashboard_lable2 != null && dashboard_lable3 != null && dashboard_lable4 != null) {
                dashboard_lable1.setText(staff1);
                dashboard_lable2.setText(staff2);
                dashboard_lable3.setText(staff3);
                dashboard_lable4.setText(staff4);
            } else {
                System.out.println("Lỗi");
            }
        } else {
            System.out.println("Lỗi");
        }
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

    private void loadData() {
        listStaff = new StaffModel().getAll();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colDepartment.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        Callback<TableColumn<Staff, String>, TableCell<Staff, String>> handleCell = (TableColumn<Staff, String> param) -> {
            final TableCell<Staff, String> cell = new TableCell<Staff, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button button = new Button();
                        button.setText("View 🔍");
                        button.setStyle("-glyph-size:40px");
                        button.setOnMouseClicked((MouseEvent event) -> {
                            Staff staff = viewDataStaff.getSelectionModel().getSelectedItem();
                        });
                        setGraphic(button);
                        setText(null);
                    }
                }

            };
            return cell;
        };
        colEdit.setCellFactory(handleCell);
        viewDataStaff.setItems(listStaff);
        FilteredList<Staff> filteredList = new FilteredList<>(listStaff);
        account_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(Staff -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (String.valueOf(Staff.getId()).contains(searchKeyword)) {
                    return true;
                } else if (Staff.getName().toLowerCase().contains(searchKeyword)) {
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
        sortedList.comparatorProperty().bind(viewDataStaff.comparatorProperty());
        viewDataStaff.setItems(sortedList);
    }
}
