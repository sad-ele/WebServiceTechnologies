
package com.foreach.soap.ws.client.generated;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
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
     * @param rowId
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "updatePlaylist", targetNamespace = "http://example.org/", className = "com.foreach.soap.ws.client.generated.UpdatePlaylist")
    @ResponseWrapper(localName = "updatePlaylistResponse", targetNamespace = "http://example.org/", className = "com.foreach.soap.ws.client.generated.UpdatePlaylistResponse")
    @Action(input = "http://example.org/PlaylistService/updatePlaylistRequest", output = "http://example.org/PlaylistService/updatePlaylistResponse")
    public String updatePlaylist(
        @WebParam(name = "rowId", targetNamespace = "")
        int rowId,
        @WebParam(name = "name", targetNamespace = "")
        String name,
        @WebParam(name = "band", targetNamespace = "")
        String band,
        @WebParam(name = "genre", targetNamespace = "")
        String genre,
        @WebParam(name = "time", targetNamespace = "")
        String time,
        @WebParam(name = "year", targetNamespace = "")
        int year);

    /**
     * 
     * @param rowId
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deletePlaylist", targetNamespace = "http://example.org/", className = "com.foreach.soap.ws.client.generated.DeletePlaylist")
    @ResponseWrapper(localName = "deletePlaylistResponse", targetNamespace = "http://example.org/", className = "com.foreach.soap.ws.client.generated.DeletePlaylistResponse")
    @Action(input = "http://example.org/PlaylistService/deletePlaylistRequest", output = "http://example.org/PlaylistService/deletePlaylistResponse")
    public String deletePlaylist(
        @WebParam(name = "rowId", targetNamespace = "")
        int rowId);

    /**
     * 
     * @param year
     * @param name
     * @param genre
     * @param band
     * @param time
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createPlaylist", targetNamespace = "http://example.org/", className = "com.foreach.soap.ws.client.generated.CreatePlaylist")
    @ResponseWrapper(localName = "createPlaylistResponse", targetNamespace = "http://example.org/", className = "com.foreach.soap.ws.client.generated.CreatePlaylistResponse")
    @Action(input = "http://example.org/PlaylistService/createPlaylistRequest", output = "http://example.org/PlaylistService/createPlaylistResponse")
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
        int year);

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
