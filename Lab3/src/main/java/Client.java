import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.foreach.soap.ws.client.generated.Playlist;
import com.foreach.soap.ws.client.generated.PlaylistService_Service;
import com.foreach.soap.ws.client.generated.CastToIntException;
import com.foreach.soap.ws.client.generated.EmptyFieldException;
import com.foreach.soap.ws.client.generated.RowIsNotExistsException;
import org.example.PlaylistService;
import org.example.error.CastToIntFault;
import org.example.error.*;


public class Client {
    public static void main(String[] args) throws MalformedURLException, CastToIntException {
        URL url = new URL("http://localhost:8080/PlaylistService?wsdl"); //standalone
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
    private static void updatePlaylistRowById(PlaylistService_Service playlistService) throws CastToIntException {

        String status = "Bad";

        Scanner scanner = new Scanner(System.in);

        System.out.print("Input rowID (integer): ");
        String rowIDString = scanner.nextLine();

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
                        updateRowsMap.put("name", name);
                        break;

                    case "band":
                        System.out.println("Input new value for 'band' field:");
                        String band = scanner.nextLine();
                        updateRowsMap.put("band", band);
                        break;

                    case "year":
                        System.out.println("Input new value for 'year' field (integer):");
                        String year = scanner.nextLine();
                        updateRowsMap.put("year", year);
                        break;

                    case "genre":
                        System.out.println("Input new value for 'genre' field:");
                        String genre = scanner.nextLine();
                        updateRowsMap.put("genre", genre);
                        break;

                    case "time":
                        System.out.println("Input new value for 'time' field (minutes:seconds):");
                        String time = scanner.nextLine();
                        updateRowsMap.put("time", time);
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
                System.out.println("You input new values for row " + rowIDString + ":\n" + updateRowsMap);
                System.out.println("Do you really want to change this fields for row " + rowIDString + "? (y -> yes, other -> no)");
                String agree = scanner.nextLine();
                if (agree.equals("y")) {
                    String name = updateRowsMap.get("name");
                    String band = updateRowsMap.get("band");
                    String genre = updateRowsMap.get("genre");
                    String time = updateRowsMap.get("time");
                    String year = updateRowsMap.get("year").trim();

                    try {
                        status = playlistService.getPlaylistServicePort().updatePlaylist(rowIDString, name, band, genre, time, year);
                    } catch (CastToIntException | EmptyFieldException | RowIsNotExistsException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    System.out.println("You just cancel your request. Try another request or exit.");
                }

            } else {
                System.out.println("All arguments is empty. Row update can not be completed.");
            }
        System.out.println("Status: " + status);
    }

    private static void deletePlaylistRowById(PlaylistService_Service playlistService) {
        String status = "0";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Input rowID (integer): ");
        String rowId = scanner.nextLine();
        try {
            status = playlistService.getPlaylistServicePort().deletePlaylist(rowId);
        } catch (CastToIntException | RowIsNotExistsException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Status: " + status);
    }

    private static void createPlaylistRow(PlaylistService_Service playlistService) {

        String status = "0";
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
        String year;

        try {
            year = yearStr.trim();
            try {
                status = playlistService.getPlaylistServicePort().createPlaylist(name, band, genre, time, year);
            } catch (CastToIntException | EmptyFieldException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (status.equals("1")) status = "Good";
            System.out.println("Status: " + status);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect year!");
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
