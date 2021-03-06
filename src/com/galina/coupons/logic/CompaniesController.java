package com.galina.coupons.logic;

import com.galina.coupons.beans.Company;
import com.galina.coupons.dao.CompaniesDao;
import com.galina.coupons.enums.ErrorType;
import com.galina.coupons.myutils.ApplicationException;
import com.galina.coupons.myutils.MyUtils;

public class CompaniesController {
    private CompaniesDao companiesDao;
    private PurchasesController purchasesController;
    private CouponsController couponsController;
    private UsersController usersController;

    public CompaniesController() {
        this.companiesDao = new CompaniesDao();
        this.purchasesController = new PurchasesController();
        this.couponsController = new CouponsController();
        this.usersController = new UsersController();
    }


    public void addCompany(Company company) throws Exception {
        companyValidations(company);
        this.companiesDao.addCompany(company);
    }

    public void updateCompany(Company company) throws Exception {
        companyValidations(company);
        this.companiesDao.updateCompany(company);
    }

    public void deleteCompany(Company company) throws Exception {
        companyValidations(company);
        this.purchasesController.deletePurchasesByCompany(company.getCompanyId());
        this.couponsController.deleteCouponsByCompany(company.getCompanyId());
        this.usersController.deleteUsersByCompany(company.getCompanyId());
        this.companiesDao.deleteCompany(company.getCompanyId());
    }

    private void companyValidations(Company company) throws Exception {
        if (company == null) {
            throw new ApplicationException(ErrorType.NULL, "A null company");
        }
//        if (!MyUtils.isNameValid(company.getCompanyName())) {
//            throw new ApplicationException(ErrorType.INVALID_EMAIL, "The name is not valid");
//        }
        if (this.companiesDao.isCompanyNameExists(company.getCompanyName())) {
            throw new ApplicationException(ErrorType.COMPANY_EXISTS, "Company with such name or e-mail already exists");
        }
        if (!MyUtils.isEmailValid(company.getCompanyEmail())) {
            throw new ApplicationException(ErrorType.INVALID_COMPANY_NAME, "Company name must be between 2-100 characters and contain letters only");
        }
        if (this.companiesDao.isCompanyEmailExists(company.getCompanyEmail())) {
            throw new ApplicationException(ErrorType.COMPANY_EXISTS, "Company with such name or e-mail already exists");
        }
        if (company.getCompanyName().length() < 2) {
            throw new ApplicationException(ErrorType.INVALID_COMPANY_NAME, "The company name is too short, company name must be between 2-100 characters and contain letters only");
        }
        if (company.getCompanyName().length() > 100) {
            throw new ApplicationException(ErrorType.INVALID_COMPANY_NAME, "The company name is too long, company name must be between 2-100 characters and contain letters only");
        }
        if (company.getCompanyPhone().length() < 7) {
            throw new ApplicationException(ErrorType.INVALID_PHONE_NUMBER, "The company phone is too short");
        }
        if (company.getCompanyPhone().length() > 12) {
            throw new ApplicationException(ErrorType.INVALID_PHONE_NUMBER, "The company phone is too long");
        }
    }

}
