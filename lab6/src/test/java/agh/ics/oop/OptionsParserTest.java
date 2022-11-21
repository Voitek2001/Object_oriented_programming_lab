package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OptionsParserTest {

    @Test
    void correctInput() {
        // given
        String[] Directions1 = {"f", "forward", "back", "go", "l", "backward", "right", "r", "turn", "a", "righ", "foward"};
        String[] Directions2 = {""};
        String[] Directions3 = {"left"};
        MoveDirection[] expected1 = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.RIGHT};
        MoveDirection[] expected2 = {};
        MoveDirection[] expected3 = {MoveDirection.LEFT};
        // when
        MoveDirection[] actual1 = OptionsParser.parse(Directions1);
        MoveDirection[] actual2 = OptionsParser.parse(Directions2);
        MoveDirection[] actual3 = OptionsParser.parse(Directions3);
        // then
        Assertions.assertArrayEquals(expected1, actual1);
        Assertions.assertArrayEquals(expected2, actual2);
        Assertions.assertArrayEquals(expected3, actual3);

    }
}
