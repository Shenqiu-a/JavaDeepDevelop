package Annotation;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/2515:32
 */

public class UseAnnotation {
    @MyAnnotation(id = 1, name = "getAll")
    public void getAll(){
        System.out.println("This is a test Method");
    }
}
