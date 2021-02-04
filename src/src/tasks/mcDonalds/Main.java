package src.tasks.mcDonalds;

import tasks.bank.Helper;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        CopyOnWriteArrayList<Cashier> cashiersQueue = new CopyOnWriteArrayList();
        Dispatcher dispatcher = Dispatcher.getInstance();
        dispatcher.setCashiersQueue(cashiersQueue);
        ExecutorService threadPool = Executors.newFixedThreadPool(dispatcher.PLAN + dispatcher.COUNT_CASHIERS);

        Helper.printMessage("MackDonald's is open");

        for (int i = 0; i < dispatcher.COUNT_CASHIERS; i++) {
            Cashier cashier = new Cashier("Cashier №" + i, dispatcher);
            cashiersQueue.add(cashier);
            threadPool.execute(cashier);
        }

        for (int i = 0; i < dispatcher.PLAN; i++) {
            Client client = new Client("Client №" + i, cashiersQueue);
            threadPool.execute(client);
            Helper.sleep(Helper.getRandom(200,500));
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
        Helper.printMessage("MackDonald's is closed");

    }

}
