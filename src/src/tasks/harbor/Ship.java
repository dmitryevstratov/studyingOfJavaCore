package src.tasks.harbor;

import tasks.bank.Helper;

class Ship implements Runnable, ShipActions{

    private boolean isRun = true;
    private final String name;
    private final int needUnload;
    private final int needLoad;
    private final WhoreHouse whoreHouse;

    private final QueueShips queueShips;

    public Ship(String name, WhoreHouse whoreHouse, QueueShips queueShips) {
        this.name = name;
        this.needUnload = Helper.getRandom(1);
        this.needLoad = Helper.getRandom(1);
        this.whoreHouse = whoreHouse;
        this.queueShips = queueShips;
    }

    public String getName() {
        return name;
    }

    public int getNeedLoad() {
        return needLoad;
    }

    public int getNeedUnload() {
        return needUnload;
    }

    public WhoreHouse getWhoreHouse() {
        return whoreHouse;
    }

    @Override
    public void enterToHarbor() {
        Helper.printMessage(getName() + " entered in the harbor");
        Helper.sleep(Helper.getRandom(2000,3000));
    }

    @Override
    public void goOutFromHarbor() {
        Helper.printMessage(getName() + " left the harbor");
    }

    @Override
    public void goToPier() {
        Helper.printMessage(getName() + " go to the pier");
        Helper.sleep(Helper.getRandom(2000,3000));
        setRun(false);
        while (!isRun()){
            synchronized (this){
                queueShips.add(this);
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        Helper.printMessage(getName() + " left queue");
        Dispatcher.addCompletedShip();
    }

    @Override
    public synchronized void setStartCargo(Cargo cargo, int count) {
        for (int i = 0; i < count; i++) {
            whoreHouse.put(cargo);
        }
    }

    public void setRun(boolean run) {
        isRun = run;
    }

    public boolean isRun() {
        return isRun;
    }

    @Override
    public void run() {
        enterToHarbor();
        goToPier();
        goOutFromHarbor();
    }
}
