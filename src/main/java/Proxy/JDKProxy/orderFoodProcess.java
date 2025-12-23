package Proxy.JDKProxy;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/2314:41
 */

public class orderFoodProcess implements orderFood{

    @Override
    public void openTheApp() {
        System.out.println("opened the app");
    }

    @Override
    public void chooseTheFood() {
        System.out.println("is choosing the food...");
    }

    @Override
    public void pay() {
        System.out.println("complete payment");
    }
}
