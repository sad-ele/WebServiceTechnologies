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
        try (Connection connection = ConnectionUtil.getConnection()) {
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
}
