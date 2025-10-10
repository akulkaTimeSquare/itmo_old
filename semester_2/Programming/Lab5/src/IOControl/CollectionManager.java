package IOControl;

import Collection.HumanBeing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class CollectionManager {
    /** CollectionManager contains a collection and allows to control it (like adding and removing) */
    HashSet<HumanBeing> collection;
    java.time.LocalDateTime initDate = LocalDateTime.now();

    public CollectionManager(HashSet<HumanBeing> collection) {
        this.collection = collection;
    }

    public void addElement(HumanBeing el) {
        collection.add(el);
    }

    public void removeElement(HumanBeing el) {
        collection.remove(el);
    }

    public void removeElementById(long id) {
        collection.removeIf(el -> el.getId().equals(id));
    }

    public HumanBeing getElementById(long id) {
        for (HumanBeing element: collection) {
            if (element.getId().equals(id)) return element;
        }
        return null;
    }

    public void updateId(HumanBeing el) {
        removeElementById(el.getId());
        addElement(el);
    }

    public boolean containsId(long id) {
        for (HumanBeing el: collection) {
            if (id == el.getId()) return true;
        }
        return false;
    }

    public int getCollectionSize() {
        return collection.size();
    }

    public int[] getHasToothpickGroupSize() {
        int c1 = 0;
        int c2 = 0;
        for (HumanBeing element: collection) {
            if (element.isHasToothpick()) c1 += 1;
            else c2 += 1;
        }
        return new int[]{c1, c2};
    }

    public LocalDateTime getInitDate() {
        return initDate;
    }

    public String getCollectionType() {
        return "HashSet<HumanBeing>";
    }

    public HashSet<HumanBeing> getCollection() {
        return collection;
    }

    public ArrayList<HumanBeing> getSortedCollection() {
        ArrayList<HumanBeing> list_collection = new ArrayList<>(collection);
        Collections.sort(list_collection);
        return list_collection;
    }

    public ArrayList<HumanBeing> getReverseSortedCollection() {
        ArrayList<HumanBeing> list_collection = new ArrayList<>(collection);
        Collections.reverse(list_collection);
        return list_collection;
    }

    public boolean isEmptyCollection() {
        return collection.isEmpty();
    }

    public void clearCollection() {
        collection.clear();
    }
}
