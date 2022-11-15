package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorldMapTest {

    @Test
    void rectangularMapTest() {
        //given
        MoveDirection[] instructions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        IWorldMap map = new RectangularMap(10, 5);

        int numOfOperation = instructions.length;
        IWorldMap map2 = new RectangularMap(10, 5);
        ArrayList<Animal> animalsPos2 = new ArrayList<>();
        Vector2d[] correctPositionsForTest2 = {
                new Vector2d(2, 3), //animal1
                new Vector2d(3, 3), //animal2
                //after 1st iteration
                new Vector2d(2, 3), //animal1
                new Vector2d(3, 3), //animal2
                //after 2nd iteration
                new Vector2d(2, 3), //animal1
                new Vector2d(3, 3), //animal2
                //after 3rd iteration (can't occupied same positions)
                new Vector2d(2, 3), //animal1
                new Vector2d(3, 3), //animal2
                //after 4th iteration
                new Vector2d(2, 2), //animal1
                new Vector2d(3, 4), //animal2
                //after 5th iteration
                new Vector2d(2, 1), //animal1
                new Vector2d(3, 5), //animal2
                //after 6th iteration
                new Vector2d(2, 0), //animal1
                new Vector2d(3, 5), //animal2
                //after 7th iteration
                new Vector2d(2, 0), //animal1
                new Vector2d(3, 5), //animal2
                //after 8th iteration

        };
        Boolean[] outputPositionForTestOccupied = new Boolean[numOfOperation];

        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        Animal a1 = new Animal(map2, positions[0]);
        Animal a2 = new Animal(map2, positions[1]);
        //when
        SimulationEngine engine = new SimulationEngine(instructions, map, positions);
        // run all
        engine.run();
        //run step by step to check "czy program popełnił parzystą liczbe błędów"
        animalsPos2.add(a1);
        animalsPos2.add(a2);
        int numOfValidAnimals = animalsPos2.size();
        for (int i = 0; i < numOfOperation; i++) {
            animalsPos2.get(i % numOfValidAnimals).move(instructions[i]);
            outputPositionForTestOccupied[i] = map2.isOccupied(correctPositionsForTest2[i]);
        }
        ArrayList<Animal> animalsPos = engine.getAnimalsPos();
        //then
        assertTrue(animalsPos.get(0).isAt(new Vector2d(2, 0)));
        assertTrue(animalsPos.get(1).isAt(new Vector2d(3, 5)));
        assertFalse(animalsPos.get(0).isAt(new Vector2d(4, 4)));
        assertFalse(animalsPos.get(1).isAt(new Vector2d(1, 1)));
        assertTrue(Arrays.stream(outputPositionForTestOccupied).allMatch(val -> true));
    }
    @Test
    void grassFieldTest() {
//given
        MoveDirection[] instructions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        IWorldMap map = new GrassField(10);

        int numOfOperation = instructions.length;
        IWorldMap map2 = new RectangularMap(10, 5);
        ArrayList<Animal> animalsPos2 = new ArrayList<>();
        Vector2d[] correctPositionsForTest2 = {
                new Vector2d(2, 3), //animal1
                new Vector2d(3, 3), //animal2
                //after 1st iteration
                new Vector2d(2, 3), //animal1
                new Vector2d(3, 3), //animal2
                //after 2nd iteration
                new Vector2d(2, 3), //animal1
                new Vector2d(3, 3), //animal2
                //after 3rd iteration (can't occupied same positions)
                new Vector2d(2, 3), //animal1
                new Vector2d(3, 3), //animal2
                //after 4th iteration
                new Vector2d(2, 2), //animal1
                new Vector2d(3, 4), //animal2
                //after 5th iteration
                new Vector2d(2, 1), //animal1
                new Vector2d(3, 5), //animal2
                //after 6th iteration
                new Vector2d(2, 0), //animal1
                new Vector2d(3, 6), //animal2
                //after 7th iteration
                new Vector2d(2, -1), //animal1
                new Vector2d(3, 7), //animal2
                //after 8th iteration

        };
        Boolean[] outputPositionForTestOccupied = new Boolean[numOfOperation];

        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        Animal a1 = new Animal(map2, positions[0]);
        Animal a2 = new Animal(map2, positions[1]);
        //when
        SimulationEngine engine = new SimulationEngine(instructions, map, positions);
        // run all
        engine.run();
        //run step by step to check "czy program popełnił parzystą liczbe błędów"
        animalsPos2.add(a1);
        animalsPos2.add(a2);
        int numOfValidAnimals = animalsPos2.size();
        for (int i = 0; i < numOfOperation; i++) {
            animalsPos2.get(i % numOfValidAnimals).move(instructions[i]);
            outputPositionForTestOccupied[i] = map2.isOccupied(correctPositionsForTest2[i]);
        }
        ArrayList<Animal> animalsPos = engine.getAnimalsPos();
        //then
        assertTrue(animalsPos.get(0).isAt(new Vector2d(2, -1)));
        assertTrue(animalsPos.get(1).isAt(new Vector2d(3, 7)));
        assertFalse(animalsPos.get(0).isAt(new Vector2d(4, 4)));
        assertFalse(animalsPos.get(1).isAt(new Vector2d(1, 1)));
        assertTrue(Arrays.stream(outputPositionForTestOccupied).allMatch(val -> true));
    }
}
