package src.tasks.mcDonalds;

import tasks.bank.Helper;

import java.util.Objects;

public class Cashier implements Runnable, CashierActions{

    private final String name;
    private final ClientsQueue clientQueue = new ClientsQueue();
    private final Dispatcher dispatcher;

    public Cashier(String name, Dispatcher dispatcher) {
        this.name = name;
        this.dispatcher = dispatcher;
    }

    public String getName() {
        return name;
    }

    public ClientsQueue getClientQueue() {
        return clientQueue;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    @Override
    public void startWork() {
        Helper.printMessage(getName() + " started work");
    }

    @Override
    public void endWork() {
        Helper.printMessage(getName() +  " finished work");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cashier cashier = (Cashier) o;
        return Objects.equals(name, cashier.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public void run() {
        startWork();
        while (dispatcher.isMcOpen() || clientQueue.getSize() > 0){
            dispatcher.alignQueue();
            synchronized (this){
                Client client = clientQueue.getClient();
                if(client != null){
                    Helper.printMessage(getName() + " service " + client.getName() + ". Clients in queue: " + clientQueue.getSize());
                    //noinspection SynchronizationOnLocalVariableOrMethodParameter
                    synchronized (client){
                        client.setRun(true);
                        client.notify();
                        Helper.sleep(Helper.getRandom(500,2000));
                        dispatcher.incCompletedClients();
                    }
                }
            }
        }
        endWork();
    }
}
