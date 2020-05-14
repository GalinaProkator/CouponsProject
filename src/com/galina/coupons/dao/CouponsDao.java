package com.galina.coupons.dao;

import com.galina.coupons.beans.Coupon;
import com.galina.coupons.enums.ErrorType;
import com.galina.coupons.myutils.ApplicationException;
import com.galina.coupons.myutils.JdbcUtils;

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


}
//    public Coupon[] getAllCoupons() {
//        LocalDate startDate1 = LocalDate.of(2020, 1, 1);
//        LocalDate endDate1 = LocalDate.of(2020, 3, 23);
//        LocalDate startDate2 = LocalDate.of(2020, 1, 1);
//        LocalDate endDate2 = LocalDate.of(2020, 23, 12);
//        LocalDate startDate3 = LocalDate.of(2020, 1, 1);
//        LocalDate endDate3 = LocalDate.of(2020, 31, 12);
//
//        Coupon coupon1 = new Coupon(1l,
//                "Breakfast for two",
//                CouponCategory.HORECA,
//                "so tasty",
//                startDate1,
//                endDate1,
//                3234567899l,
//                123,
//                "123456789");
//        Coupon coupon2 = new Coupon(1l, "Elvis never left", CouponCategory.ENTERTAINMENT,
//                "so good", startDate2, endDate2, 99994999999l, 444, "123456e789");
//        Coupon coupon3 = new Coupon(1l, "Buy a friend", CouponCategory.GOODS,
//                "robots are the future", startDate3, endDate3, 99999999959l, 888, "1234567fdg89");
//
//        Coupon[] coupons = new Coupon[3];
//        coupons[0] = coupon1;
//        coupons[1] = coupon2;
//        coupons[2] = coupon3;
//
//        return coupons;
//    }
//
//
