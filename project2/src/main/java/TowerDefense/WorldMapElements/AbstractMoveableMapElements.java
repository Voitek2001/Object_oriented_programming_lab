package TowerDefense.WorldMapElements;

import java.util.LinkedList;
import java.util.List;

abstract class AbstractMoveableMapElements extends AbstractMapElements{
    protected List<IPositionChangedObserver> positionObservers = new LinkedList<>();

    public void addPositionObserver(IPositionChangedObserver observer) {
        this.positionObservers.add(observer);
    }
    public void removePositionObserver(IPositionChangedObserver observer) {
        this.positionObservers.remove(observer);
    }

}
