/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.publicREST;

import datamodel.Kurzus;
import datamodel.KurzusTartalmak;
import datamodel.UserRoles;
import datamodel.Users;
import io.swagger.annotations.Api;
import static java.lang.Integer.parseInt;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.def.UsersRolesService;
import services.def.UsersService;

/**
 *
 * @author hallgato
 */
@Path("/public/users")
@Api(value = "Users REST")
public class UsersREST {
   private static final Logger log = LoggerFactory.getLogger(UsersREST.class.getSimpleName());

   public boolean isEncoded(String text){

    Charset charset = Charset.forName("US-ASCII");
    String checked=new String(text.getBytes(charset),charset);
    return !checked.equals(text);

    }
   
    @Inject
    UsersService service;
    
    @Inject
    UsersRolesService uservice;
    
    @POST
    @Path("/entity/register") 
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public HashMap<String, String> Register(MultivaluedMap<String, String> formParams){
        int success = 0;
        String result = "Kérés végrehajtása sikertelen!";
        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        try {
                String username="";
                String password="";
                if (!formParams.containsKey("j_username") || !formParams.containsKey("j_password")) {
                    result = "Néhány mező üres!";
                } else {
                    username = policy.sanitize(formParams.getFirst("j_username"));
                    password = formParams.getFirst("j_password");
                    if (isEncoded(username) || isEncoded(password)) {
                        result = "Nem használhatsz speciális karaktereket (blame java)!";
                    }
                    else if (username.length()<1 || password.length()<1) {
                        result = "Egy mező sem maradhat üresen!";
                    }
                    else {
                       try {
                       Users existing = service.getByUsername(username);
                       result = "Már van ilyen felhasználó!";
                       }
                       catch (Exception k) {
                            MessageDigest md = MessageDigest.getInstance("SHA-256");
                            md.update(password.getBytes("UTF-8"));
                            String hash = Base64.getEncoder().encodeToString(md.digest());
                            Users user = new Users(username,hash);
                            service.save(user);
                            user = service.getByUsername(username);
                            int id = user.getUserId();
                            UserRoles urole = new UserRoles(id,"USER");
                            uservice.save(urole);
                            result = "Regisztráció sikeres! Felhasználód: "+username+", jelszód: "+password;
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
    
    public UsersREST() {
        log.info("CREATE Instance");
    }

}
