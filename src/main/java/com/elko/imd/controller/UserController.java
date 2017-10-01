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
 * UserController is class for control rest service.
 * The {@code @RestController } annotation a specialized version of the controller which is a convenience annotation that 
 * does nothing more than add the {@code @Controller  and @ResponseBody } annotations. By annotating the controller class with 
 * {@code @RestController} annotation, you no longer need to add {@code @ResponseBody} to all the request mapping methods.
 * The {@code @ResponseBody} annotation is active by default.
 * The {@code @RequestMapping} annotation is used to map a URL to either an entire class or a particular handler method
 * 
 * @author elko
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RestController.html">RestController </a>
 * @see <a href="https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestMapping.html">RequestMapping </a>
 * @see <a href="https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-spring-beans-and-dependency-injection.html">Spring Beans and dependency injection </a>
 */

@RestController
@RequestMapping("/api")
@Validated
public class UserController {
    private Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    
     @Autowired
     UserDAO userDAO;
    
    
     Validator validator;
    
     /**
      * This method will create relationship between two email addresses.
      * Raw input for this method must be (example making john and andy be friend):
      * <pre>
      * <code>
      * {
             friends:
                 [
                 'andy@example.com',
                 'john@example.com'
                 ]
        }
      * </code>
      * </pre>
      * 
      * @param httpServletRequest  get request information of HTTP Servlets.
      * @param json body of input message parameter
      * @return <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html">ResponseEntity</a>
      * is meant to represent the entire HTTP response. You can control anything that goes into it: status code, headers, and body.
      * In this method use Map as a body of response. 
      * 
      * @see <a href="http://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletRequest.html">HttpServletRequest</a>
      * @see <a href="http://www.json.org/">JSON</a>
      * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html">ResponseEntity</a>
      * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestBody.html">RequestBody</a>
      * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/PostMapping.html">PostMapping</a>
      */
        @PostMapping(value = "/createfriend/")
	public ResponseEntity<Map >createFriend(HttpServletRequest httpServletRequest , @RequestBody String json) {
                validator = new Validator();
                Map<String,String> messageError = new HashMap<>();   
                if (!validator.isJSONValid(json)) {
                  messageError.put("Error Message", "Invalid JSon Format!");                
                  return new ResponseEntity<Map>(messageError, HttpStatus.BAD_REQUEST);
                }
            
                IsFriend friends = new Gson().fromJson(json, IsFriend.class);
                Friendship fs = new Friendship();
                fs.setUserOne(friends.getFriends().get(0));
                fs.setUserTwo(friends.getFriends().get(1));
                fs.setStatus(2);
                fs.setActionBy(1);
                
                
                Map<String,Boolean> message = new HashMap<>();   
              
               message.put("success", false);   
                    if( userDAO.createFriend(fs)){
                        message.clear();
                        message.put("success", true);
                    }

                
	       return new ResponseEntity<Map>(message, HttpStatus.OK);
	}
        
        /**
         * This method will retrieve the friends list for an email address.
         * The method will get all email addresses that's be friend with the user.
         *  Json input for this service must be :
         * <pre>
         * <code>
         *   {
         *      email: 'andy@example.com'
         *   }
         * </code>
         * </pre> 
         * 
         * @param httpServletRequest  get request information of HTTP Servlets.
         * @param json body of input message parameter
         * @return <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html">ResponseEntity</a>
         * is meant to represent the entire HTTP response. You can control anything that goes into it: status code, headers, and body.
         * In this method use GetFriend class as a body of response. 
         * 
         * @see <a href="http://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletRequest.html">HttpServletRequest</a>
         * @see <a href="http://www.json.org/">JSON</a>
         * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html">ResponseEntity</a>
         * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestBody.html">RequestBody</a>
         * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/PostMapping.html">PostMapping</a>
         */        
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
         /**
         * This method will retrieve the common	friends	list between two email addresses.	
         * The method will retrieve list of all common friends between two or more email addresses.
         * 
         *  Json input for this service must be :
         * <pre>
         * <code>
         *   {
         *       friends:
         *        [
         *       'andy@example.com',
         *       'john@example.com'
         *       ]
         *   }
         * </code>
         * </pre> 
         *  
         * @param httpServletRequest  get request information of HTTP Servlets.
         * @param json body of input message parameter
         * @return <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html">ResponseEntity</a>
         * is meant to represent the entire HTTP response. You can control anything that goes into it: status code, headers, and body.
         * In this method use GetFriend class as a body of response. 
         * 
         * @see <a href="http://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletRequest.html">HttpServletRequest</a>
         * @see <a href="http://www.json.org/">JSON</a>
         * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html">ResponseEntity</a>
         * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestBody.html">RequestBody</a>
         * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/PostMapping.html">PostMapping</a>
         */
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
        
