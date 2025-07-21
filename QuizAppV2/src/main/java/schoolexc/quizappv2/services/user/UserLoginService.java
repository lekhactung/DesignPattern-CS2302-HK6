/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.services.user;

import schoolexc.quizappv2.pojo.User;
import schoolexc.quizappv2.utils.Configs;

/**
 *
 * @author LE TUNG
 
    private static UserLoginService instance;
    private User user;

    public UserLoginService() {
        
    }

    public static UserLoginService getInstance() {
        if (instance == null) {
            instance = new UserLoginService();
        }

        return instance;
    }

    
    public void login(User s) {
        this.user = s;
    }

}
*/
public class UserLoginService {

    private static UserLoginService instance;
    private User user;

    public UserLoginService() {
        
    }

    public static UserLoginService getInstance() {
        if (instance == null) {
            instance = new UserLoginService();
        }

        return instance;
    }

    
    public void login(User s) {
        this.user = s;
    }
    
    public User getUser(){
        return this.user;
    }

}
