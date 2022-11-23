package agh.ics.oop;

import java.util.HashMap;
import java.util.Optional;

abstract class AbstractWorldMap implements IWorldMap {
    protected HashMap<Vector2d, IMapElement> elementsOnMap = new HashMap<>();
    protected Vector2d upperRight;
    protected Vector2d lowerLeft;
    protected MapVisualizer visualizer;

    public Optional<IMapElement> objectAt(Vector2d position) {
        IMapElement possibleMapEl = elementsOnMap.get(position);
        return Optional.ofNullable(possibleMapEl);
    }

    public boolean place(IMapElement elementToPlace) {
        Vector2d elePos = elementToPlace.getPosition();
        // for animal
        Optional<IMapElement> elementOnCurrPos = objectAt(elePos);
        // if position is not occupied or replace grass with animal object on this position
        // grass can not be replaced by grass
        if (elementOnCurrPos.isEmpty() || (elementOnCurrPos.get() instanceof Grass && elementToPlace instanceof Animal)) {
            this.elementsOnMap.put(elePos, elementToPlace);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position) {
        return this.elementsOnMap.containsKey(position);
    }

    @Override
    public void changePosition(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement tmp = elementsOnMap.get(oldPosition);
        elementsOnMap.remove(oldPosition);
        elementsOnMap.put(newPosition, tmp);
    }

    public String toString() {
        return visualizer.draw(this.lowerLeft, this.upperRight);
    }
}
