package TowerDefense.WorldMapElements.EnemyUtils;

import TowerDefense.Vector2d;
import TowerDefense.WorldMapElements.AbstractMoveableMapElements;

public interface IEnemyObserver {
    void die(AbstractMoveableMapElements element);
    void positionChanged(AbstractMoveableMapElements element, Vector2d oldPosition, Vector2d newPosition);
}
