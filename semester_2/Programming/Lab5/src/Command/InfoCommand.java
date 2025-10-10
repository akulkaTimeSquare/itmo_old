package Command;

import IOControl.Manager;

import java.time.LocalDateTime;

public class InfoCommand implements Command {
    /** InfoCommand shows collection's info */
    @Override
    public void execute(Manager manager, String[] args) {
        LocalDateTime initDate = manager.getInitDate();
        manager.println("\tType: " + manager.getCollectionType() + "\n" +
                        "\tInitializationDate: " + initDate.getDayOfMonth() + " " +
                        initDate.getMonth() + " " + initDate.getYear() + " " +
                        (initDate.getHour() < 10 ? "0" : "") + initDate.getHour() + ":" +
                        (initDate.getMinute() < 10 ? "0" : "") + initDate.getMinute() + ":" +
                        (initDate.getSecond() < 10 ? "0" : "") + initDate.getSecond() + "\n" +
                        "\tSize: " + manager.getCollectionSize() + "elements");
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescr() {
        return "выводит в стандартный поток информацию о коллекции.";
    }

    @Override
    public String[] getArgs() {
        return new String[0];
    }
}
