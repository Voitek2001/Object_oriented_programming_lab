package agh.ics.oop;

import java.util.ArrayList;


class SimulationEngine implements IEngine {
    private final MoveDirection[] instructions;
    private final IWorldMap map;
    private final ArrayList<Animal> animalsPos = new ArrayList<>();

    public ArrayList<Animal> getAnimalsPos() {
        return animalsPos;
    }

    public SimulationEngine(MoveDirection[] instructions, IWorldMap map, Vector2d[] animalsInitPos) {
        this.instructions = instructions;
        this.map = map;
        for (Vector2d pos : animalsInitPos) {
            Animal curr_animal = new Animal(this.map, pos);
            if (!map.place(curr_animal)) {
                System.out.printf("Pole (%d, %d) jest zajete\n", pos.x(), pos.y());
                continue;
            }
            this.animalsPos.add(curr_animal);
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
