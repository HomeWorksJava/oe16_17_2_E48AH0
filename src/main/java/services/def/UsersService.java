/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.def;

import datamodel.Users;
import java.util.List;

/**
 *
 * @author hallgato
 */
public interface UsersService {
    public List<Users> getAll();
    public Users getById(Integer pID);
    public Users getByUsername(String username);
    public void save(Users pEntity);
}
