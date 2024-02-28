package com.jamiemerrills.film;

import com.jamiemerrills.util.CC;

import java.util.List;

public class FilmManager {

    public static boolean listAllFilms(List<Film> filmList) {
        if(filmList.isEmpty()) {
            System.out.println(CC.formatColoredMessage("No films to display", CC.RED));
            return false;
        }
        else {
            for (Film film : filmList) {
                System.out.print(CC.formatColoredMessage("%d %s (%s)", CC.GREEN, filmList.indexOf(film) + 1, film.getName(), film.getTimeStamp()));
        }

        }
        System.out.println(" ");
        return true;
    }
}
