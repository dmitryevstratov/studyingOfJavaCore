package src.tasks.bank;

class Client implements Runnable, ActionsClient, ActionsOperation, ActionsCash {

    private final String name;
    private final int account;
    private boolean isRun = true;
    private volatile int cash;
    private final QueueClients queueClients;
    private String operation;

    public Client(String name, int account, int sum, QueueClients queueClients) {
        this.name = name;
        this.account = account;
        this.cash = sum;
        this.queueClients = queueClients;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }


    public int getAccount() {
        return account;
    }

    public void setRun(boolean run) {
        isRun = run;
    }

    public String getName() {
        return name;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int sum){
        if(this.cash >= sum){
            this.cash = sum;
        }else{
            this.cash = 0;
        }
    }

    public void addCash(int sum){
        //noinspection NonAtomicOperationOnVolatileField
        this.cash += sum;
    }

    @Override
    public void entered() {
        Helper.printMessage(getName() + " entered in bank");
    }

    @Override
    public void left() {
        Helper.printMessage(getName() + " left bank");
    }

    @Override
    public void goToQueue() {
        Helper.printMessage(getName() + " go to queue");
        Helper.sleep(Helper.getRandom(500, 1000));
        setRun(false);
        while (!isRun){
            synchronized (this){
                queueClients.add(this);
                Dispatcher.addClient();
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Helper.printMessage(getName() + " left queue");
        Dispatcher.addCompletedClient();
    }

    @Override
    public void run() {
        entered();
        setOperation(Operation.getOperation());
        goToQueue();
        left();
    }
}
