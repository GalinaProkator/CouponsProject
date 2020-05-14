package com.galina.coupons.logic;

import com.galina.coupons.beans.Coupon;
import com.galina.coupons.dao.CouponsDao;
import com.galina.coupons.enums.ErrorType;
import com.galina.coupons.myutils.ApplicationException;

import java.util.Calendar;

public class CouponsController {
    private CouponsDao couponsDao;
    private PurchasesController purchasesController;

    public CouponsController(){
        this.couponsDao = new CouponsDao();
        this.purchasesController = new PurchasesController();
    }

    public void addCoupon (Coupon coupon) throws Exception {
        couponValidations (coupon);
        this.couponsDao.addCoupon(coupon);
    }

    public void updateCoupon (Coupon coupon) throws Exception {
        couponValidations (coupon);
        this.couponsDao.updateCoupon(coupon);
    }

    public void deleteCoupon(Long couponId) throws Exception {
        this.purchasesController.deletePurchasesByCoupon(couponId);
        this.couponsDao.deleteCoupon(couponId);
    }

    public void deleteCouponsByCompany(Long companyId) throws Exception {
        this.couponsDao.deleteCouponsByCompany(companyId);
    }

    private void couponValidations(Coupon coupon) throws Exception {
        if(coupon == null){
            throw new ApplicationException(ErrorType.NULL, "A null coupon");
        }
        if (this.couponsDao.isCouponExists(coupon.getCouponTitle())){
            throw new ApplicationException(ErrorType.COUPON_EXISTS, "Can't create coupon, the coupon name already exists");
        }
        if(coupon.getCouponTitle().length() < 2){
            throw new ApplicationException(ErrorType.INVALID_COUPON_NAME, "The coupon name is too short");
        }
        if(coupon.getCouponTitle().length() > 100){
            throw new ApplicationException(ErrorType.INVALID_COUPON_NAME, "The coupon name is too long");
        }
        if(coupon.getDescription().length() < 10){
            throw new ApplicationException(ErrorType.INVALID_DESCRIPTION, "The coupon description is too short");
        }
        if(coupon.getDescription().length() > 800){
            throw new ApplicationException(ErrorType.INVALID_DESCRIPTION, "The coupon description is too long");
        }
        if(coupon.getPrice() < 0){
            throw new ApplicationException(ErrorType.INVALID_PRICE, "The coupon price must be 0 or more");
        }
        if(!compareTwoDates(coupon.getStartDate(), Calendar. getInstance(). getTime())){
            throw new ApplicationException(ErrorType.INVALID_START_DATE, "Start date has already passed");
        }
        if(!compareTwoDates(coupon.getStartDate(), coupon.getEndDate())){
            throw new ApplicationException(ErrorType.INVALID_END_DATE, "End date cannot be before the start date");
        }
    }

    public boolean compareTwoDates (java.util.Date date1, java.util.Date date2){
        if (date1.compareTo(date2) > 0){
            return false; //date1 occurs after date2
        }
        else{
            return true; // date1 occurs before date2 or on the same day
        }
    }
}
