package creatures;

public enum Mood {
    Happiness("Счастье"),
    Sadness("Грусть"),
    Fury("Ярость"),
    Wonder("Волнение"),
    Fine("Стабильность");

    private final String name;

    Mood (String name) {this.name = name;}

    public String getName() {
        return name;
   }
}
