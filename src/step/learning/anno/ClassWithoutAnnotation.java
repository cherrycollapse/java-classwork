package step.learning.anno;

// просто пустой класс без аннотации
public class ClassWithoutAnnotation {

    public int field1;
    public String field2;

    // Домашнее задание: в классах ClassWithAnnotation, ClassWithoutAnnotation
    //        реализовать конструкторы, которые задают полям случайные значения.

    public ClassWithoutAnnotation() {
        field1 = (int)(Math.random() * 10);
        field2 = String.valueOf(Math.random() * 10);
    }


    public void method1(){

    }

    public void method2(){

    }


}
