package com.galina.coupons.logic;

import com.galina.coupons.beans.Customer;
import com.galina.coupons.dao.CustomersDao;
import com.galina.coupons.myutils.MyUtils;

public class CustomersController {
    private CustomersDao customersDao;

    public CustomersController(){
        this.customersDao = new CustomersDao();
    }

    public void addCustomer (Customer customer) throws Exception {
        customerValidations (customer);
        this.customersDao.addCustomer(customer);

    }

    private void customerValidations(Customer customer) throws Exception {
        if(customer == null){
            throw new Exception("A null customer");
        }
        if(customer.getCustomerName().length() < 2){
            throw new Exception("The customer name is too short");
        }
        if(customer.getCustomerName().length() > 100){
            throw new Exception("The customer name is too long");
        }
        if (this.customersDao.isCustomerEmailExists(customer.getCustomerEmail())){
            throw new Exception("Can't create customer, the email already exists");
        }
        MyUtils myUtils = new MyUtils();
        if (!myUtils.isEmailValid(customer.getCustomerEmail())){
            throw new Exception("The e-mail is not valid");
        }
        if(customer.getCustomerPhone().length() < 7){
            throw new Exception("The customer phone is too short");
        }
        if(customer.getCustomerPhone().length() > 15){
            throw new Exception("The customer phone is too long");
        }
    }


}
