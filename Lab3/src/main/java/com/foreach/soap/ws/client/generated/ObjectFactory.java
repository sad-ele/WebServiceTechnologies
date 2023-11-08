
package com.foreach.soap.ws.client.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.foreach.soap.ws.client.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAll_QNAME = new QName("http://example.org/", "getAll");
    private final static QName _CastToIntException_QNAME = new QName("http://example.org/", "CastToIntException");
    private final static QName _UpdatePlaylistResponse_QNAME = new QName("http://example.org/", "updatePlaylistResponse");
    private final static QName _GetAllResponse_QNAME = new QName("http://example.org/", "getAllResponse");
    private final static QName _RowIsNotExistsException_QNAME = new QName("http://example.org/", "RowIsNotExistsException");
    private final static QName _CreatePlaylistResponse_QNAME = new QName("http://example.org/", "createPlaylistResponse");
    private final static QName _DeletePlaylist_QNAME = new QName("http://example.org/", "deletePlaylist");
    private final static QName _UpdatePlaylist_QNAME = new QName("http://example.org/", "updatePlaylist");
    private final static QName _DeletePlaylistResponse_QNAME = new QName("http://example.org/", "deletePlaylistResponse");
    private final static QName _EmptyFieldException_QNAME = new QName("http://example.org/", "EmptyFieldException");
    private final static QName _CreatePlaylist_QNAME = new QName("http://example.org/", "createPlaylist");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.foreach.soap.ws.client.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CastToIntFault }
     * 
     */
    public CastToIntFault createCastToIntFault() {
        return new CastToIntFault();
    }

    /**
     * Create an instance of {@link UpdatePlaylistResponse }
     * 
     */
    public UpdatePlaylistResponse createUpdatePlaylistResponse() {
        return new UpdatePlaylistResponse();
    }

    /**
     * Create an instance of {@link GetAll }
     * 
     */
    public GetAll createGetAll() {
        return new GetAll();
    }

    /**
     * Create an instance of {@link RowIsNotExistsFault }
     * 
     */
    public RowIsNotExistsFault createRowIsNotExistsFault() {
        return new RowIsNotExistsFault();
    }

    /**
     * Create an instance of {@link CreatePlaylistResponse }
     * 
     */
    public CreatePlaylistResponse createCreatePlaylistResponse() {
        return new CreatePlaylistResponse();
    }

    /**
     * Create an instance of {@link DeletePlaylist }
     * 
     */
    public DeletePlaylist createDeletePlaylist() {
        return new DeletePlaylist();
    }

    /**
     * Create an instance of {@link GetAllResponse }
     * 
     */
    public GetAllResponse createGetAllResponse() {
        return new GetAllResponse();
    }

    /**
     * Create an instance of {@link DeletePlaylistResponse }
     * 
     */
    public DeletePlaylistResponse createDeletePlaylistResponse() {
        return new DeletePlaylistResponse();
    }

    /**
     * Create an instance of {@link EmptyFieldFault }
     * 
     */
    public EmptyFieldFault createEmptyFieldFault() {
        return new EmptyFieldFault();
    }

    /**
     * Create an instance of {@link CreatePlaylist }
     * 
     */
    public CreatePlaylist createCreatePlaylist() {
        return new CreatePlaylist();
    }

    /**
     * Create an instance of {@link UpdatePlaylist }
     * 
     */
    public UpdatePlaylist createUpdatePlaylist() {
        return new UpdatePlaylist();
    }

    /**
     * Create an instance of {@link Playlist }
     * 
     */
    public Playlist createPlaylist() {
        return new Playlist();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "getAll")
    public JAXBElement<GetAll> createGetAll(GetAll value) {
        return new JAXBElement<GetAll>(_GetAll_QNAME, GetAll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CastToIntFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "CastToIntException")
    public JAXBElement<CastToIntFault> createCastToIntException(CastToIntFault value) {
        return new JAXBElement<CastToIntFault>(_CastToIntException_QNAME, CastToIntFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePlaylistResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "updatePlaylistResponse")
    public JAXBElement<UpdatePlaylistResponse> createUpdatePlaylistResponse(UpdatePlaylistResponse value) {
        return new JAXBElement<UpdatePlaylistResponse>(_UpdatePlaylistResponse_QNAME, UpdatePlaylistResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "getAllResponse")
    public JAXBElement<GetAllResponse> createGetAllResponse(GetAllResponse value) {
        return new JAXBElement<GetAllResponse>(_GetAllResponse_QNAME, GetAllResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RowIsNotExistsFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "RowIsNotExistsException")
    public JAXBElement<RowIsNotExistsFault> createRowIsNotExistsException(RowIsNotExistsFault value) {
        return new JAXBElement<RowIsNotExistsFault>(_RowIsNotExistsException_QNAME, RowIsNotExistsFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePlaylistResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "createPlaylistResponse")
    public JAXBElement<CreatePlaylistResponse> createCreatePlaylistResponse(CreatePlaylistResponse value) {
        return new JAXBElement<CreatePlaylistResponse>(_CreatePlaylistResponse_QNAME, CreatePlaylistResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePlaylist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "deletePlaylist")
    public JAXBElement<DeletePlaylist> createDeletePlaylist(DeletePlaylist value) {
        return new JAXBElement<DeletePlaylist>(_DeletePlaylist_QNAME, DeletePlaylist.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePlaylist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "updatePlaylist")
    public JAXBElement<UpdatePlaylist> createUpdatePlaylist(UpdatePlaylist value) {
        return new JAXBElement<UpdatePlaylist>(_UpdatePlaylist_QNAME, UpdatePlaylist.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePlaylistResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "deletePlaylistResponse")
    public JAXBElement<DeletePlaylistResponse> createDeletePlaylistResponse(DeletePlaylistResponse value) {
        return new JAXBElement<DeletePlaylistResponse>(_DeletePlaylistResponse_QNAME, DeletePlaylistResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyFieldFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "EmptyFieldException")
    public JAXBElement<EmptyFieldFault> createEmptyFieldException(EmptyFieldFault value) {
        return new JAXBElement<EmptyFieldFault>(_EmptyFieldException_QNAME, EmptyFieldFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePlaylist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.org/", name = "createPlaylist")
    public JAXBElement<CreatePlaylist> createCreatePlaylist(CreatePlaylist value) {
        return new JAXBElement<CreatePlaylist>(_CreatePlaylist_QNAME, CreatePlaylist.class, null, value);
    }

}
