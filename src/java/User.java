/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class User {
    String name;
    String email;
    

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        
    }
    
    public boolean isEqual(User user){
        return user.email.equals(email) && user.name.equals(name);
    }
    
   
}
