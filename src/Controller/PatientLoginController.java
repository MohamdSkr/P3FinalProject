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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class PatientLoginController implements Initializable {

    public static User patient;
    @FXML
    private ImageView patientLogo;
    @FXML
    private Label patientLabel;
    @FXML
    private TextField patientField;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginBtn;
    @FXML
    private Button backBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    @FXML
    private void loginBtnHandel(ActionEvent event) throws IOException, SQLException {
        patient = User.getPatientByUsername(patientField.getText(), passwordField.getText());
        if (passwordField.getText().equals("") || passwordField.getText().equals("") || patient == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Patient not found!");
            alert.setContentText("The patient username is not found");
            alert.showAndWait();
        } else {
            View.ViewManager.closePatientLogin();
            View.ViewManager.openPatientDashboard();
            patientField.setText("");
            passwordField.setText("");
        }
    }

    @FXML
    private void backBtnHandel(ActionEvent event) throws IOException {
        View.ViewManager.closePatientLogin();
        View.ViewManager.openBasicLogin();
    }

}
