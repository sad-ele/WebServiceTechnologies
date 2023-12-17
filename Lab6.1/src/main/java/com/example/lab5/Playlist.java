package com.example.lab5;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Playlist {

    private int id;
    private String name;
    private String band;
    private String genre;
    private String time;
    private int year;

    public Playlist(){
    }

    public Playlist (int id, String name, String band, String genre, String time, int year){
        this.id = id;
        this.name = name;
        this.band = band;
        this.genre = genre;
        this.time = time;
        this.year = year;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getBand(){
        return band;
    }

    public String getGenre(){
        return genre;
    }

    public String getTime(){
        return time;
    }

    public int getYear(){
        return year;
    }


    public void setId(int id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setYear(int year) {
        this.year = year;
    }


    @Override
    public String toString() {
        return "Playlist {" + "Id=" + id
                + "name=" + name +
                ", band=" + band +
                ", genre=" + genre +
                ", time=" + time +
                ", year=" + year +
                '}';
    }
}
