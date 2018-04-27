/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.google.gson.Gson;

/**
 *
 * @author rafael.recalcatti
 */
public class GerarJson {

public String getJson(Object object)
        {                                    
        //1. Convert object to JSON string
        Gson gson = new Gson();
        String json = gson.toJson(object);        
        return json;
 
   }
}
