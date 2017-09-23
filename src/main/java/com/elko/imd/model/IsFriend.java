/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elko.imd.model;

import java.util.List;

/**
 *
 * @author elko
 */
public class IsFriend {
    private List<String> friends;

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    

    public boolean isEmpty(){
    return friends == null;
    }  
    
    
     
}
