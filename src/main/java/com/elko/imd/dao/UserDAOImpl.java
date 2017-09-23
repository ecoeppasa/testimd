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
 * @author elko
 */
@Service
public class UserDAOImpl implements UserDAO{
    
    @Autowired
    SessionFactory sessionFactory;
    
    @Transactional
    @Override
    public List<User> IsFriend(String userOne,String userTwo) {
       Session session;
       session = sessionFactory.openSession();
       List<Friendship> friend = new ArrayList<>();
       friend = session.createQuery("from Friendship where userOne=? and userTwo=? and status=2  ").setParameter(0, userOne).setParameter(1, userTwo).list();
       User u = new User();       
       User n = new User();       
       List<User> user = new ArrayList<User>();
        if (!friend.isEmpty()){
            u.setEmail(userOne);
            n.setEmail(userTwo);
            user.add(u);
            user.add(n);
       }
       return user;
    }
    @Transactional
    @Override
    public List<Friendship> getFriend(String user) {
       Session session;
       session = sessionFactory.openSession();
       List<Friendship> friend = new ArrayList<>();
       friend = session.createQuery("from Friendship where (userOne=? or userTwo=?) and status=2  ").setParameter(0, user).setParameter(1, user).list();
       return friend;
    }
    
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

    @Transactional
    @Override
    public List<Subscribe> getSubscribe(String email) {
       Session session;
       session = sessionFactory.openSession();
       List<Subscribe> subscr = new ArrayList<>();
       subscr = session.createQuery("from Subscribe where target=? and status=1  ").setParameter(0, email).list();
       
       return subscr;
    }
    
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
    
}
