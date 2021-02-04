package src.tasks.bank;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class Bank {

    private final AtomicInteger vault = new AtomicInteger(1_000_000);
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final Map<Integer, Integer> accounts = new ConcurrentHashMap<>();

    public AtomicInteger getVault() {
        return vault;
    }

    public void openAccount(int number, int sum){
        accounts.put(number, sum);
    }

    public int getCashInAccount(Client client){
        return accounts.get(client.getAccount());
    }

    public void withdraw(Client client, int sum){
        if(getCashInAccount(client) < sum){
            client.addCash(getCashInAccount(client));
            accounts.put(client.getAccount(), 0);
        }else{
            client.addCash(sum);
            accounts.put(client.getAccount(), getCashInAccount(client) - sum);
        }
    }

    public void replenish(Client client, int sum){
        if(sum <= client.getCash()){
            accounts.put(client.getAccount(), getCashInAccount(client) + sum);
            client.setCash(client.getCash() - sum);
        }else{
            accounts.put(client.getAccount(), getCashInAccount(client) + client.getCash());
            client.setCash(0);
        }
    }

    public void transfer(Client client, int sum){
        if(accounts.keySet().size() > 1){
            for (Integer account : accounts.keySet()) {
                if(client.getAccount() != account){
                    int otherCashAccount = accounts.get(account);
                    if(getCashInAccount(client) >= sum){
                        accounts.put(account, otherCashAccount + sum);
                        accounts.put(client.getAccount(), getCashInAccount(client) - sum);
                    }else{
                        accounts.put(account, otherCashAccount + getCashInAccount(client));
                        accounts.put(client.getAccount(), 0);
                    }
                    break;
                }
            }
        }
    }
}
