package Controller.AdminDashboard;

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
    private Button backBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void backBtnHandel(ActionEvent event) {
        
    }
    
}
