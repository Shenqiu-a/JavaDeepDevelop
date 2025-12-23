package Feign;

import feign.Feign;

import java.util.List;

/**
 * 功能：Feign相关代码仅用于展示以及查看源码
 *          不能运行，指定服务未启动也并未配置
 * 作者：yml
 * 日期：2025/12/2216:16
 */

public class Main {
    public static void main(String[] args) {

        myFeign myFeign = Feign.builder().target(myFeign.class, "http://localhost:8080/");

        // 查看目标类
        System.out.println("apiType is : " + myFeign.class);
        System.out.println("apiType is : " + myFeign.getClass());

        List<String> names = myFeign.getAll("yml");
        for (int i = 0; i < names.toArray().length; i++) {
            System.out.println("names[" + i  + "] is "+ names.get(i));
        }
    }
}
