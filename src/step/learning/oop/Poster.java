package step.learning.oop;

import java.io.Serializable;

public class Poster
        extends Literature
        implements Periodic, Serializable {
    @Override
    public Poster setTitle( String title ) {
        return ( Poster )super.setTitle( title ) ;
    }

}
