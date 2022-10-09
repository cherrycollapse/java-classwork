package step.learning.oop;

import java.io.Serializable;

public abstract class Literature implements Serializable {
    String title;

    /**
     * alt+insert / click+generate
     */
    public String getTitle() {
        return title;
    }


    /**
     * текучий интерфейс (fluid)
     */
    public Literature setTitle(String title) {
        this.title = title;
        return this;
    }

}
