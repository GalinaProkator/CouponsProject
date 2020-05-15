package com.galina.coupons.dao;

import com.galina.coupons.beans.Purchase;
import com.galina.coupons.enums.ErrorType;
import com.galina.coupons.myutils.ApplicationException;
import com.galina.coupons.myutils.JdbcUtils;
import com.galina.coupons.myutils.MyUtils;

import java.sql.*;
import java.util.Calendar;

public class PurchasesDao {

    public long addPurchase(Purchase purchase) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            //CompanyID is defined as a primary key and auto incremented
            String sqlStatement = "INSERT INTO purchases (customer_id, coupon_id, amount, timestamp) VALUES(?,?,?,?)";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setLong(1, purchase.getCustomerId());
            preparedStatement.setLong(2, purchase.getCouponId());
            preparedStatement.setLong(3, purchase.getAmount());
            preparedStatement.setDate(4, (Date) Calendar.getInstance().getTime());

            //Executing the update
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (!resultSet.next()) {
                throw new ApplicationException(ErrorType.FAILED_CREATE_PURCHASE, "Invalid purchase key during creation");
            }
            System.out.println("Purchase has been successfully added to DB");
            return resultSet.getLong(1);

        } catch (Exception e) {
            //			e.printStackTrace();
            //If there was an exception in the "try" block above, it is caught here and notifies a level above.
            //			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, DateUtils.getCurrentDateAndTime()
            //					+" Create company failed");
            throw new Exception("Failed to create purchase " + purchase.toString(), e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public Purchase[] getAllPurchases() throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "SELECT * FROM purchases";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Executing the update
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "Cannot retrieve information");
            }
//getting the number of rows returned to create an array of purchases
            int numberOfRows = MyUtils.getRowCount(resultSet);
            if (numberOfRows == 0) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "0 purchases in the table");
            }
//            creating an array of purchases
            Purchase[] purchases = new Purchase[numberOfRows];
            int i = 0;
            while (resultSet.next()) {
                purchases[i].setId(resultSet.getLong("id"));
                purchases[i].setCustomerId(resultSet.getLong("customer_id"));
                purchases[i].setCouponId(resultSet.getLong("coupon_id"));
                purchases[i].setAmount(resultSet.getLong("amount"));
                purchases[i].setTimestamp(resultSet.getDate("timestamp"));
                i++;
            }

            return purchases;

        } catch (Exception e) {
            //			e.printStackTrace();
            throw new Exception("Failed to retrieve data", e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public Purchase getPurchase(long customerId, long couponId, java.util.Date timestamp) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "SELECT * FROM purchases";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Executing the update
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "Cannot retrieve information");
            }
//getting the number of rows returned to create an array of purchases
            int numberOfRows = MyUtils.getRowCount(resultSet);
            if (numberOfRows == 0) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "0 purchases in the table");
            }
//            creating an array of purchases
            Purchase[] purchases = new Purchase[numberOfRows];
            int i = 0;
            while (resultSet.next()) {
                purchases[i].setId(resultSet.getLong("id"));
                purchases[i].setCustomerId(resultSet.getLong("customer_id"));
                purchases[i].setCouponId(resultSet.getLong("coupon_id"));
                purchases[i].setAmount(resultSet.getLong("amount"));
                purchases[i].setTimestamp(resultSet.getDate("timestamp"));
                i++;
            }

            return purchases;

        } catch (Exception e) {
            //			e.printStackTrace();
            throw new Exception("Failed to retrieve data", e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

//    public void deletePurchasesByCompany(Long companyId) {
//        System.out.println("Purchases have been successfully deleted from DB");
//    }
//
//    public void deletePurchasesByCoupon(Long couponId) {
//        System.out.println("Purchases have been successfully deleted from DB");
//    }
//
//    public void deletePurchasesByUser(Long userId) {
//        System.out.println("Purchases have been successfully deleted from DB");
//    }
//
//    public void deletePurchase(Long customerId, Long companyId) {
//        System.out.println("Purchase has been successfully deleted from DB");
//    }
//

}
