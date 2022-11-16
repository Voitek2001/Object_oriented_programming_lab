package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {


    @Test
    void orientation() {
        // given
        Animal animal1 = new Animal();
        MapDirection expect1 = MapDirection.NORTH;
        MapDirection expect2 = MapDirection.EAST;
        MapDirection expect3 = MapDirection.WEST;
        MapDirection expect4 = MapDirection.SOUTH;
        // when
        MapDirection actual1 = animal1.getOrientation();
        animal1.move(MoveDirection.RIGHT);
        MapDirection actual2 = animal1.getOrientation();
        animal1.move(MoveDirection.LEFT);
        animal1.move(MoveDirection.LEFT);
        MapDirection actual3 = animal1.getOrientation();
        animal1.move(MoveDirection.RIGHT);
        animal1.move(MoveDirection.RIGHT);
        animal1.move(MoveDirection.RIGHT);
        MapDirection actual4 = animal1.getOrientation();
        // then
        Assertions.assertEquals(expect1, actual1);
        Assertions.assertEquals(expect2, actual2);
        Assertions.assertEquals(expect3, actual3);
        Assertions.assertEquals(expect4, actual4);
    }


    @Test
    void position() {
        // given
        Animal animal1 = new Animal();
        Vector2d expect1 = new Vector2d(2, 2);
        Vector2d expect2 = new Vector2d(4, 2);
        Vector2d expect3 = new Vector2d(4, 4);
        Vector2d expect4 = new Vector2d(2, 2);
        // when
        Vector2d actual1 = animal1.getPosition();
        animal1.move(MoveDirection.RIGHT);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        Vector2d actual2 = animal1.getPosition();
        animal1.move(MoveDirection.RIGHT);
        animal1.move(MoveDirection.BACKWARD);
        animal1.move(MoveDirection.BACKWARD);
        Vector2d actual3 = animal1.getPosition();
        animal1.move(MoveDirection.RIGHT);
        animal1.move(MoveDirection.FORWARD);
        Vector2d actual4 = animal1.getPosition();
        // then
        Assertions.assertEquals(expect1, actual1);
        Assertions.assertEquals(expect2, actual2);
        Assertions.assertEquals(expect3, actual3);
        Assertions.assertNotEquals(expect4, actual4);
    }
    @Test
    void outsideTheMap() {
        // given
        Animal animal1 = new Animal();
        Vector2d expect1 = new Vector2d(2, 0);
        Vector2d expect2 = new Vector2d(4, 2);
        Vector2d expect3 = new Vector2d(4, 4);
        Vector2d expect4 = new Vector2d(0, 4);
        // when
        animal1.move(MoveDirection.BACKWARD);
        animal1.move(MoveDirection.BACKWARD);
        animal1.move(MoveDirection.BACKWARD);
        animal1.move(MoveDirection.BACKWARD);
        Vector2d actual1 = animal1.getPosition();
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.RIGHT);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        Vector2d actual2 = animal1.getPosition();
        animal1.move(MoveDirection.RIGHT);
        animal1.move(MoveDirection.BACKWARD);
        animal1.move(MoveDirection.BACKWARD);
        animal1.move(MoveDirection.BACKWARD);
        animal1.move(MoveDirection.BACKWARD);
        Vector2d actual3 = animal1.getPosition();
        animal1.move(MoveDirection.RIGHT);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        Vector2d actual4 = animal1.getPosition();
        // then
        Assertions.assertEquals(expect1, actual1);
        Assertions.assertEquals(expect2, actual2);
        Assertions.assertEquals(expect3, actual3);
        Assertions.assertEquals(expect4, actual4);
    }

    @Test
    void correctMovementOfAnimals() {
        //given
        MoveDirection[] instructions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        IWorldMap map = new RectangularMap(10, 5);

        int num_of_operation = instructions.length;
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
        Boolean[] outputPositionForTestOccupied = new Boolean[num_of_operation];

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
        int num_of_valid_animals = animalsPos2.size();
        for (int i = 0; i < num_of_operation; i++) {
            animalsPos2.get(i % num_of_valid_animals).move(instructions[i]);
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


}