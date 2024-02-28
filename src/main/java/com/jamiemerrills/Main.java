package com.jamiemerrills;

import com.jamiemerrills.data.contentDataHandler;
import com.jamiemerrills.film.Film;
import com.jamiemerrills.film.FilmManager;
import com.jamiemerrills.show.Show;
import com.jamiemerrills.show.ShowManager;
import com.jamiemerrills.util.CC;


import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.jamiemerrills.util.CC.*;


public class Main {
    public static void main(String[] args) {


        CC cc = new CC();

        List<Show> showList = new ArrayList<>();
        List<Film> filmList = new ArrayList<>();

        File showFile = new File("C:/Users/jamie/Documents/Show tracker/shows.ser");

        if(showFile.exists()) {
            showList = contentDataHandler.showDeser();
        }

        File filmFile = new File("C:/Users/jamie/Documents/Show tracker/films.ser");

        if(filmFile.exists()) {
            filmList = contentDataHandler.filmDeser();
        }


        mainMenu(showList, filmList);
        FilmManager.listAllFilms(filmList);
        ShowManager.listAllShows(showList);

    }

    private static void mainMenu(List<Show> showList, List<Film> filmList) {
        System.out.println(CC.formatColoredMessage("--------------------", WHITE));
        System.out.println(CC.formatColoredMessage("Welcome to Content Tracker!", CYAN));
        System.out.println(CC.formatColoredMessage("--------------------", WHITE));
        System.out.println(CC.formatColoredMessage("List of stored films.", BLUE));
        FilmManager.listAllFilms(filmList);
        System.out.println(CC.formatColoredMessage("--------------------", WHITE));
        System.out.println(CC.formatColoredMessage("List of stored shows.", BLUE));
        ShowManager.listAllShows(showList);
        System.out.println(CC.formatColoredMessage("--------------------", WHITE));


        System.out.println(CC.formatColoredMessage("Please select an option:", BLUE));
        System.out.println(CC.formatColoredMessage("1. Add a show", GREEN));
        System.out.println(CC.formatColoredMessage("2. Add a film", GREEN));
        System.out.println(CC.formatColoredMessage("3. Manage a show", GREEN));
        System.out.println(CC.formatColoredMessage("4. Manage a film", GREEN));
        System.out.println(CC.formatColoredMessage("5. Remove content", GREEN));
        System.out.println(CC.formatColoredMessage("6. Exit", RED));

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch(choice) {
            case 1:
                addShow(showList, filmList);
                break;
            case 2:
                addFilm(filmList, showList);
            case 3:
                showSelect(showList, filmList);
                break;
            case 4:
                filmSelect(showList, filmList);
                break;
            case 5:
                removeContent(showList, filmList);

            case 6:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
        }
    }

    private static void addFilm(List<Film> filmList, List<Show> showList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the film: ");
        String name = scanner.nextLine();
        System.out.println("Would you like to specify the timestamp now? (Y/N)");
        String choice = scanner.nextLine();
        if(choice.equalsIgnoreCase("Y")) {
            System.out.println("Enter the timestamp: ");
            String timestamp = scanner.nextLine();
            try {
                LocalTime time = LocalTime.parse(timestamp);
                Film filmTS = new Film(name, time);
                filmList.add(filmTS);
                contentDataHandler.filmToSer(filmList);
                mainMenu(showList, filmList);

            }
            catch (Exception e) {
                System.out.println("Invalid timestamp, please try again.");
                addFilm(filmList, showList);
            }

        } else {
            Film film = new Film(name);
            filmList.add(film);
            contentDataHandler.filmToSer(filmList);
            mainMenu(showList, filmList);
        }
    }

