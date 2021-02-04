package src.tasks.harbor;

import tasks.bank.Helper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Main {

    public static void main(String[] args) {

        QueueShips queueShips = new QueueShips();
        CargoFactory containerFactory = new ContainerFactory();
        Harbor harbor = Harbor.getInstance(WhoreHouse.getInstance(Helper.getRandom(200,250)));

        Helper.printMessage("Harbor is opened. Harbor capacity = " + harbor.getWhoreHouse().getCapacity() + " ton");

        ExecutorService threadPool = Executors.newFixedThreadPool(Dispatcher.PLAN + 2);
        for (int i = 0; i < 2; i++) {
            Pier pier = new Pier("Pier"+i, queueShips, harbor);
            threadPool.execute(pier);
        }

        if (Dispatcher.isHarborOpen()){
            for (int i = 0; i < Dispatcher.PLAN; i++) {
                Ship ship = new Ship("Ship"+i, WhoreHouse.getInstance(Helper.getRandom(130,200)), queueShips);
                ship.setStartCargo(containerFactory.createCargo(Helper.getRandom(1,2)), Helper.getRandom(40,90));
                threadPool.execute(ship);
                Helper.sleep(Helper.getRandom(1000,1500));
            }
        }
        threadPool.shutdown();
        while (true){
            try{
                if(threadPool.awaitTermination(10, TimeUnit.DAYS)){
                    break;
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Helper.printMessage("Harbor is closed");

    }

}
