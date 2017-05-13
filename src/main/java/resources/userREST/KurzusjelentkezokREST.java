/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.userREST;

import datamodel.Kurzus;
import datamodel.KurzusJelentkezok;
import io.swagger.annotations.Api;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.def.KurzusjelentkezokService;

/**
 *
 * @author hallgato
 */
@Path("/user/kurzusjelentkezok")
@Api(value = "Kurzusjelentkezok REST")
public class KurzusjelentkezokREST {
   private static final Logger log = LoggerFactory.getLogger(KurzusjelentkezokREST.class.getSimpleName());

    @Inject
    KurzusjelentkezokService service;
    
    @GET
    @Path("/entity/all") 
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<KurzusJelentkezok> entityList(){
        return service.getAll();
    }
    
    public KurzusjelentkezokREST() {
        log.info("CREATE Instance");
    }

}
