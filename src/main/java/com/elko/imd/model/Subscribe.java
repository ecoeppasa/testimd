/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elko.imd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;

/**
 * This class is mapping from "Subscribe" table on database to Subscribe class model on application. 
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
@Table(name = "subscribe")
public class Subscribe {
    
    
    @Id
    @Email
    @Column(name = "requestor")
    private String requestor;
    @Email
    @Column(name = "target")
    private String target;
    @Column(name = "status")
    private int status;

    /**
     * This function for get subscribing user
     * @return requestor of subscribing user 
     */
    public String getRequestor() {
        return requestor;
    }

    /**
     * Method for set value of Requestor variable
     * @param requestor is email of subscriber user
     */
    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    /**
     * This function for get target of subscribing user
     * @return target user of subscribing 
     */
    public String getTarget() {
        return target;
    }

    /**
     * Method for set target of subscribing
     * @param target of subscribing users 
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * FUnction for get status of subscribing user, if status 1 then action is subscribe, if status 0 the action is unsubscribe
     * @return status of subscribe or unsubscribe
     */
    
    public int getStatus() {
        return status;
    }

    /**
     * Method for set status of SUbscribe
     * @param status has 1 for subscribe and 0 for unsubscribe
     */
    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
    
}
