package Proxy.CgLibProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/2316:02
 */

public class AnotherWay {
    public static void main(String[] args) {
        System.out.println("==========================");
        System.out.println("This is another way to use cglib proxy : ");
        System.out.println("==========================");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OrderFood.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.print("doubao phone ");
                Object result = methodProxy.invokeSuper(o, objects);
                System.out.println("==========================");
                return result;
            }
        });
        OrderFood proxy = (OrderFood) enhancer.create();
        proxy.openTheApp();
        proxy.chooseTheFood();
        proxy.pay();
    }
}
