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

/**
 * This is class model for table "Friendship" in database.
 * This class uses annotations to mapping relational objects between class model and database
 * @author elko
 * @since 2017-09-25
 * 
 * @see <a href="https://docs.jboss.org/hibernate/stable/annotations/reference/en/html_single/">Hibernate Annotations </a>
 * @see <a href="http://docs.oracle.com/javaee/6/tutorial/doc/bnbqa.html">Entity </a>
 * @see <a href="https://docs.oracle.com/javaee/7/api/javax/persistence/Table.html">javax.persistence.Table </a>
 * 
 */
@Entity
@Table(name = "friendship")
public class Friendship {
    
    @Id
    @GenericGenerator(name="gen" , strategy="increment")
    @GeneratedValue(generator="gen")    
    @Column(name = "id")
    private int id;
    @Column(name = "user_one")
    private String userOne;
    @Column(name = "user_two")
    private String userTwo;
    @Column(name = "status")
    private int status;
    @Column(name = "action_by_user")
    private int actionBy;

    /**
     * this function for get "id" value in Friendship class
     * @return value of id
     */
    public int getId() {
        return id;
    }

    /**
     * this method for set id in class
     * 
     * @param id is a key of Friendship table
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This function for get value of userOne variable
     * @return email of userOne
     */
    public String getUserOne() {
        return userOne;
    }

    /**
     * This method is for set UserOne variable
     * @param userOne is value of userOne variable that's want to be set
     */
    public void setUserOne(String userOne) {
        this.userOne = userOne;
    }

    /**
     * This function is for get value of userTwo variable
     * @return value of userTwo
     */
    public String getUserTwo() {
        return userTwo;
    }

    /**
     * This method is for set userTwo variable
     * @param userTwo  is value of userTwo variable
     */
    public void setUserTwo(String userTwo) {
        this.userTwo = userTwo;
    }

    /**
     * getStatus is function for get status value
     * @return int of status 
     */
    public int getStatus() {
        return status;
    }

    /**
     * setStatus is method for set up status variable
     * @param status is action of friendship
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * getActionBy is function for get value of actionBy variable
     * @return actionBy 
     */
    public int getActionBy() {
        return actionBy;
    }

    /**
     * 
     * @param actionBy is integer of id user 
     */
    public void setActionBy(int actionBy) {
        this.actionBy = actionBy;
    }
    
    
    
}
