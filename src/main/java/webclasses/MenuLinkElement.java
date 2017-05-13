package webclasses;

import webclasses.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hallgato
 */
public class MenuLinkElement {
   private String resource;
   private String name;
   
   public MenuLinkElement() {
       
   }
   public MenuLinkElement(String resource, String name) {
       this.resource = resource;
       this.name = name;
   }
    public String getResource() {
        return resource;
    }
    public void setResource(String resource) {
        this.resource = resource;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
   
}
