package reflect.ApplicationScenarios;

import reflect.ApplicationScenarios.Annation.Autowired;
import reflect.ApplicationScenarios.Annation.Bean;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/410:56
 */

public class Container {
    private Map<Class<?>, Method> map;   // 储存的是 加@Bean注解的方法

    private Map<Class<?>, Object> instanceMap;  // 实现单例

    private Object config;

    public void init() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        this.map = new HashMap<>();
        this.instanceMap = new HashMap<>();
        Class<?> aClass = Class.forName("reflect.ApplicationScenarios.Config");
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getAnnotation(Bean.class) != null)
                this.map.put(method.getReturnType(), method);  // 拿到所有生成对象的方法
        }
        this.config = aClass.getConstructor().newInstance(); // 获得可以调用 map 集合方法生成实例的对象
    }

    public Object getServiceInstanceByClass(Class<?> clazz) throws InvocationTargetException, IllegalAccessException {
        if (this.map.containsKey(clazz)) {
            if (!this.instanceMap.containsKey(clazz)) {
                Method method = this.map.get(clazz);
                Object instance = method.invoke(this.config);
                this.instanceMap.put(clazz, instance);
            }
            return this.instanceMap.get(clazz);
        }
        return null;
    }

    public Object createInstance(Class<?> clazz) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            if (constructor.getAnnotation(Autowired.class) != null) {
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Object[] argument = new Object[parameterTypes.length];
                for (int i = 0; i < argument.length; i++) {
                    argument[i] = getServiceInstanceByClass(parameterTypes[i]);
                }
                return constructor.newInstance(argument);
            }
        }
        return clazz.getConstructor().newInstance();
    }


}
