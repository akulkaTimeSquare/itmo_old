package creatures;

import creatures.animals.Flamingo;
import creatures.animals.Hedgehog;
import places.Place;

public class Player extends Human {
    private Flamingo ownFlamingo;
    private Hedgehog ownHedgehog;

    public Player(String name, boolean isMale, Mood mood, VoiceTone voiceTone,
                  Flamingo flamingo, Hedgehog hedgehog, Place location) {
        super(name, isMale, mood, voiceTone, location);
        this.ownFlamingo = flamingo;
        this.ownFlamingo.setOwner(this);
        this.ownHedgehog = hedgehog;
        this.ownHedgehog.setOwner(this);
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

    public String straightFlamingoNeck() {
        this.getOwnFlamingo().setNeckStatus(true);
        return this.getName() + " выпрямляет шею своему фламинго";
    }

    public String takeFlamingo() {
        this.getOwnFlamingo().setTakenStatus(true);
        return this.getName() + " удобно берёт своего фламинго";
    }

    public String setHedgehogDirection(Place place) {
        this.getOwnHedgehog().setDirection(place);
        return this.getName() + " направляет своего ежика на " + place.getName();
    }

    public String hitHedgehog() {
        if (this.getOwnFlamingo().getTakenStatus() || this.getOwnFlamingo().getNeckStatus()) {
            this.getOwnHedgehog().move();
        }
        return this.getName() + " стукает своим фламинго по ежику";
    }
}
