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
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * This class is mapping from "User" table on database to User class model on application. 
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
@Table(name ="user")
public class User {
    
    @Id
    @NotEmpty(message = "Email cannot be empty")
    @Column(name = "email")
    @Email
    private String email;
    
    /**
     * Function for get user's email
     * @return user's email 
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method for set user's email
     * @param email is user's email 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Function for checking is user object empty, if objects are empty will return true
     * @return true is object is empty and false if objects are not empty
     */
    public boolean isEmpty(){        
        return email == null;
    }
    
}
