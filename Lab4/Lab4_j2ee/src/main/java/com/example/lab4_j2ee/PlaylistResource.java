package com.example.lab4_j2ee;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/playlist")
@Produces({MediaType.APPLICATION_JSON})
public class PlaylistResource {
    //@Resource(lookup = "jdbc/postgresbd")
    //private static DataSource dataSource;
    private static DataSource dataSource;
    private static final String JNDI_LOOK = "java:/comp/env/jdbc/postgresbd";
    static {
        try {
            Context context = new InitialContext();
            Object lookup = context.lookup(JNDI_LOOK);
            if (lookup != null) {
                dataSource = (DataSource) lookup;
            } else {
                new RuntimeException("JNDI lookup issue");
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @GET
    public List<Playlist> getAll() {
        List<Playlist> playlists = new PostgreSQLDAO(getConnection()).getAll();
        return playlists;
    }
    public static Connection getConnection() {
        Connection result = null;
        try {
            result = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistResource.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
        return result;
    }
}