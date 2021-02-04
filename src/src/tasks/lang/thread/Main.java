package src.tasks.lang.thread;

public class Main {

    public static void main(String[] args) {

        ThreadOne threadOne1 = new ThreadOne("1");
        ThreadOne threadOne2 = new ThreadOne("2");
        ThreadTwo threadTwo1 = new ThreadTwo("1");
        ThreadTwo threadTwo2 = new ThreadTwo("2");

        Thread thread1 = new Thread(threadOne1);
        Thread thread2 = new Thread(threadOne2);
        threadTwo1.start();
        threadTwo2.start();
        thread1.start();
        thread2.start();
        try {
            System.out.println("main thread is started");
            thread1.join();
            thread2.join();
            System.out.println("main thread is finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
