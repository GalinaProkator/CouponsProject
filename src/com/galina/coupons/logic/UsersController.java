package com.galina.coupons.logic;

import com.galina.coupons.beans.User;
import com.galina.coupons.dao.UsersDao;
import com.galina.coupons.enums.ErrorType;
import com.galina.coupons.myutils.ApplicationException;
import com.galina.coupons.myutils.MyUtils;

public class UsersController {

    private UsersDao usersDao;

    public UsersController() {
        this.usersDao = new UsersDao();
    }

    public void addUser(User user) throws ApplicationException {
        userValidations(user);
        this.usersDao.addUser(user);
    }

    public void updateUser(User user) throws ApplicationException {
        userValidations(user);
        this.usersDao.updateUser(user);
    }

    public void deleteUsersByCompany(Long companyId) {
        this.usersDao.deleteUsersByCompany(companyId);
    }

    public void deleteUserByCustomer(Long customerId) {
        this.usersDao.deleteUserByCustomer (customerId);
    }

    private void userValidations(User user) throws ApplicationException {
        if (user == null) {
            throw new ApplicationException(ErrorType.NULL, "There is no user to add");
        }
        if (!MyUtils.isUsernameValid(user.getUserName())) {
            throw new ApplicationException(ErrorType.INVALID_EMAIL, "The e-mail is not valid");
        }
        if (user.getPassword().equals("")) {
            throw new ApplicationException(ErrorType.NULL, "An empty password");
        }
        if (user.getPassword().length() < 8) {
            throw new ApplicationException(ErrorType.INVALID_PASSWORD, "The password is too short, password must be between 8-20 characters and contain a number");
        }
        if (user.getPassword().length() > 20) {
            throw new ApplicationException(ErrorType.INVALID_PASSWORD, "The password is too long, password must be between 8-20 characters and contain a number");
        }
        if (user.getUserName().length() < 2) {
            throw new ApplicationException(ErrorType.INVALID_USERNAME, "The username is too short");
        }
        if (user.getUserName().length() > 20) {
            throw new ApplicationException(ErrorType.INVALID_USERNAME, "The username is too long");
        }
        if (this.usersDao.isUserNameExists(user.getUserName())) {
            throw new ApplicationException(ErrorType.USERNAME_EXISTS, "Can't create user, the username already exists");
        }
    }

}
