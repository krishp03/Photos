package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class LoginController {

    public static String name;
    @FXML private TextField loginName;
    @FXML
    ListView<User> userListView;
    @FXML
    private void login() throws IOException {
        name = loginName.getText().trim();
        if (name.equals("admin")){
            App.setRoot("admin");
            return;
        }
        for (User u:Admin.users){
            if (u.username.equals(name)) {
                HomeController.user=u;
                if (u.getAlbums() == null) u.initAlbums();
                App.setRoot("home");
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
