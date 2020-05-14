package com.galina.coupons.dao;

import com.galina.coupons.beans.Company;
import com.galina.coupons.enums.ErrorType;
import com.galina.coupons.myutils.ApplicationException;
import com.galina.coupons.myutils.JdbcUtils;

import java.sql.*;

public class CompaniesDao {

    public long addCompany(Company company) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            //CompanyID is defined as a primary key and auto incremented
            String sqlStatement = "INSERT INTO companies (company_name, company_email, company_phone, company_address) VALUES(?,?,?,?)";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setString(1, company.getCompanyName());
            preparedStatement.setString(2, company.getCompanyEmail());
            preparedStatement.setString(3, company.getCompanyPhone());
            preparedStatement.setString(4, company.getCompanyAddress());

            //Executing the update
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (!resultSet.next()) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "Invalid company key during creation");
            }
            System.out.println("Company has been successfully added to DB");
            return resultSet.getLong(1);

        } catch (Exception e) {
            //			e.printStackTrace();
            //If there was an exception in the "try" block above, it is caught here and notifies a level above.
            //			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, DateUtils.getCurrentDateAndTime()
            //					+" Create company failed");
            throw new Exception("Failed to create company " + company.toString(), e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public void updateCompany(Company company) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "UPDATE companies SET company_name = ?, company_email = ?, company_phone = ?, company_address = ?  WHERE id = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setString(1, company.getCompanyName());
            preparedStatement.setString(2, company.getCompanyEmail());
            preparedStatement.setString(3, company.getCompanyPhone());
            preparedStatement.setString(4, company.getCompanyAddress());
            preparedStatement.setLong(5, company.getCompanyId());

            //Executing the update
            int result = preparedStatement.executeUpdate();

            if (result != 1) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "Failed to update the company");
            }
            System.out.println("Company has been successfully updated");

        } catch (Exception e) {
            //			e.printStackTrace();
            throw new Exception("Failed to update company " + company.toString(), e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    public boolean isCompanyNameExists(String companyName) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "SELECT company_name FROM companies WHERE company_name = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setString(1, companyName);

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

    public boolean isCompanyEmailExists(String companyEmail) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "SELECT company_email FROM companies WHERE company_email = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setString(1, companyEmail);

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

    public Company[] getAllCompanies() throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "SELECT * FROM companies";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Executing the update
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "Cannot retrieve information");
            }
//getting the number of rows returned to create an array of companies
            int numberOfRows = getRowCount(resultSet);
            if (numberOfRows == 0){
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "0 companies in the table");
            }
//            creating an array of companies
            Company[] companies = new Company[numberOfRows];
            int i = 0;
            while (resultSet.next()){
                companies[i].setCompanyId(resultSet.getLong("id"));
                companies[i].setCompanyName(resultSet.getString("company_name"));
                companies[i].setCompanyEmail(resultSet.getString("company_email"));
                companies[i].setCompanyPhone(resultSet.getString("company_phone"));
                companies[i].setCompanyAddress(resultSet.getString("company_address"));
                i++;
            }

            return companies;

        } catch (Exception e) {
            //			e.printStackTrace();
            throw new Exception("Failed to retrieve data", e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }

    private int getRowCount(ResultSet resultSet) {
        if (resultSet == null) {
            return 0;
        }

        try {
            resultSet.last();
            return resultSet.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.beforeFirst();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }



    public void deleteCompany(Long companyId) throws Exception {
        //Turn on the connections
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Establish a connection from the connection manager
            connection = JdbcUtils.getConnection();

            //Creating the SQL query
            String sqlStatement = "DELETE FROM companies WHERE company_id = ?";

            //Combining between the syntax and our connection
            preparedStatement = connection.prepareStatement(sqlStatement);

            //Replacing the question marks in the statement above with the relevant data
            preparedStatement.setLong(1, companyId);

            //Executing the update
            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                throw new ApplicationException(ErrorType.GENERAL_ERROR, "Failed to delete company");
            }
            System.out.println(result + " Company has been successfully deleted from DB");

        } catch (Exception e) {
            throw new Exception("Failed to delete company " + companyId, e);
        } finally {
            //Closing the resources
            JdbcUtils.closeResources(connection, preparedStatement);
        }
    }
}
