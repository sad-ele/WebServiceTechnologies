
package com.foreach.soap.ws.client.generated;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PlaylistService", targetNamespace = "http://example.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PlaylistService {


    /**
     * 
     * @param year
     * @param name
     * @param genre
     * @param band
     * @param time
     * @return
     *     returns java.lang.String
     * @throws CastToIntException
     * @throws EmptyFieldException
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createPlaylist", targetNamespace = "http://example.org/", className = "com.foreach.soap.ws.client.generated.CreatePlaylist")
    @ResponseWrapper(localName = "createPlaylistResponse", targetNamespace = "http://example.org/", className = "com.foreach.soap.ws.client.generated.CreatePlaylistResponse")
    @Action(input = "http://example.org/PlaylistService/createPlaylistRequest", output = "http://example.org/PlaylistService/createPlaylistResponse", fault = {
        @FaultAction(className = EmptyFieldException.class, value = "http://example.org/PlaylistService/createPlaylist/Fault/EmptyFieldException"),
        @FaultAction(className = CastToIntException.class, value = "http://example.org/PlaylistService/createPlaylist/Fault/CastToIntException")
    })
    public String createPlaylist(
        @WebParam(name = "name", targetNamespace = "")
        String name,
        @WebParam(name = "band", targetNamespace = "")
        String band,
        @WebParam(name = "genre", targetNamespace = "")
        String genre,
        @WebParam(name = "time", targetNamespace = "")
        String time,
        @WebParam(name = "year", targetNamespace = "")
        String year)
        throws CastToIntException, EmptyFieldException
    ;

    /**
     * 
     * @param rowId
     * @return
     *     returns java.lang.String
     * @throws RowIsNotExistsException
     * @throws CastToIntException
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deletePlaylist", targetNamespace = "http://example.org/", className = "com.foreach.soap.ws.client.generated.DeletePlaylist")
    @ResponseWrapper(localName = "deletePlaylistResponse", targetNamespace = "http://example.org/", className = "com.foreach.soap.ws.client.generated.DeletePlaylistResponse")
    @Action(input = "http://example.org/PlaylistService/deletePlaylistRequest", output = "http://example.org/PlaylistService/deletePlaylistResponse", fault = {
        @FaultAction(className = CastToIntException.class, value = "http://example.org/PlaylistService/deletePlaylist/Fault/CastToIntException"),
        @FaultAction(className = RowIsNotExistsException.class, value = "http://example.org/PlaylistService/deletePlaylist/Fault/RowIsNotExistsException")
    })
    public String deletePlaylist(
        @WebParam(name = "rowId", targetNamespace = "")
        String rowId)
        throws CastToIntException, RowIsNotExistsException
    ;

    /**
     * 
     * @param year
     * @param name
     * @param genre
     * @param band
     * @param time
     * @param rowId
     * @return
     *     returns java.lang.String
     * @throws RowIsNotExistsException
     * @throws EmptyFieldException
     * @throws CastToIntException
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "updatePlaylist", targetNamespace = "http://example.org/", className = "com.foreach.soap.ws.client.generated.UpdatePlaylist")
    @ResponseWrapper(localName = "updatePlaylistResponse", targetNamespace = "http://example.org/", className = "com.foreach.soap.ws.client.generated.UpdatePlaylistResponse")
    @Action(input = "http://example.org/PlaylistService/updatePlaylistRequest", output = "http://example.org/PlaylistService/updatePlaylistResponse", fault = {
        @FaultAction(className = EmptyFieldException.class, value = "http://example.org/PlaylistService/updatePlaylist/Fault/EmptyFieldException"),
        @FaultAction(className = RowIsNotExistsException.class, value = "http://example.org/PlaylistService/updatePlaylist/Fault/RowIsNotExistsException"),
        @FaultAction(className = CastToIntException.class, value = "http://example.org/PlaylistService/updatePlaylist/Fault/CastToIntException")
    })
    public String updatePlaylist(
        @WebParam(name = "rowId", targetNamespace = "")
        String rowId,
        @WebParam(name = "name", targetNamespace = "")
        String name,
        @WebParam(name = "band", targetNamespace = "")
        String band,
        @WebParam(name = "genre", targetNamespace = "")
        String genre,
        @WebParam(name = "time", targetNamespace = "")
        String time,
        @WebParam(name = "year", targetNamespace = "")
        String year)
        throws CastToIntException, EmptyFieldException, RowIsNotExistsException
    ;

    /**
     * 
     * @return
     *     returns java.util.List<com.foreach.soap.ws.client.generated.Playlist>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAll", targetNamespace = "http://example.org/", className = "com.foreach.soap.ws.client.generated.GetAll")
    @ResponseWrapper(localName = "getAllResponse", targetNamespace = "http://example.org/", className = "com.foreach.soap.ws.client.generated.GetAllResponse")
    @Action(input = "http://example.org/PlaylistService/getAllRequest", output = "http://example.org/PlaylistService/getAllResponse")
    public List<Playlist> getAll();

}
