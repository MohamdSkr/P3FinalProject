package Controller.AdminDashboard;

import Modul.User;
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

public class UpdatePatientController implements Initializable {

    private User oldPatinet;

    @FXML
    private ImageView adminImg;
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
    private Button backBtn;
    @FXML
    private TextField ageField;
    @FXML
    private RadioButton femaleChoice;
    @FXML
    private ToggleGroup genderChoice;
    @FXML
    private Label role;
    @FXML
    private RadioButton patientChoice;
    @FXML
    private ToggleGroup roleChoice;
    @FXML
    private RadioButton adminChoice;
    @FXML
    private Button updateBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        oldPatinet = Controller.AdminDashboard.PatientPageFXMLController.patientUpdate;
        
        usernameField.setText(oldPatinet.getUsername());
        passwordField.setText(oldPatinet.getPassword());
        firstNameField.setText(oldPatinet.getFirstName());
        lastNameField.setText(oldPatinet.getLastName());
        ageField.setText(String.valueOf(oldPatinet.getAge()));
        emailField.setText(oldPatinet.getEmail());
        phoneField.setText(oldPatinet.getPhone());
        
        if (oldPatinet.getGender().equals("female")) {
            genderChoice.selectToggle(femaleChoice);
        }
        
        if (oldPatinet.getRole().equals("admin")) {
            roleChoice.selectToggle(adminChoice);
        }
        
    }

    @FXML
    private void backBtnHandel(ActionEvent event) {
        PatientPageFXMLController.updateStage.close();
    }

    @FXML
    private void updateBtnHandel(ActionEvent event) throws SQLException {
        
        User patient = new User();
        patient.setUsername(usernameField.getText());
        patient.setPassword(passwordField.getText());
        patient.setFirstName(firstNameField.getText());
        patient.setLastName(lastNameField.getText());
        patient.setAge(Integer.parseInt(ageField.getText()));
        patient.setPhone(phoneField.getText());
        patient.setEmail(emailField.getText());
        patient.setGender(((RadioButton) genderChoice.getSelectedToggle()).getText());
        patient.setRole(((RadioButton) roleChoice.getSelectedToggle()).getText());
        patient.setId(oldPatinet.getId());
        
        patient.update();
        
        Controller.AdminDashboard.PatientPageFXMLController.patientUpdate = null;
        Controller.AdminDashboard.PatientPageFXMLController.updateStage.close();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update Patient");
        alert.setContentText("The patient is updated successfuly");
        alert.showAndWait();
        
        
    }
}
