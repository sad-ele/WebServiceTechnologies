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


public class Client {
    public static void main(String[] args) throws MalformedURLException {
        //URL url = new URL("http://localhost:8080/PlaylistService?wsdl"); //standalone
        URL url = new URL("http://localhost:8083/lab1_j2ee_1_0_SNAPSHOT_war/ws/playlist?wsdl");
        PlaylistService_Service playlistService = new PlaylistService_Service(url);

        List<Playlist> playlists =
                playlistService.getPlaylistServicePort().getAll();
        System.out.println("\nAll songs");
        for (Playlist playlist : playlists) {
            System.out.println("Playlist {"  + "id=" + playlist.getId()
                    + ", name=" + playlist.getName() +
                    ", band=" + playlist.getBand() +
                    ", genre=" + playlist.getGenre() +
                    ", time=" + playlist.getTime() +
                    ", year=" + playlist.getYear() + '}');
        }
        System.out.println("Total songs: " + playlists.size());

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
}
