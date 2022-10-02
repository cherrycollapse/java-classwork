package step.learning.serial;

import java.io.Serializable;

public class DataObject implements Serializable {
    private int private_Field;

    private transient String transient_string;

    protected float protected_Field;
    public String public_Field;

    public DataObject(Object... args) {
        private_Field = args.length > 0 ? (int)args[0]:-1;
        protected_Field = args.length > 1 ? (float)args[1]:-1;
        public_Field = args.length > 2 ? (String)args[2]:"-";
        transient_string = args.length > 3 ? (String)args[3]:"-";
    }

    @Override
    public String toString() {
        return String.format("{pri:'%d',pro: '%f', pub: '%s', trans: '%s'}",
                private_Field,protected_Field,public_Field,transient_string);
    }
}