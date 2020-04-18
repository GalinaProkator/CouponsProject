package com.galina.coupons.logic;

import com.galina.coupons.beans.User;
import com.galina.coupons.dao.UsersDao;

public class UsersController {

    private UsersDao usersDao;

    public UsersController(){
        this.usersDao = new UsersDao();
    }

    public void addUser (User user) throws Exception{
        userValidations(user);
        this.usersDao.addUser(user);
    }

    private void userValidations(User user) throws Exception {
        if(user == null){
            throw new Exception("There is no user to add");
        }
        if(user.getPassword().equals("")){
            throw new Exception("An empty password");
        }
        if(user.getPassword().length() < 6){
            throw new Exception("The password is too short");
        }
        if(user.getPassword().length() > 100){
            throw new Exception("The password is too long");
        }
        if(user.getUserName().length() < 6){
            throw new Exception("The name is too short");
        }
        if(user.getUserName().length() > 15){
            throw new Exception("The name is too long");
        }
        if (this.usersDao.isUserNameExists(user.getUserName())){
            throw new Exception("Can't create user, the username already exists");
        }
    }
}
