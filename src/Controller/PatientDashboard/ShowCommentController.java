package Controller.PatientDashboard;

import Modul.AppointmentDetails;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ShowCommentController implements Initializable {

    private AppointmentDetails appointment;
    
    @FXML
    private ImageView adminImg;
    @FXML
    private TextField dateField;
    @FXML
    private TextField dayField;
    @FXML
    private TextField timeField;
    @FXML
    private TextArea commentField;
    @FXML
    private Button exitBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        appointment = FinshedAppointmentController.appointment;
        dateField.setText(appointment.getDay().toString());
        dayField.setText(appointment.getDay());
        timeField.setText(appointment.getTime());
        commentField.setText(appointment.getComment());
        
    }    

    @FXML
    private void exitBtnHandel(ActionEvent event) {
        FinshedAppointmentController.showCommentStage.close();
    }
    
}
