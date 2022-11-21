package agh.ics.oop;

import java.util.ArrayList;


public class SimulationEngine implements IEngine {
    private final MoveDirection[] instructions;
    private final ArrayList<Animal> animalsPos = new ArrayList<>();

    public ArrayList<Animal> getAnimalsPos() {
        return animalsPos;
    }

    public SimulationEngine(MoveDirection[] instructions, IWorldMap map, Vector2d[] animalsInitPos) throws IllegalArgumentException {
        this.instructions = instructions;
        for (Vector2d pos : animalsInitPos) {
            Animal currAnimal = new Animal(map, pos);
            map.place(currAnimal);
            this.animalsPos.add(currAnimal);
        }

    }

    @Override
    public void run() {
        int num_of_operation = this.instructions.length;
        int num_of_valid_animals = this.animalsPos.size();
        for (int i = 0; i < num_of_operation; i++) {
            this.animalsPos.get(i % num_of_valid_animals).move(this.instructions[i]);
        }
    }
}
