package src.tasks.harbor;

import java.util.ArrayDeque;

class QueueShips {

    private final ArrayDeque<Ship> clientsQueue = new ArrayDeque();

    public synchronized void add(Ship ship){
        clientsQueue.addLast(ship);
    }

    public synchronized Ship take(){
        return clientsQueue.pollFirst();
    }
}
