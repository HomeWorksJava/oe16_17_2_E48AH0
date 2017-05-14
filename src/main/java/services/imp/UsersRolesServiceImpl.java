/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.imp;

import datamodel.UserRoles;
import datamodel.Users;
import java.util.HashMap;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import services.GenericDaoServiceImpl;
import services.def.UsersRolesService;

@RequestScoped
public class UsersRolesServiceImpl implements UsersRolesService {
    @Inject
    GenericDaoServiceImpl dao;
    
    @Override
    public List<UserRoles> getAll() {
        return (List<UserRoles>)dao.getEntities("UserRoles.findAll", new HashMap<String, Object>());
    }

    @Override
    public UserRoles getByUserId(Integer uID) {
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("userId", uID);
        return (UserRoles)dao.getEntity("UserRoles.findByUserId", params);
    }

    @Override
    public void save(UserRoles pEntity) {
        dao.save(pEntity);
    }
    
}
