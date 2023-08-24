package com.example.managestaff.controller;

import com.example.managestaff.model.entity.Staff;
import com.example.managestaff.model.entity.UserDataModel;
import com.example.managestaff.model.repository.StaffModel;
import com.example.managestaff.model.services.ExportFile;
import com.example.managestaff.model.services.Oclock;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import com.example.managestaff.model.services.HandleImageFiles;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import javafx.scene.control.TableCell;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.util.Duration;


public class AdminTest implements Initializable {

    @FXML
    private Label staffName1, staffName2, staffName3, staffName4, staffName5,
            staffName6, staffEmail1, staffEmail2, staffEmail3, staffEmail4, staffEmail5, staffEmail6,lable;

    private VBox vboxPagination;

    @FXML
    private Label dashboard_lable1, dashboard_lable2, dashboard_lable3, dashboard_lable4;

    @FXML
    private Label dashboard_totalReport, dashboard_totalRoom,
            dashboard_totalStaff, username, testModel, labelRealTime,
            staffNameInfo, staffTotalStaff, staffActiveStaff,
            staffDeactiveStaff, userDataModelTest;

    @FXML
    private AnchorPane anchorAddAccount, anchorDashBoard,
            anchorInfo, anchorReport, anchorRoom,
            anchorSalary, anchorSetting, anchorStaff, admin_form,
            anchorRegisterForm, anchorInfoStaff;

    @FXML
    private Button btnAccountInfo, btnAddAccount, btnSalary,
            btnDBoard, btnDom, btnReport, btnSetting,
            btnSignOut, btnStaff, btnChooseFile,
            closeAnchorParent2, closeAnchorParent1;

    @FXML
    private JFXButton btnSubmitRegister, btnEditStaffInfo, btnStaffDeleteInfo,
            btnUpdateStaffInfo;

    @FXML
    private FontAwesomeIcon btnRegister, btnReload, btnExport;


    @FXML
    private TableView<Staff> viewDataStaff;

    @FXML
    private TableColumn<Staff, String> colId;

    @FXML
    private TableColumn<Staff, String> colPhone;

    @FXML
    private TableColumn<Staff, String> colName;


    @FXML
    private TableColumn<Staff, String> colGender;

    @FXML
    private TableColumn<Staff, String> colDepartment;
    @FXML
    private TableColumn<Staff, String> colEdit;

    // VAR REGISTER FORM INITIAL
    @FXML
    private JFXTextField staffEmail, staffName, staffPhoneNumber, staffId,
            staffGenderInfo, staffDobInfo,
            staffPhoneInfo, staffEmailInfo, staffDepartmentInfo,
            staffPositionInfo;

    @FXML
    private DatePicker staffDob;

    @FXML
    private JFXRadioButton rollAdmin, rollUser, genMale, genFemale;

    @FXML
    private JFXComboBox<String> listDepartments, listPositions;

    @FXML
    private ToggleGroup gender, radioRoll;

    @FXML
    Circle circlePortrait, staffCircleInfo;
    // VAR REGISTER FORM END

    @FXML
    private TextField account_search;

    @FXML
    private Pagination paginationStaff;

    @FXML
    private ImageView btnClose;

    private ObservableList<String> staffInfo;
    private ObservableList<Staff> listStaff;
    StaffModel dao = new StaffModel();
    AlertMessage alert = new AlertMessage();
    private Label[] staffNameLabels;
    private Label[] staffEmailLabels;

    private UserDataModel userDataModel;

    public void setUserDataModel(UserDataModel userDataModel) {
        this.userDataModel = userDataModel;
    }

    EventHandler<ActionEvent> btnsClose = e -> {
        Button clicked = (Button) e.getSource();
        AnchorPane parentAnchor = (AnchorPane) clicked.getParent();
        parentAnchor.setVisible(false);
    };


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        staffNameLabels = new Label[]{
                staffName1, staffName2, staffName3, staffName4, staffName5, staffName6
        };

        if (userDataModel != null) {
            String username = userDataModel.getUsername();
            System.out.println(username);
            userDataModelTest.setText("Welcome, " + username);
        } else {
            System.out.println("failed");
        }

