package Proxy.CgLibProxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/2315:41
 */

public class OrderWithDoubaoPhone {
    public static void main(String[] args) {
        /// 用 CgLib 代理，不需要接口，使用的是实现 MethodInterceptor 接口
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OrderFood.class);
        enhancer.setCallback(new doubaoPhone());
        OrderFood order = (OrderFood) enhancer.create();
        order.openTheApp();
        order.chooseTheFood();
        order.pay();
    }
}
