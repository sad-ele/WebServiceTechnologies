package com.example.lab5;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.example.lab5.errors.*;

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
                                 @QueryParam("year") String year) throws CastToIntException, EmptyFieldException {
        String status = "-1";

        if (name != null && !name.trim().isEmpty() &&
                band != null && !band.trim().isEmpty() &&
                genre != null && !genre.trim().isEmpty() &&
                time != null && !time.trim().isEmpty() &&
                year != null && !year.trim().isEmpty()) {

            try {
                Integer.parseInt(year.trim());
                status = new PostgreSQLDAO().createPlaylist(name, band, genre, time, year);

            } catch (NumberFormatException ex) {
                throw new CastToIntException("An error occurred when trying to convert year to integer. ");
            }

        } else {
            throw EmptyFieldException.DEFAULT_INSTANCE;
        }
        return status;
    }

    @DELETE
    public String deletePlaylist(@QueryParam("rowId") String rowId) throws RowIsNotExistsException, CastToIntException, EmptyFieldException {

        String status;
        if (rowId != null && !rowId.trim().isEmpty()) {
            try {
                Integer.parseInt(rowId.trim());
                status = new PostgreSQLDAO().deletePlaylist(rowId);
                if (status.equals("0")) {
                    throw RowIsNotExistsException.DEFAULT_INSTANCE;
                }
            } catch (NumberFormatException ex) {
                throw new CastToIntException("An error occurred when trying to convert rowId to integer. ");
            }
        } else {
            throw EmptyFieldException.DEFAULT_INSTANCE;
        }
        return status;
    }

    @PUT
    public String updatePlaylist(
            @QueryParam("rowId") String rowId,
            @QueryParam("name") String name,
            @QueryParam("band") String band,
            @QueryParam("genre") String genre,
            @QueryParam("time") String time,
            @QueryParam("year") String year) throws EmptyFieldException, CastToIntException, RowIsNotExistsException {

        String status;
        List<String> updateArgs = new ArrayList<>();

        if (rowId != null && !rowId.trim().isEmpty()) {
            if ((name != null && !name.trim().isEmpty()) ||
                    (band != null && !band.trim().isEmpty()) ||
                    (genre != null && !genre.trim().isEmpty()) ||
                    (time != null && !time.trim().isEmpty()) ||
                    (year != null && !year.trim().isEmpty())) {

                try {
                    Integer.parseInt(rowId.trim());
                    if (name != null && !name.trim().isEmpty()) updateArgs.add("name = '" + name + "'");
                    if (band != null && !band.trim().isEmpty()) updateArgs.add("band = '" + band + "'");
                    if (genre != null && !genre.trim().isEmpty()) updateArgs.add("genre = '" + genre + "'");
                    if (time != null && !time.trim().isEmpty()) updateArgs.add("time = '" + time + "'");
                    try {
                        if (year != null && !year.trim().isEmpty()) {
                            Integer.parseInt(year.trim());
                            updateArgs.add("year = '" + year + "'");
                        }
                    } catch (NumberFormatException e) {
                        throw new CastToIntException("An error occurred when trying to convert " +
                                "year to integer. ");
                    }
                } catch (NumberFormatException e) {
                    throw new CastToIntException("An error occurred when trying to convert 'rowId' to integer.");
                }
            } else {
                throw EmptyFieldException.DEFAULT_INSTANCE;
            }
        } else {
            throw new EmptyFieldException("rowId cannot be empty!");
        }

        status = new PostgreSQLDAO().updatePlaylist(rowId, updateArgs);
        if (status.equals("0")) {
            throw RowIsNotExistsException.DEFAULT_INSTANCE;
        }
        return status;
    }
}
