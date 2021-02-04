package src.tasks.bank;

class Operation {

    private static final String[] operations = {OperationsName.EXCHANGE, OperationsName.PAY, OperationsName.REPLENISH, OperationsName.TRANSFER, OperationsName.WITHDRAW};


    public static String getOperation() {
        return operations[Helper.getRandom(operations.length-1)];
    }
}
