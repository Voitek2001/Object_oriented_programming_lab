package agh.ics.oop;

public interface IPositionChangeObserver {


    /**
     *
     * @param oldPosition
     * old position of animal
     * @param newPosition
     * new position of animal
     * change position on map in haspmap which contains all positions as keys
     */
    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
