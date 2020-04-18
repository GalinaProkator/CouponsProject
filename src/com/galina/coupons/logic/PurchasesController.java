package com.galina.coupons.logic;

import com.galina.coupons.beans.Purchase;
import com.galina.coupons.dao.PurchasesDao;

public class PurchasesController {
    private PurchasesDao purchasesDao;

    public PurchasesController() {
        this.purchasesDao = new PurchasesDao();
    }

    public void addPurchase (Purchase purchase) throws Exception {
        purchaseValidations (purchase);
        this.purchasesDao.addPurchase(purchase);
    }

    private void purchaseValidations(Purchase purchase) throws Exception {
        if(purchase == null){
            throw new Exception("A null purchase");
        }
        if(purchase.getCompanyId() == null){
            throw new Exception("A null company ID");
        }
        if(purchase.getCustomerId() == null){
            throw new Exception("A null customer ID");
        }
        if(purchase.getAmount() == null){
            throw new Exception("Amount must be more than 0");
        }
        if(purchase.getAmount() <= 0){
            throw new Exception("Amount must be more than 0");
        }
        if(purchase.gettimestamp() == null){
            throw new Exception("A null timestamp");
        }
    }
}
