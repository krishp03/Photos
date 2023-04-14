package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PhotoController implements Initializable {

    public static Photo p;

    public static Album a;

    public static User u;

    @FXML
    ImageView selectedImage;

    @FXML
    Label selectedCaption;

    @FXML
    ListView<String> tagListView;

    @FXML Label selectedDate;

    public void back() throws IOException {
        App.setRoot("albumView");
    }

    public void recaption() throws IOException {
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText("Recaption");
        td.showAndWait();
        String newCaption = td.getEditor().getText();
        if (newCaption==null || newCaption.isEmpty()){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error Editing Photo");
            error.setContentText("Must add a caption");
            error.showAndWait();
            return;
        }
        p.setCaption(newCaption);
        App.setRoot("photoDisplay");
    }

    public void addTag() throws IOException{
        TagController.p=p;
        TagController.u=u;
        App.setRoot("addTag");
    }
    public void deleteTag() throws IOException{
        String s = tagListView.getSelectionModel().getSelectedItem();
        String fields[]=s.split("\\|");
        ArrayList<String> temp = p.getTags().get(fields[0]);
        temp.remove(fields[1]);
        App.setRoot("photoDisplay");
    }
    public void nextImage() throws IOException{
        int curr = a.getPhotos().indexOf(p);
        int next;
        if (curr == a.getPhotos().size()-1)next=0;
        else next=curr+1;
        PhotoController.p=a.getPhotos().get(next);
        App.setRoot("photoDisplay");
    }
    public void prevImage() throws IOException{
        int curr = a.getPhotos().indexOf(p);
        int prev;
        if (curr == 0)prev=a.getPhotos().size()-1;
        else prev=curr-1;
        PhotoController.p=a.getPhotos().get(prev);
        App.setRoot("photoDisplay");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedCaption.setText(p.getCaption());
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println(p.getImage().getAbsolutePath());
        Image image = new Image(p.getImage().toURI().toString());
        selectedImage.setImage(image);
        selectedImage.setPreserveRatio(true);
        selectedDate.setText(sdformat.format(p.getDate()));
        for (String key: p.getTags().keySet()) {
            ArrayList<String> values = p.getTags().get(key);
            for (String s:values) tagListView.getItems().add(key + "|" + s);
        }
    }
}