        staffDeactiveStaff.setText(String.valueOf(StaffModel.getDeactiveStaff()));
        staffActiveStaff.setText(String.valueOf(StaffModel.getActiveStaff()));
        staffTotalStaff.setText(String.valueOf(StaffModel.getToTalStaff()));

        Oclock.runClock(labelRealTime);

        Tooltip closeTooltip = new Tooltip("Close");
        Tooltip.install(closeAnchorParent1, closeTooltip);
        Tooltip.install(closeAnchorParent2, closeTooltip);
        closeAnchorParent1.setOnAction(btnsClose);
        closeAnchorParent2.setOnAction(btnsClose);


        viewDataStaff.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                System.out.println("Double-clicked!");

                Staff selectedStaff = viewDataStaff.getSelectionModel().getSelectedItem();
                anchorInfoStaff.setVisible(true);
                staffNameInfo.setText(selectedStaff.getName());
                if (selectedStaff.getGender() == 0) {
                    staffGenderInfo.setText("Male");
                } else {
                    staffGenderInfo.setText("Female");
                }
                Image image;
                if (selectedStaff.getUrlPortrait() != null) {
                    image = new Image("file:" + selectedStaff.getUrlPortrait());
                    staffCircleInfo.setFill(new ImagePattern(image));
                } else {
                    image = new Image("file:src/main/resources/com/example/managestaff/assets/images/img_staff/non_avatar.png");
                    staffCircleInfo.setFill(new ImagePattern(image));
                }

                staffDobInfo.setText(String.valueOf(selectedStaff.getDob()));
                staffPhoneNumber.setText(selectedStaff.getPhoneNumber());
                staffEmailInfo.setText(selectedStaff.getEmail());

                int departmentId = selectedStaff.getDepartmentId();
                String department = switch (departmentId) {
                    case 1 -> "HR";
                    case 2 -> "BE";
                    case 3 -> "FE";
                    case 4 -> "SALES";
                    case 5 -> "MANAGER";
                    default -> "Unknown";
                };
                staffDepartmentInfo.setText(department);

                int positionId = selectedStaff.getPositionId();
                String position = switch (positionId) {
                    case 1 -> "INTERN";
                    case 2 -> "FRESHER";
                    case 3 -> "JUNIOR";
                    case 4 -> "SENIOR";
                    case 5 -> "CEO";
                    default -> "Unknown";
                };
                staffPositionInfo.setText(position);

