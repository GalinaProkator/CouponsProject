package tester;

import com.galina.coupons.beans.Company;
import com.galina.coupons.logic.CompaniesController;

public class Tester {

    public static void main (String [] args) throws Exception {
//        UsersController usersController = new UsersController();
//        User user = new User ("Danny@danny.com", "1234567890123456", UserType.CUSTOMER, null);
//        usersController.addUser(user);
//
        CompaniesController companiesController = new CompaniesController();
        Company company = new Company ("BearBear", "bear@bear.com", "456462829", null);
        companiesController.addCompany(company);

        Company company1 = new Company (1l, "Bear Company", "bear@bearcompany.com", "459862829", null);
        companiesController.updateCompany(company1);
//
//        CustomersController customersController = new CustomersController();
//        Customer customer = new Customer ("Love", "love@love.com", "823456789");
//        customersController.addCustomer(customer);
//
//        LocalDate timestamp = LocalDate.of(2020,1,1);
//        PurchasesController purchasesController = new PurchasesController();
//        Purchase purchase = new Purchase(5l,6l,8l, timestamp);
//        purchasesController.addPurchase(purchase);



    }

}
