/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.adminREST;

import resources.userREST.KurzusREST;
import datamodel.Kurzus;
import datamodel.KurzusJelentkezok;
import datamodel.Users;
import io.swagger.annotations.Api;
import static java.lang.Integer.parseInt;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import services.def.KurzusService;
import services.def.KurzusjelentkezokService;
import services.def.UsersService;

/**
 *
 * @author hallgato
 */
@Path("/admin/kurzus")
@Api(value = "Kurzus Admin REST")
public class KurzusAdminREST {
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
    @Path("/entity/one") 
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Kurzus getEntity(@QueryParam("kurzus_id") Integer kurzus_id){
        try {
            Kurzus kurzus = service.getById(kurzus_id);
            Set<KurzusJelentkezok> collection = kurzus.getKurzusJelentkezokCollection();
            for (KurzusJelentkezok k : collection) {
                k.setUsername(uservice.getById(k.getKurzusJelentkezokPK().getUserId()).getUsername());
            }
            return kurzus;
        }
        catch (Exception x) {
            log.debug("MIVAN"+x.getMessage());
        }
        return null;
    }
    
    @POST
    @Path("/entity/kirug")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public HashMap<String, String> Kirugas(MultivaluedMap<String, String> formParams) {
        int success = 0;
        String result = "Kérés végrehajtása sikertelen!";
        try {
                int kurzus_id=0;
                int user_id=0;
                if (!formParams.containsKey("kurzus_id") || !formParams.containsKey("user_id")) {
                    result = "Hibás kérés!";
                } else {
                    kurzus_id = parseInt(formParams.getFirst("kurzus_id"));
                    user_id = parseInt(formParams.getFirst("user_id"));
                    KurzusJelentkezok kj = kjservice.getById(kurzus_id, user_id);
                    if (kj!=null) {
                        kjservice.delete(kurzus_id, user_id);
                        result = "Kirúgás sikeres!";
                        success = 1;
                    } else {
                        result = "Nem található ez a felhasználó!";
                    }
                }
        }
        catch (Exception x) {
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
    public HashMap<String, String> Torles(MultivaluedMap<String, String> formParams) {
        int success = 0;
        String result = "Kérés végrehajtása sikertelen!";
        try {
                int kurzus_id=0;
                if (!formParams.containsKey("kurzus_id")) {
                    result = "Hibás kérés!";
                } else {
                    kurzus_id = parseInt(formParams.getFirst("kurzus_id"));
                    Kurzus k = service.getById(kurzus_id);
                    if (k!=null) {
                        Set<KurzusJelentkezok> collection = k.getKurzusJelentkezokCollection();
                        for (KurzusJelentkezok kj : collection) {
                            kjservice.delete(kurzus_id, kj.getKurzusJelentkezokPK().getUserId());
                        }
                        service.delete(kurzus_id);
                        result = "Kurzus törlése sikeres!";
                        success = 1;
                    } else {
                        result = "Kurzus nem található!";
                    }
                }
        }
        catch (Exception x) {
            result = "Hiba!";
        }
        HashMap<String, String> send = new HashMap<String, String>();
        send.put("success",""+success);
        send.put("result",result);
        return send;
    }
    
    @POST
    @Path("/entity/mentes")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public HashMap<String, String> Mentes(MultivaluedMap<String, String> formParams) {
        int success = 0;
        String result = "Kérés végrehajtása sikertelen!";
        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        try {
                int kurzus_id=-1;
                String nev="";
                String leiras="";
                int maxJelentkezok=-1;
                String indulasDatum="";
                int allapot=-1;
                
                int user_id=0;
                if (!formParams.containsKey("leiras") || !formParams.containsKey("nev") || !formParams.containsKey("maxJelentkezok") || !formParams.containsKey("indulasDatum") || !formParams.containsKey("allapot")) {
                    result = "Néhány mező üres!";
                } else {
                    indulasDatum = formParams.getFirst("indulasDatum");
                    nev = policy.sanitize(formParams.getFirst("nev"));
                    leiras = policy.sanitize(formParams.getFirst("leiras"));
                    maxJelentkezok = parseInt(formParams.getFirst("maxJelentkezok"));
                    allapot = parseInt(formParams.getFirst("allapot"));
                    Date indulas = new SimpleDateFormat("yyyy-MM-dd").parse(indulasDatum);
                    if (!(allapot>=0 && allapot<=2)) {
                        result = "Hibás állapot!";
                    } else if(!(maxJelentkezok>=0 && maxJelentkezok<=5000)) {
                        result = "A max jelentkezők számának 0 és 5000 közt kell lennie.";
                    }
                    // Csak módosítás
                    else if (formParams.containsKey("kurzus_id")) {
                        kurzus_id = parseInt(formParams.getFirst("kurzus_id"));
                        Kurzus k = service.getById(kurzus_id);
                        if (k!=null) {
                            if(k.getKurzusJelentkezokCollection().size()>maxJelentkezok) {
                                result = "Többen jelentkeztek a kurzusra mint a max jelentkezők száma! Rúgj ki néhány embert.";
                            }
                            else {
                                k.setNev(nev);
                                k.setLeiras(leiras);
                                k.setAllapot(allapot);
                                k.setIndulasDatum(indulas);
                                k.setMaxJelentkezok(maxJelentkezok);
                                service.save(k);
                                result = "Kurzus sikeresen módosítva";
                                success = 1;
                            }
                        }
                    }
                    // Létrehozás
                    else {
                        String username = sc.getUserPrincipal().getName();
                        Users currentuser = uservice.getByUsername(username);
                        Kurzus k = new Kurzus(nev, leiras, maxJelentkezok, indulas, new Date(), currentuser.getUserId(), allapot);
                        if (k!=null) {
                                service.save(k);
                                result = "Kurzus sikeresen létrehozva";
                                success = 1;
                        }
                    }
                }
        }
        catch (Exception x) {
            result = "Hiba! Adjon meg minden mezőt!";
        }
        HashMap<String, String> send = new HashMap<String, String>();
        send.put("success",""+success);
        send.put("result",result);
        return send;
    }
    
    
    
    
    
    public KurzusAdminREST() {
        log.info("CREATE Instance");
    }

}