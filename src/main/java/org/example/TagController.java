package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TagController implements Initializable {

    public static Photo p;
    public static User u;

    @FXML
    ListView<String> tagTypeList;

    @FXML
    TextField addTagValue;

    @FXML TextField addTagTypeText;

    public void confirmAddTag() throws IOException {
        App.setRoot("photoDisplay");
        String value = addTagValue.getText();
        if (tagTypeList.getSelectionModel().getSelectedItem()!=null){
            if (value==null || value.isEmpty()){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Tag Add Error");
                error.setContentText("No value specified");
                error.showAndWait();
                return;
            }
            p.addTag(tagTypeList.getSelectionModel().getSelectedItem(), value);
            App.setRoot("photoDisplay");
            return;
        }
        String newTag = addTagTypeText.getText();
        if (newTag==null || newTag.isEmpty()){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Tag Add Error");
            error.setContentText("No tag specified");
            error.showAndWait();
            return;
        }
        p.addTag(newTag,value);
        if (u.getTagKeys()==null) u.initKeys();
        if (!u.getTagKeys().contains(newTag)) {
            u.getTagKeys().add(newTag);
            for (String s: u.getTagKeys()) System.out.println(s);
        }
        App.setRoot("photoDisplay");
    }

    public void cancelAddTag() throws IOException {
        App.setRoot("photoDisplay");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (u.getTagKeys()!=null) for (String s:u.getTagKeys()) tagTypeList.getItems().add(s);
    }
}
