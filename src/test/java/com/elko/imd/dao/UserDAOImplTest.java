/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elko.imd.dao;

import com.elko.imd.ImdApplicationTests;
import com.elko.imd.configuration.AppConfig;
import com.elko.imd.model.Friendship;
import com.elko.imd.model.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author elko
 */

@TransactionConfiguration(transactionManager="txManager")
@Transactional
public class UserDAOImplTest extends ImdApplicationTests{
    
    public UserDAOImplTest() {
    }
    
    @Autowired
    UserDAO userDAO;
    
    @Test
    public void testIsFriend() {
        System.out.println("CreateFriends");
        Friendship fs = new Friendship();
        fs.setUserOne("john@example.com");
        fs.setUserTwo("andy@example.com");
        fs.setStatus(2);
        fs.setActionBy(1);
        System.out.println("nr");
        boolean expResult = true;
        boolean result = userDAO.createFriend(fs);
        System.out.println("me");
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
      
    }

    @Test
    public void testGetFriend() {
    }

    @Test
    public void testSubscribe() {
    }

    @Test
    public void testGetSubscribe() {
    }

    @Test
    public void testPostNews() {
    }

    @Test
    public void testGetUnSubscribe() {
    }

    @Test
    public void testCreateFriend() {
    }
    
}
