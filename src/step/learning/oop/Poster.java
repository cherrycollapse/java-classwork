package step.learning.oop;

public class Poster
        extends Literature
        implements Periodic {
    @Override
    public Poster setTitle( String title ) {
        return ( Poster )super.setTitle( title ) ;
    }

}
