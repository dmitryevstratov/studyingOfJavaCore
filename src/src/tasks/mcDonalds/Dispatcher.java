package src.tasks.mcDonalds;

import tasks.bank.Helper;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Dispatcher{

    private CopyOnWriteArrayList<Cashier> cashiersQueue;
    private final AtomicInteger clientsCompleted = new AtomicInteger(0);
    public final int PLAN = 40;
    public final int COUNT_CASHIERS = 2;

    private static class DispatcherSingleton{
        private static final Dispatcher DISPATCHER_INSTANCE = new Dispatcher();
    }

    public static Dispatcher getInstance(){
        return DispatcherSingleton.DISPATCHER_INSTANCE;
    }

    public CopyOnWriteArrayList<Cashier> getCashiersQueue() {
        return cashiersQueue;
    }

    public void setCashiersQueue(CopyOnWriteArrayList<Cashier> cashiersQueue) {
        this.cashiersQueue = cashiersQueue;
    }

    public boolean isMcOpen(){
        return clientsCompleted.get() < PLAN;
    }

    public void incCompletedClients(){
        clientsCompleted.getAndIncrement();
    }

    public synchronized Cashier getCashierWithMaxQueue(){
        Cashier cashier = null;
        int queueSize = cashiersQueue.get(Helper.getRandom(cashiersQueue.size()-1)).getClientQueue().getSize();
        for (Cashier cas : getCashiersQueue()) {
            if(cas.getClientQueue().getSize() > queueSize){
                queueSize += cas.getClientQueue().getSize();
                cashier = cas;
            }
        }
        return cashier;
    }

    public synchronized Cashier getCashierWithMinQueue(){
        Cashier cashier = null;
        int queueSize = cashiersQueue.get(Helper.getRandom(cashiersQueue.size()-1)).getClientQueue().getSize();
        for (Cashier cas : getCashiersQueue()) {
            if(cas.getClientQueue().getSize() < queueSize){
                queueSize += cas.getClientQueue().getSize();
                cashier = cas;
            }
        }
        return cashier;
    }

    public synchronized void alignQueue(){
        Cashier cashierWithMaxQueue = getCashierWithMaxQueue();
        Cashier cashierWithMinQueue = getCashierWithMinQueue();
        if(cashierWithMaxQueue != null && !cashierWithMaxQueue.equals(cashierWithMinQueue)){
            if(cashierWithMinQueue != null){
                for (Cashier cashier : getCashiersQueue()) {
                    if(cashier.equals(cashierWithMinQueue)){
                        clientToChangeQueue(cashierWithMaxQueue, cashier);
                        break;
                    }
                }
            }else{
                for (Cashier cashier : getCashiersQueue()) {
                    if(!cashier.equals(cashierWithMaxQueue)){
                        clientToChangeQueue(cashierWithMaxQueue, cashier);
                        break;
                    }
                }
            }
        }
    }

    private void clientToChangeQueue(Cashier cashierWithMaxQueue, Cashier cashier) {
        Client lastClient = cashierWithMaxQueue.getClientQueue().getLastClient();
        cashier.getClientQueue().addClient(lastClient);
    }

}
