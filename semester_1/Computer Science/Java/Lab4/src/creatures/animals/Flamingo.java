package creatures.animals;

import enums.Mood;
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

    public void lookBehind() {
        this.setNeckStatus(false);
        String text = "Фламинго " + this.getOwner().getName() + " удивлённо поворачивается";
        if (this.getTakenStatus()) {
            this.getOwner().setMood(Mood.Happiness);
            text += " и веселит своего хозяина";
        }
        System.out.println(text);
    }
}
