package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSQLDAO {
    public List<Playlist> getAll() {
        List<Playlist> playlists = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from songs");
            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String band = rs.getString("band");
                String genre = rs.getString("genre");
                String time = rs.getString("time");
                int year = rs.getInt("year");

                Playlist playlist = new Playlist(id, name, band, genre, time, year);
                playlists.add(playlist);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return playlists;
    }

    public String createPlaylist(String name, String band, String genre, String time, int year) {
        String status = "0";

        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement stmt = connection.createStatement();
            System.out.println("INSERT INTO songs(name, band, genre, time, year) values ('" +
                    name + "', '" + band + "', " + genre + ", " + time + ", '" + year + "');");
            int rs = stmt.executeUpdate("INSERT INTO songs(name, band, genre, time, year) values ('" +
                    name + "', '" + band + "', '" + genre + "', '" + time + "', '" + year + "');");
            status = Integer.toString(rs);

        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }

    public String deletePlaylist(int rowId) {
        String status = "0";

        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement stmt = connection.createStatement();
            System.out.println("DELETE FROM songs WHERE id='" + rowId + "';");
            int rs = stmt.executeUpdate("DELETE FROM songs WHERE id='" + rowId + "';");
            status = Integer.toString(rs);
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }
    public String updatePlaylist(int rowId, List<String> updateArgs) {
        String status = "0";
        String updateFields = String.join(", ", updateArgs);

        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement stmt = connection.createStatement();
            int rs = stmt.executeUpdate("UPDATE songs SET " + updateFields + " WHERE id=" + rowId + ";");
            status = Integer.toString(rs);
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }
}
