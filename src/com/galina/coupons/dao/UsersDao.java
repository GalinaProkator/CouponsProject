package com.galina.coupons.dao;

import com.galina.coupons.enums.UserType;
import com.galina.coupons.beans.User;

public class UsersDao {
    public void addUser (User user){
        System.out.println("User has been successfully added to DB");
    }

    public User[] getAllUsers(){
        User user1 = new User("yossi@beep.de", "1234", UserType.CUSTOMER, null);
        User user2 = new User("eli@hello.com", "6666", UserType.ADMIN,  null);
        User user3 = new User("roni@hey.co.il", "8888", UserType.COMPANY,  1l);

        User[] users = new User [3];
        users[0] = user1;
        users[1] = user2;
        users[2] = user3;

        return users;
    }

    public boolean isUserNameExists (String userName){
        return false;
    }
}
