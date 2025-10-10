package Command;

import IOControl.Manager;

public class ClearCommand implements Command {
    /** Clear command clears input */
    @Override
    public void execute(Manager manager, String[] args) {
        manager.clearCollection();
        manager.println("Коллекция была очищена.");
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescr() {
        return "очищает коллекцию";
    }

    @Override
    public String[] getArgs() {
        return new String[0];
    }
}
