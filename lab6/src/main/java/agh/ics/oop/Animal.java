package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

class Animal implements IMapElement {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;
    private final List<IPositionChangeObserver> observers = new LinkedList<>();

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
            worldMap.positionChanged(this.position, possiblePosition);
            this.position = possiblePosition;
        }

        // position = position.lowerLeft(4, 4)
        // position = position.upperRight(0, 0)
    }

    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer : this.observers) {
            observer.positionChanged(oldPosition, newPosition);
        }
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

}
