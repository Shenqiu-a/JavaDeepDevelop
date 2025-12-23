package StaticClass;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/239:25
 */

public class staticClazz {

    static {
        System.out.println("static block is running...");
    }

    static class StaticInnerClass{

        static {
            System.out.println("static class`s inner static block is running...");
        }

        public static void innerStaticMethod(){
            System.out.println("static class`s inner static method is running...");
        }

        public void innerMethod(){
            System.out.println("static class`s inner method is running...");
        }
    }
}
