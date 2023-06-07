package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BasicLogin extends Stage {
    
    public BasicLogin() throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/basicLogin.fxml"));
        Parent parent = loader.load();  
        Scene scene = new Scene(parent);
        setScene(scene);
        setTitle("Doctor Appointment");
    
    }
    
}
