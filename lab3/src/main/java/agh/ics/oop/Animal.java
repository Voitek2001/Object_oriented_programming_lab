package agh.ics.oop;

class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);
    private final Vector2d rightTopCorner = new Vector2d(4, 4);
    private final Vector2d leftBottomCorner = new Vector2d(0, 0);

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    public String toString() {
        return "Animal position: (%d, %d) and orientation: %s".formatted(position.x(), position.y(), orientation);
    }

    public boolean isAt(Vector2d position) {
        return (this.position.x() == position.x() && this.position.y() == position.y());
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case LEFT -> this.orientation = this.orientation.previous();
            case RIGHT -> this.orientation = this.orientation.next();
            case FORWARD -> {
                Vector2d tmp1 = this.position.add(this.orientation.toUnitVector());
                this.position = ((tmp1.precedes(rightTopCorner) && tmp1.follows(leftBottomCorner)) ? tmp1 : this.position);
            }
            case BACKWARD -> {
                Vector2d tmp2 = this.position.add(this.orientation.toUnitVector().opposite());
                this.position = ((tmp2.precedes(rightTopCorner) && tmp2.follows(leftBottomCorner)) ? tmp2 : this.position);
            }
        }
    }

}
