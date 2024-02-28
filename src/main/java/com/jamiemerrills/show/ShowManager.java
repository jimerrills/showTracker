package com.jamiemerrills.show;

import com.jamiemerrills.util.CC;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.jamiemerrills.util.CC.*;

public class ShowManager {

    public static void listAllShows(List<Show> showList) {
        if (showList.isEmpty()) {
            System.out.println(CC.formatColoredMessage("No shows to display", RED));
            return;
        } else {
            for (Show show : showList) {
                System.out.println(CC.formatColoredMessage("%d %s (S%dE%d)", GREEN, showList.indexOf(show) + 1, show.getName(), show.getSeason(), show.getEpisode()));
            }
        }
        System.out.println(" ");
    }
}
