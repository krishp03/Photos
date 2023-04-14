package org.example;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

        @FXML
    ListView<User> userListView = new ListView<User>();

    public void logout() throws IOException {
        App.setRoot("login");
    }

    public void addUser() throws  IOException {
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText("Add User");
        td.showAndWait();
        String username = td.getEditor().getText().trim().toLowerCase();
        if (username==null || username.isEmpty()){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error Adding User");
            error.setContentText("Must add a username");
            error.showAndWait();
            return;
        }
        if(username.equals("admin")){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error Adding User");
            error.setContentText("Cannot add admin");
            error.showAndWait();
            return;
        }
        for (User u:Admin.users){
            if (u.username.equals(username)){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error Adding User");
                error.setContentText("User already exists");
                error.showAndWait();
                return;
            }
        }
        User u = new User(username);
        Admin.addUser(u);
        userListView.getItems().add(u);
    }

    public void deleteUser() throws IOException {
        User u = userListView.getSelectionModel().getSelectedItem();
        userListView.getItems().remove(u);
        Admin.deleteUser(u);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {for (User a: Admin.users) userListView.getItems().add(a);}
}
