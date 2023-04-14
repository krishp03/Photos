package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CopyController implements Initializable {

    public static User user;

    public static Photo photo;
    @FXML
    ComboBox<Album> destCopyAlbum;
    public void confirmCopy() throws IOException{
        AlbumController.user = user;
        Album dest = destCopyAlbum.getValue();
        if (dest==null){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Photo Copy Error");
            error.setContentText("No album Selected");
            error.showAndWait();
            return;
        }
        dest.addPhoto(photo);
        AlbumController.album = dest;
        App.setRoot("albumView");
    }
    public void cancelCopy() throws IOException{
        App.setRoot("albumView");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Album> convert = FXCollections.observableArrayList(user.getAlbums());
        destCopyAlbum.getItems().addAll(convert);
    }
}
