package com.galina.coupons.myutils;

import com.galina.coupons.dao.CouponsDao;

import java.util.Calendar;
import java.util.TimerTask;

public class TimerTaskDeleteOldCoupons extends TimerTask {

    CouponsDao couponsDao = new CouponsDao();

    @Override
    public void run() {
        java.util.Date today = Calendar.getInstance().getTime();
        try {
            couponsDao.removeOldCoupons(today);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
