package com.galina.coupons.logic;

import com.galina.coupons.beans.Customer;
import com.galina.coupons.dao.CustomersDao;
import com.galina.coupons.myutils.MyUtils;

import java.util.regex.Pattern;

public class CustomersController {
    private CustomersDao customersDao;

    public CustomersController(){
        this.customersDao = new CustomersDao();
    }

    public void addCustomer (Customer customer){
        //        Validations
        if(customer == null){

            System.out.println("A null customer");
            return;
        }
        if(customer.getCustomerName().length() < 2){
            System.out.println("The customer name is too short");
            return;
        }
        if(customer.getCustomerName().length() > 100){
            System.out.println("The customer name is too long");
            return;
        }
        if (this.customersDao.isCustomerEmailExists(customer.getCustomerEmail())){
            System.out.println("Can't create customer, the email already exists");
            return;
        }
        MyUtils myUtils = new MyUtils();
        if (!myUtils.isEmailValid(customer.getCustomerEmail())){
            System.out.println("The e-mail is not valid");
            return;
        }
        if(customer.getCustomerPhone().length() < 7){
            System.out.println("The customer phone is too short");
            return;
        }
        if(customer.getCustomerPhone().length() > 15){
            System.out.println("The customer phone is too long");
            return;
        }

        this.customersDao.addCustomer(customer);

    }


}
