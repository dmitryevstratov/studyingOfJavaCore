package src.tasks.lang.thread;

public class ThreadTwo extends Thread{

    private final String name;

    public ThreadTwo(String name) {
        this.name = name + " ext Thread";
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
