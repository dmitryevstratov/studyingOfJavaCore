package src.tasks.parking;

interface CarActions {

    void enteredInParking();
    void leftParking();
    void searchFreePlace();
    void toPark(ParkingPlace parkingPlace);
    void leftParkingPlace(ParkingPlace parkingPlace);
}
