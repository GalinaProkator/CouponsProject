package com.galina.coupons.dao;

import com.galina.coupons.beans.Coupon;
import com.galina.coupons.beans.CouponConciseFormat;
import com.galina.coupons.enums.CouponCategory;
import com.galina.coupons.enums.ErrorType;
import com.galina.coupons.myutils.ApplicationException;
import com.galina.coupons.myutils.JdbcUtils;
import com.galina.coupons.myutils.MyUtils;

import java.sql.*;

public class CouponsDao {
    //    public void addCoupon(Coupon coupon) {
//        System.out.println("Coupon has been successfully added to DB");
//    }
    public long addCoupon(Coupon coupon) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            //CompanyID is defined as a primary key and auto incremented
            String sqlStatement = "INSERT INTO coupons (company_id, coupon_title, category, description, start_date, end_date, amount, price, image) VALUES(?,?,?,?,?,?,?,?,?)";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setLong(1, coupon.getCompanyId());
            preparedStatement.setString(2, coupon.getCouponTitle());
            preparedStatement.setString(3, coupon.getCategory().getName());
            preparedStatement.setString(4, coupon.getDescription());
            preparedStatement.setDate(5, (Date) coupon.getStartDate());
            preparedStatement.setDate(6, (Date) coupon.getEndDate());
            preparedStatement.setLong(7, coupon.getAmount());
            preparedStatement.setInt(8, coupon.getPrice());
            preparedStatement.setString(9, coupon.getImage());


            //Executing the update
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (!resultSet.next()) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "Invalid coupon key during creation");
            }
            System.out.println("Coupon has been successfully added to DB");
            return resultSet.getLong(1);

        } catch (Exception e) {
            //			e.printStackTrace();
            //If there was an exception in the "try" block above, it is caught here and notifies a level above.
            //			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, DateUtils.getCurrentDateAndTime()
            //					+" Create company failed");
            throw new Exception("Failed to create coupon " + coupon.toString(), e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public void updateCoupon(Coupon coupon) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "UPDATE coupons SET coupon_title = ?, category = ?, description = ?, " +
                    "start_date = ?, end_date = ?, amount = ?, price = ?, image = ?  WHERE id = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setString(1, coupon.getCouponTitle());
            preparedStatement.setString(2, coupon.getCategory().getName());
            preparedStatement.setString(3, coupon.getDescription());
            preparedStatement.setDate(4, (Date) coupon.getStartDate());
            preparedStatement.setDate(5, (Date) coupon.getEndDate());
            preparedStatement.setLong(6, coupon.getAmount());
            preparedStatement.setInt(7, coupon.getPrice());
            preparedStatement.setString(8, coupon.getImage());

            //Executing the update
            int result = preparedStatement.executeUpdate();

            if (result != 1) {
                throw new ApplicationException(ErrorType.FAILED_CREATE_COUPON, "Failed to update the coupon");
            }
            System.out.println("Coupon has been successfully updated");

        } catch (Exception e) {
            //			e.printStackTrace();
            throw new Exception("Failed to update coupon " + coupon.toString(), e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public boolean isCouponExists(String couponTitle) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "SELECT coupon_title FROM coupons WHERE coupon_title = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setString(1, couponTitle);

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

    public void deleteCouponsByCompany(Long companyId) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "DELETE FROM coupons WHERE company_id = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setLong(1, companyId);

            //Executing the update
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new ApplicationException(ErrorType.FAILED_DELETE_COUPON, "Failed to delete coupons");
            }
            System.out.println(result + " Coupons have been successfully deleted from DB");

        } catch (Exception e) {
            throw new Exception("Failed to delete coupons of the company " + companyId, e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public void deleteCoupon(Long couponId) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "DELETE FROM coupons WHERE id = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setLong(1, couponId);

            //Executing the update
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new ApplicationException(ErrorType.FAILED_DELETE_COUPON, "Failed to delete coupon");
            }
            System.out.println(result + " Coupon has been successfully deleted from DB");

        } catch (Exception e) {
            throw new Exception("Failed to delete coupon " + couponId, e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


    public CouponConciseFormat[] getAllCoupons() throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "SELECT id, company_id, coupon_title, price FROM coupons";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Executing the update
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "Cannot retrieve information");
            }
//              getting the number of rows returned to create an array of coupons
            int numberOfRows = MyUtils.getRowCount(resultSet);
            if (numberOfRows == 0) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "0 coupons in the table");
            }
