package com.example.lab1_j2ee;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
import com.sun.xml.ws.fault.SOAPFaultBuilder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebService(serviceName = "PlaylistService")
public class PlaylistService {

    //@Resource(lookup = "jdbc/postgresbd")
    //private DataSource dataSource;
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
    //SOAPFaultBuilder soapFaultBuilder;

//    public PlaylistService(){
//        soapFaultBuilder.captureStackTrace = false;
//    }

    @WebMethod(operationName = "getAll")
    public List<Playlist> getAll() {
        PostgreSQLDAO dao = new PostgreSQLDAO(getConnection());
        return dao.getAll();
    }

    private Connection getConnection() {
        Connection result = null;
        try {
            result = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistService.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
        return result;
    }
}
