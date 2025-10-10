package places;

public class Plain extends Place {
    private final float length;
    private final float width;

    public Plain(String name, float length, float width) {
        super(name);
        this.length = length;
        this.width = width;
    }

    public float getLength() {
        return length;
    }

    public float getWidth() {return width; }

    @Override
    public String toString() {
        return this.getName() + " поле (" + this.getLength() + "x" + this.getWidth() + ")";
    }
}
