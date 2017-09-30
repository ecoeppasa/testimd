/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elko.imd.model;

import java.util.List;

/**
 * This is class model for List of friend objects.
 * In this class will be define message of List of friends and common friend between two or more users.
 * The output will be
 * <pre>
 * <code>{
      "success": true,
      "friends" :
                [
                'john@example.com'
                ],
      "count" : 1
        }
 * 
 * </code>
 * </pre>
 * 
 * @author elko
 * @since 2017-09-25
 * 
 */
public class GetFriend { 
    
    private boolean success;
    private List<String> friends;
    private int count;

    /**
     * isSucces is function for get value of success variable
     * @return if has a data then return true, else return false
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * method for set success variable
     * @param success  is a status if has a data then set true
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * GetFriends function for get array list of friends. 
     * 
     * @return array list of friends
     */
    public List<String> getFriends() {
        return friends;
    }

    /**
     * setFriend is method for set the list of friends
     * @param friends is list of friends
     */
    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    /**
     * function for get count variable (size of friend list)
     * @return size of friends list
     */
    public int getCount() {
        return count;
    }

    /**
     * is method for set count variable (size of friends list)
     * @param count is size of friend list
     */
    public void setCount(int count) {
        this.count = count;
    }

   
    
    
    
    
}
