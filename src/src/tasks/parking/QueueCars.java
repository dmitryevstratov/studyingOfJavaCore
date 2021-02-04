package src.tasks.parking;

import java.util.ArrayDeque;

class QueueCars {

    private final ArrayDeque<Car> carsWait = new ArrayDeque<>();

    public synchronized void add(Car car){
        carsWait.addLast(car);
    }

    public synchronized Car take(){
        return carsWait.pollFirst();
    }

    public synchronized int getSize(){
        return carsWait.size();
    }

    public synchronized void removeCar(Car car){
        carsWait.remove(car);
    }


}
