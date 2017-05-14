/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.imp;

import datamodel.KurzusTartalmak;
import java.util.HashMap;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import services.GenericDaoServiceImpl;
import services.def.KurzusTartalmakService;


@RequestScoped
public class KurzusTartalmakServiceImpl implements KurzusTartalmakService {
    @Inject
    GenericDaoServiceImpl dao;
    
    @Override
    public List<KurzusTartalmak> getAll() {
        return (List<KurzusTartalmak>)dao.getEntities("KurzusTartalmak.findAll", new HashMap<String, Object>());
    }
    
    @Override
    public KurzusTartalmak getById(Integer tID) {
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("tartalomId", tID);
        return (KurzusTartalmak)dao.getEntity("KurzusTartalmak.findByTartalomId", params);
    }
    
    @Override
    public void save(KurzusTartalmak pEntity) {
        dao.save(pEntity);
    }
    
    @Override
    public void delete(Integer tId) {
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("tartalomId", tId);
        KurzusTartalmak k = (KurzusTartalmak)dao.getEntity("KurzusTartalmak.findByTartalomId", params);
        k.setKurzusId(null);
        dao.delete(k);
    }
}
