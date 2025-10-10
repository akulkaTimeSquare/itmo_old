package Collection;

public class Coordinates implements Comparable<Coordinates>{
    private double x;
    /** max value of y field - 87, y is not null */
    private Float y;

    public Coordinates (double x, Float y) throws NumberFormatException {
        if (y > 87) throw new NumberFormatException();
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    @Override
    public int compareTo(Coordinates c) {
        return (int) (this.x - c.getX() + this.y - c.y);
    }

    @Override
    public String toString() {
        return x + " " + y.toString();
    }
}
