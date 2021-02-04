package src.tasks.callCenter;


import src.tasks.bank.Helper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Main {

    public static void main(String[] args) {

        ClientsQueue clientsQueue = new ClientsQueue();
        ExecutorService threadPool = Executors.newFixedThreadPool(Dispatcher.PLAN + 3);

        Helper.printMessage("Call center is opened");

        for (int i = 0; i < 3; i++) {
            Operator operator = new Operator("Operator №" + i, clientsQueue);
            threadPool.execute(operator);
        }

        for (int i = 0; i < Dispatcher.PLAN; i++) {
            Client client = new Client("Client №" + i, clientsQueue);
            threadPool.execute(client);
            Helper.sleep(Helper.getRandom(500, 1500));
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
        Helper.printMessage("Call center is closed");

    }

}
