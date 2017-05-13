/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.adminREST;

import datamodel.Users;
import io.swagger.annotations.Api;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.def.UsersService;

/**
 *
 * @author hallgato
 */
@Path("/admin/users")
@Api(value = "Users Admin REST")
public class UsersAdminREST {
   private static final Logger log = LoggerFactory.getLogger(UsersAdminREST.class.getSimpleName());

    @Inject
    UsersService service;
    
    @GET
    @Path("/entity/all") 
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Users> entityList(){
        return service.getAll();
    }
    
    public UsersAdminREST() {
        log.info("CREATE Instance");
    }

}
