package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminDashboard extends Stage {

    private Scene patientsPageScene;
    private Scene appointmentsPageScene;
    private Scene createPatientPageScene;
    private Scene bookedAppointmentsPageScene;
    private Scene createAppointmentPageScene;
    private Scene finshedAppointmentPageScene;

    public AdminDashboard() throws IOException {

        /* Patients Page Scene */
        FXMLLoader patientsPageLoader = new FXMLLoader(getClass().getResource("AdminDashboard/patientPageFXML.fxml"));
        Parent patientsPageParent = patientsPageLoader.load();
        patientsPageScene = new Scene(patientsPageParent);

        /* Appointments Page Scene */
        FXMLLoader appointmentsPageLoader = new FXMLLoader(getClass().getResource("AdminDashboard/appointmentsPage.fxml"));
        Parent appointmentsPageParent = appointmentsPageLoader.load();
        appointmentsPageScene = new Scene(appointmentsPageParent);

        /* Create Patient Page */
        FXMLLoader createPatientPageLoader = new FXMLLoader(getClass().getResource("AdminDashboard/createPatient.fxml"));
        Parent createPatientPageParent = createPatientPageLoader.load();
        createPatientPageScene = new Scene(createPatientPageParent);

        /* Booked Appointments Page */
        FXMLLoader bookedAppointmentsPageLoader = new FXMLLoader(getClass().getResource("AdminDashboard/bookedAppointmentsPage.fxml"));
        Parent bookedAppointmentsPageParent = bookedAppointmentsPageLoader.load();
        bookedAppointmentsPageScene = new Scene(bookedAppointmentsPageParent);

        /* Finshed Appointments Page */
        FXMLLoader finshedAppointmentsPageLoader = new FXMLLoader(getClass().getResource("AdminDashboard/finshedAppointment.fxml"));
        Parent finshedAppointmentsPageParent = finshedAppointmentsPageLoader.load();
        finshedAppointmentPageScene = new Scene(finshedAppointmentsPageParent);
        
        /* Create Appointment Page*/
        FXMLLoader createAppointmentPageSceneLoader = new FXMLLoader(getClass().getResource("AdminDashboard/createAppointment.fxml"));
        Parent createAppointmentPageSceneParent = createAppointmentPageSceneLoader.load();
        createAppointmentPageScene = new Scene(createAppointmentPageSceneParent);

        /* Add Scene */
        this.setScene(patientsPageScene);
        this.setTitle("Doctor Dashboard");

    }

    public void showPatientsPage() {
        this.setScene(patientsPageScene);
    }

    public void showAppointmentsPage() {
        this.setScene(appointmentsPageScene);
    }

    public void showCreatePatientPage() {
        this.setScene(createPatientPageScene);
    }

    public void showBookedAppointmentsPage() {
        this.setScene(bookedAppointmentsPageScene);
    }
    
    public void showFinshedAppointmentsPage() {
        this.setScene(finshedAppointmentPageScene);
    }

    public void showCreateAppointmentPageScene() {
        this.setScene(createAppointmentPageScene);
    }

}
