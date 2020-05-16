package tester;

import com.galina.coupons.myutils.TimerTaskDeleteOldCoupons;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Tester {

    public static void main (String [] args) throws Exception {
//        UsersController usersController = new UsersController();
//        User user = new User ("Danny@danny.com", "1234567890123456", UserType.CUSTOMER, null);
//        usersController.addUser(user);
//
//        CompaniesController companiesController = new CompaniesController();
//        Company company = new Company ("BearBear", "bear@bear.com", "456462829", null);
//        companiesController.addCompany(company);
//
//        Company company1 = new Company (1l, "Bear Company", "bear@bearcompany.com", "459862829", null);
//        companiesController.updateCompany(company1);

//        scheduling delete old coupons
        TimerTask timerTaskDeleteOldCoupons = new TimerTaskDeleteOldCoupons();
        Timer timer = new Timer();

        long oncePerDay = 1000*60*60*24;

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 01);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        java.util.Date date = cal.getTime();

        timer.schedule(timerTaskDeleteOldCoupons, date, oncePerDay);



    }

}
