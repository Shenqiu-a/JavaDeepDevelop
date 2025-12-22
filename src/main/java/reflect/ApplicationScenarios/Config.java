package reflect.ApplicationScenarios;

import reflect.ApplicationScenarios.Annation.Bean;
import reflect.ApplicationScenarios.BasicClass.Address;
import reflect.ApplicationScenarios.BasicClass.Customer;
import reflect.ApplicationScenarios.BasicClass.Message;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/410:54
 */

public class Config {
    @Bean
    public Customer customer() {
        return new Customer("Brain", "Brain@eamil.com");
    }

    @Bean
    public Address address() {
        return new Address("east street", "273540");
    }

    public Message message() {
        return new Message();
    }
}
