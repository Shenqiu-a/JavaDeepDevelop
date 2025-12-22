package reflect.ApplicationScenarios.BasicClass;

import reflect.ApplicationScenarios.Annation.Printable;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/410:55
 */

public class Customer {
    private String name;
    private String email;


    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Customer() {
    }

    @Printable
    public void printEmail() {
        System.out.println(email);
    }

    public void printName() {
        System.out.println(name);
    }

}
