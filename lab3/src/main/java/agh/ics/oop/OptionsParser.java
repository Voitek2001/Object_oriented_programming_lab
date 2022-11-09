package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        MoveDirection[] ret = new MoveDirection[args.length];
        int i = 0;
        for (String argument : args) {
            switch (argument) {
                case "f", "forward" -> {ret[i] = MoveDirection.FORWARD; i++;}
                case "b", "backward" -> {ret[i] = MoveDirection.BACKWARD; i++;}
                case "r", "right" -> {ret[i] = MoveDirection.RIGHT; i++;}
                case "l", "left" -> {ret[i] = MoveDirection.LEFT; i++;}
            }
        }
        return Arrays.copyOfRange(ret, 0, i);
    }
}
