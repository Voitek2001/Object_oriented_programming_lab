package agh.ics.oop;

import java.util.*;

class GrassField extends AbstractWorldMap {

    private final int n;
    private Integer[] generateDistRandomNumbers(int count, int maxValue) {
        // if count > maxValue doesn't have sense to generate distinct number, raise error or return null
        Integer[] randomNum = new Integer[maxValue];
        Random rand = new Random();

        for (int i = 0; i < maxValue; i++) {
            randomNum[i] = i;
        }

        for (int i = 0; i < maxValue; i++) {
            int randomIndex = rand.nextInt(maxValue);
            int temp = randomNum[randomIndex];
            randomNum[randomIndex] = randomNum[i];
            randomNum[i] = temp;
        }
        return Arrays.copyOfRange(randomNum, 0, count);

    }
    private Vector2d generateNewNotOccupiedPosition(Vector2d pos) { // TODO zmienic Vector2d na optionala
        LinkedList<Vector2d> tmpList = new LinkedList<>();
        for (int i = 0; i < (int) Math.sqrt(10 * n); i++) {
            for (int j = 0; j < (int) Math.sqrt(10 * n); j++) {
                Vector2d newPos = new Vector2d(i, j);
                if (!isOccupied(newPos) && !newPos.equals(pos)) {
                    tmpList.add(newPos);
                }
            }
        }
        if (tmpList.size() > 0) {
            return tmpList.get(new Random().nextInt(tmpList.size()-1));
        }
        return null;
    }
    public GrassField(int n) {
        this.visualizer = new MapVisualizer(this);
        this.n = n;
        Integer[] randomCoordinates = generateDistRandomNumbers(n, (int) Math.sqrt(10 * n) * (int) Math.sqrt(10 * n));

        for (int i = 0; i < n; i++) {
            Vector2d pos = new Vector2d(randomCoordinates[i] / (int) Math.sqrt(10 * n), randomCoordinates[i] % (int) Math.sqrt(10 * n));
            elementsOnMap.put(pos, new Grass(pos));
        }
        upperRight = new Vector2d((int) Math.sqrt(10 * n), (int) Math.sqrt(10 * n));
        lowerLeft = new Vector2d(0, 0);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        if (super.isOccupied(position) && elementsOnMap.get(position) instanceof Grass) {
            elementsOnMap.remove(position);
            Vector2d newGrassPos = generateNewNotOccupiedPosition(position);
            if (!Objects.isNull(newGrassPos)) {
                elementsOnMap.put(newGrassPos, new Grass(newGrassPos));
            }
            return true;
        }
        return !super.isOccupied(position);
    }


    @Override
    public void changePosition(Vector2d oldPosition, Vector2d newPosition) {
        super.changePosition(oldPosition, newPosition);
    }

    private void recalculateBoundsOfMap() {

        for (Map.Entry<Vector2d, IMapElement> entry : elementsOnMap.entrySet()) {
            Vector2d pos = entry.getKey();
            this.upperRight = upperRight.upperRight(pos);
            this.lowerLeft = lowerLeft.lowerLeft(pos);
        }
    }

    @Override
    public String toString() {
        recalculateBoundsOfMap();
        return super.toString();
    }
}
