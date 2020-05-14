package com.galina.coupons.dao;

import com.galina.coupons.beans.Customer;
import com.galina.coupons.enums.ErrorType;
import com.galina.coupons.enums.UserType;
import com.galina.coupons.myutils.ApplicationException;
import com.galina.coupons.myutils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomersDao {

    public void addCustomer(Customer customer) {
        System.out.println("Customer has been successfully added to DB");
    }

    public Customer[] getAllCustomers() {
        Customer customer1 = new Customer("Peter", "peter@peter.com", "123456789");
        Customer customer2 = new Customer("Anna", "anna@anna.com", "223456789");
        Customer customer3 = new Customer("Julia", "julia@julia.com", "323456789");

        Customer[] customers = new Customer[3];
        customers[0] = customer1;
        customers[1] = customer2;
        customers[2] = customer3;

        return customers;
    }

    public boolean isCustomerEmailExists(String customerEmail) {
        return false;
    }

    public void updateCustomer(Customer customer) {
        System.out.println("Customer has been successfully updated");
    }

    public void deleteCustomer(Long customerId) {
        System.out.println("Customer has been successfully deleted from th DB");
    }

    public Customer getCustomer(long customerId) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "SELECT * FROM customers JOIN users ON customers.id = users.id WHERE customerId = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setLong(1, customerId);

            //Executing the update
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "Cannot retrieve information");
            }
//            creating customer
            Customer customer = new Customer();
            customer.setId(resultSet.getLong("id"));
            customer.getUser().setUserName(resultSet.getString("username"));
            customer.getUser().setPassword(resultSet.getString("password"));
            customer.getUser().setType(UserType.CUSTOMER);
            customer.setCustomerName(resultSet.getString("customer_name"));
            customer.setCustomerEmail(resultSet.getString("customer_email"));
            customer.setCustomerPhone(resultSet.getString("customer_phone"));

            return customer;

        } catch (Exception e) {
            //			e.printStackTrace();
            throw new Exception("Failed to retrieve data", e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }
}

