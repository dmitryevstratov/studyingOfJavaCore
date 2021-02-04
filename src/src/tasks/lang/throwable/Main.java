package src.tasks.lang.throwable;

public class Main {

    public void method(int a) throws MyException {

        int c = 20 / a;

    }

    public static void main(String[] args) {

        try {
            new Main().method(0);
        } catch (MyException e) {
            e.printStackTrace();
        }


    }

}
