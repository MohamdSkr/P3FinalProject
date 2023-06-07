package Controller.AdminDashboard;

import Controller.AlertMessage;
import Modul.AppointmentDetails;
import Modul.BookedAppointment;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class EditCommentController implements Initializable {

    private AppointmentDetails appointment;

    @FXML
    private ImageView adminImg;
    @FXML
    private TextField patientField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField dayField;
    @FXML
    private TextField timeField;
    @FXML
    private TextArea commentField;
    @FXML
    private Button editBtn;
    @FXML
    private Button backBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        appointment = FinshedAppointmentController.appointmentDetails;
        patientField.setText(appointment.getPatientName());
        dateField.setText(appointment.getDate().toString());
        dayField.setText(appointment.getDay());
        timeField.setText(appointment.getTime());
        commentField.setText(appointment.getComment());

    }

    @FXML
    private void editBtnHandel(ActionEvent event) {

        if (commentField.equals("")) {
            AlertMessage.warningAlertMessage("Write Cooment", "Please write comment to patient to edit it.");
        } else {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Edit Comment Appointment");
            confirmAlert.setContentText("Are you sure to edit comment of appointment ?");
            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        appointment.setComment(commentField.getText());
                        BookedAppointment.updateFinishedComment(appointment);
                        AlertMessage.successAlertMessage("Edit comment of appointment", "The comment of appointment is edit successfuly.");
                        FinshedAppointmentController.editCommentStage.close();
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            });
        }

    }

    @FXML
    private void backBtnHandel(ActionEvent event) {
        FinshedAppointmentController.editCommentStage.close();
    }

}
