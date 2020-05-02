package com.galina.coupons.dao;

import com.galina.coupons.beans.Company;
import com.galina.coupons.enums.ErrorType;
import com.galina.coupons.myutils.ApplicationException;
import com.galina.coupons.myutils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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

//    public void addCompany (Company company){
//        System.out.println("Company has been successfully added to DB");
//    }

    public Company[] getAllCompanies() {
        Company company1 = new Company("Coca-Cola", "cocacola@cocacola.com", "3333333", null);
        Company company2 = new Company("Unilever", "unilever@unilever.com", "62375276572", null);
        Company company3 = new Company("Nike", "nike@nike.com", "6343323", null);

        Company[] companies = new Company[3];
        companies[0] = company1;
        companies[1] = company2;
        companies[2] = company3;

        return companies;
    }

    public boolean isCompanyNameExists(String companyName) {
        return false;
    }

    public boolean isCompanyEmailExists(String companyEmail) {
        return false;
    }


    public void updateCompany(Company company) {
        System.out.println("Company has been successfully updated");
    }

    public void deleteCompany(Long companyId) {
        System.out.println("Company has been successfully deleted from the DB");
    }
}
