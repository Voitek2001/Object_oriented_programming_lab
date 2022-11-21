package agh.ics.oop;


public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");

        //lab4 zmodyfikowane pod lab5
        MoveDirection[] directions2 = OptionsParser.parse(args);
        IWorldMap map2 = new RectangularMap(10, 5);
        Vector2d[] positions2 = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine2 = new SimulationEngine(directions2, map2, positions2);
        engine2.run();
        System.out.println(map2);


        //lab5
        System.out.println("LAB5");
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.println(map);

        System.out.println("system zakończył działanie");
    }

}
