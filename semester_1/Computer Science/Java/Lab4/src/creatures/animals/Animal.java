package creatures.animals;

import creatures.Creature;
import creatures.Human;
import places.Place;

abstract public class Animal extends Creature {
    private Human owner;

    public Animal(Place location) {
        super(location);
    }

    public void setOwner(Human owner) {
        this.owner = owner;
    }

    public Human getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " " + this.getOwner().getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        return this.getOwner() == ((Animal) obj).getOwner();
    }

    @Override
    public int hashCode() {
        String ownerName = this.getOwner().getName();
        return ownerName == null ? 0 : ownerName.hashCode();
    }
}
