package StaticClass;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/239:16
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("=== test begin ===");

//        System.out.println("1. 不会触发静态内部类加载 : ");
//        staticClazz.StaticInnerClass.innerStaticMethod();

        System.out.println("2. 通过创建类的实例 : ");
        staticClazz.StaticInnerClass instance = new staticClazz.StaticInnerClass();
    }
}
