/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.def;

import datamodel.KurzusTartalmak;
import java.util.List;

/**
 *
 * @author hallgato
 */
public interface KurzusTartalmakService {
    public List<KurzusTartalmak> getAll();
    public KurzusTartalmak getById(Integer tID);
    public void save(KurzusTartalmak pEntity);
    public void delete(Integer tId);
}
