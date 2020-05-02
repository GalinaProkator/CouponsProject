package com.galina.coupons.logic;

import com.galina.coupons.beans.Customer;
import com.galina.coupons.dao.CustomersDao;
import com.galina.coupons.enums.ErrorType;
import com.galina.coupons.myutils.ApplicationException;
import com.galina.coupons.myutils.MyUtils;

public class CustomersController {
    private CustomersDao customersDao;
    private PurchasesController purchasesController;
    private UsersController usersController;

    public CustomersController(){
        this.customersDao = new CustomersDao();
        this.purchasesController = new PurchasesController();
        this.usersController = new UsersController();
    }

    public void addCustomer (Customer customer) throws ApplicationException {
        customerValidations (customer);
        this.customersDao.addCustomer(customer);
    }

    public void updateCustomer (Customer customer) throws ApplicationException {
        customerValidations (customer);
        this.customersDao.updateCustomer(customer);
    }

    public void deleteCustomer(Long customerId) throws ApplicationException {
        this.purchasesController.deletePurchasesByCustomer(customerId);
        this.usersController.deleteUserByCustomer(customerId);
        this.customersDao.deleteCustomer(customerId);
    }

    private void customerValidations(Customer customer) throws ApplicationException {
        if(customer == null){
            throw new ApplicationException(ErrorType.NULL, "A null customer");
        }
        if (this.customersDao.isCustomerEmailExists(customer.getCustomerEmail())){
            throw new ApplicationException(ErrorType.CUSTOMER_EXISTS, "Can't create customer, the email already exists");
        }
        MyUtils myUtils = new MyUtils();
        if (!myUtils.isNameValid(customer.getCustomerName())) {
            throw new ApplicationException(ErrorType.INVALID_CUSTOMER_NAME, "Customer name must be between 2-50 characters and contain letters only");
        }
        if(customer.getCustomerName().length() < 2){
            throw new ApplicationException(ErrorType.INVALID_CUSTOMER_NAME, "The customer name is too short, customer name must be between 2-50 characters and contain letters only");
        }
        if(customer.getCustomerName().length() > 50){
            throw new ApplicationException(ErrorType.INVALID_CUSTOMER_NAME, "The customer name is too long, customer name must be between 2-50 characters and contain letters only");
        }
        if (!myUtils.isEmailValid(customer.getCustomerEmail())){
            throw new ApplicationException(ErrorType.INVALID_EMAIL,"The e-mail is not valid");
        }
        if(customer.getCustomerPhone().length() < 7){
            throw new ApplicationException(ErrorType.INVALID_PHONE_NUMBER,"The customer phone is too short");
        }
        if(customer.getCustomerPhone().length() > 15){
            throw new ApplicationException(ErrorType.INVALID_PHONE_NUMBER,"The customer phone is too long");
        }
    }
}
