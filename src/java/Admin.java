
import java.util.ArrayList;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Admin {
    public static ArrayList<User> users = new ArrayList<>();
    
    public  static void addUser(User user){
        users.add(user);
    }
    
    public  static void removeUser(User user){
        users.remove(user);
    }
    
    
    
}
