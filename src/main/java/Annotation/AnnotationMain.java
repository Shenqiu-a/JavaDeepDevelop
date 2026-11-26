package Annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

public class AnnotationMain {

    @Configuration
    @EnableAspectJAutoProxy
    static class AopConfig {
    }

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(
                AopConfig.class, UseAnnotation.class, AnnotationBusinessLogic.class)) {
            UseAnnotation useAnnotation = context.getBean(UseAnnotation.class);
            useAnnotation.getAll();
        }
    }
}
