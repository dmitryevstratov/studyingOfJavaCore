package src.tasks.lang.thread;

public class ThreadOne implements Runnable{

    private final String name;

    public ThreadOne(String name) {
        this.name = name + " impl Runnable";
    }

    @Override
    public void run() {

        try {
            System.out.println(name + " started");
            Thread.sleep(4000);
            System.out.println(name + " finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
