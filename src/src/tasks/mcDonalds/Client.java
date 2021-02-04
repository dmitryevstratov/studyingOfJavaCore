package src.tasks.mcDonalds;


import src.tasks.bank.Helper;

import java.util.concurrent.CopyOnWriteArrayList;

public class Client implements Runnable, ClientActions{

    private final String name;
    private final CopyOnWriteArrayList<Cashier> cashiersArray;
    private boolean isRun = true;

    public Client(String name, CopyOnWriteArrayList<Cashier> cashiersArray) {
        this.name = name;
        this.cashiersArray = cashiersArray;
    }

    public String getName() {
        return name;
    }

    public CopyOnWriteArrayList<Cashier> getCashiersArray() {
        return cashiersArray;
    }

    public boolean isRun() {
        return isRun;
    }

    public void setRun(boolean run) {
        isRun = run;
    }

    @Override
    public void goToCashier() {
        setRun(false);
        while (!isRun()){
            Cashier cashier = getCashiersArray().get(Helper.getRandom(cashiersArray.size() - 1));
            Helper.printMessage(getName() + " go to queue to " + cashier.getName());
            cashier.getClientQueue().addClient(this);
            synchronized (this){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            toOrder();
        }
    }

    @Override
    public void toOrder() {
        Helper.printMessage(getName() + " want to order...");
        Helper.sleep(Helper.getRandom(2000,3000));
    }

    @Override
    public void entered() {
        Helper.printMessage(getName() + " entered in the mc");
        Helper.sleep(Helper.getRandom(500));
    }

    @Override
    public void left() {
        Helper.printMessage(getName() + " left the mc");
    }

    @Override
    public void run() {
        entered();
        goToCashier();
        left();
    }
}
