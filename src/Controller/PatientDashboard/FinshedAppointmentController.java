package Controller.PatientDashboard;

import Controller.AlertMessage;
import Controller.PatientLoginController;
import Modul.AppointmentDetails;
import Modul.BookedAppointment;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FinshedAppointmentController implements Initializable {

    public static AppointmentDetails appointment;
    public static Stage showCommentStage;
    
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
    private TableColumn<AppointmentDetails, Date> date;
    @FXML
    private TableColumn<AppointmentDetails, String> day;
    @FXML
    private TableColumn<AppointmentDetails, String> time;
    @FXML
    private Button seeCommentBtn;
    @FXML
    private Button refershBtn;
    @FXML
    private TableColumn<AppointmentDetails, String> status;
    @FXML
    private TableView<AppointmentDetails> finishedAppointmentsTable;
    @FXML
    private TableColumn<AppointmentDetails, String> comment;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        date.setCellValueFactory(new PropertyValueFactory("date"));
        day.setCellValueFactory(new PropertyValueFactory("day"));
        time.setCellValueFactory(new PropertyValueFactory("time"));
        status.setCellValueFactory(new PropertyValueFactory("status"));
        comment.setCellValueFactory(new PropertyValueFactory("comment"));

        try {
            refershBtnHandel(new ActionEvent());
        } catch (SQLException ex) {
            System.out.println(ex);
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
    private void seeCommentBtnHandel(ActionEvent event) throws IOException {
        appointment = finishedAppointmentsTable.getSelectionModel().getSelectedItem();
        if (appointment == null) {
            AlertMessage.warningAlertMessage("Choose Appointment", "Please choose appointment to see comment.");
        } else {
            FXMLLoader showLoader = new FXMLLoader(getClass().getResource("/View/PatientDashboard/showComment.fxml"));
            Parent rootUpdate = showLoader.load();
            Scene showCommentScene = new Scene(rootUpdate);
            showCommentStage = new Stage();
            showCommentStage.setScene(showCommentScene);
            showCommentStage.setTitle("Show Comment Appointment");
            showCommentStage.show();

        }
    }

    @FXML
    private void refershBtnHandel(ActionEvent event) throws SQLException {
        finishedAppointmentsTable.getItems().setAll(BookedAppointment.getFinshedAppointmentByID(PatientLoginController.patient.getId()));
    }

}
