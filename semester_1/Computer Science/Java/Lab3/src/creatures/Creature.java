package creatures;

import places.Place;

abstract public class Creature {
    private Place currentLocation;

    public Creature(Place location) {
        this.currentLocation = location;
        this.currentLocation.setCreationsCount(this.getCurrentLocation().getCreationsCount() + 1);
    }
    public void setCurrentLocation(Place currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Place getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        return this.getCurrentLocation() == ((Creature) obj).getCurrentLocation();
    }

    abstract public String toString ();
}
