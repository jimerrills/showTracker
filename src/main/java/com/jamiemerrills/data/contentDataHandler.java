package com.jamiemerrills.data;

import com.jamiemerrills.film.Film;
import com.jamiemerrills.show.Show;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class contentDataHandler {
    public static void showToSer(List<Show> surgeryList) {
        try {
            FileOutputStream fileOut = new FileOutputStream("C:/Users/jamie/Documents/Show tracker/shows.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(surgeryList);
            fileOut.close();
            System.out.println("Surgerys have been saved to shows.ser'");
        } catch (FileNotFoundException e) {
            System.out.println("No shows file exists.");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    public static List<Show> showDeser() {
        try {
            List<Show> showList = new ArrayList<>();
            try (FileInputStream fileIn = new FileInputStream("C:/Users/jamie/Documents/Show tracker/shows.ser");
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {

                showList = (List<Show>) in.readObject();
                System.out.println("Shows have been deserialized from 'shows.ser'");
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException c) {
                System.out.println("Class not found");
                c.printStackTrace();
            }

            return showList;
        } catch (Exception e) {
            System.out.println("Failed to deserialize shows: " + e.getMessage());
            return new ArrayList<>(); // Return an empty list instead of null
        }
    }

    public static void filmToSer(List<Film> filmList) {
        try {
            FileOutputStream fileOut = new FileOutputStream("C:/Users/jamie/Documents/Show tracker/films.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(filmList);
            fileOut.close();
            System.out.println("Films have been saved to films.ser'");
        } catch (FileNotFoundException e) {
            System.out.println("No shows file exists.");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    public static List<Film> filmDeser() {
        List<Film> filmList = new ArrayList<Film>();

        try (FileInputStream fileIn = new FileInputStream("C:/Users/jamie/Documents/Show tracker/films.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            filmList = (List<Film>) in.readObject();
            System.out.println("Shows have been deserialized from 'films.ser'");
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }

        return filmList;
    }
}
