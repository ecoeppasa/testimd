/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elko.imd.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * This class for raw input of subscribe nd un subscribe API.
 * Objects of this class will be : 
 * <pre>
 * <code>
 *   {
 *          "requestor": "andy@example.com",
 *          "target": "john@example.com"
 *   }
 * </code>
 * </pre>
 * @author elko
 */
public class UserAction {
    
    @Email(message = "error email format!")
    @NotBlank(message = "please enter requestor!")
    private String requestor;
    @Email(message = "error email format!")
    @NotBlank(message = "please enter target!")
    private String target;
    
    /**
     * Function for get requestor of the action
     * @return requestor
     */
    public String getRequestor() {
        return requestor;
    }

    /**
     * Method for set requestor of the action
     * @param requestor is email of requestor 
     */
    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    /**
     * Function for get target of the action
     * @return target of action
     */
    public String getTarget() {
        return target;
    }

    /**
     * Method for set target of the action 
     * @param target target of action
     */
    public void setTarget(String target) {
        this.target = target;
    }
    
    
}
