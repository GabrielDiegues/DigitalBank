/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gabriel
 */
public class ClientAccount_DAO {
    // Variables
    public static Map<String, Client_DAO> allClients = new HashMap<>();
    private Client_DAO client;
    
    // Functions
    public static boolean accountCheck(String client) {
        if(!allClients.containsKey(client)) { return false; }
        return true;
    }
    
    
    
    // Getters
    public Client_DAO getClient() {    
        return client;
    } 

    // Constructor
    public ClientAccount_DAO(String clientName) {
        this.client = new Client_DAO(clientName); 
        allClients.put(clientName, this.client);
    } 
}
