package com.jamiemerrills.film;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Film implements Serializable {


    private String name;
    private LocalTime timeStamp;

    public Film(String name) {
        this.name = name;
    }

    public Film(String name, LocalTime timeStamp) {
        this.name = name;
        this.timeStamp = timeStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimeStamp(LocalTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTimeStamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm:ss");
        return timeStamp.format(formatter);

    }
}
