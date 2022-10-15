package agh.ics.oop;


import java.util.Arrays;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");
        int length = args.length;
        int i, j = 0;
        MoveDirection[] instructions = new MoveDirection[length];
        String[] instToPrint = new String[length];
        MoveDirection tmp;

        for (i = 0; i < args.length; i++) {
            tmp = switch (args[i]) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> MoveDirection.UNKNOWN;
            };
            if (tmp == MoveDirection.UNKNOWN) {
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

        //test MapDirection
        // test toString method
        for(MapDirection val : MapDirection.values()) {
            System.out.print(val + " ");
        }
        System.out.println();
        // test next method
        for(MapDirection val : MapDirection.values()) {
            System.out.print(val.next() + " ");
        }
        System.out.println();
        // test previous method
        for(MapDirection val : MapDirection.values()) {
            System.out.print(val.previous() + " ");
        }
        System.out.println();
        // test toUnitVector
        for(MapDirection val : MapDirection.values()) {
            System.out.print(val.toUnitVector() + " ");
        }
        System.out.println();

        System.out.println("system zakończył działanie");
    }
    public static void run(MoveDirection[] instructions) {
        for(MoveDirection argument : instructions){
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
