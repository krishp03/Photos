package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Controller for Login Screen
 * @author Krish Patel
 * @author Roshan Varadhan
 */
public class LoginController {

    /**
     * Username
     */
    public static String name;
    @FXML private TextField loginName;
    @FXML
    ListView<User> userListView;

    /**
     * Logs in to admin or user system depending on username
     * @throws IOException
     */
    @FXML
    private void login() throws IOException {
        name = loginName.getText().trim();
        if (name.equals("admin")){
            Main.setRoot("admin");
            return;
        }
        for (User u:Admin.users){
            if (u.getUsername().equals(name)) {
                HomeController.user=u;
                if (u.getAlbums() == null) u.initAlbums();
                Main.setRoot("home");
                return;
            }
        }
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("No User Found");
        error.setContentText("This user does not exist");
        error.showAndWait();
        loginName.setText("");
    }
}
