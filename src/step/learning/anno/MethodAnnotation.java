package step.learning.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) // аннотация для методов
public @interface MethodAnnotation {
    String value() default "" ; // аннотация со значением, value - по умолчанию

}
