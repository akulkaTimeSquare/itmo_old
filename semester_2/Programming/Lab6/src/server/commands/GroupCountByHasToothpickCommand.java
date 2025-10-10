package server.commands;

import common.collection.HumanBeing;
import server.serverIO.CollectionManager;

public class GroupCountByHasToothpickCommand extends ServerCommand {
    /** GroupCountByHasToothpickCommand groups element in two categories: with toothpick and without */
    @Override
    public void execute(String value, HumanBeing element){
        if (CollectionManager.isEmptyCollection()) addToResponse("Нет элементов в коллекции!");
        else {
            int[] groupSize = CollectionManager.getHasToothpickGroupSize();
            addToResponse("\thasToothpick true - " + groupSize[0] +
                          "; hasToothpick false - " + groupSize[1]);
        }
    }
}
