public class Point {
    private int _x;
    private int _y;
    private final int MIN_COORDINATE = 0;

    public Point(final int x, final int y) {
        this._x = ((x > 0) ? x : 0);
        this._y = ((y > 0) ? y : 0);
    }

    public Point(final Point other) {
        this._x = other._x;
        this._y = other._y;
    }

    public int getX() {
        return this._x;
    }

    public int getY() {
        return this._y;
    }

    public void setX(final int num) {
        if (num >= 0) {
            this._x = num;
        }
    }

    public void setY(final int num) {
        if (num >= 0) {
            this._y = num;
        }
    }

    public boolean equals(final Point other) {
        return this._x == other._x && this._y == other._y;
    }

    public boolean isAbove(final Point other) {
        return this._y > other._y;
    }

    public boolean isUnder(final Point other) {
        return other.isAbove(this);
    }

    public boolean isLeft(final Point other) {
        return this._x < other._x;
    }

    public boolean isRight(final Point other) {
        return other.isLeft(this);
    }

    public double distance(final Point p) {
        return Math.sqrt(Math.pow(p._y - this._y, 2.0) + Math.pow(p._x - this._x, 2.0));
    }

    public void move(final int deltaX, final int deltaY) {
        if (this._x + deltaX >= 0 && this._y + deltaY >= 0) {
            this.setX(this._x + deltaX);
            this.setY(this._y + deltaY);
        }
    }

    public Point middle(final Point p) {
        return new Point((this._x + p._x) / 2, (this._y + p._y) / 2);
    }

    @Override
    public String toString() {
        return "(" + this._x + "," + this._y + ")";
    }
}