package step.learning;

import step.learning.anno.AnnotationsDemo;
import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;
import step.learning.anno.MethodAnnotation;
import step.learning.oop.Library;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Определяем текущий класс
        Class<?> currentClass = Main.class;
        // Извлечь из имени класса имя пакета
        String packageName = currentClass.getPackage().getName();
        // получаем перечень имен классов из пакета и его под-пакетов
        List<String> classNames = getClassNames(packageName);

        if (classNames == null) {
            System.out.println("Error scanning package");
            return;
        }

        // выбрать только те классы, которые аннотированы как DemoClass
        List<Class<?>> demoClasses = new ArrayList<>();
        for (String className : classNames) {
            Class<?> theClass;

            try {
                theClass = Class.forName(className);
            } catch (Exception ignored) { // ignored - спец имя для неиспользуемой переменнойй
                continue;
            }

            if (theClass.isAnnotationPresent(DemoClass.class)) {
                demoClasses.add(theClass);
            }

            // System.out.println( className ) ;
        }

//        Д.З. Обеспечить повтор диалога (меню-выбор) до выбора выхода
//        При формировании меню учесть приоритет, указанный в DemoClass аннотации
//        Добавить вывод уведомления в случае если в классе не будет найден метод EntryPoint

        int userChoice;

        demoClasses.sort(new Comparator<>() {
            public int compare(Class<?> obj1, Class<?> obj2) {
                return Integer.compare(obj1.getAnnotation(DemoClass.class).priority(), obj2.getAnnotation(DemoClass.class).priority());
            }
        });


        do {
            System.out.println("Demo classes : ");
            int i = 1;

            for (Class<?> theClass : demoClasses) {
                System.out.printf("%d. %s %n", i++, theClass.getName());
            }

            System.out.println("0. Exit");
            System.out.print("Make your choice: ");

            Scanner kbScanner = new Scanner(System.in);
            userChoice = -1;

            try {
                userChoice = kbScanner.nextInt();
            } catch (InputMismatchException ignored) {
                System.out.println("Incorrect choice");
            }


            System.out.println("Choice :" + userChoice);

            if (userChoice == 0) {
                System.out.println("Exit...");
                return;
            }

            int index = userChoice - 1;
            if (index >= demoClasses.size()) {
                System.out.println("Incorrect choice");
            } else {
                Class<?> execClas = demoClasses.get(index);

                Method[] methods = execClas.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(EntryPoint.class)) {
                        try {
                            method.invoke(execClas.getDeclaredConstructor().newInstance());
                        } catch (Exception ex) {
                            System.out.println("Execution error : " + ex.getMessage());
                        }
                    }
                    else{
                        System.out.println("(Entry Point not present)");
                    }
                }
            }
            System.out.println();

        }while(true);
    }


    private static List<String> getClassNames(String packageName) {

        // class loader
        // ClassLoader = currentClass.getClassLoader() ;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL packageResource = classLoader.getResource(       // classLoader - "системный" элемент,
                packageName.replace(       // способный определить файловый
                        ".",               // ресурс по имени (соотношение пакетов
                        "/")              // и реальных файлов). В имени ресурса
        );                            // вместо "." должны быть "/"
        if (packageResource == null) {
            System.out.println("Resource identification error");
            return null;
        }
        String packagePath = packageResource.getPath();  // Реальный файловый путь
        //   System.out.println( packagePath ) ;
        // Сканнируем как папку, определяем содержимое
        File packageBase = new File(packagePath);
        File[] list = packageBase.listFiles();
        if (list == null) {
            System.out.println("Error scanning directory " + packageBase);
            return null;
        }
        List<String> classNames = new ArrayList<>();
        for (File file : list) {
            // задание: если имеем директорию, вывести ее состав (один уровень вложенности)
            // задание: нас интересуют только файлы типа ".class", собираем информацию о них
            //  в коллекцию; для работы с классом нужно только имя, но с учетом пакета
            //   System.out.println( file.getName() + " " + ( file.isFile() ? "file" : "dir" ) ) ;
            if (file.isFile()) {
                String filename = file.getName();
                if (filename.endsWith(".class")) {
                    classNames.add(packageName + "." + filename.substring(0, filename.lastIndexOf('.')));
                }
            } else if (file.isDirectory()) {
                File[] subList = file.listFiles();
                if (subList != null) {
                    for (File sub : subList) {
                        //        System.out.println( " > " + sub.getName() + " " +ConsoleColors.GREEN + ( sub.isFile() ? "file" : "dir" ) +ConsoleColors.RESET ) ;
                        if (sub.isFile()) {
                            String filename = sub.getName();
                            if (filename.endsWith(".class")) {
                                classNames.add(packageName + "." + file.getName() + "." + filename.substring(0, filename.lastIndexOf('.')));
                            }
                        }
                    }
                }
            }
        }
        return classNames;
    }
}
/*
    Практика
    При старте программа проводит "анализ" существующих классов на признак "запускаемости"
    Собирает эти классы в меню и предлагает выбор для запуска.
    Для запуска один из методов класса должен быть помечен как стартовый.
    Уточнения:
    - классы могут находиться в пакете "step.learning" и в его под-пакетах
    - уровень вложенности - один, под-под-пакеты уже не сканнируем
    - "запускаемость" класса обозначается аннотацией "DemoClass(priority)"
    - стартовый метод обозначается аннотацией "EntryPoint"
    - меню пользователя с бесконечным повтором и с пунктом "выход"

    Заметки по решению
    узнать имя пакета
     а) hardcode - взять из названия
     б) рефлексией
    имя пакета - имя каталога, но для работы с файлом нужно полное имя
     его поставляет ClassLoader, доступный через
     а) Thread.currentThread().getContextClassLoader()
     б) Main.class.getClassLoader()

 */

/*
    Установка Java
    1. IDE
     - Intellij Idea (Ultimate) под студентческой лицензией
     - NetBeans
     - Eclipse
    2. JDK - Java Development Kit - инструментарий разработчика (компилятор)
       JRE - Java Runtime Environment - "платформа" - среда запуска

    Java:
    Java ME (Micro Edition) - Java Card - для устройств с ограниченным фукнционалом
    Java SE (Standard Edition) - базовый "набор"
    Java EE (Enterprise Edition) - доп. инструменты (сервер, библиотеки и т.п.)

    Шаблоны проектов (системы сборки)
    Maven
    Ant
    Gradle
 */