//            creating an array of coupons
            CouponConciseFormat[] coupons = new CouponConciseFormat[numberOfRows];
            int i = 0;
            while (resultSet.next()) {
                coupons[i].setId(resultSet.getLong("id"));
                coupons[i].setCompanyId(resultSet.getLong("company_id"));
                coupons[i].setCouponTitle(resultSet.getString("coupon_title"));
                coupons[i].setPrice(resultSet.getInt("price"));
                i++;
            }

            return coupons;

        } catch (Exception e) {
            //			e.printStackTrace();
            throw new Exception("Failed to retrieve data", e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public Coupon getCoupon(Long couponId) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "SELECT * FROM coupons WHERE id = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setLong(1, couponId);

            //Executing the update
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "Cannot retrieve information");
            }
//            creating a coupon
            Coupon coupon = new Coupon();
            coupon.setId(resultSet.getLong("id"));
            coupon.setCompanyId(resultSet.getLong("company_id"));
            coupon.setCouponTitle(resultSet.getString("coupon_title"));
            coupon.setCategory(CouponCategory.valueOf(resultSet.getString("category")));
            coupon.setDescription(resultSet.getString("description"));
            coupon.setStartDate(resultSet.getTimestamp("start_date"));
            coupon.setEndDate(resultSet.getTimestamp("end_date"));
            coupon.setAmount(resultSet.getLong("amount"));
            coupon.setPrice(resultSet.getInt("price"));
            coupon.setImage(resultSet.getString("image"));

            return coupon;

        } catch (Exception e) {
            //			e.printStackTrace();
            throw new Exception("Failed to retrieve data", e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public CouponConciseFormat[] getCouponsByMaxPrice(int price) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "SELECT id, company_id, coupon_title, price FROM coupons WHERE price <= ? ORDER BY price";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setLong(1, price);

            //Executing the update
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "Cannot retrieve information");
            }
//              getting the number of rows returned to create an array of coupons
            int numberOfRows = MyUtils.getRowCount(resultSet);
            if (numberOfRows == 0) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "0 coupons in the table");
            }
//            creating an array of coupons
            CouponConciseFormat[] coupons = new CouponConciseFormat[numberOfRows];
            int i = 0;
            while (resultSet.next()) {
                coupons[i].setId(resultSet.getLong("id"));
                coupons[i].setCompanyId(resultSet.getLong("company_id"));
                coupons[i].setCouponTitle(resultSet.getString("coupon_title"));
                coupons[i].setPrice(resultSet.getInt("price"));
                i++;
            }

            return coupons;

        } catch (Exception e) {
            //			e.printStackTrace();
            throw new Exception("Failed to retrieve data", e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public CouponConciseFormat[] getCouponsByCustomer(long customerId) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "SELECT id, company_id, coupon_title, price " +
                    "FROM coupons WHERE id IN (SELECT coupon_id FROM purchases WHERE customer_id = ?) " +
                    "ORDER BY company_id";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setLong(1, customerId);

            //Executing the update
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "Cannot retrieve information");
            }
//              getting the number of rows returned to create an array of coupons
            int numberOfRows = MyUtils.getRowCount(resultSet);
            if (numberOfRows == 0) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "0 coupons in the table");
            }
//            creating an array of coupons
            CouponConciseFormat[] coupons = new CouponConciseFormat[numberOfRows];
            int i = 0;
            while (resultSet.next()) {
                coupons[i].setId(resultSet.getLong("id"));
                coupons[i].setCompanyId(resultSet.getLong("company_id"));
                coupons[i].setCouponTitle(resultSet.getString("coupon_title"));
                coupons[i].setPrice(resultSet.getInt("price"));
                i++;
            }

            return coupons;

        } catch (Exception e) {
            //			e.printStackTrace();
            throw new Exception("Failed to retrieve data", e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public void removeOldCoupons(java.util.Date today) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "DELETE FROM coupons WHERE end_date < ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setDate(1, (Date) today);

            //Executing the update
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new ApplicationException(ErrorType.FAILED_DELETE_COUPON, "Failed to delete coupons");
            }
            System.out.println(result + " Coupons have been successfully deleted from DB");

        } catch (Exception e) {
            throw new Exception("Failed to delete coupons before with due date before" + today, e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }


}
