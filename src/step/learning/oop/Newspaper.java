package step.learning.oop;

import step.learning.ConsoleColors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Newspaper
        extends Literature
        implements Printable, Periodic {
    Date date ;
    Calendar calendar ;

    static final SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");  // sql format /форматировщик даты как на ввод, так и на вывод

    static final SimpleDateFormat datePrinter = new SimpleDateFormat("dd.MM.yyyy");

    static final SimpleDateFormat datePrinterShort = new SimpleDateFormat("dd.MM");


    public Date getDate() {
        return date ;
    }

    public Newspaper setDate( String dateString ) throws ParseException {
        this.date = dateParser.parse( dateString ) ;  // методы Date deprecated
        this.calendar = Calendar.getInstance() ;    // рекомендованный обьект для работы с датами
        calendar.setTime(this.date) ;               // работы с датами
        return this ;
    }

//    public Newspaper setDate(Date date) {
//        this.date = date;
//        return this;
//    }


    @Override
    public Newspaper setTitle( String title ) {
        return ( Newspaper )super.setTitle( title ) ;
    }

    @Override
    public void print() {

        Calendar currentDate = Calendar.getInstance() ;

        String date ;

        if ( currentDate.get( Calendar.DATE ) == calendar.get( Calendar.DATE ) ) {
            date = "Today" ;
        }
        else if( currentDate.get( Calendar.YEAR ) == calendar.get( Calendar.YEAR ) ) {
            date = datePrinterShort.format( this.getDate() ) ;
        }
        else {
            date = datePrinter.format( this.getDate() ) ;
        }

        System.out.printf( ConsoleColors.WHITE_BACKGROUND + ConsoleColors.BLACK_BOLD + "(Newspaper)" + ConsoleColors.RESET + " Title : %s. Date : %s%n", super.getTitle() , date ) ;

       // date.getYear(); - deprecated
    }













}
