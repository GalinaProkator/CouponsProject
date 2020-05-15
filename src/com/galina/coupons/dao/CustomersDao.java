package com.galina.coupons.dao;

import com.galina.coupons.beans.Customer;
import com.galina.coupons.enums.ErrorType;
import com.galina.coupons.enums.UserType;
import com.galina.coupons.myutils.ApplicationException;
import com.galina.coupons.myutils.JdbcUtils;
import com.galina.coupons.myutils.MyUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomersDao {

    public long addCustomer(Customer customer) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            //CompanyID is defined as a primary key and auto incremented
            String sqlStatement = "INSERT INTO customers (customer_name, customer_email, customer_phone) VALUES(?,?,?)";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, customer.getCustomerEmail());
            preparedStatement.setString(3, customer.getCustomerPhone());

            //Executing the update
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (!resultSet.next()) {
                throw new ApplicationException(ErrorType.FAILED_CREATE_COMPANY, "Invalid customer key during creation");
            }
            System.out.println("Customer has been successfully added to DB");
            return resultSet.getLong(1);

        } catch (Exception e) {
            //			e.printStackTrace();
            //If there was an exception in the "try" block above, it is caught here and notifies a level above.
            //			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, DateUtils.getCurrentDateAndTime()
            //					+" Create company failed");
            throw new Exception("Failed to create company " + customer.toString(), e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public void updateCustomer(Customer customer) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "UPDATE customers " +
                    "SET customer_name = ?, customer_email = ?, customer_phone = ?  " +
                    "WHERE id = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, customer.getCustomerEmail());
            preparedStatement.setString(3, customer.getCustomerPhone());
            preparedStatement.setLong(5, customer.getId());

            //Executing the update
            int result = preparedStatement.executeUpdate();

            if (result != 1) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "Failed to update the customer");
            }
            System.out.println("Customer has been successfully updated");

        } catch (Exception e) {
            //			e.printStackTrace();
            throw new Exception("Failed to update customer " + customer.toString(), e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public boolean isCustomerEmailExists(String customerEmail) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "SELECT customer_email FROM customers WHERE customer_email = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setString(1, customerEmail);

            //Executing the update
            ResultSet result = preparedStatement.executeQuery();

            if (result == null) {
                return false;
            } else return true;

        } catch (Exception e) {
            throw new Exception("Query failed", e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public Customer[] getAllCustomers() throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "SELECT * FROM customers JOIN users " +
            "ON customers.id = users.id ";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Executing the update
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "Cannot retrieve information");
            }
//getting the number of rows returned to create an array of companies
            int numberOfRows = MyUtils.getRowCount(resultSet);
            if (numberOfRows == 0) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "0 customers in the table");
            }
//            creating an array of customers
            Customer[] customers = new Customer[numberOfRows];
            int i = 0;
            while (resultSet.next()) {
                customers[i].setId(resultSet.getLong("id"));
                customers[i].getUser().setUserName(resultSet.getString("username"));
                customers[i].getUser().setPassword(resultSet.getString("password"));
                customers[i].getUser().setType(UserType.CUSTOMER);
                customers[i].setCustomerName(resultSet.getString("customer_name"));
                customers[i].setCustomerEmail(resultSet.getString("customer_email"));
                customers[i].setCustomerPhone(resultSet.getString("customer_phone"));
                i++;
            }

            return customers;

        } catch (Exception e) {
            //			e.printStackTrace();
            throw new Exception("Failed to retrieve data", e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public Customer getCustomer(long customerId) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "SELECT * FROM customers JOIN users " +
                    "ON customers.id = users.id " +
                    "WHERE customerId = ?";

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

    public void deleteCustomer(Long customerId) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "DELETE FROM customers WHERE id = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setLong(1, customerId);

            //Executing the update
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new ApplicationException(ErrorType.FAILED_DELETE_CUSTOMER, "Failed to delete customer");
            }
            System.out.println(result + " Company has been successfully deleted from DB");

        } catch (Exception e) {
            throw new Exception("Failed to delete customer " + customerId, e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


}

