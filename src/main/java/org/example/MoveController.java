package org.example;

import java.io.IOException;

public class MoveController {
    public void confirmMove() throws IOException{
        App.setRoot("albumView");
    }
    public void cancelMove() throws IOException{
        App.setRoot("albumView");
    }
}
