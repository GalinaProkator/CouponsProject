package com.galina.coupons.logic;

import com.galina.coupons.beans.Purchase;
import com.galina.coupons.dao.PurchasesDao;
import com.galina.coupons.enums.ErrorType;
import com.galina.coupons.myutils.ApplicationException;

public class PurchasesController {
    private PurchasesDao purchasesDao;

    public PurchasesController() {
        this.purchasesDao = new PurchasesDao();
    }

    public void addPurchase (Purchase purchase) throws Exception {
        purchaseValidations (purchase);
        this.purchasesDao.addPurchase(purchase);
    }

    public void deletePurchasesByCompany(Long companyId) throws Exception {
        this.purchasesDao.deletePurchasesByCompany(companyId);
    }

    public void deletePurchasesByCoupon(Long couponId) throws Exception {
        this.purchasesDao.deletePurchasesByCoupon(couponId);
    }

    public void deletePurchasesByCustomer(Long customerId) throws Exception {
        this.purchasesDao.deletePurchasesByUser(customerId);
    }

    public void deletePurchase(Long purchaseId) throws Exception {
        this.purchasesDao.deletePurchase(purchaseId);
    }


    private void purchaseValidations(Purchase purchase) throws ApplicationException {
        if(purchase == null){
            throw new ApplicationException(ErrorType.NULL, "A null purchase");
        }
        if(purchase.getCouponId() == null){
            throw new ApplicationException(ErrorType.NULL, "A null company ID");
        }
        if(purchase.getCustomerId() == null){
            throw new ApplicationException(ErrorType.NULL, "A null customer ID");
        }
        if(purchase.getAmount() == null){
            throw new ApplicationException(ErrorType.INVALID_AMOUNT_OF_ITEMS, "Amount must be more than 0");
        }
        if(purchase.getAmount() <= 0){
            throw new ApplicationException(ErrorType.INVALID_AMOUNT_OF_ITEMS,"Amount must be more than 0");
        }
    }



}
