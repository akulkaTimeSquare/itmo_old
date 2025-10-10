package common.collection;

import java.io.Serializable;

public enum Mood implements Serializable {
    SADNESS("sadness"),
    GLOOM("gloom"),
    APATHY("apathy"),
    FRENZY("frenzy");

    private final String name;
    private static final long serialVersionUID = 3L;
    Mood (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
