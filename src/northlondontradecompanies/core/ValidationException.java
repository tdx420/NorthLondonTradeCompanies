package northlondontradecompanies.core;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This is a custom Exception class, which inherits from <code>Exception</code> class.
 * The class is meant to be use whenever a situation arise when  a user makes an invalid entry , and the system wants to throw an checked exception 
 * in order to trigger an error message, informing the user of the error so that it can be corrected
 * All the validation situations that are user recoverable will use this class.
 * 
 * @author tdx_429
 */
public class ValidationException extends Exception{
    
    /**
     * The constructor requires a String message to be supplied which will contain the text of the validation error. 
     * @param message 
     */
    public ValidationException(String message){
        super(message);
    }   
}