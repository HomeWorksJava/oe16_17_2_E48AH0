/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.adminREST;

import datamodel.Kurzus;
import datamodel.KurzusJelentkezok;
import datamodel.KurzusTartalmak;
import datamodel.Users;
import io.swagger.annotations.Api;
import static java.lang.Integer.parseInt;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resources.userREST.KurzusREST;
import services.def.KurzusService;
import services.def.KurzusTartalmakService;
import services.def.KurzusjelentkezokService;
import services.def.UsersService;

/**
 *
 * @author hallgato
 */
@Path("/admin/kurzustartalom")
@Api(value = "Kurzus Tartalmak Admin REST")
public class KurzusTartalmakAdminREST {
   @Context
   protected SecurityContext sc;
   private static final Logger log = LoggerFactory.getLogger(KurzusTartalmakAdminREST.class.getSimpleName());

    @Inject
    KurzusService service;
    
    @Inject
    KurzusjelentkezokService kjservice;
    
    @Inject
    UsersService uservice;
    
    @Inject
    KurzusTartalmakService tservice;
    
    @GET
    @Path("/entity/one")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public KurzusTartalmak getEntity(@QueryParam("tartalom_id") Integer tartalom_id){
        try {
            KurzusTartalmak tartalom = tservice.getById(tartalom_id);
            return tartalom;
        }
        catch (Exception x) {
            log.debug("Hiba!");
        }
        return null;
    }
    
    @POST
    @Path("/entity/mentes")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public HashMap<String, String> Mentes(MultivaluedMap<String, String> formParams){
        int success = 0;
        String result = "Kérés végrehajtása sikertelen!";
        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        try {
                String cim="";
                String tartalom="";
                if (!formParams.containsKey("cim") || !formParams.containsKey("tartalom")) {
                    result = "Néhány mező üres!";
                } else {
                    cim = policy.sanitize(formParams.getFirst("cim"));
                    tartalom = policy.sanitize(formParams.getFirst("tartalom"));
                    // Csak mentés
                    if (cim.length()<1) {
                        result = "A cím nem maradhat üresen!";
                    }
                    else if (formParams.containsKey("tartalom_id")) {
                        int tartalom_id = parseInt(formParams.getFirst("tartalom_id"));
                        KurzusTartalmak kt = tservice.getById(tartalom_id);
                        if (kt!=null) {
                            kt.setCim(cim);
                            kt.setTartalom(tartalom);
                            tservice.save(kt);
                            result = "Tartalom módosítva";
                            success = 1;
                        }
                    }
                    // Létrehozás
                    else if (formParams.containsKey("kurzus_id")) {
                        int kurzus_id = parseInt(formParams.getFirst("kurzus_id"));
                        Kurzus kurzus = service.getById(kurzus_id);
                        if (kurzus!=null) {
                            KurzusTartalmak kt = new KurzusTartalmak();
                            kt.setCim(cim);
                            kt.setTartalom(tartalom);
                            kt.setLetrehozva(new Date());
                            kt.setKurzus_Id(kurzus_id);
                            kurzus.getKurzusTartalmakCollection().add(kt);
                            service.save(kurzus);
                            tservice.save(kt);
                            result = "Tartalom létrehozva!";
                            success = 1;
                        }
                    }
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
    @Path("/entity/delete")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public HashMap<String, String> Delete(MultivaluedMap<String, String> formParams){
        int success = 0;
        String result = "Kérés végrehajtása sikertelen!";
        try {
                int tartalom_id=-1;
                if (!formParams.containsKey("tartalom_id")) {
                    result = "Nincs kiválasztva tartalom!";
                } else {
                        tartalom_id = parseInt(formParams.getFirst("tartalom_id"));
                        tservice.delete(tartalom_id);
                        result = "Tartalom törölve";
                        success = 1;
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
    
    public KurzusTartalmakAdminREST() {
        log.info("CREATE Instance");
    }

}
