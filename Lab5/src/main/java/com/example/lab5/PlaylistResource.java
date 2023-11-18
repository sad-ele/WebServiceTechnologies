package com.example.lab5;

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

    @POST
    public String createPlaylist(@QueryParam("name") String name,
                                 @QueryParam("band") String band,
                                 @QueryParam("genre") String genre,
                                 @QueryParam("time") String time,
                                 @QueryParam("year") int year) {
        return new PostgreSQLDAO().createPlaylist(name, band, genre, time, year);
    }

    @DELETE
    public String deletePlaylist(@QueryParam("rowId") int rowId) {
        return new PostgreSQLDAO().deletePlaylist(rowId);
    }

    @PUT
    public String updatePlaylist(
            @QueryParam("rowId") int rowId,
            @QueryParam("name") String name,
            @QueryParam("band") String band,
            @QueryParam("genre") String genre,
            @QueryParam("time") String time,
            @QueryParam("year") int year) {

        List<String> updateArgs = new ArrayList<>();

        if (name != null && !name.trim().isEmpty()) updateArgs.add("name = '" + name + "'");
        if (band != null && !band.trim().isEmpty()) updateArgs.add("band = '" + band + "'");
        if (genre != null && !genre.trim().isEmpty()) updateArgs.add("genre = '" + genre + "'");
        if (year != 0) updateArgs.add("year = '" + year + "'");
        if (time != null && !time.trim().isEmpty()) updateArgs.add("time = '" + time + "'");

        return new PostgreSQLDAO().updatePlaylist(rowId, updateArgs);
    }
}
