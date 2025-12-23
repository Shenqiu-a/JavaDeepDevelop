package Proxy.JDKProxy;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/2314:53
 */

public class OrderWithDoubaoPhone {
    public static void main(String[] args) {
        /// 用豆包手机点外卖 （豆包手机代理）
        doubaoPhone phone = new doubaoPhone();
        orderFood order = phone.orderFood(new orderFoodProcess());
        order.openTheApp();
        order.chooseTheFood();
        order.pay();
    }
}
