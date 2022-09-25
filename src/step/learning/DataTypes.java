package step.learning;

public class DataTypes {

    public void run() {
        System.out.println(
                ConsoleColors.GREEN +
                        "Hello " +
                        ConsoleColors.YELLOW +
                        "world!" +
                        ConsoleColors.RESET
        );
        String str1 = new String("Hello");
        String str2 = new String("Hello");

//        if(str1==str2) System.out.println("==");
//        else System.out.println("!=");
        // == - полное сравнивание, reference equality
        // .equals
        if (str1.equals(str2)) System.out.println("==");
        else System.out.println("!=");

        String str3 = "Hello, " + "World!"; // Константные строки складываются сразу
        String str4 = "Hello" + ", World!"; // Pool строк - если строка есть в пуле, то новая не создается
        if (str3.equals(str4)) System.out.println("=="); // Равны по референсу
        else System.out.println("!=");
        // immutable строки, появится новая строка после замены

        // Primitives = value types
        byte bx = -100; // В Java все числовые типы знаковые (беззнаковых нет)
        short sx = -3000;
        int ix = 20; // -1 (в бинаронй форме) = 1111 1111 - каждое число разбивается на четверки
        long lx = 100L; // окончание L
        float fx = 0.1f; // 0.1 - double
        float fy = (float) 0.1; // хард кастинг, либо преобразовать либо поставить f
        double dx = 1.5e-7; // экспоненциальные формы записи
        char c = 'A'; // UTF-16 (2 byte encoding)
        boolean b = true;

        // в Java нет перегрузки стандартных операторов

        Object obj; // Общий базовый тип
        // у всех объектов есть критические секции

        Integer iy = 10; // Object wrap for int


        System.out.println(bx + " " + ConsoleColors.GREEN + "(byte)" + ConsoleColors.RESET);
        System.out.println(sx + " " + ConsoleColors.GREEN + "(short)" + ConsoleColors.RESET);
        System.out.println(ix + " " + ConsoleColors.GREEN + "(int)" + ConsoleColors.RESET);
        System.out.println(lx + " " + ConsoleColors.GREEN + "(long)" + ConsoleColors.RESET);
        System.out.println(fx + " " + ConsoleColors.GREEN + "(float)" + ConsoleColors.RESET);
        System.out.println(fy + " " + ConsoleColors.GREEN + "(float)" + ConsoleColors.RESET);
        System.out.println(dx + " " + ConsoleColors.GREEN + "(double)" + ConsoleColors.RESET);
        System.out.println(c + " " + ConsoleColors.GREEN + "(char)" + ConsoleColors.RESET);
        System.out.println(b + " " + ConsoleColors.GREEN + "(boolean)" + ConsoleColors.RESET);



    }

}

/* Тема : Типы данных

1. Основной единицей данных явялется класс.
!! Классы и пакеты строго именуются :
- package - называется как каталог (case sensitive)
 = рекомендуется lowercase
 = refactor -> rename - переименовать пакет
- класс называется как и файл (CapitalCamelCase)
 = в одном классе один public класс, несколько может быть в одном классе, если они а) private б) внутренние
 = класс нельзя разделить на несколько файлов
 */