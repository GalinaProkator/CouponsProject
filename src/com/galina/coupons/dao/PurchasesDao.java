package com.galina.coupons.dao;

import com.galina.coupons.beans.Purchase;

import java.time.LocalDate;

public class PurchasesDao {
    public void addPurchase (Purchase purchase){
        System.out.println("Purchase has been successfully added to DB");
    }

    public Purchase[] getAllPurchases(){
        LocalDate timestamp1 = LocalDate.of(2020,1,1);
        LocalDate timestamp2 = LocalDate.of(2020,3,23);
        LocalDate timestamp3 = LocalDate.of(2020,1,1);

        Purchase purchase1 = new Purchase(2l, 3l, 99999999l, timestamp1);
        Purchase purchase2 = new Purchase(3l, 4l, 99999999l, timestamp2);
        Purchase purchase3 = new Purchase(4l, 2l, 99999999l, timestamp3);

        Purchase[] purchases = new Purchase[3];
        purchases[0] = purchase1;
        purchases[1] = purchase2;
        purchases[2] = purchase3;

        return purchases;
    }

    public boolean isPurchaseExists (Long customerId, Long companyId){
        return false;
    }

    public void deletePurchasesByCompany(Long companyId) {
        System.out.println("Purchases have been successfully deleted from DB");
    }

    public void deletePurchasesByCoupon(Long couponId) {
        System.out.println("Purchases have been successfully deleted from DB");
    }

    public void deletePurchasesByUser(Long userId) {
        System.out.println("Purchases have been successfully deleted from DB");
    }

    public void deletePurchase(Long customerId, Long companyId) {
        System.out.println("Purchase has been successfully deleted from DB");
    }

    public void updatePurchase(Purchase purchase) {
        System.out.println("Purchase has been successfully updated");
    }
}
