/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elko.imd.dao;

import com.elko.imd.ImdApplicationTests;
import com.elko.imd.configuration.AppConfig;
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
        System.out.println("IsFriend");
        User u = new User();
        User n = new User();
        n.setEmail("andy@example.com");        
        u.setEmail("john@example.com");
        System.out.println("nr");
        List<User> expResult = new ArrayList<>();
        expResult.add(n);
        expResult.add(u);
        List<User> result = userDAO.IsFriend("john@example.com", "andy@example.com");
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
    
}
