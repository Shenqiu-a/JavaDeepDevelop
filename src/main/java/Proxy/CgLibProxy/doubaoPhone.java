package Proxy.CgLibProxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/2315:35
 */

public class doubaoPhone implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        System.out.println("==========================");
        if ("openTheApp".equals(method.getName())){
            System.out.print("doubao phone has ");
            result = methodProxy.invokeSuper(o, objects);
            System.out.println("open the app completed");
            System.out.println("==========================");
        } else if ("chooseTheFood".equals(method.getName())) {
            System.out.print("doubao phone ");
            result = methodProxy.invokeSuper(o, objects);
            System.out.println("choose the food completed");
            System.out.println("==========================");
        } else if ("pay".equals(method.getName())) {
            System.out.print("doubao phone ");
            result = methodProxy.invokeSuper(o, objects);
            System.out.println("pay completed");
            System.out.println("==========================");
        }
        return result;
    }
}
