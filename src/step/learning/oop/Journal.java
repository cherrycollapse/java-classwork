package step.learning.oop;

import step.learning.ConsoleColors;

import java.io.Serializable;

public class Journal
        extends Literature
        implements Printable, Periodic, Serializable {
    Integer number ;

    // Аксессоры :
    public Integer getNumber() {
        return number ;
    }

    public Journal setNumber( Integer number ) {
        this.number = number ;
        return this;
    }

    public Journal setTitle( String title ) {
        super.setTitle( title ) ;
        return this ;
    }

    // Методы :
    @Override
    public void print() {
        System.out.printf( ConsoleColors.RED_BACKGROUND + ConsoleColors.BLACK_BOLD +"(Journal)" + ConsoleColors.RESET +"   Title : %s. Number : #%s%n", super.getTitle(),this.getNumber());
    }
}
