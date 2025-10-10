package creatures.animals;

import creatures.Mood;
import places.Place;

public class Flamingo extends Animal {
    private boolean neckStatus = false;
    private boolean takenStatus = false;

    public Flamingo(Place location) {
        super(location);
    }

    public void setNeckStatus(boolean neckStatus) {
        this.neckStatus = neckStatus;
    }

    public boolean getNeckStatus() {
        return neckStatus;
    }

    public void setTakenStatus(boolean takenStatus) {
        this.takenStatus = takenStatus;
    }

    public boolean getTakenStatus() {
        return takenStatus;
    }

    public String lookBehind() {
        this.setNeckStatus(false);
        String text = "Фламинго " + this.getOwner() + " удивлённо поворачивается";
        if (this.getTakenStatus()) {
            this.getOwner().setMood(Mood.Happiness);
            text += " и веселит своего хозяина";
        }
        return text;
    }

    @Override
    public String toString() {
        return "Фламинго " + this.getOwner();
    }
}
