package src.tasks.parking;

import tasks.bank.Helper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Main {

    public static void main(String[] args) {

        Parking parking = Parking.getInstance();
        QueueCars queueCars = new QueueCars();
        Dispatcher.setParking(parking);
        Valet valet = new Valet("Вася", queueCars);
        ExecutorService executor = Executors.newFixedThreadPool(20+1);

        executor.execute(valet);

        for (int i = 1; i <= Dispatcher.ALL_PLACES; i++) {
            parking.getParkingPlaces().add(new ParkingPlace(i));
        }

        for (int i = 1; i <= 20; i++) {
            Car car = new Car("Car-" + i, queueCars);
            executor.execute(car);
            Helper.sleep(Helper.getRandom(500,1000));
        }
        executor.shutdown();
    }

}
