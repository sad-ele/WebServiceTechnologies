package org.example.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.example.client.generated.Playlist;
import org.example.client.generated.PlaylistService_Service;


public class Client {
    public void serviceRequest(String accessPoint) throws MalformedURLException {
        URL url = new URL(accessPoint);
        PlaylistService_Service playlistService = new PlaylistService_Service(url);

        try (
                Scanner scanner = new Scanner(System.in)) {
            System.out.println("Choose CRUD method (input CREATE, READ, UPDATE or DELETE), or input 'exit' for exit:");
            String choosenMethod;
            do {
                choosenMethod = scanner.nextLine();

                if (choosenMethod != null && !choosenMethod.trim().isEmpty()) {

                    switch (choosenMethod) {
                        case ("CREATE"):
                            createPlaylistRow(playlistService);
                            System.out.println("That's it! You can choose another CRUD method or input 'exit' for exit");
                            break;
                        case ("READ"):
                            readPlaylistRowsByFields(playlistService);
                            System.out.println("That's it! You can choose another CRUD method or input 'exit' for exit");
                            break;
                        case ("UPDATE"):
                            updatePlaylistRowById(playlistService);
                            System.out.println("That's it! You can choose another CRUD method or input 'exit' for exit");
                            break;
                        case ("DELETE"):
                            deletePlaylistRowById(playlistService);
                            System.out.println("That's it! You can choose another CRUD method or input 'exit' for exit");
                            break;
                        case ("exit"):
                            System.out.println("Bye-Bye!");
                            break;
                        default:
                            System.out.println("You can input just CREATE, READ, UPDATE or DELETE!");
                            System.out.println("Try again or use 'exit' for exit.");
                            break;
                    }
                }
            } while (!Objects.equals(choosenMethod, "exit"));
        }
    }
    private static void updatePlaylistRowById(PlaylistService_Service playlistService) {

        String status = "Bad";

        Scanner scanner = new Scanner(System.in);

        System.out.print("Input rowID (integer): ");
        String rowIDString = scanner.nextLine();

        int rowId = -1;
        if (rowIDString != null && !rowIDString.trim().isEmpty()) {
            try {
                rowId = Integer.parseInt(rowIDString.trim());
            } catch (NumberFormatException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Your request is incorrect! RowId is not an integer.");
            }
        }

        if (rowId != -1) {

            System.out.println("What fields you want update for this row? \n" +
                    "Choose fields from 'name', 'band', 'genre', 'time', 'year' and input them");
            String updateRows = scanner.nextLine();


            String[] updateRowsList = updateRows.split(",", -1);

            Map<String, String> updateRowsMap = new HashMap<>();
            updateRowsMap.put("name", "");
            updateRowsMap.put("band", "");
            updateRowsMap.put("genre", "");
            updateRowsMap.put("time", "");
            updateRowsMap.put("year", "");


            for (String row : updateRowsList) {
                switch (row) {
                    case "name":
                        System.out.println("Input new value for 'name' field:");
                        String name = scanner.nextLine();
                        if (name != null && !name.trim().isEmpty()) {
                            updateRowsMap.put("name", name);
                        } else {
                            System.out.println("Field 'name' is incorrect and will not be updated!");
                        }
                        break;

                    case "band":
                        System.out.println("Input new value for 'band' field:");
                        String band = scanner.nextLine();
                        if (band != null && !band.trim().isEmpty()) {
                            updateRowsMap.put("band", band);
                        } else {
                            System.out.println("Field 'band' is incorrect and will not be updated!");
                        }
                        break;

                    case "year":
                        System.out.println("Input new value for 'year' field (integer):");
                        String year = scanner.nextLine();
                        try {
                            Integer.parseInt(year.trim());
                        } catch (NumberFormatException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                            year = "";
                        }
                        if (!year.trim().isEmpty()) {
                            updateRowsMap.put("year", year);
                        } else {
                            System.out.println("Field 'year' is not an integer and will not be updated!");
                        }
                        break;

                    case "genre":
                        System.out.println("Input new value for 'genre' field:");
                        String genre = scanner.nextLine();
                        if (genre != null && !genre.trim().isEmpty()) {
                            updateRowsMap.put("genre", genre);
                        } else {
                            System.out.println("Field 'genre' is incorrect and will not be updated!");
                        }
                        break;

                    case "time":
                        System.out.println("Input new value for 'time' field (minutes:seconds):");
                        String time = scanner.nextLine();
                        if (time != null && !time.trim().isEmpty()) {
                            updateRowsMap.put("time", time);
                        } else {
                            System.out.println("Field 'time' is incorrect and will not be updated!");
                        }
                        break;

                    default:
                        System.out.println("Incorrect request. Try again!");
                        break;
                }
            }

            int i = 0;
            for(String val : updateRowsMap.values()){
                if (val != null && !val.trim().isEmpty()) {
                    i++;
                }
            }

            if (i != 0) {

                System.out.println("You input new values for row " + rowId + ":\n" + updateRowsMap);
                System.out.println("Do you really want to change this fields for row " + rowId + "? (y -> yes, other -> no)");
                String agree = scanner.nextLine();
                if (agree.equals("y")) {
                    String name = updateRowsMap.get("name");
                    String band = updateRowsMap.get("band");
                    String genre = updateRowsMap.get("genre");
                    String time = updateRowsMap.get("time");
                    int year = Integer.parseInt(updateRowsMap.get("year").trim());

                    status = playlistService.getPlaylistServicePort().updatePlaylist(rowId, name, band, genre, time, year);

                } else {
                    System.out.println("You just cancel your request. Try another request or exit.");
                }


            } else {
                System.out.println("All arguments is empty. Row update can not be completed.");
            }
        }
        System.out.println("Status: " + status);
    }

