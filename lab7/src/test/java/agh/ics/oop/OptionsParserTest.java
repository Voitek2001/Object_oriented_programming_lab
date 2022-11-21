package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OptionsParserTest {

    @Test
    void correctInput() {
        // given
        // invalid input
        String[] Directions1 = {"f", "forward", "back", "go", "l", "backward", "right", "r", "turn", "a", "righ", "foward"};
        String[] Directions2 = {""};
        // valid input
        String[] Directions3 = {"left"};
        String[] Directions4 = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] expected3 = {MoveDirection.LEFT};
        MoveDirection[] expected4 = {
                MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.LEFT,
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.RIGHT, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD
        };
        // when
        MoveDirection[] actual3 = OptionsParser.parse(Directions3);
        MoveDirection[] actual4 = OptionsParser.parse(Directions4);
        // then
        Assertions.assertArrayEquals(expected3, actual3);
        Assertions.assertArrayEquals(expected4, actual4);
        Assertions.assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(Directions1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(Directions2));

    }
}
