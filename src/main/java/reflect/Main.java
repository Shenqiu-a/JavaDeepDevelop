package reflect;

import reflect.ApplicationScenarios.Container;
import reflect.clazz.MyAnnotation;
import reflect.clazz.User;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 功能：反射 怎么获取类，访问类的成员，怎么调用/修改类的方法或属性
 * 作者：yml
 * 日期：2025/12/310:56
 */

public class Main {
    public static void main(String[] args) throws Exception {
        createClazzInstance();
    }


    /**
     * 1 访问一下 User这个类的一些属性和方法
     */
    public static void VisitUserClazz(){
        String publicStaticField = User.publicStaticField;
        System.out.println(publicStaticField);

        User.myPublicStaticMethod();

        User user = new User("Brain", 22);
        System.out.println(user.name);
        user.myPublicMethod();
    }

    /**
     * 2 获取类 class对象
     * @throws ClassNotFoundException
     */
    public static void GetClazz() throws ClassNotFoundException {
        /// 获取类 class对象
        // 在编译时就确定了User的类对象
        Class<User> userClass = User.class;  // 这种方式，不会立即触发类的初始化,只有在访问该类的静态成员或者实例化该类的时候才会触发

        // 在运行时从User实例中获取，User实例的具体类型，只能在运行时创建和确定，因此无法在编译时确定类型，所以采用通配符
        User user = new User("Brain", 22);
        Class<?> userClazz = user.getClass();  // 这种方式，是在运行时从User实例获取的

        // 通过类名获取 需要类的完全限定名 同样也是在运行时获取
        Class<?> clazz = Class.forName("reflect.clazz.User");
    }

    /**
     * 3 获取类中的属性   由于反射是在运行时动态执行的，所以无法在编译阶段进行错误的检测和捕获，只能在执行时被发现和处理，所以在获取成员时，需要处理异常
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     */
    public static void getClazzFields() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> userClazz = Class.forName("reflect.clazz.User");

        // 获取当前类的所有字段（不包含继承的）
        Field[] declaredFields = userClazz.getDeclaredFields();
        System.out.println("当前类的所有字段（不包含继承的）：");
        for (Field field : declaredFields) {
            System.out.println(field.getName());
        }

        // 获取当前类所有Public的字段（包括继承的）
        Field[] fields = userClazz.getFields();
        System.out.println("当前类所有Public的字段（包括继承的）:");
        for (Field field : fields) {
            System.out.println(field.getName());
        }

        Field[] declaredFields1 = userClazz.getSuperclass().getDeclaredFields();
        System.out.println("当前类所有继承的字段：");
        for (Field field : declaredFields1) {
            System.out.println(field.getName());
        }

        Constructor<?>[] declaredConstructors = userClazz.getDeclaredConstructors();  // 获取当前类所有构造方法
        Method[] declaredMethods = userClazz.getDeclaredMethods();  // 获取当前类所有方法
        Annotation[] declaredAnnotations = userClazz.getDeclaredAnnotations();  // 获取当前类所有注解

        System.out.println("====================================");
        Field field = userClazz.getField("name");
        System.out.println("某个字段（含有注解）:");
        System.out.println(field.getName());
        System.out.println(field.getType());
        System.out.println(field.getAnnotation(MyAnnotation.class));
        System.out.println("某个字段（不含注解）:");
        Field field2 = userClazz.getDeclaredField("age");
        System.out.println(field2.getName());
        System.out.println(field2.getType());
        System.out.println(field.getAnnotation(MyAnnotation.class));
        System.out.println("如果是泛型类型:");
        Field comments = userClazz.getDeclaredField("comments");
        System.out.println("name:" + comments.getName());
        System.out.println("getType:" + comments.getType());
        System.out.println("getGenericType:" + comments.getGenericType());
        System.out.println("====================================");

