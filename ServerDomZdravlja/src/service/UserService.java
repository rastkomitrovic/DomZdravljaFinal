/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Rastko
 */
public interface UserService {
    public List<User> getAll()throws SQLException;
    public boolean add(User user)throws SQLException;
}
