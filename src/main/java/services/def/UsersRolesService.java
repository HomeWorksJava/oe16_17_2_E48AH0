/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.def;

import datamodel.UserRoles;
import java.util.List;

/**
 *
 * @author hallgato
 */
public interface UsersRolesService {
    public List<UserRoles> getAll();
    public UserRoles getByUserId(Integer uID);
    public void save(UserRoles pEntity);
}
