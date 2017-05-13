/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.api;

import datamodel.Kurzus;
import datamodel.KurzusJelentkezok;
import java.util.HashMap;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import services.GenericDaoServiceImpl;

@RequestScoped
public class KurzusServiceImpl implements KurzusService {
    @Inject
    GenericDaoServiceImpl dao;
    
    @Override
    public List<Kurzus> getAll() {
        return (List<Kurzus>)dao.getEntities("Kurzus.findAll", new HashMap<String, Object>());
    }
    
    @Override
    public Kurzus getById(Integer pID) {
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("kurzusId", pID);
        return (Kurzus)dao.getEntity("Kurzus.findByKurzusId", params);
    }
    
    @Override
    public void delete(Integer pID) {
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("kurzusId", pID);
        Kurzus k = (Kurzus)dao.getEntity("Kurzus.findByKurzusId", params);
        k.setKurzusJelentkezokCollection(null);
        dao.delete(k);
    }
    
    @Override
    public void save(Kurzus pEntity) {
        dao.save(pEntity);
    }
}
