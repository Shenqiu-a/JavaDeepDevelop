package Annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/11/2411:37
 */

@Aspect
public class AnnotationBusinessLogic {

    @Around(value = "@annotation(myAnnotation)", argNames = "joinPoint,myAnnotation")
    public Object businessLogic(ProceedingJoinPoint joinPoint,
                                MyAnnotation myAnnotation) throws Throwable {
        // 可通过 myAnnotation.id() 和 myAnnotation.name() 获取注解属性
        System.out.println("正在进行处理...");
        return joinPoint.proceed();
    }
}
