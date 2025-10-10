package enums;

public enum Status {
    Worse("Худший"),
    Standard("Обычный"),
    Special("Специальный"),
    Great("Почётный"),
    Royalty("Королевский");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
