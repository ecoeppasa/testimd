/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elko.imd.model;

import java.util.List;

/**
 * Class IsFriend is definition for object array list of friends.
 * this class will get and set list of friend, this class is temporary object for definition of object list
 * 
 * 
 * @author elko
 * @since 2017-09-25
 */
public class IsFriend {
    private List<String> friends;

    /**
     * getFriends is function for get array list of friends.
     * @return list of friends
     */
    public List<String> getFriends() {
        return friends;
    }

    /**
     * setFriends is method for set array list of friends
     * @param friends is list of friends 
     */
    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    /**
     * isEmpty is function for checking is list in this objects are null  
     * @return if list empty will return true
     */
    public boolean isEmpty(){
    return friends == null;
    }  
    
    
     
}
