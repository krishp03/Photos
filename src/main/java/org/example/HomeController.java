package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class HomeController implements Initializable {

    public static User user;

    @FXML
    ListView<Album> albumListView = new ListView<Album>();

    @FXML
    Label selectedAlbumName;
    @FXML Label selectedAlbumPhotos;
    @FXML Label selectedAlbumDates;
    @FXML Label welcomeMessage;



    @FXML private void logout() throws IOException {
        App.setRoot("login");
    }

    public void albumOpen() throws IOException{
        AlbumController.user = user;
        AlbumController.album = albumListView.getSelectionModel().getSelectedItem();
        App.setRoot("albumView");
    }

    public void createAlbum() throws IOException {
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText("Create New Album");
        td.showAndWait();
        String newName = td.getEditor().getText();
        for (Album a: user.getAlbums()){
            if (a.getName().toLowerCase().equals(newName.toLowerCase())){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Album Add Error");
                error.setContentText("This album already exists");
                error.showAndWait();
                return;
            }
        }
        Album a = new Album(newName);
        user.addAlbum(a);
        albumListView.getItems().add(a);
    }

    public void deleteAlbum() throws IOException{
        Album a = albumListView.getSelectionModel().getSelectedItem();
        albumListView.getItems().remove(a);
        user.getAlbums().remove(a);
    }

    public void renameAlbum() throws IOException {

        Album a = albumListView.getSelectionModel().getSelectedItem();
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText("Create New Album");
        td.showAndWait();
        String newName = td.getEditor().getText();
        for (Album i: user.getAlbums()){
            if (i.getName().toLowerCase().equals(newName.toLowerCase())){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Rename Add Error");
                error.setContentText("This album already exists");
                error.showAndWait();
                return;
            }
        }
        albumListView.getItems().remove(a);
        user.getAlbums().get(user.getAlbums().indexOf(a)).setName(newName);
        albumListView.getItems().add(a);
        albumListView.getSelectionModel().select(a);
    }

    private void showAlbum(){
        Album curr = albumListView.getSelectionModel().getSelectedItem();
        if(curr!=null) {
            int numPhotos = curr.getPhotos().size();
            Date earliest = curr.getEarliestDate();
            Date latest = curr.getLatestDate();
            if (earliest != null && latest != null) {
                SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd");
                selectedAlbumDates.setText(sdformat.format(earliest) + " - " + sdformat.format(latest));
            }
            selectedAlbumName.setText(curr.getName());
            selectedAlbumPhotos.setText(numPhotos + " Photos");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        welcomeMessage.setText(user.username + "'s Photo Gallery");
        for (Album a: user.getAlbums()) albumListView.getItems().add(a);
        albumListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Album>(){

            @Override
            public void changed(ObservableValue<? extends Album> arg0, Album arg1, Album arg2) {
                showAlbum();
            }

        });

    }
}
