package step.learning.anno;

// пометить класс маркером
@MarkerAnnotation
public class ClassWithAnnotation {

    @FieldAnnotation(value = "For version 1.0", priority = 1)
    public int field1;

    @FieldAnnotation(value = "For version 1.1", priority = 2)
    public String field2;

    // Домашнее задание: в классах ClassWithAnnotation, ClassWithoutAnnotation
    //        реализовать конструкторы, которые задают полям случайные значения.

    public ClassWithAnnotation() {
        field1 = (int)(Math.random() * 10);
        field2 = String.valueOf(Math.random() * 10);
    }

    @MethodAnnotation("Deprecated")
    public void method1 () {
        System.out.println("method1 works");
    }

    @MethodAnnotation("Recommended")
    public void method2 () {
        System.out.println("method2 works");
    }
}
