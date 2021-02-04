package src.tasks.callCenter;

import java.util.concurrent.atomic.AtomicInteger;

class Dispatcher {

    public static final int PLAN = 5;
    private static final AtomicInteger countCallsOverPlan = new AtomicInteger(0);
    private static final AtomicInteger clientsCompleted = new AtomicInteger(0);

    public static void addCompletedClient(){
        clientsCompleted.getAndIncrement();
    }

    public static boolean isOpenCallCenter() {
        return clientsCompleted.get() < PLAN + countCallsOverPlan.get();
    }

    public static void incCountCallsOverPlan(){
        countCallsOverPlan.getAndIncrement();
    }
}
