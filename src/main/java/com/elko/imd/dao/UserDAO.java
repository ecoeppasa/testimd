/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elko.imd.dao;

import com.elko.imd.model.Friendship;
import com.elko.imd.model.GetFriend;
import com.elko.imd.model.NewsFeed;
import com.elko.imd.model.NewsRecipient;
import com.elko.imd.model.Subscribe;
import com.elko.imd.model.User;
import com.elko.imd.model.UserAction;
import java.util.List;
import javax.validation.Valid;
import org.hibernate.validator.constraints.Email;
import org.springframework.validation.annotation.Validated;

/**
 *
 * An interface is a reference type in Java. It is similar to class. It is a collection of abstract methods. 
 * A class implements an interface, thereby inheriting the abstract methods of the interface.
 * This interface is about reference / blue print of User Service.
 * The interface consisting of create friend, get common friends, subscribe, block update, post news fedd and etc..
 * 
 * @author elko
 * @since 2017-09-25
 * 
 */

@Validated
public interface UserDAO {
     /**
     * This is abstract method for create friendship between two email addresses.
     * It's must be implement in implementation class.
     * 
     * @param friendship is user's email that's want to be friend 
     * @return List of User
     * 
     * 
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/List.html">List</a>
     * @see <a href="https://docs.jboss.org/hibernate/orm/3.5/javadocs/org/hibernate/Session.html">Session </a>
     * @see <a href="https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/queryhql.html">Hibernate Query Language (HQL)</a>
     * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html" >Transactional</a>
     */
    public boolean createFriend(@Valid Friendship friendship);
   
     /**
     * This is abstract method for create getFriend function.
     * It's must be implement in implementation class.
     * @param user is an email of users that want to get all the friends
     * @return List of friendship 
     * 
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/List.html">List</a>
     * @see <a href="https://docs.jboss.org/hibernate/orm/3.5/javadocs/org/hibernate/Session.html">Session </a>
     * @see <a href="https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/queryhql.html">Hibernate Query Language (HQL)</a>
     * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html" >Transactional</a>
     */
    
    public List<Friendship> getFriend(@Email(message = "use email format") String user) ;
      /**
      * This is abstract method for create subscribe function.
      * It's must be implement in implementation class.
      * @param userAction is a raw input for subscribe method. This consists of a requestor email's and a target email's  
      * @param status is represent of subscribe and unsubscribe, if status is 1 then the action is subscribe 
      * @return boolean if the data successfully saved will returned true, but if the data fails to be stored will be returned false
      * 
      * @see <a href="https://docs.jboss.org/hibernate/core/3.3/api/org/hibernate/Session.html">Session </a>
      * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html" >Transactional</a>
      */
    public boolean subscribe(@Valid UserAction userAction,int status);
    /**
     * This is abstract method for create getSubscribe function.
     * It's must be implement in implementation class.
     * @param email is the user's email who want to get all the subscriber
     * @return list of subscribers object
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/List.html">List</a>
     * @see <a href="https://docs.jboss.org/hibernate/orm/3.5/javadocs/org/hibernate/Session.html">Session </a>
     * @see <a href="https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/queryhql.html">Hibernate Query Language (HQL)</a>
     * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html" >Transactional</a>
     * 
     */
    public List<Subscribe> getSubscribe (@Email(message = "use email format") String email);
    /**
       * This is abstract method for create getUnSubscribe function.
       * It's must be implement in implementation class. 
       * @param email is the user's email who want to get all the unsubscriber
       * @return List of user's unsubscribers 
       * 
       * @see <a href="https://docs.jboss.org/hibernate/orm/3.5/javadocs/org/hibernate/Session.html">Session </a>
       * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html" >Transactional</a>
       */
    public List<Subscribe> getUnSubscribe (@Email(message = "use email format") String email);
   /**
     * This is abstract method for create postNews function.
     * It's must be implement in implementation class.
     * @param newsFeed newsFeed is raw input in JSON format that filled by sender and text message to post as a update status
     * @return boolean , if news feed successful to post then return true, else will returned false
     * 
     * @see <a href="https://docs.jboss.org/hibernate/orm/3.5/javadocs/org/hibernate/Session.html">Session </a>
     * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html" >Transactional</a>
     */
    public boolean postNews (@Valid NewsFeed newsFeed);
}
