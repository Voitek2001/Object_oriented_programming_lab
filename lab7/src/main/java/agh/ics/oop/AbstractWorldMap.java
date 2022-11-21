package agh.ics.oop;

import java.util.HashMap;
import java.util.Optional;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    private final HashMap<Vector2d, IMapElement> elementsOnMap = new HashMap<>();

    protected MapVisualizer visualizer;

    protected HashMap<Vector2d, IMapElement> getElementsOnMap() {
        return elementsOnMap;
    }

    public Optional<IMapElement> objectAt(Vector2d position) {
        IMapElement possibleMapEl = elementsOnMap.get(position);
        return Optional.ofNullable(possibleMapEl);
    }

    public void place(Animal animal) throws IllegalArgumentException {
        //zakładam że place umieszcza tylko klase Animal na mapie
        Vector2d elePos = animal.getPosition();
        Optional<IMapElement> elementOnCurrPos = objectAt(elePos);
        elementOnCurrPos.ifPresentOrElse(
                (value) -> {if (!(value instanceof Animal)) {
                    this.elementsOnMap.remove(elePos);
                    this.elementsOnMap.put(elePos, animal);} else {
                    throw new IllegalArgumentException("Pole (%d, %d) jest zajete\n".formatted(elePos.x(), elePos.y()));}
                },
                () -> this.elementsOnMap.put(elePos, animal)
        );
        animal.addObserver(this);
    }

    public boolean isOccupied(Vector2d position) {
        return elementsOnMap.containsKey(position);
    }


    public void positionChanged(AbstractWorldElement animal, Vector2d oldPosition, Vector2d newPosition) {
        HashMap<Vector2d, IMapElement> elementsOnMap = getElementsOnMap();
        elementsOnMap.remove(oldPosition);
        elementsOnMap.put(newPosition, animal);
    }

    public String toString(Vector2d lowerLeft, Vector2d upperRight) {
        return visualizer.draw(lowerLeft, upperRight);
    }

    public abstract Bounds getBounds();
}
