package src.tasks.bank;

class Cashier implements Runnable, ActionsCashier, ActionsCash {

    private final String name;
    private final QueueClients queueClients;
    private final Bank bank;
    private volatile int cash = 1_000;

    public synchronized int getCash() {
        return cash;
    }

    public synchronized void setCash(int sum) {
        if(sum < 0){
            bank.getVault().getAndSet(bank.getVault().get() + sum);
            this.cash = 0;
        }else{
            this.cash = sum;
        }
        Helper.printMessage("In " + getName() + " cash = " + getCash() + ", in bank " + bank.getVault());
    }

    public synchronized void addCash(int sum) {
        this.cash += sum;
        Helper.printMessage("In " + getName() + " cash = " + getCash());
    }

    public Cashier(String name, QueueClients queueClients, Bank bank) {
        this.name = name;
        this.queueClients = queueClients;
        this.bank = bank;
    }

    public String getName() {
        return name;
    }

    @Override
    public void startWork() {
        Helper.printMessage(getName() + " start work");
    }

    @Override
    public void endWork() {
        Helper.printMessage(getName() + " end work");
    }

    @Override
    public void run() {
        startWork();
        while (Dispatcher.isClose()){
            synchronized (this){
                Client client = queueClients.take();
                if(client != null){
                    Helper.printMessage(getName() + " carries out an operation " + client.getOperation() +
                            " for " + client.getName()
                            + " cash = " + client.getCash()
                            + ", cash in account = "
                            + bank.getCashInAccount(client));

                    switch (client.getOperation()) {
                        case OperationsName.WITHDRAW:
                            bank.withdraw(client, Helper.getRandom(2000, 6000));
                            break;
                        case OperationsName.REPLENISH:
                            bank.replenish(client, Helper.getRandom(1000, 3000));
                            break;
                        case OperationsName.TRANSFER:
                            bank.transfer(client, Helper.getRandom(1000, 3000));
                            break;
                        case OperationsName.EXCHANGE:
                            int cashToExchange = Helper.getRandom(400, 2000);
                            Helper.printMessage(client.getName() + " want to exchange " + cashToExchange);
                            setCash(getCash() - cashToExchange);
                            client.addCash(cashToExchange);
                            break;
                        case OperationsName.PAY:
                            int cashToPay = Helper.getRandom(1000);
                            client.setCash(client.getCash() - cashToPay);
                            addCash(cashToPay);
                            break;
                    }
                    Helper.printMessage(client.getName() + " cash = " + client.getCash()
                            + " cash in account = " + bank.getCashInAccount(client));
                    Helper.sleep(Helper.getRandom(1000,2000));
                    //noinspection SynchronizationOnLocalVariableOrMethodParameter
                    synchronized (client){
                        client.setRun(true);
                        client.notify();
                    }
                }
            }
        }
        endWork();
    }

}
