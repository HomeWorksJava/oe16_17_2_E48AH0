/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;


import io.swagger.jaxrs.config.BeanConfig;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

@ApplicationPath("rest")
public class RESTApplication extends Application{

    public RESTApplication() {
        super();
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("2.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080/Kurzuskezelo-1.0");
        beanConfig.setBasePath("/rest");
        beanConfig.setResourcePackage("resources");      
        beanConfig.setScan(true);
        beanConfig.setPrettyPrint(true);
        
    }
     @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet();
        resources.add(resources.userREST.KurzusREST.class);
        resources.add(resources.publicREST.UsersREST.class);
        resources.add(resources.userREST.KurzusjelentkezokREST.class);
        resources.add(resources.adminREST.KurzusAdminREST.class);
        resources.add(resources.adminREST.UsersAdminREST.class);
        resources.add(resources.adminREST.KurzusTartalmakAdminREST.class);
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        resources.add(org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature.class);
        return resources;
    }
    
}