package com.jamiemerrills.show;

import java.io.*;
import java.time.LocalTime;
import java.util.List;

public class Show implements Serializable {


    private String name;
    private int season;
    private int episode;
    private LocalTime timeStamp;

    public Show(String name) {
        this.name = name;
        this.episode = 0;
        this.season = 0;
        this.timeStamp = null;
    }

    public Show(String name, int episode, int season) {
        this.name = name;
        this.episode = episode;
        this.season = season;
        this.timeStamp = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public void setTimeStamp(LocalTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public LocalTime getTimeStamp() {
        return timeStamp;
    }
}


