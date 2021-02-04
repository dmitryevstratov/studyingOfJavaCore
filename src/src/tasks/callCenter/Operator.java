package src.tasks.callCenter;


import src.tasks.bank.Helper;

class Operator implements Runnable, OperatorActions{

    private final String name;
    private final ClientsQueue clientsQueue;

    public Operator(String name, ClientsQueue clientsQueue) {
        this.name = name;
        this.clientsQueue = clientsQueue;
    }

    public String getName() {
        return name;
    }

    @Override
    public void startWork() {
        Helper.printMessage(getName() + " ready to work");
    }

    @Override
    public void talkWithClient(Client client) {
        client.speak();
    }

    @Override
    public void endWork() {
        Helper.printMessage(getName() + " closed");
    }

    @Override
    public void run() {
        startWork();
        while (Dispatcher.isOpenCallCenter() || clientsQueue.getSize() > 0){
            synchronized (this){
                Client client = clientsQueue.getClient();
                if(client != null){
                    Helper.printMessage(getName() + " connected with " + client.getName());
                    talkWithClient(client);
                    //noinspection SynchronizationOnLocalVariableOrMethodParameter
                    synchronized (client){
                        client.setRun(true);
                        client.notify();
                        Dispatcher.addCompletedClient();
                    }
                }
            }
        }
        endWork();
    }
}
