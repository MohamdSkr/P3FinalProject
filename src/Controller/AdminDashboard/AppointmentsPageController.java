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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AppointmentsPageController implements Initializable {

    public static Appointment appointmentUpdate;
    public static Stage appointmentStage;

    @FXML
    private TableColumn<Appointment, String> time;
    @FXML
    private TableColumn<Appointment, String> status;
    @FXML
    private TableView<Appointment> freeAppointmentsTable;
    @FXML
    private TableColumn<Appointment, Date> date;
    @FXML
    private TableColumn<Appointment, String> day;
    @FXML
    private Button createBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button refershBtn;
    @FXML
    private ImageView adminImg1;
    @FXML
    private Button bookedBtn1;
    @FXML
    private Button appointmentsBtn1;
    @FXML
    private Button patientsBtn1;
    @FXML
    private Button logoutBtn1;
    @FXML
    private Button finshBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        date.setCellValueFactory(new PropertyValueFactory("date"));
        day.setCellValueFactory(new PropertyValueFactory("day"));
        time.setCellValueFactory(new PropertyValueFactory("time"));
        status.setCellValueFactory(new PropertyValueFactory("status"));

        try {
            refershBtnHandel(new ActionEvent());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
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
    private void createBtnHandel(ActionEvent event) {
        View.ViewManager.adminDashboard.showCreateAppointmentPageScene();
    }

    @FXML
    private void logoutBtnHandel(ActionEvent event) throws IOException {
        View.ViewManager.closeAdminDashboard();
        View.ViewManager.openBasicLogin();
        View.ViewManager.adminDashboard.showPatientsPage();
        AdminLoginController.admin = null;
    }

    @FXML
    private void updateBtnHandel(ActionEvent event) throws IOException {

        appointmentUpdate = freeAppointmentsTable.getSelectionModel().getSelectedItem();
        if (appointmentUpdate == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Choose Appointment");
            alert.setContentText("Please choose appointment to update information.");
            alert.show();

        } else {
            FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/View/AdminDashboard/updateAppointment.fxml"));
            Parent rootUpdate = loaderUpdate.load();
            Scene updateAppointmentScene = new Scene(rootUpdate);
            appointmentStage = new Stage();
            appointmentStage.setScene(updateAppointmentScene);
            appointmentStage.setTitle("Update Patient");
            appointmentStage.show();

        }

    }

    @FXML
    private void deleteBtnHandel(ActionEvent event) {
        Appointment appointment = freeAppointmentsTable.getSelectionModel().getSelectedItem();
        if (appointment == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Choose Appointment");
            alert.setContentText("Please choose appointment to delete it.");
            alert.show();

        } else {

            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("appointment delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this appointment ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        appointment.delete();
                        refershBtnHandel(event);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText("Delete appointment");
                        alert.setContentText("The appointment is deleted successfuly.");
                        alert.show();
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

    @FXML
    private void finshBtnHandel(ActionEvent event) {
        View.ViewManager.adminDashboard.showFinshedAppointmentsPage();
    }
    
    

}
