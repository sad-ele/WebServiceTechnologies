package org.example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
import com.sun.xml.ws.fault.SOAPFaultBuilder;
import java.util.ArrayList;
import javax.jws.WebParam;
import org.example.error.*;

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
                                @WebParam(name = "year") String year) throws EmptyFieldException, CastToIntException {

        String status;

        if (name != null && !name.trim().isEmpty() &&
                band != null && !band.trim().isEmpty() &&
                genre != null && !genre.trim().isEmpty() &&
                time != null && !time.trim().isEmpty() &&
                year != null && !year.trim().isEmpty()) {

            try {
                int yearInt = Integer.parseInt(year.trim());

                PostgreSQLDAO dao = new PostgreSQLDAO();
                status = dao.createPlaylist(name, band, genre, time, yearInt);

            } catch (NumberFormatException ex) {
                CastToIntFault fault = CastToIntFault.defaultInstance();
                throw new CastToIntException("Error was occurred in class: " + PlaylistService.class.getName() +
                        ", method - createPlaylist(). \n We get 'NumberFormatException': " + ex +
                        ", when trying convert year to int.", fault);
            }

        } else {
            EmptyFieldFault fault = EmptyFieldFault.defaultInstance();
            throw new EmptyFieldException("Error was occurred in class " + PlaylistService.class.getName() +
                    ", method createPlaylist(). \n At least 1 field is empty ", fault);
        }

        return status;
    }

    @WebMethod(operationName = "deletePlaylist")
    public String deletePlaylist(@WebParam(name = "rowId") String rowId) throws CastToIntException, RowIsNotExistsException {
        String status;
        try {
            int rowIdInt = Integer.parseInt(rowId.trim());
            PostgreSQLDAO dao = new PostgreSQLDAO();
            status = dao.deletePlaylist(rowIdInt);
            System.out.println(status);
            if (status.equals("0")) {
                RowIsNotExistsFault fault = RowIsNotExistsFault.defaultInstance();
                throw new RowIsNotExistsException("Error was occurred in class: " + PlaylistService.class.getName() +
                        ", method - deletePlaylist(). \n We can't delete row in table in DB with rowId = " + rowId +
                        " because row does not exist", fault);
            }

        } catch (NumberFormatException ex) {
            CastToIntFault fault = CastToIntFault.defaultInstance();
            throw new CastToIntException("Error was occurred in class: " + PlaylistService.class.getName() +
                    ", method - deletePlaylist(). \n We get 'NumberFormatException': " + ex +
                    ", when trying convert rowId to int.", fault);
        }

        return status;
    }

    @WebMethod(operationName = "updatePlaylist")
    public String updatePlaylist(@WebParam(name = "rowId") String rowId,
                                @WebParam(name = "name") String name,
                                @WebParam(name = "band") String band,
                                 @WebParam(name = "genre") String genre,
                                @WebParam(name = "time") String time,
                                @WebParam(name = "year") String year) throws EmptyFieldException,
            RowIsNotExistsException,CastToIntException {

        String status;
        int rowIdInt;

        try {
            rowIdInt = Integer.parseInt(rowId.trim());
        }
        catch (NumberFormatException ex) {
            CastToIntFault fault = CastToIntFault.defaultInstance();
            throw new CastToIntException("Error was occurred in class: " + PlaylistService.class.getName() +
                    ", method - updatePlaylist(). \n We get 'NumberFormatException': " + ex +
                    ", when trying convert row-id to int.", fault);
        }

            List<String> updateArgs = new ArrayList<>();

            if (name != null && !name.trim().isEmpty()) updateArgs.add("name = '" + name + "'");
            if (band != null && !band.trim().isEmpty()) updateArgs.add("band = '" + band + "'");
            if (genre != null && !genre.trim().isEmpty()) updateArgs.add("genre = '" + genre + "'");
            if (time != null && !time.trim().isEmpty()) updateArgs.add("time = '" + time + "'");

            if (year == null || year.trim().isEmpty())
            {
                //Integer.parseInt(year.trim());
                EmptyFieldFault fault = EmptyFieldFault.defaultInstance();
                fault.setMessage("All required parameters are empty. Please, input at least one of them.");
                throw new EmptyFieldException("Error was occurred in class " + PlaylistService.class.getName() +
                        ", method updatePlaylist().", fault);
            }
            else {
                try {
                    Integer.parseInt(year.trim());
                    updateArgs.add("year = '" + year + "'");
                } catch (NumberFormatException ex) {
                    CastToIntFault fault = CastToIntFault.defaultInstance();
                    throw new CastToIntException("Error was occurred in class: " + PlaylistService.class.getName() +
                            ", method - updatePlaylist(). \n We get 'NumberFormatException': " + ex +
                            ", when trying convert 'year' to int.", fault);
                }
            }

            int i = 0;
            for (String param : updateArgs) {
                if (param != null && !param.trim().isEmpty()) {
                    i++;
                }
            }

            PostgreSQLDAO dao = new PostgreSQLDAO();
            status = dao.updatePlaylist(rowIdInt, updateArgs);

        return status;
    }

}
