package br.poc.agenda.backend;

/**
 * Created by 36736636825 on 15/06/2016.
 */

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

import static br.poc.agenda.backend.OfyService.ofy;

@Api(name = "loginEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend.agenda.poc.br", ownerName = "backend.agenda.poc.br", packagePath = ""))
public class LoginEndpoint {

    // Make sure to add this endpoint to your web.xml file if this is a web application.

    private static final Logger LOG = Logger.getLogger(LoginEndpoint.class.getName());

    public LoginEndpoint() {
    }

    /**
     * Return a collection of quotes
     *
     * @param count The number of quotes
     * @return a list of Quotes
     */
    @ApiMethod(name = "listLogin")
    public CollectionResponse<Login> listLogin(@Nullable @Named("cursor") String cursorString, @Nullable @Named("count") Integer count) {

        Query<Login> query = ofy().load().type(Login.class);
        if (count != null) query.limit(count);
        if (cursorString != null && cursorString != "") {
            query = query.startAt(Cursor.fromWebSafeString(cursorString));
        }

        List<Login> records = new ArrayList<Login>();
        QueryResultIterator<Login> iterator = query.iterator();
        int num = 0;
        while (iterator.hasNext()) {
            records.add(iterator.next());
            if (count != null) {
                num++;
                if (num == count) break;
            }
        }

        //Find the next cursor
        if (cursorString != null && cursorString != "") {
            Cursor cursor = iterator.getCursor();
            if (cursor != null) {
                cursorString = cursor.toWebSafeString();
            }
        }
        return CollectionResponse.<Login>builder().setItems(records).setNextPageToken(cursorString).build();
    }

    /*   @ApiMethod( name = "usuario")
       public List<Login> usuarioGet(@Named("email") String email, @Named("senha") String senha){
           List<Login> usuario = new ArrayList<>();
           List<Login> usr = ofy().load().type(Login.class).list();
           if (usr == null){
               usuario = null;
           }*//*else if(!usr.getSenha().equals(senha)){
            usuario = null;
        }*//*else {
            usuario = usr;
        }
        return usuario;
    }
*/
    @ApiMethod(name = "loginSenha", httpMethod = ApiMethod.HttpMethod.GET)
    public Login usuarioSenha(@Named("email") String name, @Named("senha") String password) {
        Login auth = new Login();
        LOG.info("Getting usuarioSenha with ID: " + name);
        Login user = ofy().load().type(Login.class).id(name).now();
        LOG.info("Getting usuarioSenha with ID: " + user);
        if (user == null) {
            auth = null;
        } else if (!user.senha.equals(password)) {
            LOG.info("Getting usuarioSenha with ID: " + user + password);
            auth = null;
        } else {
            auth = user;
        }
        return auth;
    }

    /**
     * This inserts a new <code>Compromisso</code> object.
     *
     * @param login The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertLogin")
    public Login insertLogin(Login login) throws ConflictException {
        //If if is not null, then check if it exists. If yes, throw an Exception
        //that it is already present
        if (login.getId() != null) {
            if (findRecord(login.getId()) != null) {
                throw new ConflictException("Object already exists");
            }
        }
        //Since our @Id field is a Long, Objectify will generate a unique value for us
        //when we use put
        ofy().save().entity(login).now();
        return login;
    }

    /**
     * This updates an existing <code>Quote</code> object.
     *
     * @param login The object to be added.
     * @return The object to be updated.
     */
    @ApiMethod(name = "updateLogin")
    public Login updateLogin(Login login) throws NotFoundException {
        if (findRecord(login.getId()) == null) {
            throw new NotFoundException("Compromisso Record does not exist");
        }
        ofy().save().entity(login).now();
        return login;
    }

    /**
     * This deletes an existing <code>Compromisso</code> object.
     *
     * @param id The id of the object to be deleted.
     */
    @ApiMethod(name = "removeLogin")
    public void removeLogin(@Named("id") Long id) throws NotFoundException {
        Login record = findRecord(id);
        if (record == null) {
            throw new NotFoundException("Compromisso Record does not exist");
        }
        ofy().delete().entity(record).now();
    }

    //Private method to retrieve a <code>Compromisso</code> record
    private Login findRecord(Long id) {
        return ofy().load().type(Login.class).id(id).now();
        //or return ofy().load().type(Compromisso.class).filter("id",id).first.now();
    }

}

