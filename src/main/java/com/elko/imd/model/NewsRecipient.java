/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elko.imd.model;

import java.util.List;
import java.util.Set;

/**
 * This class for retrieve all email addresses that can receive updates from an email address.
 * Objects of this class will be : 
 * <pre>
 * <code>
 *   {
 *       "success": true
 *       "recipients":
 *                       [
 *                        "lisa@example.com",
 *                        "kate@example.com"
 *                       ]
 *   }
 * </code>
 * </pre>
 * @author elko
 */
public class NewsRecipient {
    private boolean success;
    private Set<String> recipients;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Set<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(Set<String> recipients) {
        this.recipients = recipients;
    }

    
    
}
