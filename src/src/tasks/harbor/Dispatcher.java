package src.tasks.harbor;

import java.util.concurrent.atomic.AtomicInteger;

class Dispatcher {

    public static final int PLAN = 20;
    private static final AtomicInteger completedShips = new AtomicInteger(0);

    public static void addCompletedShip(){
        completedShips.getAndIncrement();
    }

    public static boolean isHarborOpen(){
        return PLAN != completedShips.get();
    }

}
