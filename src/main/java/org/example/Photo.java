package org.example;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class Photo implements Serializable {
    public File image;
    public HashMap<String, ArrayList<String>> tags;
    public String caption;
    public Date date;

    private Calendar calendar;

    public Photo(File image, String caption){
        this.image = image;
        this.caption=caption;
        this.tags = new HashMap<String, ArrayList<String>>();
        calendar = new GregorianCalendar();
        calendar.set(Calendar.MILLISECOND, 0);
        date = calendar.getTime();
    }

    public void addTag(String tag, String value){
        if (!tags.containsKey(tag)){
            ArrayList<String> values = new ArrayList<>();
            tags.put(tag, values);
        }
        tags.get(tag).add(value);
    }

//    public boolean deleteTag(String tag, String value) throws IOException {
//        try{
//            tags.get(tag).remove(value);
//            return true;
//        }
//        catch(Exception e){
//            return false;
//        }
//    }

    public void setCaption(String caption){
        this.caption=caption;
    }

    public String getCaption(){
        return caption;
    }
    public Date getDate(){
        return date;
    }
    public File getImage(){
        return image;
    }
    public HashMap<String, ArrayList<String>> getTags(){
        return tags;
    }

    public String toString(){return caption;}

}
