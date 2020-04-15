package com.galina.coupons.logic;

import com.galina.coupons.beans.Company;
import com.galina.coupons.dao.CompaniesDao;
import com.galina.coupons.myutils.MyUtils;

public class CompaniesController {
    private CompaniesDao companiesDao;

    public CompaniesController(){
        this.companiesDao = new CompaniesDao();
    }

    public void addCompany (Company company) throws Exception {
        companyValidations (company);
        this.companiesDao.addCompany(company);

    }

    private void companyValidations(Company company) throws Exception {
        if(company == null){
            throw new Exception("A null company");
        }
        if(company.getCompanyName().length() < 2){
            throw new Exception("The company name is too short");
        }
        if(company.getCompanyName().length() > 100){
            throw new Exception("The company name is too long");
        }
        if (this.companiesDao.isCompanyEmailExists(company.getCompanyEmail())){
            throw new Exception("Can't create company, the email already exists");
        }
        MyUtils myUtils = new MyUtils();
        if (!myUtils.isEmailValid(company.getCompanyEmail())){
            throw new Exception("The e-mail is not valid");
        }
        if(company.getCompanyPhone().length() < 7){
            throw new Exception("The company phone is too short");
        }
        if(company.getCompanyPhone().length() > 15){
            throw new Exception("The company phone is too long");
        }
        if (this.companiesDao.isCompanyNameExists(company.getCompanyName())){
            throw new Exception("Can't create company, the company already exists");
        }
    }

}
