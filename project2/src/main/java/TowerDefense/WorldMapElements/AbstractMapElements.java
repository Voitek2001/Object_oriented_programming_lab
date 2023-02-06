package TowerDefense.WorldMapElements;

import TowerDefense.Vector2d;

import java.util.LinkedList;
import java.util.List;

abstract class AbstractMapElements {

    protected Vector2d position;
    protected List<IHealthObserver> healthObservers = new LinkedList<>();

    public void addHealthObserver(IHealthObserver observer) {
        this.healthObservers.add(observer);
    }
    public void removeHealthObserver(IHealthObserver observer) {
        this.healthObservers.remove(observer);
    }
}
