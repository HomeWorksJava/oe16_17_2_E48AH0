/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.userREST;

import datamodel.Kurzus;
import datamodel.KurzusJelentkezok;
import datamodel.Users;
import io.swagger.annotations.Api;
import static java.lang.Integer.parseInt;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.def.KurzusService;
import services.def.KurzusjelentkezokService;
import services.def.UsersService;

/**
 *
 * @author hallgato
 */
@Path("/user/kurzus")
@Api(value = "Kurzus REST")
public class KurzusREST {
   @Context
   protected SecurityContext sc;
   private static final Logger log = LoggerFactory.getLogger(KurzusREST.class.getSimpleName());

    @Inject
    KurzusService service;
    
    @Inject
    KurzusjelentkezokService kjservice;
    
    @Inject
    UsersService uservice;
    
    @GET
    @Path("/entity/all") 
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Kurzus> entityList(){
        return service.getAll();
    }
    

    
    @POST
    @Path("/entity/jelentkez")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public HashMap<String, String> Jelentkez(MultivaluedMap<String, String> formParams) {
        int success = 0;
        String result = "Kérés végrehajtása sikertelen!";
        try {
            String username = sc.getUserPrincipal().getName();
            Users currentuser = uservice.getByUsername(username);
            if (currentuser!=null) {
                int kurzus_id=0;
                if (!formParams.containsKey("kurzus_id")) {
                    result = "Hibás kérés!";
                } else {
                    kurzus_id = parseInt(formParams.getFirst("kurzus_id"));
                    Kurzus kurzus = service.getById(kurzus_id);
                    if (kurzus!=null) {
                        if (kurzus.getKurzusJelentkezokCollection().size()>=kurzus.getMaxJelentkezok()) {
                           result = "Ez a kurzus már betelt."; 
                        } else if (kurzus.isRegistered(currentuser.getUserId())) {
                            result = "Már jelentkezve vagy erre a kurzusra!";
                        } else if (kurzus.getAllapot()>0) {
                            result = "Erre a kurzusra már nem lehet jelentkezni!";
                        } else if (kurzus.getLetrehozoUserId()==currentuser.getUserId()) {
                            result = "A saját kurzusodra nem jelentkezhetsz!";
                        } else {
                            Date d = new Date();
                            KurzusJelentkezok pEntity = new KurzusJelentkezok(kurzus.getKurzusId(),currentuser.getUserId(),d);
                            kjservice.save(pEntity);
                            result = "Jelentkezés sikeres!";
                            success = 1;
                        }
                    } else {
                        result = "Nem található ez a kurzus!";
                    }
                }
            } else {
                result = "Nem vagy bejelentkezve!";
            }
        }
        catch (Exception x) {
            log.debug(x.getMessage());
            result = "Hiba!";
        }
        HashMap<String, String> send = new HashMap<String, String>();
        send.put("success",""+success);
        send.put("result",result);
        return send;
    }
    
    @POST
    @Path("/entity/lejelentkez")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public HashMap<String, String> Lejelentkez(MultivaluedMap<String, String> formParams) {
        int success = 0;
        String result = "Kérés végrehajtása sikertelen!";
        try {
            String username = sc.getUserPrincipal().getName();
            Users currentuser = uservice.getByUsername(username);
            if (currentuser!=null) {
                int kurzus_id=0;
                if (!formParams.containsKey("kurzus_id")) {
                    result = "Hibás kérés!";
                } else {
                    kurzus_id = parseInt(formParams.getFirst("kurzus_id"));
                    Kurzus kurzus = service.getById(kurzus_id);
                    if (kurzus!=null) {
                        if (!kurzus.isRegistered(currentuser.getUserId())) {
                            result = "Nem vagy erre a kurzusra jelentkezve!";
                        } else if (kurzus.getAllapot()>0) {
                            result = "Ez a kurzus már elindult! Kérd az admint, hogy kirúgjun.";
                        } else {
                            kjservice.delete(kurzus_id, currentuser.getUserId());
                            result = "Lejelentkezés sikeres!";
                            success = 1;
                        }
                    } else {
                        result = "Nem található ez a kurzus!";
                    }
                }
            } else {
                result = "Nem vagy bejelentkezve!";
            }
        }
        catch (Exception x) {
            log.debug(x.getMessage());
            result = "Hiba!";
        }
        HashMap<String, String> send = new HashMap<String, String>();
        send.put("success",""+success);
        send.put("result",result);
        return send;
    }
    
    public KurzusREST() {
        log.info("CREATE Instance");
    }

}
