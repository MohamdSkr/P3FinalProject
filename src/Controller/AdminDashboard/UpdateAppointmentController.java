package Controller.AdminDashboard;

import Controller.AdminLoginController;
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

public class UpdateAppointmentController implements Initializable {

    private Appointment oldAppointment;

    @FXML
    private ImageView adminImg;
    @FXML
    private Label dateLabel;
    @FXML
    private DatePicker dateField;
    @FXML
    private Label timeLabel;
    @FXML
    private TextField timeField;
    @FXML
    private Button updateBtn;
    @FXML
    private Button backBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        oldAppointment = Controller.AdminDashboard.AppointmentsPageController.appointmentUpdate;
        dateField.setValue(oldAppointment.getDate().toLocalDate());
        timeField.setText(oldAppointment.getTime());

    }

    @FXML
    private void updateBtnHandel(ActionEvent event) throws SQLException {

        Appointment appointment = new Appointment();
        appointment.setDate(Date.valueOf(dateField.getValue()));
        appointment.setDay(dateField.getValue().getDayOfWeek().toString());
        appointment.setTime(timeField.getText());
        appointment.setId(oldAppointment.getId());
        appointment.setStatus("free");
        appointment.update();

        Controller.AdminDashboard.AppointmentsPageController.appointmentUpdate = null;
        Controller.AdminDashboard.AppointmentsPageController.appointmentStage.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update Appointment");
        alert.setContentText("The appointment is updated successfuly");
        alert.showAndWait();

    }

    @FXML
    private void backBtnHandel(ActionEvent event) {
        AppointmentsPageController.appointmentStage.close();
    }

}
