package creatures.animals;

import interfaces.Moving;
import creatures.Soldier;
import exceptions.OvercrowdedException;
import places.Place;

public class Hedgehog extends Animal implements Moving {
    private Soldier direction;

    public Hedgehog(Place location) {
        super(location);
    }

    public void setDirection(Soldier direction) {
        this.direction = direction;
    }

    public Soldier getDirection() {
        return direction;
    }

    public void move() {
        if (this.direction == null) System.out.println("Ёжик" + this.getOwner().getName() +
                "теряется, не понимая, куда ему бежать");
        try {
            this.getCurrentLocation().addCreationsCount(1);
            this.setCurrentLocation(this.getDirection().getCurrentLocation());
            System.out.println("Ёжик " + this.getOwner().getName() + " бежит");
        }
        catch (OvercrowdedException e) {
            System.out.println(e.getMessage() + " Ёжик " + this.getOwner().getName() + " застревает в толпе");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        return this.getDirection() == ((Hedgehog) obj).getDirection();
    }
}
