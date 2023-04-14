package org.example;

import javafx.event.ActionEvent;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;

public class PhotoController {


    public void back() throws IOException {
        App.setRoot("albumView");
    }

    public void recaption() throws IOException {
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText("Recaption");
        td.showAndWait();
    }

    public void addTag() throws IOException{
        App.setRoot("addTag");
    }
    public void deleteTag() throws IOException{}
    public void nextImage() throws IOException{}
    public void prevImage() throws IOException{}
}
