package creatures.animals;

import creatures.Moving;
import places.Place;

public class Hedgehog extends Animal implements Moving {
    private Place direction;

    public Hedgehog(Place location) {
        super(location);
        this.direction = location;
    }

    public void setDirection(Place direction) {
        this.direction = direction;
    }

    public Place getDirection() {
        return direction;
    }

    public String move() {
        this.setCurrentLocation(this.getDirection());
        Place location = this.getCurrentLocation();
        location.setCreationsCount(location.getCreationsCount() + 1);
        return "Ёжик " + this.getOwner() + " бежит";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        return this.getDirection() == ((Hedgehog) obj).getDirection();
    }

    @Override
    public String toString() {
        return "Ёжик " + this.getOwner();
    }
}
