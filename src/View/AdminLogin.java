package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminLogin extends Stage{
    
    public AdminLogin() throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/adminLogin.fxml"));
        Parent parent = loader.load();  
        Scene scene = new Scene(parent);
        setScene(scene);
        setTitle("Admin Login");
        
    }
    
}
