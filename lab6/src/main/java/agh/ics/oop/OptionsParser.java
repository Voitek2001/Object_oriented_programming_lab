package agh.ics.oop;

import java.util.Arrays;
import java.util.Objects;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        return Arrays.stream(args)
                .map(instruction -> switch (instruction) {
                    case "f", "forward" -> MoveDirection.FORWARD;
                    case "b", "backward" -> MoveDirection.BACKWARD;
                    case "r", "right" -> MoveDirection.RIGHT;
                    case "l", "left" -> MoveDirection.LEFT;
                    default -> null;
                }).filter(Objects::nonNull).toArray(MoveDirection[]::new);
    }
}
