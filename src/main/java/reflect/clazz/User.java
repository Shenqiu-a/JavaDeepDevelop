package reflect.clazz;

import java.util.List;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/310:31
 */

public class User extends Person {
    @MyAnnotation
    public String name;
    private final int age;
    private boolean sex;

    private List<String> comments;
    private static String privateStaticField;
    public static final String publicStaticField = "中国";

    static {
        System.out.println("UserClass is initialized");
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(){
        this.age = 30;
    }

    public void myPublicMethod(){
        System.out.println("This is a public method");
    }

    private void myPrivateMethod(){
        System.out.println("This is a private method");
    }

    private void myPrivateMethod(String  name, int age){
        System.out.println("This is a private method with parameters. " + name + " " + age);
    }

    public static void myPublicStaticMethod(){
        System.out.println("This is a Public Static Method");
    }

    private static void myPrivateStaticMethod(){
        System.out.println("This is a Private Static Method");
    }

    private static void myPrivateStaticMethod(String name){
        System.out.println("This is a Private Static Method with parameters. " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public static String getCompany() {
        return privateStaticField;
    }

    public static void setCompany(String privateStaticField) {
        User.privateStaticField = privateStaticField;
    }
}
