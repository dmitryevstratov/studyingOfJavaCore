package src.tasks.lang.voidClass;

class Main {

    public void foo() {}

    public static void main(String[] args) {

        Main v = new Main();
        try {
            if (v.getClass().getMethod("foo").getReturnType() == Void.TYPE){
                System.out.println(Void.TYPE);
            }else{
                System.out.println(false);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

}
