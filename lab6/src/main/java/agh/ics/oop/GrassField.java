package agh.ics.oop;

import java.util.*;

class GrassField extends AbstractWorldMap {

    private final int n;

    public GrassField(int n) {
        this.visualizer = new MapVisualizer(this);
        this.n = n;
        Integer[] randomCoordinates = generateDistRandomNumbers(n, (int) Math.sqrt(10 * n) * (int) Math.sqrt(10 * n));

        for (int i = 0; i < n; i++) {
            Vector2d pos = new Vector2d(randomCoordinates[i] / (int) Math.sqrt(10 * n), randomCoordinates[i] % (int) Math.sqrt(10 * n));
            getElementsOnMap().put(pos, new Grass(pos));
        }

    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        if (super.isOccupied(position) && getElementsOnMap().get(position) instanceof Grass) {
            findNewPositionForGrass(position);
            return true;
        }
        return !super.isOccupied(position);
    }


    @Override
    public String toString() {
        Bounds newBounds = getBounds();
        return super.toString(newBounds.lowerLeft(), newBounds.upperRight());
    }

    private Bounds getBounds() {
        Vector2d lowerLeft = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d upperRight = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

        for (Map.Entry<Vector2d, IMapElement> entry : getElementsOnMap().entrySet()) {
            Vector2d pos = entry.getKey();
            upperRight = upperRight.upperRight(pos);
            lowerLeft = lowerLeft.lowerLeft(pos);
        }
        return new Bounds(lowerLeft, upperRight);
    }

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

    private void findNewPositionForGrass(Vector2d oldPosition) {
        Optional<Vector2d> newGrassPos = generateNewNotOccupiedPosition(oldPosition);
        getElementsOnMap().remove(oldPosition);
        newGrassPos.ifPresent(// check if it is even possible to place grass on new place
                (newPos) -> getElementsOnMap().put(newPos, new Grass(newPos))
        );
    }

//    (Dla zaawansowanych).
//    Zmodyfikuj implementację tak, żeby po spotkaniu zwierzęcia i trawy, trawa znikała.
//    Nowe kępki trawy powinny pojawiać się losowo w obszarze z punktu 1, po zjedzeniu trawy przez zwierzę,
//    przy założeniu, że nowe położenie kępki trawy nie pokrywa się z istniejącą kępką trawy, ani z żadnym zwierzęciem.
//
//    Po dłuższym zastanowieniu, nie za bardzo wiem jak mógłbym usunąć atrybut n
//    i nadal móc realizować losowanie trawy dla powyższego punktu.
    private Optional<Vector2d> generateNewNotOccupiedPosition(Vector2d pos) {
        LinkedList<Vector2d> tmpList = new LinkedList<>();
        for (int i = 0; i < (int) Math.sqrt(10 * n); i++) {
            for (int j = 0; j < (int) Math.sqrt(10 * n); j++) {
                Vector2d newPos = new Vector2d(i, j);
                if (!isOccupied(newPos) && !newPos.equals(pos)) {
                    tmpList.add(newPos);
                }
            }
        }

        return Optional.ofNullable(tmpList.get(new Random().nextInt(tmpList.size()-1)));

    }


}
