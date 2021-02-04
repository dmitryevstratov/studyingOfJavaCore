package src.tasks.lang.stringBiulder;

public class Main {

    public static void main(String[] args) {

        char[] chars = {122,110,124,125,126};
        StringBuilder sb1 = new StringBuilder("Manchester");
        StringBuilder sb2 = new StringBuilder("Manchester");
        String str = "United";
        System.out.println(sb1.append(20));
        System.out.println(sb1.append(chars));
        System.out.println(sb1.append(new Object()));
        System.out.println(sb1.delete(sb2.length(), sb1.length()));
        System.out.println(sb1 == sb2);
        System.out.println(sb1 + " - " + sb2);
        System.out.println(sb1.equals(sb2));
        System.out.println(sb1.insert(5, 20));
        System.out.println(sb1.append(" ").append(str));
        System.out.println(sb1.reverse());
        System.out.println(sb1.reverse().delete(sb2.length(),sb1.length()));
        sb1.delete(0,sb1.length());
        sb1.append(sb2);
        System.out.println(sb1.compareTo(sb2));
        StringBuffer sf = new StringBuffer();
    }

}
