/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elko.imd.controller;

import com.elko.imd.dao.UserDAO;
import com.elko.imd.handler.Validator;
import com.elko.imd.model.Friendship;
import com.elko.imd.model.GetFriend;
import com.elko.imd.model.IsFriend;
import com.elko.imd.model.NewsFeed;
import com.elko.imd.model.NewsRecipient;
import com.elko.imd.model.Subscribe;
import com.elko.imd.model.User;
import com.elko.imd.model.UserAction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@Validated
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
                List<User> list = userDAO.IsFriend(friends.getFriends().get(0).toString(),friends.getFriends().get(1).toString());
                Map<String,Boolean> message = new HashMap<>();   
              
                message.put("succes", Boolean.TRUE);
                if(list.isEmpty()){
                    message.put("succes", Boolean.FALSE);
	        }
                
	       return new ResponseEntity<Map>(message, HttpStatus.OK);
	}
        
         //-------------------Retrieve all friends--------------------------------------------------------
        @PostMapping(value = "/getfriend/")
	public ResponseEntity<GetFriend >listFriend(HttpServletRequest httpServletRequest , @RequestBody String json) {
                validator = new Validator();
                   
                if (!validator.isJSONValid(json)) {
                  GetFriend friend =new GetFriend();
                  List<String> ls =new ArrayList<>();
                  ls.add("Error JSON Format");
                  friend.setSuccess(false);
                  friend.setFriends(ls);
                  friend.setCount(0);
                  return new ResponseEntity<GetFriend>(friend, HttpStatus.BAD_REQUEST);
                }
                GetFriend friends = new GetFriend();
                User user = new Gson().fromJson(json, User.class);
                 List<String> lString = new ArrayList<>();
                if(user.isEmpty() || user.getEmail() == null){
                    friends.setCount(0);
                    friends.setFriends(lString);
                    friends.setSuccess(false);
                    return new ResponseEntity<GetFriend>(friends, HttpStatus.NOT_FOUND);
	        }
                List<Friendship> fs = userDAO.getFriend(user.getEmail());
                if(fs.isEmpty()){
                    friends.setCount(0);
                    friends.setFriends(lString);
                    friends.setSuccess(false);
                    return new ResponseEntity<GetFriend>(friends, HttpStatus.NOT_FOUND);
	        }
                
                for (Friendship object : fs) {
                    if (!object.getUserOne().equals(user.getEmail()))
                    lString.add(object.getUserOne());                
                    if (!object.getUserTwo().equals(user.getEmail()))    
                    lString.add(object.getUserTwo());
                    
                 }
               
                friends.setCount(lString.size());
                friends.setFriends(lString);
                friends.setSuccess(true);
                
               
                
	       return new ResponseEntity<GetFriend>(friends, HttpStatus.OK);
	}
        
           //-------------------Retrieve all common between friends--------------------------------------------------------
        @PostMapping(value = "/getcommonfriend/")
	public ResponseEntity<GetFriend >listCommon(HttpServletRequest httpServletRequest , @RequestBody String json) {
                validator = new Validator();
                   
                if (!validator.isJSONValid(json)) {
                  GetFriend friend =new GetFriend();
                  List<String> ls =new ArrayList<>();
                  ls.add("Error JSON Format");
                  friend.setSuccess(false);
                  friend.setFriends(ls);
                  friend.setCount(0);
                  return new ResponseEntity<GetFriend>(friend, HttpStatus.BAD_REQUEST);
                }
                
               
                GetFriend friends = new GetFriend();
                IsFriend isFriend = new Gson().fromJson(json, IsFriend.class);
               
                 List<String> lString = new ArrayList<>();
                
                 if(isFriend.isEmpty() || isFriend.getFriends()== null){
                    friends.setCount(0);
                    friends.setFriends(lString);
                    friends.setSuccess(false);
                    return new ResponseEntity<GetFriend>(friends, HttpStatus.NOT_FOUND);
	        }
                
                 
                List<List<Friendship>> friend = new ArrayList<List<Friendship>>();                 
                List<Friendship> fs = null;
                for (String s : isFriend.getFriends()){
                          fs = userDAO.getFriend(s);
                          if (!fs.isEmpty()){
                          friend.add(fs);
                      }
                          
                     }
               
                if(friend.size()==1){
                    friends.setCount(0);
                    friends.setFriends(lString);
                    friends.setSuccess(false);
                    return new ResponseEntity<GetFriend>(friends, HttpStatus.NOT_FOUND);
	        }
                Set<String> set = new HashSet<String>();
                for (int i=0;i<friend.size();i++)
                {
                    List<String> u1 = new ArrayList<>();
                    for (int j=0;j<friend.get(i).size();j++){
                     
                               set.add(friend.get(i).get(j).getUserOne());
                                set.add(friend.get(i).get(j).getUserTwo());
                         
                    }
                }                
                lString.addAll(set);
                for(String f : isFriend.getFriends()){
                    lString.remove(f);
                }
                friends.setCount(lString.size());
                friends.setFriends(lString);
                friends.setSuccess(true);
                
	       return new ResponseEntity<GetFriend>(friends, HttpStatus.OK);
	}
        
        @PostMapping(value = "/subscribe/")
        public ResponseEntity<Map> subscribe(HttpServletRequest httpServletRequest , @RequestBody String json){

            UserAction obj = gson.fromJson(json,UserAction.class);
            Map<String,Boolean> message = new HashMap<>();   
             message.put("success", false);   
            if( userDAO.subscribe(obj,1)){
                message.clear();
                message.put("success", true);
            }

            return new ResponseEntity<Map>(message, HttpStatus.OK);

        }
        
        @PostMapping(value = "/unsubscribe/")
        public ResponseEntity<Map> unsubscribe(HttpServletRequest httpServletRequest , @RequestBody String json){

            UserAction obj = gson.fromJson(json,UserAction.class);
            Map<String,Boolean> message = new HashMap<>();   
             message.put("success", false);   
            if( userDAO.subscribe(obj,0)){
                message.clear();
                message.put("success", true);
            }

            return new ResponseEntity<Map>(message, HttpStatus.OK);

        }
        
        @PostMapping(value = "/newsposting/")
        public ResponseEntity<NewsRecipient> newsposting(HttpServletRequest httpServletRequest , @RequestBody String json){

            NewsFeed newsFeed = gson.fromJson(json,NewsFeed.class);
            NewsRecipient nr =new NewsRecipient();
            nr.setSuccess(false);
            List<String> mentioned = new ArrayList<String>();
            String text = newsFeed.getText();
            Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(text);
            
            if( userDAO.postNews(newsFeed)){
                while (m.find()) {
                 mentioned.add(m.group());
                }
                 List<Friendship> fs = userDAO.getFriend(newsFeed.getSender());
                for (Friendship object : fs) {
                    if (!object.getUserOne().equals(newsFeed.getSender()))
                    mentioned.add(object.getUserOne());                
                    if (!object.getUserTwo().equals(newsFeed.getSender()))    
                    mentioned.add(object.getUserTwo());                    
                 }
                System.out.println("1");
                List<Subscribe> u = userDAO.getSubscribe(newsFeed.getSender());
                System.out.println("2");
                for (Subscribe us : u){
                    mentioned.add(us.getRequestor());
                }
                System.out.println("3");
                Set<String> set = new HashSet<>();
                set.addAll(mentioned);
                nr.setSuccess(true);
                nr.setRecipients(set);
            }

            return new ResponseEntity<NewsRecipient>(nr, HttpStatus.OK);

        }
    
}
