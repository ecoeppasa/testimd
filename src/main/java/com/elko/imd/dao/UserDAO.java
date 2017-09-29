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
 * @author elko
 */

@Validated
public interface UserDAO {
  /**
     * 
     * @param userOne is the email of the first user to be compared, whether it has a relationship with other one email
     * @param userTwo is the email of the second user to be compared, whether it has a relationship with other one email
     * @return List of User
     * 
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/List.html">List</a>
     * @see <a href="https://docs.jboss.org/hibernate/orm/3.5/javadocs/org/hibernate/Session.html">Session </a>
     * @see <a href="https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/queryhql.html">Hibernate Query Language (HQL)</a>
     * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html" >Transactional</a>
     */
    public List<User> IsFriend(@Email(message = "use email format") String userOne,@Email(message = "use email format") String userTwo) ;
     /**
     * 
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
      * 
      * 
      * @param userAction is a raw input for subscribe method. This consists of a requestor email's and a target email's  
      * @param status is represent of subscribe and unsubscribe, if status is 1 then the action is subscribe 
      * @return boolean if the data successfully saved will returned true, but if the data fails to be stored will be returned false
      * 
      * @see <a href="https://docs.jboss.org/hibernate/core/3.3/api/org/hibernate/Session.html">Session </a>
      * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html" >Transactional</a>
      * 
      * @exception HibernateException
      */
    public boolean subscribe(@Valid UserAction userAction,int status);
    /**
     * 
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
       * 
       * @param email is the user's email who want to get all the unsubscriber
       * @return List of user's unsubscribers 
       * 
       * @see <a href="https://docs.jboss.org/hibernate/orm/3.5/javadocs/org/hibernate/Session.html">Session </a>
       * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html" >Transactional</a>
       */
    public List<Subscribe> getUnSubscribe (@Email(message = "use email format") String email);
   /**
     * 
     * @param newsFeed newsFeed is raw input in JSON format that filled by sender and text message to post as a update status
     * @return boolean , if news feed successful to post then return true, else will returned false
     * 
     * @see <a href="https://docs.jboss.org/hibernate/orm/3.5/javadocs/org/hibernate/Session.html">Session </a>
     * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html" >Transactional</a>
     */
    public boolean postNews (@Valid NewsFeed newsFeed);
}
