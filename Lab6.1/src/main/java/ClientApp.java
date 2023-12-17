import com.example.lab5.Playlist;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientApp {

    private static final String URL = "http://localhost:8080/rest/playlist";

    public static void main(String[] args) {
        Client client = Client.create();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose CRUD method (input CREATE, READ, UPDATE or DELETE), or input 'exit' for exit:");
        String choosenMethod;
        do {
            choosenMethod = scanner.nextLine();
            if (choosenMethod != null && !choosenMethod.trim().isEmpty()) {
                    switch (choosenMethod) {
                        case ("CREATE"):
                            createPlaylistRow(client);
                            System.out.println("That's it! You can choose another CRUD method or input 'exit' for exit");
                            break;
                        case ("READ"):
                            readPlaylistRowsByFields(client);
                            System.out.println("That's it! You can choose another CRUD method or input 'exit' for exit");
                            break;
                        case ("UPDATE"):
                            updatePlaylistRowById(client);
                            System.out.println("That's it! You can choose another CRUD method or input 'exit' for exit");
                            break;
                        case ("DELETE"):
                            deletePlaylistRowById(client);
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

    private static void updatePlaylistRowById(Client client) {

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

                System.out.println("You input new values for row " + rowIDString + ":\n" + updateRowsMap);
                System.out.println("Do you really want to change this fields for row " + rowIDString + "? (y -> yes, other -> no)");
                String agree = scanner.nextLine();
                if (agree.equals("y")) {
                    String name = updateRowsMap.get("name");
                    String band = updateRowsMap.get("band");
                    String genre = updateRowsMap.get("genre");
                    String time = updateRowsMap.get("time");
                    String yearStr = updateRowsMap.get("year").trim();
                    //int year = Integer.parseInt(yearStr);

                    WebResource webResource = client.resource(URL);
                    webResource = webResource.queryParam("rowId",
                            rowIDString).queryParam("name", name).queryParam("band",
                            band).queryParam("genre", genre).queryParam("time",
                            time).queryParam("year", yearStr);
                    ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).put(ClientResponse.class);
                    if (response.getStatus() == 200)
                        System.out.println("OK");
                    else System.out.println("Something went wrong");

                } else {
                    System.out.println("You just cancel your request. Try another request or exit.");
                }
    }

    private static void deletePlaylistRowById(Client client) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Input rowID (integer): ");
        String rowIDString = scanner.nextLine();

        WebResource webResource = client.resource(URL);
        webResource = webResource.queryParam("rowId", rowIDString);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);

        if (response.getStatus() == 200)
            System.out.println("OK");
        else System.out.println("Something went wrong");
    }

    private static void createPlaylistRow(Client client) {

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

        WebResource webResource = client.resource(URL);
        webResource = webResource.queryParam("name", name).queryParam("band",
                        band).queryParam("genre", genre).queryParam("time",
                        time).queryParam("year", yearStr);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class);
        if (response.getStatus() == 200)
            System.out.println("OK");
        else System.out.println("Something went wrong");
    }

    private static void readPlaylistRowsByFields(Client client) {
        for (Playlist playlist : findAll(client)) {
            System.out.println(playlist);
        }
    }

    private static List<Playlist> findAll(Client client) {
        WebResource webResource = client.resource(URL);
        webResource = webResource.queryParam(URL, URL);

        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<List<Playlist>> type = new GenericType<List<Playlist>>() {};
        return response.getEntity(type);
    }
}
