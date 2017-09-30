/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elko.imd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * This class is mapping from "newsfeed" table on database to NewsFeed class model on application. 
 * This class uses annotations to mapping relational objects between class model and database.
 * 
 * 
 * @author elko
 * @since 2017-09-25
 * 
 * @see <a href="https://docs.jboss.org/hibernate/stable/annotations/reference/en/html_single/">Hibernate Annotations </a>
 * @see <a href="http://docs.oracle.com/javaee/6/tutorial/doc/bnbqa.html">Entity </a>
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/persistence/Table.html">javax.persistence.Table </a>

 */
@Entity
@Table(name = "newsfeed")
public class NewsFeed {

    @Id
    @GenericGenerator(name="gen" , strategy="increment")
    @GeneratedValue(generator="gen")  
    @Column(name = "id")
    private Long id;
    @Email
    @Column(name = "sender")
    private String sender;
    @NotEmpty
    @Column(name = "text")
    private String text;

    /**
     * This method for retrieve id of newsfeed
     * @return id of news feed 
     */
    public Long getId() {
        return id;
    }

    /**
     * method for set newsfeed id
     * @param id of news feed, it's automatically generate by system
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * function for getting sender of news feed.
     * 
     * @return sendoer of newsfeed 
     */
    public String getSender() {
        return sender;
    }

    /**
     * method for set value of sender variable 
     * @param sender is email who create news feed 
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * function for get news feed message.
     * @return message of news feed 
     */
    public String getText() {
        return text;
    }

    /**
     * method for set news feed text message
     * @param text message of news feed
     */
    public void setText(String text) {
        this.text = text;
    }
    
    
    
}
