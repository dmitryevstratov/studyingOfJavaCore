package src.tasks.parking;

import src.tasks.bank.Helper;

class Car implements Runnable, CarActions{

    private final String name;
    private boolean isRun;
    private final QueueCars queueCars;

    public String getName() {
        return name;
    }

    public Car(String name, QueueCars queueCars) {
        this.name = name;
        this.queueCars = queueCars;
    }

    @Override
    public void enteredInParking() {
        Helper.printMessage(getName() + " entered in the parking");
        Helper.sleep(Helper.getRandom(500,1000));
    }

    @Override
    public void toPark(ParkingPlace parkingPlace) {
        parkingPlace.setCar(this);
        Dispatcher.incCountFreePlaces();
        Helper.printMessage(getName() + " parked in place №" + parkingPlace.getNumber() + ". Free places: " + Dispatcher.getCountFreePlaces());
        Helper.sleep(Helper.getRandom(3000,5000));
    }

    @Override
    public void leftParkingPlace(ParkingPlace parkingPlace) {
        parkingPlace.setBusy(false);
        Dispatcher.decCountFreePlaces();
        Helper.printMessage(getName() + " left parking place №" + parkingPlace.getNumber() + ". Free places: " + Dispatcher.getCountFreePlaces());
    }

    @Override
    public void leftParking() {
        Dispatcher.incCompletedCars();

        Helper.printMessage(getName() + " left the parking");
    }

    @Override
    public void searchFreePlace() {
        ParkingPlace parkingPlace = Dispatcher.getFreeParkingPlace();
        if(parkingPlace != null){
            toPark(parkingPlace);
            leftParkingPlace(parkingPlace);
        }else{
            setRun(false);
            while (!isRun){
                synchronized (this){
                    try {
                        Helper.printMessage(getName() + " wait...");
                        queueCars.add(this);
                        wait(5000);
                        if(!isRun){
                            setRun(true);
                            queueCars.removeCar(this);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public void setRun(boolean run) {
        isRun = run;
    }

    @Override
    public void run() {
        enteredInParking();
        searchFreePlace();
        leftParking();
    }

}
