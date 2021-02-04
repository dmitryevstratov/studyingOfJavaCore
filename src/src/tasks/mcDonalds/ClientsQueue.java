package src.tasks.mcDonalds;

import java.util.ArrayDeque;
import java.util.Deque;

class ClientsQueue {

    private final Deque<Client> clientQueue = new ArrayDeque<>();

    public synchronized void addClient(Client client){
        clientQueue.addLast(client);
    }

    public synchronized Client getClient(){
        return clientQueue.pollFirst();
    }

    public synchronized int getSize(){
        return clientQueue.size();
    }

    public synchronized Client getLastClient(){
        return clientQueue.pollLast();
    }

}
