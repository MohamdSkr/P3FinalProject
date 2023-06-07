package Controller.AdminDashboard;

import Controller.AdminLoginController;
import Controller.AlertMessage;
import Modul.Appointment;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class CreateAppointmentController implements Initializable {

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
    private Label dateLabel;
    @FXML
    private DatePicker dateField;
    @FXML
    private Label timeLabel;
    @FXML
    private TextField timeField;
    @FXML
    private Button backBtn;
    @FXML
    private Button addBtn;
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
    private void logoutBtnHandel(ActionEvent event) throws IOException {
        View.ViewManager.closeAdminDashboard();
        View.ViewManager.openBasicLogin();
        View.ViewManager.adminDashboard.showPatientsPage();
        AdminLoginController.admin = null;
    }

    @FXML
    private void backBtnHandel(ActionEvent event) {
        dateField.setValue(null);
        timeField.setText("");
        View.ViewManager.adminDashboard.showAppointmentsPage();
    }

    @FXML
    private void addBtnHandel(ActionEvent event) throws SQLException {

        Appointment a = new Appointment();
        a.setDate(Date.valueOf(dateField.getValue()));
        a.setDay(dateField.getValue().getDayOfWeek().toString());
        a.setTime(timeField.getText());
        a.setStatus("free");

        if (!a.found()) {
            a.add();
            View.ViewManager.adminDashboard.showAppointmentsPage();
            AlertMessage.informationAlertMessage("Add Appointment", "New appointment is add successfuly");
            dateField.setValue(null);
            timeField.setText("");
        }else {
            AlertMessage.warningAlertMessage("Appointment Exist", "The appointment is already exist");
        }

    }

    @FXML
    private void finshBtnHandel(ActionEvent event) {
        View.ViewManager.adminDashboard.showFinshedAppointmentsPage();
    }

}
