package creatures;

import places.Place;
import places.Mound;

public class Soldier extends Human implements Moving {
    private int health = 50;
    private Place trainingLocation;

    public Soldier(String name, boolean isMale, Mood mood, VoiceTone voice, Place trainingLocation, Place location) {
        super(name, isMale, mood, voice, location);
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

    public String move() {
        this.setCurrentLocation(this.getTrainingLocation());
        health += 25;
        if (this.getTrainingLocation() instanceof Mound) health += 25;
        this.getCurrentLocation().setCreationsCount(this.getCurrentLocation().getCreationsCount() + 1);
        return "Солдат " + this.getName() + " разгуливает по " +
                this.getTrainingLocation() + " (здоровье - " + this.getHealth() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        return this.getTrainingLocation() == ((Soldier) obj).getTrainingLocation();
    }
}
