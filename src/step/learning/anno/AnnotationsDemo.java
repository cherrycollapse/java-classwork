package step.learning.anno;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@DemoClass
public class AnnotationsDemo {

    @FieldAnnotation(value = "For all versions", priority =-1)
    private String separator = "------------------------------------------------------------";

    @MethodAnnotation( "Entry Point" )
    @EntryPoint
    public void run() {
        // извлечь аннотацию - получить информацию о типе
        Class<?> type = ClassWithAnnotation.class ; // по классу
        Class<?> thisType = this.getClass() ; // по обьекту

        // Запрос аннотаций из типа
        MarkerAnnotation marker = type.getAnnotation( MarkerAnnotation.class ) ;
        if( marker!= null ) {
            System.out.printf( "Class %s has marker annotation", type.getName()  ) ;
        }
        else {
            System.out.printf( "Class %s has no marker annotation", type.getName()  ) ;
        }
        System.out.println();

        marker = thisType.getAnnotation( MarkerAnnotation.class ) ;
        if( marker!= null ) System.out.printf( "Class %s has marker annotation", thisType.getName()  ) ;
        else System.out.printf( "Class %s has no marker annotation", thisType.getName()  ) ;
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------");
//        Class<?> nameType ;
//        try {
//            nameType = Class.forName("ClassWithAnnotation" ) ;
//        } catch ( ClassNotFoundException ex ) {
//            System.out.println( ex.getMessage() ) ;
//            return ;
//        }
//        marker = nameType.getAnnotation( MarkerAnnotation.class ) ;
//        if( marker!= null ) System.out.printf( "Class %s has marker annotation", nameType.getName()  ) ;
//        else System.out.printf( "Class %s has no marker annotation", nameType.getName()  ) ;


        Method[] methods = type.getDeclaredMethods() ;

        Object obj;
        try{ obj = type.getDeclaredConstructor().newInstance() ; }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return;
        }

        for( Method method : methods ) {
            if( method.isAnnotationPresent(MethodAnnotation.class) ) {
                MethodAnnotation methodAnnotation = method.getAnnotation(MethodAnnotation.class);
                System.out.printf("Method '%s' of class '%s' does have '%s' method annotation %n", method.getName(), type.getName(),methodAnnotation.value());

                // run method only recommended
                if(methodAnnotation.value().equals("Recommended")) {
                    try {
                        method.setAccessible(true); // для запуска недоступных методов
                        method.invoke(obj);
                    } catch (IllegalAccessException | InvocationTargetException ex) {
                        System.out.println("Invoke error" + ex.getMessage());
                    }
                }
                else{
                    System.out.println("Method is not recommended");
                }

            }
            else{
                System.out.printf("Method '%s' of class '%s' has not method annotation %n", method.getName(), type.getName());
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------------");


//        Method[] methods2 = thisType.getDeclaredMethods() ;
//        for( Method method : methods2 ) {
//            if( method.isAnnotationPresent(MethodAnnotation.class) ) {
//                MethodAnnotation methodAnnotation = method.getAnnotation(MethodAnnotation.class);
//                System.out.printf("Method '%s' of class '%s' does have '%s' method annotation %n", method.getName(), thisType.getName(),methodAnnotation.value());
//            }
//            else{
//                System.out.printf("Method '%s' of class '%s' has not method annotation %n", method.getName(), thisType.getName());
//            }
//        }
//        System.out.println("-----------------------------------------------------------------------------------------------");
//
//        Class<?> nameType ;
//        try {
//            nameType = Class.forName("ClassWithAnnotation" ) ;
//        } catch ( ClassNotFoundException ex ) {
//            System.out.println( ex.getMessage() ) ;
//            return ;
//        }
//
//        Method[] methods3 = nameType.getDeclaredMethods() ;
//        for( Method method : methods3 ) {
//            if( method.isAnnotationPresent(MethodAnnotation.class) ) {
//                MethodAnnotation methodAnnotation = method.getAnnotation(MethodAnnotation.class);
//                System.out.printf("Method '%s' of class '%s' does have '%s' method annotation %n", method.getName(), nameType.getName(),methodAnnotation.value());
//            }
//            else{
//                System.out.printf("Method '%s' of class '%s' has not method annotation %n", method.getName(), nameType.getName());
//            }
//        }
//        System.out.println("-----------------------------------------------------------------------------------------------");

        // field annotations
        Field[] fields = type.getDeclaredFields() ;
        for(Field field : fields){
            if(field.isAnnotationPresent(FieldAnnotation.class)){
                FieldAnnotation fieldAnnotation = field.getAnnotation(FieldAnnotation.class);
                System.out.printf("Field '%s' of class '%s' is '%s' with priority '%d'%n", field.getName(), type.getName(), fieldAnnotation.value(), fieldAnnotation.priority());
            }
            else{
                System.out.printf("Field '%s' of class '%s' has no annotation %n", field.getName(), type.getName());

            }
        }

        System.out.println(separator);
        Field[] fields2 = thisType.getDeclaredFields() ;
        for(Field field : fields2){
            if(field.isAnnotationPresent(FieldAnnotation.class)){
                FieldAnnotation fieldAnnotation = field.getAnnotation(FieldAnnotation.class);
                System.out.printf("Field '%s' of class '%s' is '%s' with priority '%d'%n", field.getName(), thisType.getName(), fieldAnnotation.value(), fieldAnnotation.priority());
                System.out.printf("Value %s%n", separator);
            }
            else{
                System.out.printf("Field '%s' of class '%s' has no annotation %n", field.getName(), thisType.getName());

            }
        }

    }
}


/*
Тема : Аннотации
Это аналог атрибутов в C#
- разновидность интерфейсов, реализуемых в классах.
Аннотации делят на маркеры (без данных) и аннотации со значениями.
Аннотации играют роль метаданных для класса

Создание аннотаций :
public @interface
+ указание доступности(@Retention(RetentionPolicy(RUNTIME) - доступно после запуска приложения
время разработки - .java (SOURCE - после сборки) / время выполнения - .class (RUNTIME - после запуска)
+ указание цели (@Target(ElementType.TYPE) - может ограничить для классов, методов, полей

Указание (применение) аннотаций :
перед нужным элементом добавляем @MarkerAnnotation

Определение аннотаций :


 */