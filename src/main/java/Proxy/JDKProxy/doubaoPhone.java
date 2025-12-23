package Proxy.JDKProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/2314:46
 */

public class doubaoPhone {
    public orderFood orderFood(orderFoodProcess  orderProcess) {
        orderFood order = (orderFood) Proxy.newProxyInstance(
                doubaoPhone.class.getClassLoader(),
                new Class[]{orderFood.class},
                new InvocationHandler() {
                    /// proxy:代理对象
                    /// method:被代理的方法
                    /// args:方法参数数组
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 用反射调用方法
                        if ("openTheApp".equals(method.getName())){
                            System.out.print("doubao phone has ");
                        } else if ("chooseTheFood".equals(method.getName())) {
                            System.out.print("doubao phone ");
                        } else if ("pay".equals(method.getName())) {
                            System.out.print("doubao phone ");
                        }
                        return method.invoke(orderProcess, args);
                    }
                });
        return order;
    }
}
