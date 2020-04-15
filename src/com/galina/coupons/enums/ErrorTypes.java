package com.galina.coupons.enums;

public enum ErrorTypes {
    GENERAL_ERROR (601, "GENERAL_ERROR", "General Error", true),
    INVALID_USERNAME (602, "INVALID_USERNAME", "The username already exists", false),
    INVALID_COMPANY (603, "INVALID_COMPANY", "Company doesn't exists", false),
    INVALID_COUPON (604, "INVALID_COUPON", "Coupon doesn't exists", false),
    INVALID_CUSTOMER (605, "INVALID_CUSTOMER", "Customer doesn't exists", false),
    INVALID_PASSWORD (606, "INVALID_PASSWORD", "Password must be between 8-16 characters and contain a number", false),
    INVALID_AMOUNT_OF_ITEMS (607, "INVALID_AMOUNT_OF_ITEMS", "Amount must be more than 0", false),
    INVALID_COUPON_NAME (608, "INVALID_COUPON_NAME", "The coupon name already exists", false),
    INVALID_PRICE (609, "INVALID_PRICE", "The price must be more than 0", false),
    CUSTOMER_CREATION_FAILED (610, "CUSTOMER_CREATION_FAILED", "Failed to create customer", true),
    INVALID_USERTYPE (611, "INVALID_USERTYPE", "Invalid type of user", false),
    INVALID_USER_ID (612, "INVALID_USER_ID", "User ID and customer ID doesn't match", false),
    INVALID_START_DATE (613, "INVALID_START_DATE", "Start date has already passed", false),
    INVALID_END_DATE (614, "INVALID_END_DATE", "End date cannot be before the start date", false),
    MISSING_COMPANY_ID (615, "MISSING_COMPANY_ID", "Company ID must be specified for a company user", false),
    FAILED_TO_GENERATE_ID (616, "FAILED_TO_GENERATE_ID", "Failed to generate ID", true),
    INVALID_LOGIN_DETAILS (617, "INVALID_LOGIN_DETAILS", "Invalid username or password", false);

    private int errorNumber;
    private String errorName;
    private String errorMessage;
    private boolean isShowStackTrace;

    ErrorTypes(int errorNumber, String errorName, String errorMessage, boolean isShowStackTrace) {
        this.errorNumber = errorNumber;
        this.errorName = errorName;
        this.errorMessage = errorMessage;
        this.isShowStackTrace = isShowStackTrace;
    }

    public int getErrorNumber() {
        return errorNumber;
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
