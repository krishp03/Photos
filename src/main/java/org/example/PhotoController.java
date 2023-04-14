package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class PhotoController {

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
    }

    public void addTag() throws IOException{
        TagController.p=p;
        TagController.u=u;
        App.setRoot("addTag");
    }
    public void deleteTag() throws IOException{

    }
    public void nextImage() throws IOException{}
    public void prevImage() throws IOException{}
}
