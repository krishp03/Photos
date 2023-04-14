package org.example;

import java.io.IOException;

public class SearchController {

    private int searchChoice=0;
    public void confirmSearch() throws IOException{
        Album temp = new Album("temp");
        
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
