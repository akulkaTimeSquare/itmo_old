package places;

import creatures.Creature;

public class Pit extends Place {
    private final float depth;

    public Pit(String name, float depth, int maxCreations, Creature...creatures) {
        super(name, maxCreations, creatures);
        this.depth = depth;
    }
    public float getDepth() {
        return depth;
    }

    @Override
    public String toString() {
        return null;
    }
}
