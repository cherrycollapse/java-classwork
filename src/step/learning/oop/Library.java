package step.learning.oop;

import step.learning.ConsoleColors;
import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@DemoClass
public class Library implements Serializable {
    List<Literature> funds;

    public Library() {
        funds = new ArrayList<>();
    }

    public void add(Literature literature) {
        funds.add(literature);
    }

    void showPrintable() {
        System.out.println(ConsoleColors.GREEN + "Printable : " + ConsoleColors.RESET);

        for (Literature literature : funds) {
            if (literature instanceof Printable) {
                ((Printable) literature).print();
            }
        }

        System.out.print("------------------------------------------------------\n");
    }

    void showUnprintable() {
        System.out.println(ConsoleColors.RED + "Unprintable : " + ConsoleColors.RESET);

        for (Literature literature : funds) {
            if (!(literature instanceof Printable)) {
                System.out.println(ConsoleColors.BLUE_BACKGROUND + ConsoleColors.BLACK_BOLD + "(" + literature.getClass().getSimpleName() + ")" + ConsoleColors.RESET + "  " + literature.getTitle());
            }
        }

        System.out.print("------------------------------------------------------\n");
    }

    void showNonPeriodic() {
        System.out.println(ConsoleColors.RED + "NON Periodic : " + ConsoleColors.RESET);

        for (Literature literature : funds) {
            if (!(literature instanceof Periodic)) {
                if (literature instanceof Printable) {
                    ((Printable) literature).print();
                } else {
                    System.out.println(ConsoleColors.BLUE_BACKGROUND + ConsoleColors.BLACK_BOLD + "(" + literature.getClass().getSimpleName() + ")" + ConsoleColors.RESET + "  " + literature.getTitle());
                }
            }
        }

        System.out.print("------------------------------------------------------\n");
    }

    void showPeriodic() {
        System.out.println(ConsoleColors.GREEN + "Periodic : " + ConsoleColors.RESET);

        for (Literature literature : funds) {
            if (literature instanceof Periodic) {
                if (literature instanceof Printable) {
                    ((Printable) literature).print();
                } else {
                    System.out.println(ConsoleColors.BLUE_BACKGROUND + ConsoleColors.BLACK_BOLD + "(" + literature.getClass().getSimpleName() + ")" + ConsoleColors.RESET + "  " + literature.getTitle());
                }
            }
        }

        System.out.print("------------------------------------------------------\n");
    }

    public void printFunds() {
        showPrintable();
        showUnprintable();
        showPeriodic();
        showNonPeriodic();
    }

    // Д.З. Впровадити серіалізацію фондів бібліотеки.
    // На початку роботи "бібліотека" намагається десеріалізувати фонди
    // та видає загальну кількість зчитаних даних.
    // Далі пропонує меню щодо відображення за видами (Printable, Periodic...)

    // сериализация
    public void serializeFunds() {
        try (FileOutputStream file = new FileOutputStream("funds.ser")) {
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(this.funds);
            oos.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    // десериализация
    public void deserializeAll() {
        try (FileInputStream file = new FileInputStream("funds.ser")) {
            ObjectInputStream ios = new ObjectInputStream(file);

            List<Literature> list = (List<Literature>) ios.readObject();

            for (Literature data : list) {
                if (data instanceof Literature) {
                    add(data);
                }
            }

            System.out.println("Deserialization is done. Amount of data read :" + list.size());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    @EntryPoint
    public void run() {
        // заполнение
        add(new Book().setAuthor("Knuth").setTitle("Art of programming"));
        add(new Hologram().setTitle("Pectoral"));
        try {
            add(new Journal().setTitle("Marvel Spider-Man").setNumber(16));
            add(new Newspaper().setTitle("Daily Planet").setDate("2022-09-22"));
            add(new Newspaper().setTitle("Washington Post").setDate("2022-09-21"));
            add(new Newspaper().setTitle("The Daily Mail").setDate("2021-09-21"));

        } catch (ParseException e) {
            System.out.println("Funds creation failed : " + e.getMessage());
        }

        add(new Poster().setTitle("Avengers Poster"));
        add(new Poster().setTitle("House of Dragon Poster"));

        //  printFunds();

        serializeFunds();
        deserializeAll();

        // меню для отображения по категориям
        System.out.print("Select :\n 1. All\n 2. Printable\n 3. UnPrintable\n 4. Periodic\n 5. NonPeriodic\n > ");

        Scanner kbScanner = new Scanner(System.in);
        String str = kbScanner.nextLine();

        System.out.println();
        switch (str) {
            case "1":
                printFunds();
                break;
            case "2":
                showPrintable();
                break;
            case "3":
                showUnprintable();
                break;
            case "4":
                showPeriodic();
                break;
            case "5":
                showNonPeriodic();
                break;
            default:
                return;
        }

    }

}


/*
Тема : ООП
Сущности : классы, интерфейсы, структуры, абстрактные классы,
           делегаты, атрибуты, enum, event, generics

Поля, модификаторы, констркуторы, деструкторы, свойства, методы расширения, статика

Наследование : множественное наследование/реализация, модификаторы наследования, where (Generics),
виртуальность (и замещение методов) , делегирование,

Полиморфизм : преобразование типов UpCast / DownCast
в тч проверка принадлежности к типу, перегрузки

UML диаграмма - StarUML
-------------------------------------------------
В Java свойств нет, принято создавать аксессоры для полей

 */