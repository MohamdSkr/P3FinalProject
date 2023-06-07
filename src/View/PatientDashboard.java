package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PatientDashboard extends Stage{

    private Scene freeAppointmentScene;
    private Scene bookedAppointmentScene;
    private Scene finshedAppointmentScene;
    
    public PatientDashboard() throws IOException {
        
        /* Free Appointment Scene */
        FXMLLoader freeAppointmentSceneLoader = new FXMLLoader(getClass().getResource("PatientDashboard/freeAppointment.fxml"));
        Parent freeAppointmentSceneParent = freeAppointmentSceneLoader.load();  
        freeAppointmentScene = new Scene(freeAppointmentSceneParent);
        
        
        /* Booked Appointment Scene */
        FXMLLoader bookedAppointmentSceneLoader = new FXMLLoader(getClass().getResource("PatientDashboard/bookedAppointment.fxml"));
        Parent bookedAppointmentSceneParent = bookedAppointmentSceneLoader.load();  
        bookedAppointmentScene = new Scene(bookedAppointmentSceneParent);
        
        
        /* Finshed Appointment Scene */
        FXMLLoader finshedAppointmentSceneLoader = new FXMLLoader(getClass().getResource("PatientDashboard/finshedAppointment.fxml"));
        Parent finshedAppointmentSceneParent = finshedAppointmentSceneLoader.load();  
        finshedAppointmentScene = new Scene(finshedAppointmentSceneParent);
        
        
        /* Default Scene */
        setScene(freeAppointmentScene);
        setTitle("Patient Dashboard");
        
    }
    
    public void showFreeAppointmentScene() {
        this.setScene(freeAppointmentScene);
    }
    
    public void showBookedAppointmentScene() {
        this.setScene(bookedAppointmentScene);
    }
    
    public void showFinshedAppointmentScene() {
        this.setScene(finshedAppointmentScene);
    }
    
}
