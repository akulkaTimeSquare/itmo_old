package common;

public enum Command {
    ADD("add", "добавляет новый элемент в коллекцию", new String[0]),
    CLEAR("clear", "очищает коллекцию", new String[0]),
    EXECUTE_SCRIPT("execute_script", "считывает и исполняет скрипт из указанного файла",
            new String[]{"[file_name]"}),
    EXIT("exit", "завершает программу", new String[0]),
    GROUP_COUNTING_BY_HAS_TOOTHPICK("group_counting_by_has_toothpick", "группирует элементы коллекции " +
            "по значению поля hasToothpick, выводит количество элементов в каждой группе", new String[0]),
    HELP("help", "Выводит справку по доступным командам", new String[0]),
    HISTORY("history", "выводит 8 последних использованных команд на сервере", new String[0]),
    INFO("info", "выводит в стандартный поток информацию о коллекции", new String[0]),
    PRINT_DESCENDING("print_descending", "выводит элементы коллекции в порядке убывания", new String[0]),
    PRINT_UNIQUE_CAR("print_unique_car", "выводит уникальные значения поля " +
            "car всех элементов коллекции", new String[0]),
    REMOVE_BY_ID("remove_by_id", "удалить элемент из коллекции по id", new String[]{"id"}),
    REMOVE_GREATER("remove_greater", "удаляет все элемент коллекции, " +
            "превышающий заданный", new String[0]),
    REMOVE_LOWER("remove_lower", "удаляет элементы из коллекции, меньшие заданного", new String[0]),
    SHOW("show", "выводит в стандартный поток все элементы коллекции в строковом представлении",
            new String[0]),
    UPDATE("update", "обновляет значение элемента коллекции по id", new String[]{"id"}),
    LOGIN("login", "позволяет клиенту авторизоваться клиенту на сервере " +
            "(работает повторно)", new String[0]),
    REGISTER("register", "позволяет клиенту зарегистрироваться на сервере и войти " +
            "под введенными данными", new String[0]);

    private final String name;
    private final String description;
    private final String[] args;

    Command(String name, String description, String[] args) {
        this.name = name;
        this.description = description;
        this.args = args;
    }

    public String getName() {
        return this.name;
    }

    public String getDescr() {
        return this.description;
    }

    public String[] getArgs() {
        return this.args;
    }

    public int getArgCount() {
        return getArgs().length;
    }
}
