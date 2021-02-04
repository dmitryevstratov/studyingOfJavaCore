package src.tasks.lang.stringClass;

class Main {

    public static void main(String[] args) {

        String str1 = new String("K ex");
        String str2 = "Kex";
        String str3 = str2;
        String str4 = new String(str2);
        char[] chars = {122,113,114,100,103,105,110};
        int[] ints = {110, 115, 120, 122};
        byte[] bytes = {110,114};
        StringBuilder sb = new StringBuilder("Kex-shmex");
        StringBuffer sf = new StringBuffer("Kexy rexy");
        String str5 = new String(chars);
        String str6 = new String(chars, 2,4);
        String str7 = new String(ints, 0, ints.length);
        String str8 = new String(bytes);
        String str9 = new String(sb);
        String str10 = new String(sf);

        System.out.println(str1.length());
        char x = str1.charAt(2);
        System.out.println(x);
        System.out.println(str2.codePointAt(1));
        char[] chars1 = new char[10];
        byte[] bytes1 = new byte[10];
        sf.getChars(1,sf.length(), chars1, 0);
        System.out.println(chars1);
        byte[] bytes2 = str4.getBytes();
        System.out.println(bytes2);
        System.out.println(str1.compareTo(str2));
        System.out.println(str1.compareToIgnoreCase(str2));
        System.out.println(str1.startsWith("e", 1));
        System.out.println(str9.endsWith("x"));
        System.out.println(str10.indexOf("e"));
        System.out.println(str10.lastIndexOf("e"));
        String subStr = str10.substring(2,6);
        System.out.println(subStr);
        CharSequence charSequence = str10.subSequence(1, 8);
        System.out.println(charSequence);
        String strConcatStr1Str10 = str1.concat(str10);
        System.out.println(strConcatStr1Str10);
        System.out.println(strConcatStr1Str10.contains(str1));
        System.out.println(strConcatStr1Str10);
        System.out.println(str1.intern() == str2);
        System.out.println(str1.startsWith("Kex"));
        System.out.println(str1.contentEquals(str2));
        System.out.println(str1.repeat(2));
        System.out.println(str1.isBlank());
        String join = String.join(".", str1,str2,str3);
        System.out.println(join);


    }

}
