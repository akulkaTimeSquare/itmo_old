package Collection;

public enum Mood {
    SADNESS("sadness"),
    GLOOM("gloom"),
    APATHY("apathy"),
    FRENZY("frenzy");

    private final String name;

    Mood (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
