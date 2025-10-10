package common.collection;

import java.io.Serial;
import java.io.Serializable;

public class Car implements Comparable<Car>, Serializable {
    @Serial
    private static final long serialVersionUID = 3L;
    /** field name is not null*/
    private final String name;
    public Car(String nameText) {
        this.name = nameText;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Car c) {
        return this.name.compareTo(c.getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
