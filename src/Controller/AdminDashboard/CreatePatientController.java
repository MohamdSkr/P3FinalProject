package Controller.AdminDashboard;

import Controller.AdminLoginController;
import Modul.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

public class CreatePatientController implements Initializable {

    @FXML
    private ImageView adminImg;
    @FXML
    private Button bookedBtn;
    @FXML
    private Button appointmentsBtn;
    @FXML
    private Button patientsBtn;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private Label gender;
    @FXML
    private RadioButton maleChoice;
    @FXML
    private Button addBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private TextField ageField;
    @FXML
    private RadioButton femaleChoice;
    @FXML
    private ToggleGroup genderChoice;
    @FXML
    private ToggleGroup roleChoice;
    @FXML
    private Label role;
    @FXML
    private RadioButton patientChoice;
    @FXML
    private RadioButton adminChoice;
    @FXML
    private Button finshBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void addBtnHandel(ActionEvent event) throws SQLException {

        String username = usernameField.getText();
        String password = passwordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String email = emailField.getText();
        String phone = phoneField.getText();
        String gender = ((RadioButton) genderChoice.getSelectedToggle()).getText();
        String role = ((RadioButton) roleChoice.getSelectedToggle()).getText();

        User patient = new User(username, password, firstName, lastName, age, email, phone, gender, role);

        patient.add();

        View.ViewManager.adminDashboard.showPatientsPage();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Patient");
        alert.setContentText("New patient is add successfuly");
        alert.showAndWait();

    }

    @FXML
    private void backBtnHandel(ActionEvent event) {
        View.ViewManager.adminDashboard.showPatientsPage();
    }

    @FXML
    private void logoutBtnHandel(ActionEvent event) throws IOException {
        View.ViewManager.closeAdminDashboard();
        View.ViewManager.openBasicLogin();
        View.ViewManager.adminDashboard.showPatientsPage();
        AdminLoginController.admin = null;
    }

    @FXML
    private void finshBtnHandel(ActionEvent event) {
        View.ViewManager.adminDashboard.showFinshedAppointmentsPage();
    }

}
