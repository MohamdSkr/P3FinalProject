package Controller.AdminDashboard;

import Controller.AdminLoginController;
import Controller.AlertMessage;
import Modul.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PatientPageFXMLController implements Initializable {

    public static User patientUpdate = null;
    public static Stage updateStage;

    @FXML
    private ImageView adminImg;
    @FXML
    private Button bookedBtn;
    @FXML
    private Button appointmentsBtn;
    @FXML
    private Button patientsBtn;
    @FXML
    private Button createBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchBtn;
    @FXML
    private TableView<User> patientsTable;
    @FXML
    private TableColumn<User, String> firstName;
    @FXML
    private TableColumn<User, String> lastName;
    @FXML
    private TableColumn<User, Integer> age;
    @FXML
    private TableColumn<User, String> gender;
    @FXML
    private TableColumn<User, String> phone;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> role;
    @FXML
    private Button logoutBtn;
    @FXML
    private Button refershBtn;
    @FXML
    private Button finshBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        firstName.setCellValueFactory(new PropertyValueFactory("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory("lastName"));
        age.setCellValueFactory(new PropertyValueFactory("age"));
        gender.setCellValueFactory(new PropertyValueFactory("gender"));
        phone.setCellValueFactory(new PropertyValueFactory("phone"));
        email.setCellValueFactory(new PropertyValueFactory("email"));
        role.setCellValueFactory(new PropertyValueFactory("role"));

        try {
            refershBtnHandel(new ActionEvent());
        } catch (SQLException ex) {
            Logger.getLogger(PatientPageFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void bookedBtnHandel(ActionEvent event) {
        View.ViewManager.adminDashboard.showBookedAppointmentsPage();
    }

    @FXML
    private void appointmentsBtnHandel(ActionEvent event) {
        View.ViewManager.adminDashboard.showAppointmentsPage();
    }

    @FXML
    private void patientsBtnHandel(ActionEvent event) {
        View.ViewManager.adminDashboard.showPatientsPage();
    }

    @FXML
    private void createBtnHandel(ActionEvent event) {
        View.ViewManager.adminDashboard.showCreatePatientPage();
    }

    @FXML
    private void updateBtnHandel(ActionEvent event) throws IOException {

        patientUpdate = patientsTable.getSelectionModel().getSelectedItem();
        if (patientUpdate == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Choose Patient");
            alert.setContentText("Please choose patient to update information.");
            alert.show();

        } else {
            FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/View/AdminDashboard/updatePatient.fxml"));
            Parent rootUpdate = loaderUpdate.load();
            Scene updatePatientScene = new Scene(rootUpdate);
            //store loaded fxml in our global stage updateStage and show it
            updateStage = new Stage();
            updateStage.setScene(updatePatientScene);
            updateStage.setTitle("Update patient " + patientUpdate.getUsername());
            updateStage.show();

        }

    }

    @FXML
    private void deleteBtnHandel(ActionEvent event) throws SQLException {

        User patient = patientsTable.getSelectionModel().getSelectedItem();
        if (patient == null) {
            AlertMessage.warningAlertMessage("Choose Patient", "Please choose patient to delete it.");
        } else {

            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("User delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this user ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        //delete the selected user from database table using delete method in our User model
                        patient.delete();
                        refershBtnHandel(event);
                        AlertMessage.successAlertMessage("Delete Patient", "The patient is deleted successfuly.");
                    } catch (Exception e) {
                        e.getMessage();
                    }

                }
            });
        }
    }

    @FXML

    private void searchBtnHandel(ActionEvent event) throws SQLException {
        ObservableList<User> patients = FXCollections.observableArrayList(User.search(searchField.getText()));
        patientsTable.getItems().setAll(patients);
    }

    @FXML
    private void logoutBtnHandel(ActionEvent event) throws IOException {
        View.ViewManager.closeAdminDashboard();
        View.ViewManager.openBasicLogin();
        View.ViewManager.adminDashboard.showPatientsPage();
        AdminLoginController.admin = null;
    }

    @FXML
    private void refershBtnHandel(ActionEvent event) throws SQLException {
        ObservableList<User> patients = FXCollections.observableArrayList(User.getAllPatient());
        patientsTable.getItems().setAll(patients);
    }

    @FXML
    private void finshBtnHandel(ActionEvent event) {
        View.ViewManager.adminDashboard.showFinshedAppointmentsPage();
    }

}
