package com.galina.coupons.dao;

import com.galina.coupons.beans.PostLoginData;
import com.galina.coupons.beans.UserEntity;
import com.galina.coupons.enums.ErrorType;
import com.galina.coupons.enums.UserType;
import com.galina.coupons.myutils.ApplicationException;
import com.galina.coupons.myutils.JdbcUtils;
import com.galina.coupons.myutils.MyUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class UsersDao {
    public long addUser(String username, String hashedPassword, UserType type, Long company_id) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            //CompanyID is defined as a primary key and auto incremented
            String sqlStatement = "INSERT INTO users (username, password, type, company_id) VALUES(?,?,?,?)";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setString(3, String.valueOf(type));
            preparedStatement.setLong(4, company_id);

            //Executing the update
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (!resultSet.next()) {
                throw new ApplicationException(ErrorType.FAILED_CREATE_USER, "Invalid user key during creation");
            }
            System.out.println("User has been successfully added to DB");
            return resultSet.getLong(1);

        } catch (Exception e) {
            //			e.printStackTrace();
            //If there was an exception in the "try" block above, it is caught here and notifies a level above.
            //			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, DateUtils.getCurrentDateAndTime()
            //					+" Create company failed");
            throw new Exception("Failed to create user " + new UserEntity(username, hashedPassword, type, company_id).toString(), e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public void updateUser(String hashedPassword, long userId) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
//            a user can only change its password
            String sqlStatement = "UPDATE users SET password = ?  WHERE id = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setString(1, hashedPassword);
            preparedStatement.setLong(2, userId);

            //Executing the update
            int result = preparedStatement.executeUpdate();

            if (result != 1) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "Failed to update the user");
            }
            System.out.println("User has been successfully updated");

        } catch (Exception e) {
            //			e.printStackTrace();
            throw new Exception("Failed to update user ", e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public boolean isUserNameExists(String username) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "SELECT username FROM users WHERE username = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setString(1, username);

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

    public UserEntity[] getAllUsers() throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "SELECT * FROM users";

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
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "0 companies in the table");
            }
//            creating an array of companies
            UserEntity[] userEntities = new UserEntity[numberOfRows];
            int i = 0;
            while (resultSet.next()) {
                userEntities[i].setId(resultSet.getLong("id"));
                userEntities[i].setUserName(resultSet.getString("username"));
                userEntities[i].setPassword(resultSet.getString("password"));
                userEntities[i].setType(UserType.valueOf(resultSet.getString("type")));
                userEntities[i].setCompanyId(resultSet.getLong("company_id"));
                i++;
            }

            return userEntities;

        } catch (Exception e) {
            //			e.printStackTrace();
            throw new Exception("Failed to retrieve data", e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public UserEntity getUser(long userId) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "SELECT * FROM users WHERE id = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setLong(1, userId);

            //Executing the update
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "Cannot retrieve information");
            }
//            creating a user
            UserEntity userEntity = new UserEntity();
            userEntity.setId(resultSet.getLong("id"));
            userEntity.setUserName(resultSet.getString("username"));
            userEntity.setPassword(resultSet.getString("password"));
            userEntity.setType(UserType.valueOf(resultSet.getString("type")));
            userEntity.setCompanyId(resultSet.getLong("company_id"));

            return userEntity;

        } catch (Exception e) {
            //			e.printStackTrace();
            throw new Exception("Failed to retrieve data", e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public void deleteUsersByCompany(Long companyId) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "DELETE FROM users WHERE company_id = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setLong(1, companyId);

            //Executing the update
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new ApplicationException(ErrorType.FAILED_DELETE_USER, "Failed to delete users");
            }
            System.out.println(result + " Users have been successfully deleted from DB");

        } catch (Exception e) {
            throw new Exception("Failed to delete users of the company " + companyId, e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public void deleteUser(Long customerOrUserId) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "DELETE FROM users WHERE id = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setLong(1, customerOrUserId);

            //Executing the update
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new ApplicationException(ErrorType.FAILED_DELETE_USER, "Failed to delete user");
            }
            System.out.println(result + " User has  been successfully deleted from DB");

        } catch (Exception e) {
            throw new Exception("Failed to delete user " + customerOrUserId, e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public PostLoginData login(String username, String password) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "SELECT * FROM users WHERE username = ? AND password = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            //Executing the update
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new ApplicationException(ErrorType.INVALID_LOGIN_DETAILS, "Invalid username or password");
            }
//            creating a postLoginData
            PostLoginData postLoginData = new PostLoginData();
            postLoginData.setId(resultSet.getLong("id"));
            postLoginData.setUserType(UserType.valueOf(resultSet.getString("type")));
            postLoginData.setCompanyId(resultSet.getLong("company_id"));

            return postLoginData;


        } catch (Exception e) {
            throw new Exception("Query failed", e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

}

