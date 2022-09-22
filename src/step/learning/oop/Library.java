package step.learning.oop;

import step.learning.ConsoleColors;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Library {
    List<Literature> funds ;

    public Library() {
        funds = new ArrayList<>() ;
    }

    public  void add( Literature literature ) {
        funds.add( literature ) ;
    }

    void showPrintable() {
        System.out.println( ConsoleColors.GREEN + "Printable : " + ConsoleColors.RESET ) ;

        for( Literature literature:funds  ) {
            if (literature instanceof Printable) {
                ( (Printable) literature).print() ;
            }
        }

        System.out.print("------------------------------------------------------\n");
    }

    void showUnprintable() {
        System.out.println( ConsoleColors.RED + "Unprintable : " + ConsoleColors.RESET ) ;

        for( Literature literature:funds  ) {
            if( !(literature instanceof Printable) ) {
                System.out.println( ConsoleColors.BLUE_BACKGROUND + ConsoleColors.BLACK_BOLD + "(" + literature.getClass().getSimpleName() + ")" + ConsoleColors.RESET+ "  " + literature.getTitle() ) ;
            }
        }

        System.out.print("------------------------------------------------------\n");
    }

    void showNonPeriodic() {
        System.out.println( ConsoleColors.RED + "NON Periodic : " + ConsoleColors.RESET ) ;

        for( Literature literature:funds  ) {
            if ( !(literature instanceof Periodic) ) {
                if ( literature instanceof Printable ) {
                    ( (Printable) literature ).print() ;
                }
                else {
                    System.out.println( ConsoleColors.BLUE_BACKGROUND + ConsoleColors.BLACK_BOLD + "(" + literature.getClass().getSimpleName() + ")" + ConsoleColors.RESET+ "  " + literature.getTitle() ) ;
                }
            }
        }

        System.out.print("------------------------------------------------------\n");
    }

    void showPeriodic() {
        System.out.println( ConsoleColors.GREEN + "Periodic : " + ConsoleColors.RESET ) ;

        for( Literature literature:funds  ) {
            if ( literature instanceof Periodic ) {
                if ( literature instanceof Printable ) {
                    ( (Printable) literature ).print() ;
                }
                else {
                    System.out.println( ConsoleColors.BLUE_BACKGROUND + ConsoleColors.BLACK_BOLD + "(" + literature.getClass().getSimpleName() + ")" + ConsoleColors.RESET+ "  " + literature.getTitle() ) ;
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

    public void run() {
        add(new Book()
                .setAuthor("Knuth")
                .setTitle("Art of programming") ) ;
        add(new Hologram().setTitle("Pectoral") ) ;
        try {
            add( new Journal()
                    .setTitle("Marvel Spider-Man")
                    .setNumber(16));
            add( new Newspaper()
                    .setTitle("Daily Planet")
                    .setDate("2022-09-22") ) ;
            add( new Newspaper()
                    .setTitle("Washington Post")
                    .setDate("2022-09-21") ) ;
            add (new Newspaper()
                    .setTitle("The Daily Mail")
                    .setDate("2021-09-21") ) ;

        } catch (ParseException e) {
            System.out.println( "Funds creation failed : " + e.getMessage() ) ;
        }

        add(new Poster().setTitle("Avengers Poster") ) ;
        add(new Poster().setTitle("House of Dragon Poster") ) ;

        printFunds();

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