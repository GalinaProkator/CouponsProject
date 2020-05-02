package com.galina.coupons.dao;

import com.galina.coupons.beans.User;
import com.galina.coupons.enums.UserType;

public class UsersDao {
    public void addUser (User user){
        System.out.println("User has been successfully added to DB");
    }

    public void updateUser(User user) {
        System.out.println("User has been successfully updated");
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

    public void deleteUsersByCompany(Long companyId) {
        System.out.println("Users have been successfully deleted from DB");
    }

    public void deleteUserByCustomer(Long customerId) {
        System.out.println("User has been successfully deleted from DB");
    }
}
