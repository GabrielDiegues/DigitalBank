/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class Client_DAO {
    // Variables
    private String name;
    private Map<String, Double> Accounts;
    public static Client_DAO actualClient; 
    
    // Functions
    public void withDraw(String key) {
        double accountValue = Accounts.get(key);
        Optional<Integer> optionalValue = checkValue("Type the withdraw value");
        if(optionalValue.isPresent()) {
            int value = optionalValue.get();
            if(value <= accountValue) { 
                Accounts.put(key, accountValue - value);
                JOptionPane.showMessageDialog(null, "Your Withdraw completed succesfully");
                updateBalance(key);
            }
            else {
                JOptionPane.showMessageDialog(null, "Insufficient funds");
            }
        }
    }
    
    
    public void deposit(String key) {
        double accountValue = Accounts.get(key);
        Optional<Integer> optionalValue;
        optionalValue = checkValue("Type the deposit value");
        if(optionalValue.isPresent()) {
            int value = optionalValue.get();
            Accounts.put(key, accountValue + value);
            JOptionPane.showMessageDialog(null, "Your deposit was completed succesfully!");
            updateBalance(key);
        }
    }
    
    
    public void transfer(String key) {
        double accountValue = Accounts.get(key);
        Optional<Integer> optionalValue;
        optionalValue = checkValue("Type the transfer value");
        if(optionalValue.isPresent()) {
            int value = optionalValue.get();
            String name;
            name = JOptionPane.showInputDialog(null, "Type the account name to which you want to transfer");
            if(value > accountValue) { JOptionPane.showMessageDialog(null, "Insufficient funds"); }
            else if(!ClientAccount_DAO.allClients.containsKey(name.trim())) { JOptionPane.showMessageDialog(null, "The account that you are trying to transfer doesn't exit"); }
            else if(ClientAccount_DAO.allClients.get(name.trim()).getName().trim().equals(this.name.trim())) { JOptionPane.showMessageDialog(null, "You can't do a transfer to yourself"); }
            else {
                Client_DAO client = ClientAccount_DAO.allClients.get(name.trim());
                client.addBalance(value, key);
                this.Accounts.put(key, accountValue - value);
                JOptionPane.showMessageDialog(null, "Your transfer was completed succesfully!");
                updateBalance(key);
            }
        }
    }
    
    
    private void addBalance(double value, String key) { this.Accounts.put(key, this.Accounts.get(key) + value); }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.name);
        return hash;
    }

    
    private Optional<Integer> checkValue(String msg) {
        Optional<Integer> value = Optional.empty();
        String userInput = JOptionPane.showInputDialog(null, msg);
        try {
            value = Optional.of(Integer.parseInt(userInput));
        } 
        catch(NumberFormatException e) {
            if(userInput != null) { JOptionPane.showMessageDialog(null, "Please type a valid value"); } 
        }
        return value;
        
    }
    
    
    private void updateBalance(String key) {
        for(String accountsKey : Accounts.keySet()) {
            if(key.equals("CheckingAccount") && accountsKey.equals(key)) {
                view.CheckingAccountMenu_GUI.balance_lbl.setText(Double.toString(Accounts.get(key)));
                break;
            }
            if(key.equals("SavingsAccount") && accountsKey.equals(key)) {
                view.SavingsAccountMenu_GUI.balance_lbl.setText(Double.toString(Accounts.get(key)));
            }
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client_DAO other = (Client_DAO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    

    // Getters
    public String getName() {    
        return name;
    }

    public double getBalance(String key) { return Accounts.get(key); }
    // Constructor
    public Client_DAO(String name) {
        this.name = name;
        this.Accounts = new HashMap<>();
        this.Accounts.put("CheckingAccount", 0.d);
        this.Accounts.put("SavingsAccount", 0.d);
    }
}
