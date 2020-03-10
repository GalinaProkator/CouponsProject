package com.galina.coupons.dao;

import com.galina.coupons.beans.Company;

import java.util.regex.Pattern;

public class CompaniesDao {
    public void addCompany (Company company){
        System.out.println("Company has been successfully added to DB");
    }

    public Company[] getAllCompanies(){
        Company company1 = new Company("Coca-Cola", "cocacola@cocacola.com", "3333333", null);
        Company company2 = new Company("Unilever", "unilever@unilever.com", "62375276572", null);
        Company company3 = new Company("Nike", "nike@nike.com", "6343323", null);

        Company[] companies = new Company[3] ;
        companies[0] = company1;
        companies[1] = company2;
        companies[2] = company3;

        return companies;
    }

    public boolean isCompanyNameExists (String companyName){
        return false;
    }

    public boolean isCompanyEmailExists (String companyEmail){
        return false;
    }


}
