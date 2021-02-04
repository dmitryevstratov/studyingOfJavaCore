package src.tasks.callCenter;

import src.tasks.bank.Helper;

class Client implements Runnable, ClientActions{

   private final String name;
   private boolean isRun = true;
   private final ClientsQueue clientsQueue;

    Client(String name, ClientsQueue clientsQueue) {
        this.name = name;
        this.clientsQueue = clientsQueue;
    }

    @Override
    public void speak() {
        for (int i = 0; i < 5; i++) {
            Helper.printMessage(getName() + " say: bla-bla-bla");
            Helper.sleep(200);
        }
    }

    @Override
    public void endCall() {
        Helper.printMessage(getName() + " end call");
    }

    @Override
    public void startCall() {
        Helper.printMessage(getName() + " call...");
    }

    @Override
    public void goToQueue() {
        setRun(false);
        clientsQueue.addClient(this);
        while (!isRun()){
            synchronized (this){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public boolean isRun() {
        return isRun;
    }

    public void setRun(boolean run) {
        isRun = run;
    }

    @Override
    public void call() {
        startCall();
        goToQueue();
        endCall();
    }

    @Override
    public void run() {
        call();
        if(Helper.getRandom(1) == 1){
            Dispatcher.incCountCallsOverPlan();
            Helper.sleep(Helper.getRandom(3000,4000));
            call();
        }
    }
}
