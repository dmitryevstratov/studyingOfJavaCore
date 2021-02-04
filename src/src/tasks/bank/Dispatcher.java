package src.tasks.bank;

import java.util.concurrent.atomic.AtomicInteger;

class Dispatcher {

    public static final int K_SPEED = 1;
    public static final int PLAN = 10;
    private static final AtomicInteger completedClients = new AtomicInteger(0);
    private static final AtomicInteger clientsInBank = new AtomicInteger(0);

    public static boolean isClose(){
        return completedClients.get() != PLAN;
    }

    public static void addClient(){
        clientsInBank.getAndIncrement();
    }

    public static void addCompletedClient(){
        completedClients.getAndIncrement();
        clientsInBank.getAndDecrement();
    }
}
