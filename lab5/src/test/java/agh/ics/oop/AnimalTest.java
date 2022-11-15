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



}