package agh.ics.oop;


class RectangularMap extends AbstractWorldMap {

    private final Vector2d upperRight;
    private final Vector2d lowerLeft;
    public RectangularMap(int width, int height) {
        this.upperRight = new Vector2d(width, height);
        this.lowerLeft = new Vector2d(0, 0);
        this.visualizer = new MapVisualizer(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !getElementsOnMap().containsKey(position) && position.precedes(upperRight) && position.follows(lowerLeft);
    }

}
