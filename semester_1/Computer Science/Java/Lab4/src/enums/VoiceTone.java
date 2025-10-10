package enums;

public enum VoiceTone {
    Standard("Стандартый"),
    Squeak("Писклявый"),
    Rough("Грубый"),
    Humorous("Насмешливый"),
    Optimistic("Оптимистичный"),
    Serious("Серьёзный");

    private final String name;

    VoiceTone(String name) {this.name = name;}

    public String getName() {
        return name;
    }
}
