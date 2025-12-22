package reflect.ApplicationScenarios;

import reflect.ApplicationScenarios.Annation.Printable;
import reflect.ApplicationScenarios.BasicClass.Customer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/410:52
 */

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, NoSuchFieldException {
        Container container = new Container();
        container.init();

        String className = "reflect.ApplicationScenarios.BasicClass.Order";
        String fieldName = "customer";

        Class<?> clazz = Class.forName(className);
        Object instance = container.createInstance(clazz);
        Field filed = clazz.getDeclaredField(fieldName);
        filed.setAccessible(true);
        Object fieldValue = filed.get(instance);

        System.out.println(fieldValue);

        Method[] methods = fieldValue.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getAnnotation(Printable.class) != null) {
                System.out.println(method.getName());
                method.invoke(fieldValue);
            }
        }

        System.out.println(fieldValue == container.getServiceInstanceByClass(Customer.class)); // true
    }
}
