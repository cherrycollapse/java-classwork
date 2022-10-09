package step.learning.oop;

import step.learning.ConsoleColors;

import java.io.Serializable;

public class Book
        extends Literature
        implements Printable, Serializable {
    String author;

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Book setTitle(String title){
        super.setTitle(title);
        return  this;
    }

    @Override
    public void print() {
        System.out.printf( ConsoleColors.YELLOW_BACKGROUND + ConsoleColors.BLACK_BOLD + "(Book)" + ConsoleColors.RESET + "      Title : %s. Author : %s%n", super.getTitle(), this.author);
    }


}
