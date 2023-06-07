package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class BasicLoginController implements Initializable {

    @FXML
    private VBox loginContainer;
    @FXML
    private Button adminBtn;
    @FXML
    private Button patientBtn;
    @FXML
    private Button registerBtn;
    @FXML
    private ImageView adminLogo;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void adminBtnHandel(ActionEvent event) throws IOException {
        
        View.ViewManager.closeBasicLogin();
        View.ViewManager.openAdminLogin();
        
    }

    @FXML
    private void patientBtnHandel(ActionEvent event) throws IOException {
        
        View.ViewManager.closeBasicLogin();
        View.ViewManager.openPatientLogin();
        
    }

    @FXML
    private void registerBtnHandel(ActionEvent event) throws IOException {
        View.ViewManager.closeBasicLogin();
        View.ViewManager.openPatientRegister();
    }
    
}
