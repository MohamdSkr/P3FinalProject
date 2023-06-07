package Controller;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PatientRegisterController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private RadioButton maleChoice;
    @FXML
    private Button registerBtn;
    @FXML
    private ToggleGroup genderChoice;
    @FXML
    private ToggleGroup roleChoice;
    @FXML
    private TextField ageField;
    @FXML
    private RadioButton femaleChoice;
    @FXML
    private RadioButton patinetChoice;
    @FXML
    private RadioButton adminChoice;
    @FXML
    private Button backBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void registerBtnHandel(ActionEvent event) throws SQLException {

        boolean empty
                = usernameField.getText().equals("")
                || passwordField.getText().equals("")
                || firstNameField.getText().equals("")
                || lastNameField.getText().equals("")
                || ageField.getText().equals("")
                || phoneField.getText().equals("")
                || emailField.getText().equals("");

        if (empty) {
            AlertMessage.warningAlertMessage("Empty Fields", "Enter values to all fields tp register.");
        } else {

            User user = new User();
            user.setUsername(usernameField.getText());
            user.setPassword(passwordField.getText());
            user.setFirstName(firstNameField.getText());
            user.setLastName(lastNameField.getText());
            user.setAge(Integer.parseInt(ageField.getText()));
            user.setPhone(phoneField.getText());
            user.setEmail(emailField.getText());
            user.setGender(((RadioButton) genderChoice.getSelectedToggle()).getText());
            user.setRole(((RadioButton) roleChoice.getSelectedToggle()).getText());

            if (!user.found()) {
                if (user.add() > 0) {
                    AlertMessage.successAlertMessage("Add User", "New User is add successfuly");
                    clearFields();
                }
            } else {
                AlertMessage.warningAlertMessage("User is exist", "User is found try another data.");

            }

        }

    }

    @FXML
    private void backBtnHandel(ActionEvent event) throws IOException {
        View.ViewManager.closePatientRegister();
        View.ViewManager.openBasicLogin();
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        ageField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        maleChoice.setSelected(true);
        patinetChoice.setSelected(true);
    }

}
