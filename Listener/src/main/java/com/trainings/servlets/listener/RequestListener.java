package com.trainings.servlets.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class RequestListener
 *
 */

//This listener will monitor the creation and destruction of servlets and then output a message
//to the console when a servlet is either created or destroyed 
@WebListener
public class RequestListener implements ServletRequestListener {


	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent event)  { 
        
    	System.out.println("Request Destroyed");
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent event)  { 
         
    	System.out.println("Request Created");
    	
    }
	
}
