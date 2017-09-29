/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elko.imd.controller;

import com.elko.imd.ImdApplicationTests;
import java.io.UnsupportedEncodingException;
import javax.servlet.Filter;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author elko
 */
@WebAppConfiguration
public class UserControllerTest extends ImdApplicationTests{
    
    public UserControllerTest() {
    }
    
   
    @Autowired
    private WebApplicationContext wac;
        
    private MockMvc mockMvc;
 
    private String credetntial;
    @Before
    public void setup () throws UnsupportedEncodingException {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
        
     
    }
    
    @Test
    public void testListAllUsers() throws Exception {
        
        MockHttpServletRequestBuilder builder =
                                      MockMvcRequestBuilders.post("/api/getfriend/")
                                        .header("Header",
                                                "Value ")
                                        .content(createGetFriendInJson("andy@example.com"));
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andDo(MockMvcResultHandlers.print());
        
    }

    @Test
    public void testListFriend() throws Exception {
          MockHttpServletRequestBuilder builder =
                                      MockMvcRequestBuilders.post("/api/isfriend/")
                                        .header("Header",
                                                "Value ")
                                        .content(createUserInJson("andy@example.com",
                                                            "john@example.com"));
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testListCommon() throws Exception {
        System.out.println("testing common firends");
        System.out.println(createUsercommon("andy@example.com", "john@example.com"));
        MockHttpServletRequestBuilder builder =
                                      MockMvcRequestBuilders.post("/api/getcommonfriend/")
                                        .header("Header",
                                                "Value ")
                                        .content(createUsercommon("andy@example.com", "john@example.com")
                                    );
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testSubscribe() throws Exception {
        System.out.println("testing Subscribe");
        MockHttpServletRequestBuilder builder =
                                      MockMvcRequestBuilders.post("/api/subscribe/")
                                        .header("Header",
                                                "Value ")
                                        .content(createUserSubscribe("lisa@example.com", "john@example.com")
                                    );
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andDo(MockMvcResultHandlers.print());
        
    }

    @Test
    public void testUnsubscribe() throws Exception {
   System.out.println("testing Subscribe");
        MockHttpServletRequestBuilder builder =
                                      MockMvcRequestBuilders.post("/api/unsubscribe/")
                                        .header("Header",
                                                "Value ")
                                        .content(createUserSubscribe("andy@example.com", "john@example.com")
                                    );
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testNewsposting() throws Exception {
        System.out.println("testing Subscribe");
        MockHttpServletRequestBuilder builder =
                                      MockMvcRequestBuilders.post("/api/newsposting/")
                                        .header("Header",
                                                "Value ")
                                        .content(createNews("john@example.com", "Hello World! kate@example.com")
                                    );
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andDo(MockMvcResultHandlers.print());
    }
    
    private static String createUserInJson (String emailone, String emailtwo) {
        return "{ friends : ['"+emailone+"','"+emailtwo+"']}";
    }
    
    private String createGetFriendInJson(String email){
        return email ="{email: '"+email+"'}";
    } 
    
    private static String createUsercommon(String userone,String usertwo){
       return "      {friends: [ "+
               " '"+userone+"',"+
                 " '"+usertwo+"' ] } " ;
    }
    
    private static String createUserSubscribe(String userone,String usertwo){
        
        return "{'requestor': '"+userone+"'," +
                "'target': '"+usertwo+"' }";
    }
    
    private static String createNews(String sender,String text){
        return "{\n" +
                "\"sender\": \""+sender+"\",\n" +
                "\"text\": \""+text+"\"\n" +
                "}";
    }
    
}
