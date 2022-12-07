package agh.ics.oop;

import java.util.Objects;

class Animal extends AbstractWorldElement {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;

    private final IWorldMap worldMap;

    public Animal() {
        this(new RectangularMap(10, 10), new Vector2d(2, 2));
    }
    public Animal(IWorldMap map) {
        this(map, new Vector2d(2, 2));
    }
    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.position = initialPosition;
        this.worldMap = map;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public void move(MoveDirection direction) {
        Vector2d possiblePosition = this.position;
        switch (direction) {
            case LEFT -> this.orientation = this.orientation.previous();
            case RIGHT -> this.orientation = this.orientation.next();
            case FORWARD -> possiblePosition = this.position.add(this.orientation.toUnitVector());
            case BACKWARD -> possiblePosition = this.position.add(this.orientation.toUnitVector().opposite());
        }
//        System.out.println(worldMap.canMoveTo(possiblePosition));
        if (worldMap.canMoveTo(possiblePosition)) {
            this.positionChanged(this.position, possiblePosition);
            this.position = possiblePosition;
        }

        // position = position.lowerLeft(4, 4)
        // position = position.upperRight(0, 0)
    }


    public Vector2d getPosition() {
        return position;
    }

    public boolean isAt(Vector2d position) {
        return Objects.equals(this.position, position); // use object to avoid nullpointerexception
    }

    public String toString() {
//        return "Animal position: (%d, %d) and orientation: %s".formatted(position.x(), position.y(), orientation);
        return switch (this.orientation) {
            case WEST -> "<";
            case SOUTH -> "v";
            case NORTH -> "^";
            case EAST -> ">";
        };
    }

    public String getImagePath() {
        return switch (this.orientation) {
            case WEST -> "left.png";
            case SOUTH -> "down.png";
            case NORTH -> "up.png";
            case EAST -> "right.png";
        };
    }

    public String describePosition() {
        return switch (this.orientation) {
            case WEST -> "W";
            case SOUTH -> "S";
            case NORTH -> "N";
            case EAST -> "E";
        } + " (%d , %d)".formatted(this.position.x(), this.position.y());
    }

}
