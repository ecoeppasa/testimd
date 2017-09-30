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
 * This is class model for table "Friendship" in database
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserOne() {
        return userOne;
    }

    public void setUserOne(String userOne) {
        this.userOne = userOne;
    }

    public String getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(String userTwo) {
        this.userTwo = userTwo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getActionBy() {
        return actionBy;
    }

    public void setActionBy(int actionBy) {
        this.actionBy = actionBy;
    }
    
    
    
}