        Field publicStaticField = userClazz.getDeclaredField("publicStaticField");
        System.out.println("某个Public静态字段的值:" + publicStaticField.get(null));
        // 私有的没有权限，访问不到
        Field privateStaticField = userClazz.getDeclaredField("privateStaticField");
        privateStaticField.setAccessible(true);  // 通过setAccessible(true) 允许访问
        privateStaticField.set(null, "中国");  // 也可以设置字段的值
        System.out.println("某个Private静态字段的值:" + privateStaticField.get(null));

        // 获取的字段不存在 会抛出NoSuchFieldException
        Field nameNotExit = userClazz.getField("nameNotExit");
    }

    /**
     * 4 获取类中的方法  执行
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void getClazzMethods() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User("Brain", 22);
        Class<?> userClazz = user.getClass();

        Method[] declaredMethods = userClazz.getDeclaredMethods();
        System.out.println("当前类所有方法：");
        for (Method method : declaredMethods) {
            System.out.println(method.getName());
        }
        System.out.println("====================================");
        Method myPrivateMethod = userClazz.getDeclaredMethod("myPrivateStaticMethod");
        myPrivateMethod.setAccessible(true);
        myPrivateMethod.invoke(null);
        System.out.println("====================================");
        Method myPrivateMethod1 = userClazz.getDeclaredMethod("myPrivateMethod", String.class, int.class);
        myPrivateMethod1.setAccessible(true);
        myPrivateMethod1.invoke(new User(), "Brains", 23);
        System.out.println("====================================");

    }

    /**
     * 5 创建类实例
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static void createClazzInstance() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException {
        Container container = new Container();
        container.init();
        /// 由于泛型，无法在编译时确定类型，只能运行时确定，因此无法在编译时创建实例，只能使用Class.forName()方法获取类，然后使用newInstance()方法创建实例
        /// 包括使用 userClazz 进行cast转换类型，也要使用User.class这种方式获取类,否则会报错
        Class<User> userClazz = User.class;
        Constructor<User> declaredConstructor = userClazz.getDeclaredConstructor(String.class,int.class);
        System.out.println("============ 这一段可省略 ============");
        Constructor<User> defaultConstructor = userClazz.getDeclaredConstructor();
        Class<?>[] parameterTypes = defaultConstructor.getParameterTypes();
        System.out.println("Constructor ParameterTypes : ");
        Object[] arguments = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++){
            arguments[i] = container.getServiceInstanceByClass(parameterTypes[i]);
            System.out.println(parameterTypes[i]);
        }
        User user3 = defaultConstructor.newInstance(arguments);
        System.out.println("user3 : " + user3);
        System.out.println("====================================");
        User brains = declaredConstructor.newInstance("Brains", 24);
        Object obj2 = declaredConstructor.newInstance("Brains", 28);
        User user2 = userClazz.cast(obj2);
        System.out.println(brains);
        System.out.println("====================================");
        /// 使用Class.forName()方法获取类，然后使用newInstance()方法创建实例
        Class<?> user = Class.forName("reflect.clazz.User");
        Constructor<?> constructor = user.getDeclaredConstructor();
        Object obj = constructor.newInstance();
        // 强转 反射存在的意义在于，在运行时，根据类名获取类，然后创建实例，但是这样会损失类型检查，因此，如果编译时已经明确了类型，那其实就不必依赖于反射
        if (obj instanceof User) {
            User user1 = (User) obj;
        }
        // 获取字段 获取字段值 设置字段值
        Field field = user.getDeclaredField("name");
        Field age = user.getDeclaredField("age");
        age.setAccessible(true);
        age.set(brains, 30);
        System.out.println(field.get(brains));
        System.out.println(age.get(brains));
        System.out.println("====================================");
        // 获取方法 调用
        Method myPublicMethod = user.getDeclaredMethod("myPublicMethod");
        myPublicMethod.invoke(brains);
        System.out.println("====================================");
        Method myPrivateMethod = user.getDeclaredMethod("myPrivateMethod", String.class, int.class);
        myPrivateMethod.setAccessible(true);
        myPrivateMethod.invoke(brains, "Brains", 23);
        System.out.println("====================================");
    }
}
