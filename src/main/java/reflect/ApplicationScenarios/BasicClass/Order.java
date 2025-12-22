package reflect.ApplicationScenarios.BasicClass;

import reflect.ApplicationScenarios.Annation.Autowired;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/410:57
 */

public class Order {
    private Customer customer;
    private Address address;

    public Order() {

    }
    @Autowired
    public Order(Customer customer, Address address) {
        this.customer = customer;
        this.address = address;
    }

}
