package Controller.AdminDashboard;

import Controller.AdminLoginController;
import Controller.AlertMessage;
import Modul.Appointment;
import Modul.AppointmentDetails;
import Modul.BookedAppointment;
import Modul.User;
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

public class FinshedAppointmentController implements Initializable {

    public static AppointmentDetails appointmentDetails;
    public static Stage editCommentStage;
    
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
    private Button finshBtn;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchBtn;
    @FXML
    private Button refershBtn;
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
    private Button editCommentBtn;
    @FXML
    private TableView<AppointmentDetails> finisedAppointmentsTable;
    @FXML
    private TableColumn<AppointmentDetails, String> comment;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        patientName.setCellValueFactory(new PropertyValueFactory("patientName"));
        date.setCellValueFactory(new PropertyValueFactory("date"));
        day.setCellValueFactory(new PropertyValueFactory("day"));
        time.setCellValueFactory(new PropertyValueFactory("time"));
        stauts.setCellValueFactory(new PropertyValueFactory("status"));
        comment.setCellValueFactory(new PropertyValueFactory("comment"));

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
    private void logoutBtnHandel(ActionEvent event) throws IOException {
        View.ViewManager.closeAdminDashboard();
        View.ViewManager.openBasicLogin();
        View.ViewManager.adminDashboard.showPatientsPage();
        AdminLoginController.admin = null;
    }

    @FXML
    private void finshBtnHandel(ActionEvent event) {
        View.ViewManager.adminDashboard.showFinshedAppointmentsPage();
    }

    @FXML
    private void searchBtnHandel(ActionEvent event) throws SQLException {
        ObservableList<AppointmentDetails> appointments = FXCollections.observableArrayList(BookedAppointment.search(searchField.getText(), "finished"));
        finisedAppointmentsTable.getItems().setAll(appointments);
    }

    @FXML
    private void refershBtnHandel(ActionEvent event) throws SQLException {
        finisedAppointmentsTable.getItems().setAll(BookedAppointment.getFinisedAppointmentsDetails());
    }

    @FXML
    private void editCommentBtnHandel(ActionEvent event) throws IOException {
        appointmentDetails = finisedAppointmentsTable.getSelectionModel().getSelectedItem();
        if (appointmentDetails == null) {
            AlertMessage.warningAlertMessage("Choose Appointment", "Please choose patient to edit comment it.");
        } else {
            FXMLLoader editLoader = new FXMLLoader(getClass().getResource("/View/AdminDashboard/editComment.fxml"));
            Parent rootUpdate = editLoader.load();
            Scene editAppointmentScene = new Scene(rootUpdate);
            editCommentStage = new Stage();
            editCommentStage.setScene(editAppointmentScene);
            editCommentStage.setTitle("Edit Comment Appointment");
            editCommentStage.show();

        }
    }

}
