package src.tasks.harbor;

import src.tasks.bank.Helper;

class Pier implements Runnable, PierActions{

    private final String name;
    private final QueueShips queueShips;
    private final Harbor harbor;

    public Pier(String name, QueueShips queueShips, Harbor harbor) {
        this.name = name;
        this.queueShips = queueShips;
        this.harbor = harbor;
    }

    public String getName() {
        return name;
    }

    @Override
    public void unloadFromShip(Ship ship) {
        while(ship.getWhoreHouse().getFullness() != 0){
            Cargo cargoFromShip = ship.getWhoreHouse().take();
            if (!harbor.getWhoreHouse().put(cargoFromShip)){
                ship.getWhoreHouse().put(cargoFromShip);
                Helper.printMessage("Harbor is filled");
                break;
            }
        }
    }

    @Override
    public void loadShip(Ship ship) {
        while (harbor.getWhoreHouse().getFullness() != 0){
            Cargo cargoFromHarbor = harbor.getWhoreHouse().take();
            if(!ship.getWhoreHouse().put(cargoFromHarbor)){
                harbor.getWhoreHouse().put(cargoFromHarbor);
                Helper.printMessage(ship.getName() + " is filled");
                break;
            }
        }
    }

    @Override
    public void startWork() {
        Helper.printMessage(getName() + " start work");
    }

    @Override
    public void endWork() {
        Helper.printMessage(getName() + " closed");
    }

    @Override
    public void run() {
        startWork();
        while (Dispatcher.isHarborOpen()){
            synchronized (this){
                Ship ship = queueShips.take();
                if(ship != null){
                    Helper.printMessage(getName() + " took " + ship.getName() + ". "+ "Harbor storage fullness = " + harbor.getWhoreHouse().getFullness() +
                            ", " + ship.getName() + " storage fullness = " + ship.getWhoreHouse().getFullness() + " (capacity = " + ship.getWhoreHouse().getCapacity() + ")");
                    if (ship.getNeedUnload() == 1){
                        Helper.printMessage(ship.getName() + " need to unload");
                        unloadFromShip(ship);
                    }
                    if(ship.getNeedLoad() == 1){
                        Helper.printMessage(ship.getName() + " need to load");
                        loadShip(ship);
                    }
                    if(ship.getNeedUnload() == 0 && ship.getNeedLoad() == 0){
                        Helper.printMessage(ship.getName() + " need to relaxation");
                    }
                    Helper.printMessage("Harbor storage fullness = " + harbor.getWhoreHouse().getFullness() +
                            ", " + ship.getName() + " storage fullness = " + ship.getWhoreHouse().getFullness());
                    //noinspection SynchronizationOnLocalVariableOrMethodParameter
                    synchronized (ship){
                        ship.setRun(true);
                        ship.notify();
                    }
                }
            }
        }
        endWork();
    }
}
