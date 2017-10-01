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
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 *
 * UserDAOImpl class is the implementation of all abstract method of USerDAO interface class. 
 * in this class all transactions are set in each method or function. First spring will do dependency injection for 
 * SessionFactory class
 * 
 * @author elko
 * @since 2017-09-25
 * @version 1.0
 * @see <a href="https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-spring-beans-and-dependency-injection.html">Spring Beans and dependency injection </a>
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Service.html" >  Service </a>
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html" >Transactional</a>
 */
@Service
public class UserDAOImpl implements UserDAO{
    
    @Autowired
    SessionFactory sessionFactory;
    
    /**
     * This function will create friendship between two email addresses.
     * User input two email addresses and the function will create relation between them.
     * 
     * @param friendship object
     * @return List of User
     * 
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/List.html">List</a>
     * @see <a href="https://docs.jboss.org/hibernate/orm/3.5/javadocs/org/hibernate/Session.html">Session </a>
     * @see <a href="https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/queryhql.html">Hibernate Query Language (HQL)</a>
     * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html" >Transactional</a>
     */
    
    @Transactional    
    @Override
    public boolean createFriend(Friendship friendship) {
         Session session = sessionFactory.getCurrentSession();
        try {  
        session.saveOrUpdate(friendship);
        } catch (HibernateException r) {  
            return false;
        }
       return true;
    }
    
    /**
     * This service will get all user's friend. 
     * 
     * @param user is an email of users that want to get all the friends
     * @return List of friendship 
     * 
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/List.html">List</a>
     * @see <a href="https://docs.jboss.org/hibernate/orm/3.5/javadocs/org/hibernate/Session.html">Session </a>
     * @see <a href="https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/queryhql.html">Hibernate Query Language (HQL)</a>
     * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html" >Transactional</a>
     */
    @Transactional
    @Override
    public List<Friendship> getFriend(String user) {
       Session session;
       session = sessionFactory.openSession();
       List<Friendship> friend = new ArrayList<>();
       friend = session.createQuery("from Friendship where (userOne=? or userTwo=?) and status=2  ").setParameter(0, user).setParameter(1, user).list();
       return friend;
    }
    
    
    /**
     * this service will inserting to subscribe table on database.
     * raw input for this subscribe table is from UserAction class and action status,
     * if user want to subscribe other user then status must be fill by 1 (one), 
     * or user want to block update / unsubscribe other user then status must be fill be 0 (zero)
     * 
     * @param userAction is a raw input for subscribe method. This consists of a requestor email's and a target email's  
     * @param status is represent of subscribe and unsubscribe, if status is 1 then the action is subscribe 
     * @return boolean if the data successfully saved will returned true, but if the data fails to be stored will be returned false
     * 
     * @see <a href="https://docs.jboss.org/hibernate/core/3.3/api/org/hibernate/Session.html">Session </a>
     * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html" >Transactional</a>
     * 
     */
    @Transactional
    @Override
    public boolean subscribe(UserAction userAction,int status) {       
        Session session = sessionFactory.getCurrentSession();
        Subscribe subscribe = new Subscribe();
        subscribe.setRequestor(userAction.getRequestor());
        subscribe.setTarget(userAction.getTarget());
        subscribe.setStatus(status);
                
        try {  
        session.saveOrUpdate(subscribe);
        } catch (HibernateException r) {  
            return false;
        }
       return true;
    }

    /**
     * 
     * This function is to get someone subscriber.
     * This function need an email as an input parameter, then function will retrieve all the user's subscriber.
     * If has subscriber the function will return list of subscriber.
     * 
     * @param email is the user's email who want to get all the subscriber
     * @return list of subscribers object
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/List.html">List</a>
     * @see <a href="https://docs.jboss.org/hibernate/orm/3.5/javadocs/org/hibernate/Session.html">Session </a>
     * @see <a href="https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/queryhql.html">Hibernate Query Language (HQL)</a>
     * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html" >Transactional</a>
     * 
     */
    @Transactional
    @Override
    public List<Subscribe> getSubscribe(String email) {
       Session session;
       session = sessionFactory.openSession();
       List<Subscribe> subscr = new ArrayList<>();
       subscr = session.createQuery("from Subscribe where target=? and status=1  ").setParameter(0, email).list();
       
       return subscr;
    }
    
    /**
     * This service will post user update / news feed.
     * The service need raw  input of news feed consisting of sender and text message. 
     * 
     * @param newsFeed newsFeed is raw input in JSON format that filled by sender and text message to post as a update status
     * @return boolean , if news feed successful to post then return true, else will returned false
     * 
     * @see <a href="https://docs.jboss.org/hibernate/orm/3.5/javadocs/org/hibernate/Session.html">Session </a>
     * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html" >Transactional</a>
     */
    @Transactional
    @Override
    public boolean postNews(NewsFeed newsFeed) {
        Session session = sessionFactory.getCurrentSession();
        try {  
        session.saveOrUpdate(newsFeed);
        } catch (HibernateException r) {  
            return false;
        }
       return true;
    }

    /**
     * This function is to get is the user's has an another user thats block update from the user.
     * This function need an email as an input parameter, then function will retrieve all the user's unsubscriber.
     * If has subscriber the function will return list of unsubscriber / blocking from the user's update.
     * @param email is the user's email who want to get all the unsubscriber
     * @return List of user's unsubscribers 
     * 
     * @see <a href="https://docs.jboss.org/hibernate/orm/3.5/javadocs/org/hibernate/Session.html">Session </a>
     * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html" >Transactional</a>
     */
    @Override
    public List<Subscribe> getUnSubscribe(String email) {
         Session session;
       session = sessionFactory.openSession();
       List<Subscribe> subscr = new ArrayList<>();
       subscr = session.createQuery("from Subscribe where (target=? or requestor=?) and status=0  ").setParameter(0, email).setParameter(1, email).list();
       
       return subscr;
    }

}
