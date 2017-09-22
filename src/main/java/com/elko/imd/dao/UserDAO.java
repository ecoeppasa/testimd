/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elko.imd.dao;

import com.elko.imd.model.Friendship;
import com.elko.imd.model.GetFriend;
import com.elko.imd.model.User;
import java.util.List;

/**
 *
 * @author elko
 */
public interface UserDAO {
    public List<User> IsFriend(String userOne,String userTwo) ;
    public List<Friendship> getFriend(String user) ;
    public List<Friendship> getCommonFriend(String user) ;
}
