package Controller.AdminDashboard;

import Controller.AdminLoginController;
import Controller.AlertMessage;
import Modul.Appointment;
import Modul.AppointmentDetails;
import Modul.BookedAppointment;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class BookedAppointmentsPageController implements Initializable {

    public static Stage finshStage;
    public static AppointmentDetails finshAppointment;
    
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
    private TableView<AppointmentDetails> bookedAppointmentsTable;
    @FXML
    private TableColumn<AppointmentDetails, String> patientName;
    @FXML
    private TableColumn<AppointmentDetails, Date> date;
    @FXML
    private TableColumn<AppointmentDetails, String> day;
    @FXML
    private TableColumn<AppointmentDetails, String> time;
    @FXML
    private TableColumn<AppointmentDetails, String> stauts;
    @FXML
    private TextField searchField;
    @FXML
    private Button refershBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private Button finshBtn;
    @FXML
    private Button finshAppointmentBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        patientName.setCellValueFactory(new PropertyValueFactory("patientName"));
        date.setCellValueFactory(new PropertyValueFactory("date"));
        day.setCellValueFactory(new PropertyValueFactory("day"));
        time.setCellValueFactory(new PropertyValueFactory("time"));
        stauts.setCellValueFactory(new PropertyValueFactory("status"));

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

    private void createBtnHandel(ActionEvent event) {
        View.ViewManager.adminDashboard.showCreatePatientPage();
    }

    @FXML
    private void logoutBtnHandel(ActionEvent event) throws IOException {
        View.ViewManager.closeAdminDashboard();
        View.ViewManager.openBasicLogin();
        View.ViewManager.adminDashboard.showPatientsPage();
        AdminLoginController.admin = null;
    }

    @FXML
    private void searchBtnHandel(ActionEvent event) throws SQLException {
        ObservableList<AppointmentDetails> appointments = FXCollections.observableArrayList(BookedAppointment.search(searchField.getText(), "waiting"));
        bookedAppointmentsTable.getItems().setAll(appointments);
    }


    @FXML
    private void refershBtnHandel(ActionEvent event) throws SQLException {
        bookedAppointmentsTable.getItems().setAll(BookedAppointment.getBookedAppointmentsDetails());
    }

    @FXML
    private void finshBtnHandel(ActionEvent event) {
        View.ViewManager.adminDashboard.showFinshedAppointmentsPage();
    }

    @FXML
    private void finshAppointmentBtnHandel(ActionEvent event) throws IOException {
        finshAppointment = bookedAppointmentsTable.getSelectionModel().getSelectedItem();
        if (finshAppointment == null) {
            AlertMessage.warningAlertMessage("Choose Patient", "Please choose patient to finshed it.");
        } else {
            FXMLLoader finishLoader = new FXMLLoader(getClass().getResource("/View/AdminDashboard/finshThe"
                    + "Apointment.fxml"));
            Parent rootUpdate = finishLoader.load();
            Scene finshAppointmentScene = new Scene(rootUpdate);
            finshStage = new Stage();
            finshStage.setScene(finshAppointmentScene);
            finshStage.setTitle("Finsh Appointment");
            finshStage.show();

        }
    }

}
