package src.tasks.parking;

import java.util.ArrayList;

class Parking {

    private final ArrayList<ParkingPlace> parkingPlaces = new ArrayList<>();

    private static class ParkingSingleton{
        private static final Parking PARKING_INSTANCE = new Parking();
    }

    public static Parking getInstance(){
        return ParkingSingleton.PARKING_INSTANCE;
    }

    public ArrayList<ParkingPlace> getParkingPlaces() {
        return parkingPlaces;
    }
}
