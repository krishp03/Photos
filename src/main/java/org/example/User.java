package org.example;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    public String username;
    public static ArrayList<Album> albums;

    public User(String username){
        this.username=username;
    }

    public String getUsername(){return username;}

    public ArrayList<Album> getAlbums(){return albums;}

    public void initAlbums(){albums = new ArrayList<Album>();}

    public void addAlbum(Album a){albums.add(a);}
    public void deleteAlbum(Album a){albums.remove(a);}

    public String toString(){return username;}

}
