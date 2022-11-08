package agh.ics.oop;

import java.util.HashMap;

class RectangularMap implements IWorldMap {

    // hashmap which keys are position (as vector) and values are Animal
    private final HashMap<Vector2d, Animal> animalPositions;

    private final Vector2d UPPER_RIGHT;
    private final Vector2d LOWER_LEFT = new Vector2d(0, 0);
    private final MapVisualizer visualizer;


    public RectangularMap(int width, int height) {
        UPPER_RIGHT = new Vector2d(width, height);
        this.visualizer = new MapVisualizer(this);
        this.animalPositions = new HashMap<>();
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animalPositions.containsKey(position) && position.precedes(UPPER_RIGHT) && position.follows(LOWER_LEFT);
    }

    @Override
    public boolean place(Animal animal) {
        if(!animalPositions.containsKey(animal.getPosition())) {
            animalPositions.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public void changePosition(Vector2d oldPosition, Vector2d newPosition) {
        // assume that values are correct
        Animal animal = animalPositions.get(oldPosition);
        animalPositions.remove(oldPosition);
        animalPositions.put(newPosition, animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animalPositions.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        //return null when the map contains no such mapping for the key
        return animalPositions.get(position);

    }

    public String toString() {
        return visualizer.draw(this.LOWER_LEFT, this.UPPER_RIGHT);
    }
}
