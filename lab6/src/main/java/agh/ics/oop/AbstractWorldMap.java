package agh.ics.oop;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    private final HashMap<Vector2d, IMapElement> elementsOnMap = new HashMap<>();

    protected MapVisualizer visualizer;

    protected HashMap<Vector2d, IMapElement> getElementsOnMap() {
        return elementsOnMap;
    }

    public Optional<IMapElement> objectAt(Vector2d position) {
        IMapElement possibleMapEl = elementsOnMap.get(position);
        return Optional.ofNullable(possibleMapEl);
    }

    public boolean place(IMapElement elementToPlace) {
        //zakładam że place umieszcza tylko klase Animal na mapie
        Vector2d elePos = elementToPlace.getPosition();
        Optional<IMapElement> elementOnCurrPos = objectAt(elePos);
        AtomicBoolean canPlace = new AtomicBoolean(false);
        elementOnCurrPos.ifPresentOrElse(
                (value) -> {if (!(value instanceof Animal)) {
                    this.elementsOnMap.remove(elePos);
                    this.elementsOnMap.put(elePos, elementToPlace); canPlace.set(true);}},
                () -> {this.elementsOnMap.put(elePos, elementToPlace); canPlace.set(true);}
        );
        return canPlace.get();
    }

    public boolean isOccupied(Vector2d position) {
        return this.elementsOnMap.containsKey(position);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement tmp = elementsOnMap.get(oldPosition);
        elementsOnMap.remove(oldPosition);
        elementsOnMap.put(newPosition, tmp);
    }

    public String toString(Vector2d lowerLeft, Vector2d upperRight) {
        return visualizer.draw(lowerLeft, upperRight);
    }
}
