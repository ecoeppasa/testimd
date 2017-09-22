/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elko.imd.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 *
 * @author elko
 */
public class Validator {
    

   
    public boolean isJSONValid(String json) {
    Gson gson = new Gson();
    try {
           Object o = gson.fromJson(json, Object.class);
       } catch (Exception e) {
              return false;
       }
        return true;        

    }
}
