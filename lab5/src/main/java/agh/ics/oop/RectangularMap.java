package agh.ics.oop;

import java.util.HashMap;

class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height) {
        this.upperRight = new Vector2d(width, height);
        this.lowerLeft = new Vector2d(0, 0);
        this.visualizer = new MapVisualizer(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !elementsOnMap.containsKey(position) && position.precedes(upperRight) && position.follows(lowerLeft);
    }

}
