/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.imp;

import datamodel.Kurzus;
import datamodel.KurzusJelentkezok;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import services.GenericDaoServiceImpl;
import services.def.KurzusjelentkezokService;


@RequestScoped
public class KurzusjelentkezokServiceImpl implements KurzusjelentkezokService {
    @Inject
    GenericDaoServiceImpl dao;
    
    @Override
    public List<KurzusJelentkezok> getAll() {
        return (List<KurzusJelentkezok>)dao.getEntities("KurzusJelentkezok.findAll", new HashMap<String, Object>());
    }
    
    @Override
    public KurzusJelentkezok getById(Integer kID, Integer uID) {
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("kurzusId", kID);
        params.put("userId", uID);
        return (KurzusJelentkezok)dao.getEntity("KurzusJelentkezok.findByUserAndKurzusId", params);
    }
    
    @Override
    public void save(KurzusJelentkezok pEntity) {
        dao.save(pEntity);
    }
    
    @Override
    public void delete(Integer kId, Integer uId) {
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("kurzusId", kId);
        params.put("userId", uId);
        KurzusJelentkezok k = (KurzusJelentkezok)dao.getEntity("KurzusJelentkezok.findByUserAndKurzusId", params);
        k.setUsers(null);
        k.setKurzus(null);
        dao.delete(k);
    }
}
