package exceptions;

import places.Place;

public class OvercrowdedException extends Exception {
    private Place place;

    public OvercrowdedException(Place place) {
        super(place.getName() + " переполнено!");
    }

    public Place getPlace() {
        return place;
    }
}
