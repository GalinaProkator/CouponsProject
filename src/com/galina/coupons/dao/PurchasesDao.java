package com.galina.coupons.dao;

import com.galina.coupons.beans.Purchase;
import com.galina.coupons.enums.ErrorType;
import com.galina.coupons.myutils.ApplicationException;
import com.galina.coupons.myutils.JdbcUtils;
import com.galina.coupons.myutils.MyUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
            java.sql.Date sqlNow = MyUtils.convertUtilToSql(Calendar.getInstance().getTime());
            preparedStatement.setLong(1, purchase.getCustomerId());
            preparedStatement.setLong(2, purchase.getCouponId());
            preparedStatement.setLong(3, purchase.getAmount());
            preparedStatement.setDate(4, sqlNow);

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

    public Purchase getPurchase(long purchaseId) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "SELECT * FROM purchases WHERE id = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setLong(1, purchaseId);

            //Executing the update
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "Cannot retrieve information");
            }

            Purchase purchase = new Purchase();
            purchase.setId(resultSet.getLong("id"));
            purchase.setCustomerId(resultSet.getLong("customer_id"));
            purchase.setCouponId(resultSet.getLong("coupon_id"));
            purchase.setAmount(resultSet.getLong("amount"));
            purchase.setTimestamp(resultSet.getTimestamp("timestamp"));

            return purchase;

        } catch (Exception e) {
            //			e.printStackTrace();
            throw new Exception("Failed to retrieve data", e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public void deletePurchasesByCompany(Long companyId) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "DELETE FROM purchases WHERE company_id = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setLong(1, companyId);

            //Executing the update
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new ApplicationException(ErrorType.FAILED_DELETE_PURCHASE, "Failed to delete purchases");
            }
            System.out.println(result + " Purchases has been successfully deleted from DB");

        } catch (Exception e) {
            throw new Exception("Failed to delete coupon purchases of the company " + companyId, e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public void deletePurchasesByCoupon(Long couponId) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "DELETE FROM purchases WHERE coupon_id = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setLong(1, couponId);

            //Executing the update
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new ApplicationException(ErrorType.FAILED_DELETE_PURCHASE, "Failed to delete purchases");
            }
            System.out.println(result + " Purchases has been successfully deleted from DB");

        } catch (Exception e) {
            throw new Exception("Failed to delete purchases of the coupon " + couponId, e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public void deletePurchasesByUser(Long userId) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "DELETE FROM purchases WHERE customer_id = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setLong(1, userId);

            //Executing the update
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new ApplicationException(ErrorType.FAILED_DELETE_PURCHASE, "Failed to delete purchases");
            }
            System.out.println(result + " Purchases has been successfully deleted from DB");

        } catch (Exception e) {
            throw new Exception("Failed to delete purchases of the user " + userId, e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public void deletePurchase(Long purchaseId) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "DELETE FROM purchases WHERE id = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setLong(1, purchaseId);

            //Executing the update
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new ApplicationException(ErrorType.FAILED_DELETE_PURCHASE, "Failed to delete purchase");
            }
            System.out.println(result + " Purchase has been successfully deleted from DB");

        } catch (Exception e) {
            throw new Exception("Failed to delete purchase " + purchaseId, e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

}
