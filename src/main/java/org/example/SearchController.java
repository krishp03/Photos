package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class SearchController {

    public static Album album;
    @FXML
    DatePicker startDate;
    @FXML
    DatePicker endDate;

    @FXML TextField tagOne;
    @FXML TextField valueOne;
    @FXML TextField tagTwo;
    @FXML TextField valueTwo;

    private int searchChoice=0;
    public void confirmSearch() throws IOException{
        Album temp = new Album("Search Results");
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate startLocal = startDate.getValue();
        Date start=null, end=null;
        if (startLocal!=null) start = Date.from(startLocal.atStartOfDay(defaultZoneId).toInstant());
        LocalDate endLocal = endDate.getValue();
        if (endLocal!=null) end = Date.from(startLocal.atStartOfDay(defaultZoneId).toInstant());
        String keyOne = tagOne.getText();
        String firstValue = valueOne.getText();
        if (start!=null){
            if (end==null){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Search Error");
                error.setContentText("No End Date");
                error.showAndWait();
                return;
            }
        }else if (end!=null){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Search Error");
                error.setContentText("No Start Date");
                error.showAndWait();
                return;
        }
        if (start!=null) {
            if (keyOne == null || keyOne.isEmpty()) {
                if (firstValue != null) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Search Error");
                    error.setContentText("Incomplete Criteria");
                    error.showAndWait();
                    return;
                }
            } else if (firstValue == null || firstValue.isEmpty()) {
                if (keyOne != null) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Search Error");
                    error.setContentText("Incomplete Criteria");
                    error.showAndWait();
                    return;
                }
            } else {
                for (Photo p : album.getPhotos()) {
                    if (p.getTags().containsKey(keyOne)) if (p.getTags().get(keyOne).contains(firstValue)) temp.addPhoto(p);
                }
            }
                for (Photo p : album.getPhotos())
                    if (p.getDate().compareTo(start) > 0 && p.getDate().compareTo(end) < 0)
                        if (!temp.getPhotos().contains(p)) temp.addPhoto(p);
            }
        else{
            String secondKey = tagTwo.getText();
            String secondValue = valueTwo.getText();
            if (searchChoice==0){
                for (Photo p : album.getPhotos()) {
                    if (p.getTags().containsKey(keyOne)) if (p.getTags().get(keyOne).contains(firstValue)) temp.addPhoto(p);
                }
            }
            else{
                if (!(secondKey==null || secondValue==null || secondValue.isEmpty() || secondKey.isEmpty())){
                    if (searchChoice==1){
                        for (Photo p : album.getPhotos()) {
                            if (p.getTags().containsKey(keyOne)){
                                if (p.getTags().get(keyOne).contains(firstValue)){
                                    if (p.getTags().containsKey(secondKey)){
                                        if (p.getTags().get(secondKey).contains(secondValue)){
                                            temp.addPhoto(p);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else{
                        for (Photo p:album.getPhotos()){
                            if (p.getTags().containsKey(keyOne)) if (p.getTags().get(keyOne).contains(firstValue)) temp.addPhoto(p);
                            if (p.getTags().containsKey(secondKey)) if (p.getTags().get(secondKey).contains(secondValue)) if(!temp.getPhotos().contains(p)) temp.addPhoto(p);
                        }
                    }
                }
            }
        }
        AlbumController.album=temp;
        App.setRoot("albumView");
    }
    public void cancelSearch() throws IOException{
        App.setRoot("albumView");
    }
    public void setAndSearch() throws IOException{
        searchChoice=1;
    }
    public void setOrSearch() throws IOException{
        searchChoice=2;
    }
}
