package com.galina.coupons.dao;

import com.galina.coupons.beans.Customer;

public class CustomersDao {

    public void addCustomer (Customer customer){
        System.out.println("Customer has been successfully added to DB");
    }

    public Customer[] getAllCustomers(){
        Customer customer1 = new Customer("Peter", "peter@peter.com", "123456789");
        Customer customer2 = new Customer("Anna", "anna@anna.com", "223456789");
        Customer customer3 = new Customer("Julia", "julia@julia.com", "323456789");

        Customer[] customers = new Customer[3] ;
        customers[0] = customer1;
        customers[1] = customer2;
        customers[2] = customer3;

        return customers;
    }

    public boolean isCustomerEmailExists (String customerEmail){
        return false;
    }

    public void updateCustomer(Customer customer) {
        System.out.println("Customer has been successfully updated");
    }

    public void deleteCustomer(Long customerId) {
        System.out.println("Customer has been successfully deleted from th DB");
    }
}
