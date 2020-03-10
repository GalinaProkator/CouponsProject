package com.galina.coupons.logic;

import com.galina.coupons.beans.Company;
import com.galina.coupons.dao.CompaniesDao;
import com.galina.coupons.myutils.MyUtils;

import java.util.regex.Pattern;

public class CompaniesController {
    private CompaniesDao companiesDao;

    public CompaniesController(){
        this.companiesDao = new CompaniesDao();
    }

    public void addCompany (Company company){
        //        Validations
        if(company == null){

            System.out.println("A null company");
            return;
        }
        if(company.getCompanyName().length() < 2){
            System.out.println("The company name is too short");
            return;
        }
        if(company.getCompanyName().length() > 100){
            System.out.println("The company name is too long");
            return;
        }
        if (this.companiesDao.isCompanyEmailExists(company.getCompanyEmail())){
            System.out.println("Can't create company, the email already exists");
            return;
        }
        MyUtils myUtils = new MyUtils();
        if (!myUtils.isEmailValid(company.getCompanyEmail())){
            System.out.println("The e-mail is not valid");
            return;
        }
        if(company.getCompanyPhone().length() < 7){
            System.out.println("The company phone is too short");
            return;
        }
        if(company.getCompanyPhone().length() > 15){
            System.out.println("The company phone is too long");
            return;
        }
        if (this.companiesDao.isCompanyNameExists(company.getCompanyName())){
            System.out.println("Can't create company, the company already exists");
            return;
        }
        this.companiesDao.addCompany(company);

    }

    public boolean isEmailValid(String email) {
        boolean isEmailValid = Pattern.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", email);
        if (!isEmailValid) {
            return false;
        }
        return true;
    }
}
