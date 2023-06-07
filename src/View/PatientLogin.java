package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PatientLogin extends Stage{
    
    public PatientLogin() throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/patientLogin.fxml"));
        Parent parent = loader.load();  
        Scene scene = new Scene(parent);
        setScene(scene);
        setTitle("Patient Login");
        
    }
    
}