    private static void addShow(List<Show> showList, List<Film> filmList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the show: ");
        String name = scanner.nextLine();
        System.out.println("Would you like to specify the season/episode now? (Y/N)");
        String choice = scanner.nextLine();
        if(choice.equalsIgnoreCase("Y")) {
            System.out.println("Enter the season: ");
            int season = scanner.nextInt();

            System.out.println("Enter the episode: ");
            int episode = scanner.nextInt();

            Show showSE = new Show(name, episode, season);
            showList.add(showSE);
            contentDataHandler.showToSer(showList);
            mainMenu(showList, filmList);

        } else {
            Show show = new Show(name);
            showList.add(show);
            contentDataHandler.showToSer(showList);
            mainMenu(showList, filmList);
        }
    }
    private static void showSelect(List<Show> showList, List<Film> filmList) {
        System.out.println("--------------------");
        System.out.println("List of stored shows.");
        ShowManager.listAllShows(showList);
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------");
        System.out.println("Welcome to Show Manager!");
        System.out.println("Please select a show to manage from below: ");

        try {
            int choice = scanner.nextInt();
            for (Show show : showList) {
                if ((showList.indexOf(show) + 1) == choice) {
                    manageShow(show, showList, filmList);
                    mainMenu(showList, filmList);
                }
            }
            }catch(Exception e){
                System.out.println("Invalid selection, please try again.");
                showSelect(showList, filmList);
            }
            System.out.println("Invalid selection, please try again.");
            showSelect(showList, filmList);


        }
    private static void filmSelect(List<Show> showList, List<Film> filmList) {
        System.out.println("--------------------");
        System.out.println("List of stored films.");
        FilmManager.listAllFilms(filmList);
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------");
        System.out.println("Welcome to Film Manager!");
        System.out.println("Please select a film to manage from below: ");

        try {
            int choice = scanner.nextInt();
            for (Film film : filmList) {
                if ((filmList.indexOf(film) + 1) == choice) {
                    manageFilm(film, showList, filmList);
                    mainMenu(showList, filmList);
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid selection, please try again.");
            showSelect(showList, filmList);
        }
        System.out.println("Invalid selection, please try again.");
        showSelect(showList, filmList);
    }

    private static void manageFilm(Film film, List<Show> showList, List<Film> filmList) {
        LocalTime timestamp;
        String unformattedTimestamp;
        System.out.println("Enter desired timestamp: ");
        Scanner scanner = new Scanner(System.in);
        unformattedTimestamp = scanner.nextLine();

        try {
            timestamp = LocalTime.parse(unformattedTimestamp);
            film.setTimeStamp(timestamp);
            System.out.println(CC.formatColoredMessage("You have updated '%s' now set to %s", GREEN, film.getName(), film.getTimeStamp()));
            contentDataHandler.showToSer(showList);
            mainMenu(showList, filmList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void manageShow(Show show, List<Show> showList, List<Film> filmList) {
        int episode;
        int season;
        System.out.println("1. Change episode");
        System.out.println("2. Change season & episode");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch(choice) {
            case 1:
                System.out.println("Enter desired episode: ");
                episode = scanner.nextInt();
                try {
                    show.setEpisode(episode);
                    System.out.println("You have updated '" + show.getName() + "' now set to (S" + show.getSeason() + "E" + show.getEpisode() + ")");
                    contentDataHandler.showToSer(showList);
                    mainMenu(showList, filmList);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return;
            case 2:
                System.out.println("Enter desired season: ");
                season = scanner.nextInt();
                System.out.println("Enter desired episode: ");
                episode = scanner.nextInt();
                try {
                    show.setSeason(season);
                    show.setEpisode(episode);
                    System.out.println("You have updated '" + show.getName().toUpperCase() + "' now set to (S" + show.getSeason() + "E" + show.getEpisode() + ")");
                    contentDataHandler.showToSer(showList);
                    mainMenu(showList, filmList);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


        }
    }
    private static void removeContent(List<Show> showList, List<Film> filmList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(CC.formatColoredMessage("--------------------", WHITE));
        System.out.println(CC.formatColoredMessage("Welcome to the content removal tool!", CYAN));
        System.out.println(CC.formatColoredMessage("--------------------", WHITE));
        System.out.println(CC.formatColoredMessage("Please select an option:", BLUE));
        System.out.println(CC.formatColoredMessage("1. Remove a show", GREEN));
        System.out.println(CC.formatColoredMessage("2. Remove a film", GREEN));
        System.out.println(CC.formatColoredMessage("3. Return to main menu", RED));
        int choice = scanner.nextInt();
        switch(choice) {
            case 1:
                removeShow(showList, filmList);
                break;
            case 2:
                removeFilm(showList, filmList);
                break;
            case 3:
                mainMenu(showList, filmList);
                break;
        }
    }

    private static void removeShow(List<Show> showList, List<Film> filmList) {
        System.out.println(CC.formatColoredMessage("--------------------", WHITE));
        System.out.println(CC.formatColoredMessage("Please select a show to remove from below", BLUE));
        System.out.println(CC.formatColoredMessage("--------------------", WHITE));
        ShowManager.listAllShows(showList);
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if(choice == 999) {
            mainMenu(showList, filmList);
        }
        for(Show show : showList) {
            if((showList.indexOf(show) + 1) == choice) {
                showList.remove(show);
                System.out.println(CC.formatColoredMessage("%s has been removed from your list", GREEN, show.getName()));
                contentDataHandler.showToSer(showList);
                mainMenu(showList, filmList);
            }
        }
    }

    private static void removeFilm(List<Show> showList, List<Film> filmList) {
        System.out.println(CC.formatColoredMessage("--------------------", WHITE));
        System.out.println(CC.formatColoredMessage("Please select a film to remove from below", BLUE));
        System.out.println(CC.formatColoredMessage("(Enter 999 to return to main menu)", GRAY));
        System.out.println(CC.formatColoredMessage("--------------------", WHITE));
        FilmManager.listAllFilms(filmList);
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if(choice == 999) {
            mainMenu(showList, filmList);
        }
        for(Film film : filmList) {
            if((filmList.indexOf(film) + 1) == choice) {
                filmList.remove(film);
                System.out.println(CC.formatColoredMessage("%s has been removed from your list", GREEN, film.getName()));
                contentDataHandler.filmToSer(filmList);
                mainMenu(showList, filmList);
            }
            mainMenu(showList, filmList);
        }
    }
}