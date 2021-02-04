package src.tasks.parking;

class ParkingPlace {

    private final int number;
    private Car car;
    private boolean isBusy;

    public ParkingPlace(int number) {
        this.number = number;
    }

    public void setCar(Car car) {
        this.car = car;
        this.isBusy = true;
    }

    public void setBusy(boolean busy) {
        if(busy){
            this.isBusy = true;
        }else{
            this.isBusy = false;
            this.car = null;
        }
    }

    public int getNumber() {
        return number;
    }

    public boolean isBusy() {
        return isBusy;
    }

}
