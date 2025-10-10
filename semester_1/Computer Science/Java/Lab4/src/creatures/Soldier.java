package creatures;

import enums.Mood;
import enums.Status;
import enums.VoiceTone;
import exceptions.TiredSoldierException;
import exceptions.OvercrowdedException;
import interfaces.Moving;
import places.Place;
import places.Mound;

public class Soldier extends Human implements Moving {
    private int health = 50;
    private Place trainingLocation;
    private boolean poseStatus;

    public Soldier(String name, boolean isMale, Mood mood, VoiceTone voice, Place trainingLocation, Place location) {
        super(name, isMale, Status.Great, mood, voice, location);
        this.trainingLocation = trainingLocation;
    }

    public void setHealth(int health) {
        health = Math.max(0, Math.min(health, 100));
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setTrainingLocation(Place trainingLocation) {
        this.trainingLocation = trainingLocation;
    }

    public Place getTrainingLocation() {
        return trainingLocation;
    }

    public void move() {
        this.setCurrentLocation(this.getTrainingLocation());
        health += 25;
        if (this.getTrainingLocation() instanceof Mound) health += 25;
        try {
            this.getCurrentLocation().addCreationsCount(1);
            System.out.println("Солдат " + this.getName() + " разгуливает по " +
                    this.getTrainingLocation() + " (здоровье: " + this.getHealth() + ")");
        }
        catch (OvercrowdedException e) {
            System.out.println(e.getMessage() + " Солдат " + this.getName() + " никуда не идёт...");
            System.out.println("Солдат отжимается");
        }
    }

    public void takeGatePose() throws TiredSoldierException {
        this.poseStatus = true;
        this.health -= 20;
        if (health < 0) {
            throw new TiredSoldierException(this);
        }
        System.out.println(this.getName() + " занимает стойку ворот");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        return this.getTrainingLocation() == ((Soldier) obj).getTrainingLocation();
    }
}
