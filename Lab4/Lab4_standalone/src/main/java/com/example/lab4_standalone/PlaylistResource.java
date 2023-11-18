package com.example.lab4_standalone;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/playlist")
@Produces({MediaType.APPLICATION_JSON})

public class PlaylistResource {
    @GET
    public ArrayList<Playlist> getAll() {
        return (ArrayList<Playlist>) new PostgreSQLDAO().getAll();
    }
}
