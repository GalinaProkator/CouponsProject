package tester;
import com.galina.coupons.beans.Company;
import com.galina.coupons.beans.Customer;
import com.galina.coupons.beans.User;
import com.galina.coupons.enums.UserType;
import com.galina.coupons.logic.CompaniesController;
import com.galina.coupons.logic.CustomersController;
import com.galina.coupons.logic.UsersController;

public class Tester {

    public static void main (String [] args){
        UsersController usersController = new UsersController();
        User user = new User ("Danny@danny.com", "1234567890123456", UserType.CUSTOMER, null);
        usersController.addUser(user);

        CompaniesController companiesController = new CompaniesController();
        Company company = new Company ("Husky", "husky@husky.com", "5566778", null);
        companiesController.addCompany(company);

        CustomersController customersController = new CustomersController();
        Customer customer = new Customer ("Love", "love@love.com", "823456789");
        customersController.addCustomer(customer);
}

}