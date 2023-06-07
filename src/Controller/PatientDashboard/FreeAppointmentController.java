package Controller.PatientDashboard;

import Controller.AlertMessage;
import Controller.PatientLoginController;
import Modul.Appointment;
import Modul.BookedAppointment;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class FreeAppointmentController implements Initializable {

    @FXML
    private ImageView adminImg;
    @FXML
    private Button finshedAppointmentsBtn;
    @FXML
    private Button bookedAppointmentsBtn;
    @FXML
    private Button freeAppointmentsBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private TableView<Appointment> freeAppointmentsTable;
    @FXML
    private TableColumn<Appointment, Date> date;
    @FXML
    private TableColumn<Appointment, String> day;
    @FXML
    private TableColumn<Appointment, String> time;
    @FXML
    private TableColumn<Appointment, String> status;
    @FXML
    private Button chooseAppointmentsBtn;
    @FXML
    private Button refershBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        date.setCellValueFactory(new PropertyValueFactory("date"));
        day.setCellValueFactory(new PropertyValueFactory("day"));
        time.setCellValueFactory(new PropertyValueFactory("time"));
        status.setCellValueFactory(new PropertyValueFactory("status"));

        try {
            refershBtnHandel(new ActionEvent());
        } catch (SQLException ex) {
            Logger.getLogger(FreeAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void finshedAppointmentsBtnHandel(ActionEvent event) {
        View.ViewManager.patientDashboard.showFinshedAppointmentScene();
    }

    @FXML
    private void bookedAppointmentsBtnHandel(ActionEvent event) {
        View.ViewManager.patientDashboard.showBookedAppointmentScene();
    }

    @FXML
    private void freeAppointmentsBtnHandel(ActionEvent event) {
        View.ViewManager.patientDashboard.showFreeAppointmentScene();
    }

    @FXML
    private void logoutBtnHandel(ActionEvent event) throws IOException {
        View.ViewManager.closePatioentDashboard();
        View.ViewManager.openBasicLogin();
        View.ViewManager.patientDashboard.showFreeAppointmentScene();
        PatientLoginController.patient = null;
    }

    @FXML
    private void chooseAppointmentsBtnHandel(ActionEvent event) {

        Appointment appointment = freeAppointmentsTable.getSelectionModel().getSelectedItem();
        if (appointment == null) {
            AlertMessage.warningAlertMessage("Choose Appointment", "Please choose appointment to booked it.");
        } else {

            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("Booked Appointment");
            deleteConfirmAlert.setContentText("Are you sure to booked this appointment ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        int done = BookedAppointment.add(PatientLoginController.patient, appointment);
                        if (done > 0) {
                            appointment.setStatus("booked");
                            appointment.update();
                            AlertMessage.successAlertMessage("Booked appointment", "The appointment is booked successfuly.");
                            refershBtnHandel(event);
                        }
                    } catch (Exception e) {
                        e.getMessage();
                    }

                }
            });
        }

    }

    @FXML
    private void refershBtnHandel(ActionEvent event) throws SQLException {
        freeAppointmentsTable.getItems().setAll(Appointment.getAllAppointment());
    }

}
