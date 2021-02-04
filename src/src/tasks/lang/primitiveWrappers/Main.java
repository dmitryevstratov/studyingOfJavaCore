package src.tasks.lang.primitiveWrappers;

class Main {

    public static void main(String[] args) {

        Integer i = new Integer("100j");
        Double d = Double.valueOf(2.4);
        Float f = Float.valueOf(1.5f);
        Character c = Character.valueOf('4');
        Short s = Short.valueOf(String.valueOf(10));
        Byte b = Byte.valueOf(String.valueOf(1));
        Boolean aBoolean = new Boolean("trey");
        Long l = Long.valueOf(200000);


    }

}
