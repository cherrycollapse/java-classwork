package step.learning.anno;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@DemoClass
public class HomeworkDemo {

    //  Домашнее задание: в классах ClassWithAnnotation, ClassWithoutAnnotation
    //  реализовать конструкторы, которые задают полям случайные значения.
    //  В сканнирующем цикле проверить: если у поля есть аннотация, то нужно вывести
    //        его (поля) значение
    //          - если объекта нет - создать новый (если у полей вообще нет аннотаций, то
    //              объект и не будет создан)
    //          - если есть (ранее создан) - использовать его

    void scanFields(Object obj) {
        Class<?> type;
        if (obj instanceof Class<?>) {
            type = (Class<?>) obj;
        } else {
            type = obj.getClass();
        }

        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(FieldAnnotation.class)) {
                try {
                    System.out.printf("Field '%s' of class '%s' is '%s' with value '%s'%n", field.getName(), obj.getClass(), field.getType(), field.get(obj));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    return;
                }
            } else {
                System.out.printf("Field '%s' of class '%s' has no annotations %n", field.getName(), type.getName());
            }
        }
    }


    @EntryPoint
    public void run() {
        Object test = new ClassWithAnnotation();
        scanFields(test);

        System.out.println();
        Class<?> type2;
        try {
            type2 = Class.forName("step.learning.anno.ClassWithoutAnnotation");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found: " + ex.getMessage());
            return;
        }
        scanFields(type2);
    }
}
