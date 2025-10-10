package places;
import creatures.Creature;
abstract public class Place {
    private int creationsCount;
    private final String name;

    public Place(String name, Creature ...creatures){
        if (name == null) name = "";
        this.name = name;
        for (Creature creature: creatures) {
            creature.setCurrentLocation(this);
        }
    }

    public void setCreationsCount(int creationsCount) {
        if (creationsCount + this.getCreationsCount() >= 0) {
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

    abstract public String toString();

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
