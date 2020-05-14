package com.galina.coupons.enums;

public enum ErrorType {
    //    General
    GENERAL_ERROR(601, "GENERAL_ERROR", "General Error, please contact admin", true),
    NULL(602, "NULL_INPUT", "No input given", true),

    INVALID_EMAIL(603, "INVALID_EMAIL", "E-mail is not valid", false),
    INVALID_PHONE_NUMBER(604, "INVALID_PHONE_NUMBER", "Phone number must be between 7-12 figures", false),

    //    User errors
    INVALID_USER(610, "INVALID_USER", "User doesn't exists", false),
    USERNAME_EXISTS(611, "USERNAME_EXISTS", "Username already exists", false),
    INVALID_USERNAME(612, "INVALID_USERNAME", "Username must be between 2-20 characters and contain letters only", false),
    INVALID_PASSWORD(613, "INVALID_PASSWORD", "Password must be between 8-20 characters and contain a number", false),
    INVALID_LOGIN_DETAILS(614, "INVALID_LOGIN_DETAILS", "Invalid username or password", false),
    INVALID_USER_TYPE(615, "INVALID_USER_TYPE", "Invalid type of user", false),

    //    Company errors
    INVALID_COMPANY(620, "INVALID_COMPANY", "Company doesn't exists", false),
    COMPANY_EXISTS(611, "COMPANY_EXISTS", "Company with such name or e-mail already exists", false),
    INVALID_COMPANY_NAME(621, "INVALID_COMPANY_NAME", "Company name must be between 2-100 characters and contain letters only", false),

    //    Coupon errors
    INVALID_COUPON(630, "INVALID_COUPON", "Coupon doesn't exists", false),
    COUPON_EXISTS(631, "COUPON_EXISTS", "The coupon name already exists", false),
    INVALID_COUPON_NAME(632, "INVALID_COUPON_NAME", "The coupon name must be 2-100 characters and contain letters only", false),
    INVALID_DESCRIPTION(633, "INVALID_DESCRIPTION", "The description must be 10-800 characters", false),
    INVALID_PRICE(634, "INVALID_PRICE", "The price must be 0 or more", false),
    INVALID_START_DATE(635, "INVALID_START_DATE", "Start date has already passed", false),
    INVALID_END_DATE(636, "INVALID_END_DATE", "End date cannot be before the start date", false),
    INVALID_COUPON_CATEGORY(637, "INVALID_COUPON_CATEGORY", "Invalid coupon category", false),

    //    Customer errors
    INVALID_CUSTOMER(640, "INVALID_CUSTOMER", "Customer doesn't exists", false),
    CUSTOMER_EXISTS(641, "CUSTOMER_EXISTS", "The customer e-mail already exists", false),
    INVALID_CUSTOMER_NAME(642, "INVALID_CUSTOMER_NAME", "Customer name must be between 2-50 characters and contain letters only", false),

    //    Purchase errors
    INVALID_AMOUNT_OF_ITEMS(650, "INVALID_AMOUNT_OF_ITEMS", "Amount must be more than 0", false),
    INVALID_COUPON_ID(651, "INVALID_COUPON_ID", "Invalid coupon ID", false),
    INVALID_COMPANY_ID(652, "INVALID_COMPANY_ID", "Invalid company ID", false),

    //    Create errors
    FAILED_CREATE_USER(661, "FAILED_GENERATE_USER", "Failed to create user", true),
    FAILED_CREATE_CUSTOMER(662, "FAILED_CREATE_CUSTOMER", "Failed to create customer", true),
    FAILED_CREATE_COMPANY(663, "FAILED_CREATE_COMPANY", "Failed to create company", true),
    FAILED_CREATE_COUPON(664, "FAILED_CREATE_COUPON", "Failed to create coupon", true),
    FAILED_CREATE_PURCHASE(665, "FAILED_CREATE_PURCHASE", "Failed to create purchase", true),

    //    Delete errors
    FAILED_DELETE_USER(670, "FAILED_DELETE_USER", "Failed to delete user", true),
    FAILED_DELETE_CUSTOMER(671, "FAILED_DELETE_CUSTOMER", "Failed to delete customer", true),
    FAILED_DELETE_COMPANY(672, "FAILED_DELETE_COMPANY", "Failed to delete company", true),
    FAILED_DELETE_COUPON(673, "FAILED_DELETE_COUPON", "Failed to delete coupon(s)", true),
    FAILED_DELETE_PURCHASE(674, "FAILED_DELETE_PURCHASE", "Failed to delete purchase(s)", true);

    private int errorCode;
    private String errorName;
    private String errorMessage;
    private boolean isShowStackTrace;

    private ErrorType(int errorCode, String errorName, String errorMessage, boolean isShowStackTrace) {
        this.errorCode = errorCode;
        this.errorName = errorName;
        this.errorMessage = errorMessage;
        this.isShowStackTrace = isShowStackTrace;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorName() {
        return errorName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isShowStackTrace() {
        return isShowStackTrace;
    }
}
