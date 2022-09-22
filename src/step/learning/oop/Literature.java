package step.learning.oop;

public abstract class Literature {
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
