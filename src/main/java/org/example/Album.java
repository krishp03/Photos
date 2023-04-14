package org.example;

import java.util.*;
public class Album{

    public String name;
    public ArrayList<Photo> photos;

    public Album(String name){
        this.name=name;
        photos=new ArrayList<Photo>();
    }

    public String getName(){return name;}

    public void setName(String name){this.name=name;}

    public ArrayList<Photo> getPhotos(){
        return photos;
    }

    public Date getEarliestDate(){
        if (photos.size()==0) return null;
        if (photos.get(0)==null) return null;
        Date x = photos.get(0).date;
        for (int i=1; i<photos.size();i++) if (photos.get(i).date.compareTo(x)<0) x=photos.get(i).date;
        return x;
    }
    public Date getLatestDate(){
        if (photos.size()==0) return null;
        if (photos.get(0)==null) return null;
        Date x = photos.get(0).date;
        for (int i=1; i<photos.size();i++) if (photos.get(i).date.compareTo(x)>0) x=photos.get(i).date;
        return x;
    }

    public void addPhoto(Photo p){
        photos.add(p);
    }

    public void deletePhoto(Photo p){photos.remove(p);}

    public String toString(){return name;}
}
