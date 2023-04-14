package org.example;

import javafx.event.ActionEvent;

import java.io.IOException;

public class TagController {
    public void confirmAddTag() throws IOException {
        App.setRoot("photoDisplay");
    }

    public void cancelAddTag() throws IOException {
        App.setRoot("photoDisplay");
    }
}
