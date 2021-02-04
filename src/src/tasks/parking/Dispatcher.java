package src.tasks.parking;

import java.util.concurrent.atomic.AtomicInteger;

class Dispatcher{

    private static Parking parking;
    public static final int ALL_PLACES = 3;
    public static final int PLAN = 20;
    private static final AtomicInteger countFreePlaces = new AtomicInteger(0);
    private static final AtomicInteger completedCars = new AtomicInteger(0);

    public static void setParking(Parking park) {
        parking = park;
    }

    public static ParkingPlace getFreeParkingPlace(){
        synchronized (Dispatcher.class){
            ParkingPlace place = null;
            for (ParkingPlace parkingPlace : parking.getParkingPlaces()) {
                if(!parkingPlace.isBusy()){
                    place = parkingPlace;
                    break;
                }
            }
            return place;
        }
    }

    public static int getCountFreePlaces() {
        return ALL_PLACES - countFreePlaces.get();
    }

    public static void incCountFreePlaces(){
        countFreePlaces.getAndIncrement();
    }

    public static void decCountFreePlaces(){
        countFreePlaces.getAndDecrement();
    }

    public static void incCompletedCars(){
        completedCars.getAndIncrement();
    }

    public static int getCompletedCars(){
        return completedCars.get();
    }

    public static boolean isParkingOpen(){
        return PLAN != getCompletedCars();
    }

}
