package agh.ics.oop;


import java.util.Arrays;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");
        int length = args.length;
        int i, j = 0;
        Direction[] instructions = new Direction[length];
        String[] instToPrint = new String[length];
        Direction tmp;

        for (i = 0; i < args.length; i++) {
            tmp = switch (args[i]) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "r" -> Direction.RIGHT;
                case "l" -> Direction.LEFT;
                default -> Direction.UNKNOWN;
            };
            if (tmp == Direction.UNKNOWN) {
                continue;
            }
            instructions[j] = tmp;
            instToPrint[j] = args[i];
            j += 1;
        }


        // print all args passed to program
        instructions = Arrays.copyOfRange(instructions, 0, j);
        instToPrint = Arrays.copyOfRange(instToPrint, 0, j);
        System.out.println(String.join(", ", instToPrint));
        // process steps and print them
        run(Arrays.copyOfRange(instructions, 0, j));
        System.out.println("system zakończył działanie");

    }
    public static void run(Direction[] instructions) {
        for(Direction argument : instructions){
            String message = switch (argument) {
                case FORWARD -> "Do przodu";
                case BACKWARD -> "Do tyłu";
                case RIGHT -> "W prawo";
                case LEFT -> "W lewo";
                default -> "";
            };
            System.out.println(message);
        }
    }
}
