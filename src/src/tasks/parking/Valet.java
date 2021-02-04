package src.tasks.parking;

import src.tasks.bank.Helper;

class Valet implements Runnable, ValetActions{

    private final String name;
    private final QueueCars queueCars;

    public Valet(String name, QueueCars queueCars) {
        this.name = name;
        this.queueCars = queueCars;
    }

    @Override
    public void startWork() {
        Helper.printMessage(getName() + " start work");
    }

    @Override
    public void endWork() {
        Helper.printMessage(getName() + " end work");
    }

    public String getName() {
        return name;
    }

    public QueueCars getQueueCars() {
        return queueCars;
    }

    @Override
    public void searchFreePlaceForCar() {
        if(getQueueCars().getSize() > 0 && Dispatcher.getCountFreePlaces() > 0){
            Car car = getQueueCars().take();
            ParkingPlace freeParkingPlace = Dispatcher.getFreeParkingPlace();
            //noinspection SynchronizationOnLocalVariableOrMethodParameter
            synchronized (car){
                Helper.printMessage(getName() + " found the place №" + freeParkingPlace.getNumber() +
                        " for " + car.getName());
                car.setRun(true);
                car.notify();
                car.toPark(freeParkingPlace);
                car.leftParkingPlace(freeParkingPlace);
            }
        }
    }

    @Override
    public void run() {
        startWork();
        while (Dispatcher.isParkingOpen()){
            searchFreePlaceForCar();
        }
        endWork();
    }
}
