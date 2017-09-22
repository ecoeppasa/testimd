/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elko.imd.controller;

import com.elko.imd.dao.UserDAO;
import com.elko.imd.handler.Validator;
import com.elko.imd.model.Friendship;
import com.elko.imd.model.IsFriend;
import com.elko.imd.model.IsFriendMessage;
import com.elko.imd.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author elko
 */
@RestController
@RequestMapping("/api")
public class UserController {
    private Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    
     @Autowired
     UserDAO userDAO;
    
    
     Validator validator;
    
    //-------------------Check is users are friend--------------------------------------------------------
        @PostMapping(value = "/isfriend/")
	public ResponseEntity<Map >listAllUsers(HttpServletRequest httpServletRequest , @RequestBody String json) {
                validator = new Validator();
                Map<String,String> messageError = new HashMap<>();   
                if (!validator.isJSONValid(json)) {
                  messageError.put("Error Message", "Invalid JSon Format!");                
                  return new ResponseEntity<Map>(messageError, HttpStatus.BAD_REQUEST);
                }
            
                IsFriend friends = new Gson().fromJson(json, IsFriend.class);
                List<User> list = userDAO.IsFriend(friends.getFriend().get(0).toString(),friends.getFriend().get(1).toString());
                Map<String,Boolean> message = new HashMap<>();   
              
                message.put("Succes", Boolean.TRUE);
                if(list.isEmpty()){
                    message.put("Succes", Boolean.FALSE);
	        }
                
	       return new ResponseEntity<Map>(message, HttpStatus.OK);
	}
        
}
