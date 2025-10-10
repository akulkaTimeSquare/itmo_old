package places;

import creatures.Creature;

public class Road extends Place {
    private float quality;

    public Road(String name, float quality, Creature ...creatures) {
        super(name, creatures);
        this.quality = quality;
    }

    public void setQuality(float quality) {
        this.quality = quality;
    }

    public float getQuality() {
        return quality;
    }

    @Override
    public String toString() {
        return "Road with " + this.getQuality();
    }
}
