package src.tasks.lang.throwable;

public class Main {

    public static void main(String[] args) {

        try {
            MyException e = new MyException();
            e.initCause(new NullPointerException("Actual cause"));
            throw e;
        } catch (MyException e) {
            System.out.println(e.getCause());
        }


    }

}
