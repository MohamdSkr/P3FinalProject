package Controller.AdminDashboard;

import Controller.AdminLoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AddCommentController implements Initializable {

    @FXML
    private ImageView adminImg;
    @FXML
    private Button bookedBtn;
    @FXML
    private Button appointmentsBtn;
    @FXML
    private Button patientsBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private Label patientLabel;
    @FXML
    private TextField patientField;
    @FXML
    private Label appointmentsLabel;
    @FXML
    private TextField appointmentsField;
    @FXML
    private TextArea commentArea;
    @FXML
    private Button addCommentBtn;
    @FXML
    private Button backBtn;

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
    private void logoutBtnHandel(ActionEvent event) throws IOException {
        View.ViewManager.closeAdminDashboard();
        View.ViewManager.openBasicLogin();
        View.ViewManager.adminDashboard.showPatientsPage();
        AdminLoginController.admin = null;
    }

    @FXML
    private void addCommentBtnHandel(ActionEvent event) {
    }

    @FXML
    private void backBtnHandel(ActionEvent event) {
        View.ViewManager.adminDashboard.showBookedAppointmentsPage();
    }
    
}
