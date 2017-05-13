/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.api;

import datamodel.Kurzus;
import java.util.List;

/**
 *
 * @author hallgato
 */
public interface KurzusService {
    public List<Kurzus> getAll();
    public Kurzus getById(Integer pID);
    public void save(Kurzus pEntity);
    public void delete(Integer pID);
}
