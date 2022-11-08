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



        // lab2

//        test MapDirection
//         test toString method
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

        //lab3
        Animal a = new Animal();
        System.out.println(a.isAt(new Vector2d(2, 2)));

        MoveDirection[] parsedDirections = OptionsParser.parse(args);
        for (MoveDirection argument : parsedDirections) {
            a.move(argument);
            System.out.println(a);
        }
        //Odpowiedz na pytanie: jak zaimplementować mechanizm,
        //który wyklucza pojawienie się dwóch zwierząt w tym samym miejscu.
        //Stworzenie nowej klasy "map" trzymająca pozycje wszystkich zwierząt które istnieją(hash mape)

        //lab4
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.println(map);

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
