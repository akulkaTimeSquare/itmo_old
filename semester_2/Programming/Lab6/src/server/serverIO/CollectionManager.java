package server.serverIO;

import common.collection.HumanBeing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class CollectionManager {
    /** CollectionManager contains a collection and allows to control it (like adding and removing) */
    private static HashSet<HumanBeing> collection = new HashSet<>();
    private static final java.time.LocalDateTime initDate = LocalDateTime.now();

    public static void addElement(HumanBeing el) {
        collection.add(el);
    }

    public static void removeElement(HumanBeing el) {
        collection.remove(el);
    }

    public static void removeElementById(long id) {
        collection.removeIf(el -> el.getId().equals(id));
    }

    public static HumanBeing getElementById(long id) {
        for (HumanBeing element: collection) {
            if (element.getId().equals(id)) return element;
        }
        return null;
    }

    public static void updateId(HumanBeing el) {
        removeElementById(el.getId());
        addElement(el);
    }

    public static boolean containsId(long id) {
        for (HumanBeing el: collection) {
            if (id == el.getId()) return true;
        }
        return false;
    }

    public static int getCollectionSize() {
        return collection.size();
    }

    public static int[] getHasToothpickGroupSize() {
        return new int[]{(int) collection.stream().filter(HumanBeing::isHasToothpick).count(),
                (int) collection.stream().filter(element -> !element.isHasToothpick()).count()};
    }

    public static LocalDateTime getInitDate() {
        return initDate;
    }

    public static String getCollectionType() {
        return "HashSet<HumanBeing>";
    }

    public static void setCollection(HashSet<HumanBeing> collection) {
        CollectionManager.collection = collection;
    }

    public static HashSet<HumanBeing> getCollection() {
        return collection;
    }

    public static ArrayList<HumanBeing> getSortedCollection() {
        ArrayList<HumanBeing> list_collection = new ArrayList<>(collection);
        Collections.sort(list_collection);
        return list_collection;
    }

    public static ArrayList<HumanBeing> getReversedCollection() {
        ArrayList<HumanBeing> list_collection = new ArrayList<>(collection);
        Collections.reverse(list_collection);
        return list_collection;
    }

    public static boolean isEmptyCollection() {
        return collection.isEmpty();
    }

    public static void clearCollection() {
        collection.clear();
    }

}
