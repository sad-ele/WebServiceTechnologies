package org.example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
import com.sun.xml.ws.fault.SOAPFaultBuilder;

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
}