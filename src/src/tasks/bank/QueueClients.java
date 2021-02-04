package src.tasks.bank;

import java.util.ArrayDeque;

class QueueClients {

    private final ArrayDeque<Client> clientsQueue = new ArrayDeque();

    public synchronized void add(Client client){
        clientsQueue.addLast(client);
        Dispatcher.addClient();
    }

    public synchronized Client take(){
        return clientsQueue.pollFirst();
    }
}
