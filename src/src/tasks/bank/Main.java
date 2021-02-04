package src.tasks.bank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Main {

    public static void main(String[] args) {

        Bank bank = new Bank();
        QueueClients queueClients = new QueueClients();

        Helper.printMessage("Bank is open");
        ExecutorService threadPool = Executors.newFixedThreadPool(2 + Dispatcher.PLAN);
        for (int i = 0; i < 2; i++) {
            Cashier cashier = new Cashier("Кассир" + i, queueClients, bank);
            threadPool.execute(cashier);
        }

        if (Dispatcher.isClose()){
            for (int i = 0; i < 10; i++) {
                Client client = new Client("Клиент" + i, (i+25)*11/7, Helper.getRandom(1000, 3000), queueClients);
                // create account bean (new Account(number, name, sum))
                // add account in bank to all accounts (array)
                bank.openAccount(client.getAccount(), Helper.getRandom(5000, 10_000));
                threadPool.execute(client);
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
        Helper.printMessage("Bank is closed");

    }

}