    private static void deletePlaylistRowById(PlaylistService_Service playlistService) {
        String status = "Bad";

        Scanner scanner = new Scanner(System.in);

        System.out.print("Input rowID (integer): ");
        String rowIDString = scanner.nextLine();

        try {
            int rowId = Integer.parseInt(rowIDString.trim());
            status = playlistService.getPlaylistServicePort().deletePlaylist(rowId);
            if (status.equals("1")) status = "Good";
            System.out.println("Status: " + status);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect rowId value! Input just one integer.");
        }
    }

    private static void createPlaylistRow(PlaylistService_Service playlistService) {

        String status = "Bad";

        Scanner scanner = new Scanner(System.in);

        System.out.print("Input name: ");
        String name = scanner.nextLine();
        System.out.print("Input band: ");
        String band = scanner.nextLine();
        System.out.print("Input genre: ");
        String genre = scanner.nextLine();
        System.out.print("Input time (minutes:seconds): ");
        String time = scanner.nextLine();
        System.out.print("Input year (integer): ");
        String yearStr = scanner.nextLine();

        int year;


        if ((name != null && !name.trim().isEmpty())  &&
                (band != null && !band.trim().isEmpty()) &&
                (genre != null && !genre.trim().isEmpty()) &&
                (time != null && !time.trim().isEmpty()))  {
            try {
                year = Integer.parseInt(yearStr.trim());
                status = playlistService.getPlaylistServicePort().createPlaylist(name, band, genre, time, year);
                if (status.equals("1")) status = "Good";
                System.out.println("Status: " + status);
            } catch (NumberFormatException ex) {
                System.out.println("Incorrect value!");
            }
        }
        else {
            System.out.println("Your request is incorrect!");
        }
    }

    private static void readPlaylistRowsByFields(PlaylistService_Service playlistService) {
        List<Playlist> playlists =
                playlistService.getPlaylistServicePort().getAll();
        for (Playlist playlist : playlists) {
            System.out.println("Playlist {"  + "id=" + playlist.getId()
                    + ", name=" + playlist.getName() +
                    ", band=" + playlist.getBand() +
                    ", genre=" + playlist.getGenre() +
                    ", time=" + playlist.getTime() +
                    ", year=" + playlist.getYear() + '}');
        }
        System.out.println("Total songs: " + playlists.size());
    }
}
