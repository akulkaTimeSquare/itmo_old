package creatures;

import exceptions.OvercrowdedException;
import places.Place;

abstract public class Creature {
    private Place currentLocation;

    public Creature(Place location) {
        this.currentLocation = location;
        try {
            this.currentLocation.addCreationsCount(1);
        }
        catch (OvercrowdedException e) {
            System.out.println(e.getMessage());
            class GodWaitRoom extends Place {
                public GodWaitRoom(String name, int maxCreations, Creature... creatures) {
                    super(name, maxCreations, creatures);
                }
            }
            System.out.println("Перемещение к Создателю...");
            GodWaitRoom waitRoom = new GodWaitRoom("Комната ожидания Создателя", 1, this);
        }
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