                btnStaffDeleteInfo.setOnMouseClicked(e -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure to want delete this acount");
                    Optional<ButtonType> option = alert.showAndWait();
                    if (option.get().equals(ButtonType.OK)) {
                        StaffModel.delete(selectedStaff);
                        anchorInfoStaff.setVisible(false);
                    }
                });

                btnEditStaffInfo.setOnMouseClicked(e -> {
                    staffPhoneInfo.setEditable(true);
                    btnEditStaffInfo.setVisible(false);
                    btnUpdateStaffInfo.setVisible(true);
                });
            }
        });

        Tooltip registerTooltip = new Tooltip("Add New");
        Tooltip.install(btnRegister, registerTooltip);
        btnRegister.setOnMouseClicked(e -> {
            anchorRegisterForm.setVisible(true);
            anchorRegisterForm.toFront();
        });

        Tooltip exportTooltip = new Tooltip("Export to .xlxs");
        Tooltip.install(btnExport, exportTooltip);
        btnExport.setOnMouseClicked(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
            File outputFile = fileChooser.showSaveDialog(viewDataStaff.getScene().getWindow());

            if (outputFile != null) {
                try {
                    ExportFile.exportToExcel(viewDataStaff, outputFile);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnClose.setOnMouseClicked(e -> {
            anchorRegisterForm.setVisible(false);
        });

        TableView.TableViewSelectionModel<Staff> selectionModel = viewDataStaff.getSelectionModel();

        Staff test = selectionModel.getSelectedItem();
        if (test != null) {
            String name = test.getName();
            testModel.setText(test.getEmail());
        }

        Tooltip reloadTooltip = new Tooltip("Reload");
        Tooltip.install(btnReload, reloadTooltip);
        btnReload.setOnMouseClicked(e -> {
            listStaff.clear();
            listStaff = new StaffModel().getAll();
            viewDataStaff.setItems(getItemsForPage(0));
        });

        getTotalStaff();
        getStaffInfo();
        showInfoInLabels();
        AtomicReference<String> pathImgRef = new HandleImageFiles().handleBtn(btnChooseFile, circlePortrait);
        btnSubmitRegister.setOnMouseClicked(e -> {
            int id = Integer.parseInt(staffId.getText());
            String name = staffName.getText();
            JFXRadioButton selectRadioGender = (JFXRadioButton) gender.getSelectedToggle();
            String genderValue = selectRadioGender.getText();
            System.out.println(genderValue);
            int gender = genderValue == "Male" ? 1 : 0;
            Date getDate = Date.valueOf(staffDob.getValue());
            String phoneNumber = staffPhoneNumber.getText();
            String email = staffEmail.getText();
            String department = (String) listDepartments.getValue();
            int departmentId = department == "HR" ? 1 : department == "BE" ? 2 : department == "FE" ? 3 : department == "SALES" ? 4 : 5;
            String position = (String) listPositions.getValue();
            int positionId = position == "INTERN" ? 1 : position == "FRESHER" ? 2 : position == "JUNIOR" ? 3 : position == "SENIOR" ? 4 : 5;
            RadioButton selectRadioRoll = (RadioButton) radioRoll.getSelectedToggle();
            String rollValue = selectRadioRoll.getText();
            int roll = rollValue == "USER" ? 1 : 2;

//             lam thử cái choose language xem

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
                    alert.successMessage("Sucessfull!");
                    anchorRegisterForm.setVisible(false);
                    staffId.clear();
                    staffName.clear();
                    staffPhoneNumber.clear();
                    staffEmail.clear();
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

    @FXML
    void closeAnchor(MouseEvent event) {
    }

    public void getStaffInfo() {
        staffInfo = dao.getFullnames();
    }

    // get the names of the last four users
    public void showInfoInLabels() {
        if (staffInfo != null && staffInfo.size() >= 12) {
            for (int i = 0; i < 6; i++) {
                staffNameLabels[i].setText(staffInfo.get(i * 2));
                staffEmailLabels[i].setText(staffInfo.get(i * 2 + 1));
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

    private static final int ITEMS_PER_PAGE = 20;

    private void loadData() {

        listStaff = new StaffModel().getAll();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colGender.setCellValueFactory(cellData -> {
            Staff staff = cellData.getValue();
            String genderText = staff.getGender() == 1 ? "Female" : "Male";
            return new SimpleStringProperty(genderText);
        });
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colDepartment.setCellValueFactory(cellData -> {
            Staff staff = cellData.getValue();
            int departmentId = staff.getDepartmentId();
            String department = switch (departmentId) {
                case 1 -> "HR";
                case 2 -> "BE";
                case 3 -> "FE";
                case 4 -> "SALES";
                case 5 -> "MANAGER";
                default -> "Unknown";
            };
            return new SimpleStringProperty(department);
        });


        Callback<TableColumn<Staff, String>, TableCell<Staff, String>> handleCell = (TableColumn<Staff, String> param) -> {
            final TableCell<Staff, String> cell = new TableCell<Staff, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        int rowIndex = getIndex() + 1;
                        setText(Integer.toString(rowIndex));
                    }
                }

            };
            return cell;
        };
        colEdit.setCellFactory(handleCell);

        int pageCount = (int) Math.ceil((double) listStaff.size() / ITEMS_PER_PAGE);
        paginationStaff.setPageCount(pageCount);
        paginationStaff.setPageFactory(this::createPage);
        viewDataStaff.setItems(getItemsForPage(0));

        FilteredList<Staff> filteredList = new FilteredList<>(listStaff);
        account_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(Staff -> {
                if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
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
            viewDataStaff.setItems(filteredList);
        });
        SortedList<Staff> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(viewDataStaff.comparatorProperty());
        viewDataStaff.setItems(getItemsForPage(0));
    }

    private Node createPage(int pageIndex) {
        viewDataStaff.setItems(getItemsForPage(pageIndex));
        return viewDataStaff;
    }

    private ObservableList<Staff> getItemsForPage(int pageIndex) {
        int fromIndex = pageIndex * ITEMS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ITEMS_PER_PAGE, listStaff.size());
        return FXCollections.observableArrayList(listStaff.subList(fromIndex, toIndex));
    }

}

