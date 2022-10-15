package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {
    @Test
    void next() {
        MapDirection[] testValues = MapDirection.values();
        for(MapDirection argument : testValues) {
            MapDirection correct = switch (argument) {
                case NORTH -> MapDirection.EAST;
                case SOUTH -> MapDirection.WEST;
                case EAST -> MapDirection.SOUTH;
                case WEST -> MapDirection.NORTH;
            };
            assertEquals(correct, argument.next());
        }
    }

    @Test
    void previous() {
        MapDirection[] testValues = MapDirection.values();
        for(MapDirection argument : testValues) {
            MapDirection correct = switch (argument) {
                case NORTH -> MapDirection.WEST;
                case SOUTH -> MapDirection.EAST;
                case EAST -> MapDirection.NORTH;
                case WEST -> MapDirection.SOUTH;
            };
            assertEquals(correct, argument.previous());
        }
    }


}
