package creatures;

import creatures.animals.Flamingo;
import creatures.animals.Hedgehog;
import enums.Mood;
import enums.Status;
import enums.VoiceTone;
import places.Place;

public class Player extends Human {
    private Flamingo ownFlamingo;
    private Hedgehog ownHedgehog;

    public Player(String name, boolean isMale, Status status, Mood mood, VoiceTone voiceTone,
                  Flamingo flamingo, Hedgehog hedgehog, Place location) {
        super(name, isMale, status, mood, voiceTone, location);
        this.setOwnFlamingo(flamingo);
        this.getOwnFlamingo().setOwner(this);
        this.setOwnHedgehog(hedgehog);
        this.getOwnHedgehog().setOwner(this);
    }

    public void setOwnFlamingo(Flamingo ownFlamingo) {
        this.ownFlamingo = ownFlamingo;
    }

    public Flamingo getOwnFlamingo() {
        return ownFlamingo;
    }

    public void setOwnHedgehog(Hedgehog ownHedgehog) {
        this.ownHedgehog = ownHedgehog;
    }

    public Hedgehog getOwnHedgehog() {
        return ownHedgehog;
    }

    public void straightFlamingoNeck() {
        this.getOwnFlamingo().setNeckStatus(true);
        System.out.println(this.getName() + " выпрямляет шею своему фламинго");
    }

    public String takeFlamingo() {
        this.getOwnFlamingo().setTakenStatus(true);
        return this.getName() + " удобно берёт своего фламинго";
    }

    public void setHedgehogDirection(Soldier soldier) {
        this.getOwnHedgehog().setDirection(soldier);
        System.out.println(this.getName() + " направляет своего ежика на " + soldier.getName());
    }

    public void hitHedgehog() {
        if (this.getCurrentLocation() != this.getOwnHedgehog().getCurrentLocation()) {
            this.getOwnHedgehog().setCurrentLocation(this.getCurrentLocation());
        }
        if (this.getOwnFlamingo().getTakenStatus() && this.getOwnFlamingo().getNeckStatus()) {
            this.getOwnHedgehog().move();
            System.out.println(this.getName() + " стукает своим фламинго по ежику");
        }
        else {
            this.takeFlamingo();
            this.straightFlamingoNeck();
            this.hitHedgehog();
        }
    }
}
