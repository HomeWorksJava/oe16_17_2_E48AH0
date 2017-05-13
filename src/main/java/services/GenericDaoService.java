/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

public interface GenericDaoService {
    
    public EntityManager getEM();
    public void save(Object pSort);
 
    public List getEntities(String pQuery, Map<String,Object> pParams);

    public Object getEntity(String pQuery, Map<String,Object> pParams);
    
}
