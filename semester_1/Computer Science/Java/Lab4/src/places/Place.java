package places;
import creatures.Creature;
import exceptions.OvercrowdedException;

abstract public class Place {
    private int creationsCount;
    private final int maxCreations;
    private final String name;

    public Place(String name, int maxCreations, Creature ...creatures){
        if (name == null) name = "";
        this.name = name;
        this.maxCreations = maxCreations;
        for (Creature creature: creatures) {
            creature.setCurrentLocation(this);
        }
    }

    public void setCreationsCount(int creationsCount) {
        if (creationsCount >= 0) {
            this.creationsCount = creationsCount;
        }
    }

    public int getCreationsCount() {
        return creationsCount;
    }

    public String getName() {
        return name;
    }

    public void setCreaturesLocation(Creature...creatures) {
        for (Creature creature: creatures) {
            creature.setCurrentLocation(this);
        }
    }

    public void addCreationsCount(int delta) throws OvercrowdedException {
        int temp = delta + this.getCreationsCount();
        if (temp >= 0 && temp <= maxCreations) {
            this.setCreationsCount(temp);
        }
        else {
            throw new OvercrowdedException(this);
        }
    }

    public String toString() {
        return "Place " + this.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        return this.getCreationsCount() == ((Place) obj).getCreationsCount();
    }

    @Override
    public int hashCode() {
        String name = this.getName();
        int hashName = name == null ? 0 : name.hashCode();
        hashName = 29 * hashName + this.getCreationsCount();
        return hashName;
    }
}
