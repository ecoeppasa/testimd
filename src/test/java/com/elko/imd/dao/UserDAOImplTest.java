/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elko.imd.dao;

import com.elko.imd.ImdApplicationTests;
import com.elko.imd.model.User;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author elko
 */
public class UserDAOImplTest extends ImdApplicationTests{
    
    public UserDAOImplTest() {
    }
    
    
    
    @Transactional
    @Test
    public void testIsFriend() {
      /*  System.out.println("IsFriend");
        UserDAOImpl instance = new UserDAOImpl();
        List<User> expResult = null;
        List<User> result = instance.IsFriend();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");*/
      
    }
    
}
