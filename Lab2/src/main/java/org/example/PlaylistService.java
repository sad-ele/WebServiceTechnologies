package org.example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
import com.sun.xml.ws.fault.SOAPFaultBuilder;
import java.util.ArrayList;
import javax.jws.WebParam;

@WebService(serviceName = "PlaylistService")
public class PlaylistService {
    SOAPFaultBuilder soapFaultBuilder;

    public PlaylistService(){
        soapFaultBuilder.captureStackTrace = false;
    }

    @WebMethod(operationName = "getAll")
    public List<Playlist> getAll() {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.getAll();
    }

    @WebMethod(operationName = "createPlaylist")
    public String createPlaylist(@WebParam(name = "name") String name,
                                @WebParam(name = "band") String band,
                                 @WebParam(name = "genre") String genre,
                                @WebParam(name = "time") String time,
                                @WebParam(name = "year") int year) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.createPlaylist(name, band, genre, time, year);
    }

    @WebMethod(operationName = "deletePlaylist")
    public String deletePlaylist(@WebParam(name = "rowId") int rowId) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.deletePlaylist(rowId);
    }

    @WebMethod(operationName = "updatePlaylist")
    public String updatePlaylist(@WebParam(name = "rowId") int rowId,
                                @WebParam(name = "name") String name,
                                @WebParam(name = "band") String band,
                                 @WebParam(name = "genre") String genre,
                                @WebParam(name = "time") String time,
                                @WebParam(name = "year") int year) {

        List<String> updateArgs = new ArrayList<>();

        if (name != null && !name.trim().isEmpty()) updateArgs.add("name = '" + name + "'");
        if (band != null && !band.trim().isEmpty()) updateArgs.add("band = '" + band + "'");
        if (genre != null && !genre.trim().isEmpty()) updateArgs.add("genre = '" + genre + "'");
        if (year != 0) updateArgs.add("year = '" + year + "'");
        if (time != null && !time.trim().isEmpty()) updateArgs.add("time = '" + time + "'");


        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.updatePlaylist(rowId, updateArgs);
    }

}
