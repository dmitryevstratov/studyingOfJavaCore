package src.tasks.harbor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;

public class WhoreHouse implements WhoreHouseActions{

    private final int capacity;

    private final AtomicInteger fullness = new AtomicInteger(0);
    private final Deque<Cargo> storage = new ArrayDeque<>();

    private WhoreHouse(int capacity) {
        this.capacity = capacity;
    }

    public static WhoreHouse getInstance(int weight){
        return new WhoreHouse(weight);
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getFullness() {
        return this.fullness.get();
    }

    @Override
    public Cargo take() {
        Cargo cargo = this.storage.pollFirst();
        if(cargo != null){
            this.fullness.getAndSet(this.fullness.get() - cargo.getWeightCargo());
        }
        return cargo;
    }

    @Override
    public boolean put(Cargo cargo) {
        if(this.capacity >= this.fullness.get() + cargo.getWeightCargo() && cargo != null){
            this.storage.addLast(cargo);
            this.fullness.getAndAdd(cargo.getWeightCargo());
            return true;
        }
        return false;
    }
}
