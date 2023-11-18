import com.example.lab4_standalone.Playlist;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientApp {

    //private static final String URL = "http://localhost:8080/rest/playlist";
    private static final String URL = "http://localhost:8083/Lab4_j2ee_1_0_SNAPSHOT_war/rest/playlist";

    public static void main(String[] args) {
        Client client = Client.create();

        System.out.println("\nAll songs");
        List<Playlist> playlists = findAll(client);
        for (Playlist playlist : playlists) {
            System.out.println(playlist);
        }

        System.out.println("\nSongs older than 2015");
        for (Playlist playlist : playlists) {
            if (playlist.getYear() < 2015) {
                System.out.println("Playlist {Id=" + playlist.getId() +", name="+ playlist.getName() +", band=" +
                        playlist.getBand() +", genre=" + playlist.getGenre() +", time=" + playlist.getTime() +
                        ", year=" + playlist.getYear() + "}");
            }
        }

        System.out.println("\nAlternative songs");
        for (Playlist playlist : playlists) {
            if (Objects.equals(playlist.getGenre(), "alternative")) {
                System.out.println("Playlist {Id=" + playlist.getId() +", name="+ playlist.getName() +", band=" +
                        playlist.getBand() +", genre=" + playlist.getGenre() +", time=" + playlist.getTime() +
                        ", year=" + playlist.getYear() + "}");
            }
        }

        System.out.println("\nOur Last Night songs younger than 2012");
        for (Playlist playlist : playlists) {
            if (Objects.equals(playlist.getBand(), "Our Last Night") && playlist.getYear() > 2012) {
                System.out.println("Playlist {Id=" + playlist.getId() +", name="+ playlist.getName() +", band=" +
                        playlist.getBand() +", genre=" + playlist.getGenre() +", time=" + playlist.getTime() +
                        ", year=" + playlist.getYear() + "}");
            }
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
