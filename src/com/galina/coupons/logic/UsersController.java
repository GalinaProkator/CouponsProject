package com.galina.coupons.logic;

import com.galina.coupons.beans.User;
import com.galina.coupons.dao.UsersDao;

public class UsersController {

    private UsersDao usersDao;

    public UsersController(){
        this.usersDao = new UsersDao();
    }

    public void addUser (User user){
//        Validations
        if(user == null){

            System.out.println("There is no user to add");
            return;
        }
        if(user.getPassword().equals("")){
            System.out.println("An empty password");
            return;
        }
        if(user.getPassword().length() < 6){
            System.out.println("The password is too short");
            return;
        }
        if(user.getPassword().length() > 100){
            System.out.println("The password is too long");
            return;
        }
        if(user.getUserName().length() < 6){
            System.out.println("The name is too short");
            return;
        }
        if(user.getUserName().length() > 15){
            System.out.println("The name is too long");
            return;
        }

        if (this.usersDao.isUserNameExists(user.getUserName())){
            System.out.println("Can't create user, the username already exists");
            return;
        }
        this.usersDao.addUser(user);
    }
}