         /**
         * This method will subscribe to updates from an email address.
         * The raw input must be : 
         * <pre>
         * <code>
         *  {
         *   "requestor": "lisa@example.com",
         *   "target": "john@example.com"
         *  }
         * </code>
         * </pre>
         * 
         * @param httpServletRequest  get request information of HTTP Servlets.
         * @param json body of input message parameter
         * @return <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html">ResponseEntity</a>
         * is meant to represent the entire HTTP response. You can control anything that goes into it: status code, headers, and body.
         * In this method use Map as a body of response. 
         * 
         * @see <a href="http://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletRequest.html">HttpServletRequest</a>
         * @see <a href="http://www.json.org/">JSON</a>
         * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html">ResponseEntity</a>
         * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestBody.html">RequestBody</a>
         * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/PostMapping.html">PostMapping</a>
         */
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
        
         /**
         * 
         *This method will block updates from an email address.	
         * The raw input must be : 
         * <pre>
         * <code>
         *  {
         *   "requestor": "lisa@example.com",
         *   "target": "john@example.com"
         *  }
         * </code>
         * </pre>
         * 
         * @param httpServletRequest  get request information of HTTP Servlets.
         * @param json body of input message parameter
         * @return <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html">ResponseEntity</a>
         * is meant to represent the entire HTTP response. You can control anything that goes into it: status code, headers, and body.
         * In this method use Map as a body of response. 
         * 
         * @see <a href="http://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletRequest.html">HttpServletRequest</a>
         * @see <a href="http://www.json.org/">JSON</a>
         * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html">ResponseEntity</a>
         * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestBody.html">RequestBody</a>
         * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/PostMapping.html">PostMapping</a>
         */
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
        
         /**
         * *This method will retrieve all email addresses that can receive updates from an email address.	
         *  Eligibility for receiving updates from i.e. "john@example.com":	
         *  has not blocked updates from "john@example.com", and	
         *   at	least	one	of	the	following:	
         *   <ul>
         *   <li> has	a	friend	connection	with	"john@example.com"	</li>
         *   <li> has	subscribed	to	updates	from	"john@example.com"	</li>
         *   <li> has	been	@mentioned	in	the	update      </li>
         *  </ul>
         * The raw input must be : 
         * <pre>
         * <code>
         *  {
         *       "sender": "john@example.com",
         *       "text": "Hello World! kate@example.com"
         *  }
         * </code>
         * </pre>
         * 
         * @param httpServletRequest  get request information of HTTP Servlets.
         * @param json body of input message parameter
         * @return <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html">ResponseEntity</a>
         * is meant to represent the entire HTTP response. You can control anything that goes into it: status code, headers, and body.
         * In this method use NewsRecipient class as a body of response. 
         * 
         * @see <a href="http://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletRequest.html">HttpServletRequest</a>
         * @see <a href="http://www.json.org/">JSON</a>
         * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html">ResponseEntity</a>
         * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestBody.html">RequestBody</a>
         * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/PostMapping.html">PostMapping</a>
         */
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
                List<Subscribe> u = userDAO.getSubscribe(newsFeed.getSender());
                for (Subscribe us : u){
                    mentioned.add(us.getRequestor());
                }
                List<Subscribe> un = userDAO.getUnSubscribe(newsFeed.getSender());
                for (Subscribe usc : un){
                    mentioned.remove(usc.getRequestor());                    
                    mentioned.remove(usc.getTarget());
                }
                Set<String> set = new HashSet<>();
                set.addAll(mentioned);
                nr.setSuccess(true);
                nr.setRecipients(set);
            }

            return new ResponseEntity<NewsRecipient>(nr, HttpStatus.OK);

        }
    
}
