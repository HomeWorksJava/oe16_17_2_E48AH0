/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.imp;

import datamodel.Users;
import datamodel.Users;
import java.util.HashMap;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import services.GenericDaoServiceImpl;
import services.def.UsersService;


@RequestScoped
public class UsersServiceImpl implements UsersService {
    @Inject
    GenericDaoServiceImpl dao;
    
    @Override
    public List<Users> getAll() {
        return (List<Users>)dao.getEntities("Users.findAll", new HashMap<String, Object>());
    }
    
    @Override
    public Users getById(Integer pID) {
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("userId", pID);
        return (Users)dao.getEntity("Users.findByUserId", params);
    }
    
    @Override
    public Users getByUsername(String username) {
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("username", username);
        return (Users)dao.getEntity("Users.findByUsername", params);
    }
    
    @Override
    public void save(Users pEntity) {
        dao.save(pEntity);
    }
}