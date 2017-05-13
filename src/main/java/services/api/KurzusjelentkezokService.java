/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.api;

import datamodel.KurzusJelentkezok;
import java.util.List;

/**
 *
 * @author hallgato
 */
public interface KurzusjelentkezokService {
    public List<KurzusJelentkezok> getAll();
    public KurzusJelentkezok getById(Integer kID, Integer uID);
    public void save(KurzusJelentkezok pEntity);
    public void delete(Integer kId, Integer uId);
}

