package Command;

import IOControl.Manager;

public class GroupCountByHasToothpickCommand implements Command {
    /** GroupCountByHasToothpickCommand groups element in two categories: with toothpick and without */
    @Override
    public void execute(Manager manager, String[] args){
        if (manager.isEmptyCollection()) manager.println("Нет элементов в коллекции!");
        else {
            int[] groupSize = manager.getHasToothpickGroupSize();
            manager.println("\thasToothpick true - " + groupSize[0] +
                    "; hasToothpick false - " + groupSize[1]);
        }
    }

    @Override
    public String getName() {
        return "group_counting_by_has_toothpick";
    }

    @Override
    public String getDescr() {
        return "группирует элементы коллекции по значению поля hasToothpick, " +
                "выводит количество элементов в каждой группе";
    }

    @Override
    public String[] getArgs() {
        return new String[0];
    }
}
