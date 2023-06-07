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

public class FinshTheApointmentController implements Initializable {

    private AppointmentDetails appointmentDetails;

    @FXML
    private ImageView adminImg;
    @FXML
    private Button backBtn;
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
    private Button finshBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        appointmentDetails = BookedAppointmentsPageController.finshAppointment;
        patientField.setText(appointmentDetails.getPatientName());
        dateField.setText(appointmentDetails.getDate().toString());
        dayField.setText(appointmentDetails.getDay());
        timeField.setText(appointmentDetails.getTime());

    }

    @FXML
    private void backBtnHandel(ActionEvent event) {
        BookedAppointmentsPageController.finshStage.close();
        BookedAppointmentsPageController.finshAppointment = null;
    }

    @FXML
    private void finshBtnandel(ActionEvent event) {

        if (commentField.equals("")) {
            AlertMessage.warningAlertMessage("Write Cooment", "Please write comment to patient to finshed it.");
        } else {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Appointment Finished");
            confirmAlert.setContentText("Are you sure to finsh this appointment ?");
            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        appointmentDetails.setComment(commentField.getText());
                        BookedAppointment.finshAppointment(appointmentDetails);
                        AlertMessage.successAlertMessage("Finish appointment", "The appointment is finised successfuly.");
                        BookedAppointmentsPageController.finshStage.close();
                        BookedAppointmentsPageController.finshAppointment = null;
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            });
        }

    }

}
