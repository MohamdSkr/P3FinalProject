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

public class AdminLoginController implements Initializable {

    public static User admin = null;

    @FXML
    private Label passwordLabel;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginBtn;
    @FXML
    private ImageView adminLogo;
    @FXML
    private Label adminLabel;
    @FXML
    private TextField adminField;
    @FXML
    private Button backBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void loginBtnHandel(ActionEvent event) throws IOException, SQLException {
        admin = User.getAdminByUsername(adminField.getText(), passwordField.getText());
        if (adminField.getText().equals("") || passwordField.getText().equals("")) {
            AlertMessage.warningAlertMessage("Empty Fields", "Enter username & password to login.");
        } else if (admin == null) {
            AlertMessage.warningAlertMessage("Admin Not Found", "Amdin not found.");
        } else {
            View.ViewManager.closeAdminLogin();
            View.ViewManager.openAdminDashboard();
            adminField.setText("");
            passwordField.setText("");
        }
    }

    @FXML
    private void backBtnBtnHandel(ActionEvent event) throws IOException {
        View.ViewManager.closeAdminLogin();
        View.ViewManager.openBasicLogin();
    }

}
