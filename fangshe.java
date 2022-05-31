package calculation;

import java.util.Random;

public class fangshe {
    public static void main(String[] args) {
        int[] miyao = random_miyao();
        System.out.println("密钥是"+3+"和"+7+"密文为：qxbxpluxvwhuzjct");
//        encrypt("zct",miyao);
      decode("qxbxpluxvwhuzjct",new int[]{3,7});
    }

    static int x = 0, y = 0;

    public static int gcd(int x, int y) {//求两数最大公约数，如果是1就是互素
        int t;
        while (y != 0) {
            t = x;
            x = y;
            y = t % y;
        }
        return x;
    }

    public static int euclid_2(int a, int b)//求逆运算
    {
        int first = a;
        int second = b;
        if (second == 0) {
            x = 1;
            y = 0;
            return first;
        }
        int r = euclid_2(second, first % second);
        int t = y;
        y = x - (first / second) * y;
        x = t;
        return x;   //结束循环
    }

    public static int[] random_miyao() {
        int[] res = new int[2];
        Random random = new Random();
        int k = 2;
        while (gcd(k, 26) != 1) {
            k = random.nextInt();
            k = Math.abs(k % 26);
        }
        res[0] = k;
        k = random.nextInt();
        k = Math.abs(k % 26);
        res[1] = k;
        return res;
    }

    public static int[] string_to_int(String mingwen) {
        int[] res = new int[mingwen.length()];
        String s = mingwen.toLowerCase();
        for (int i = 0; i < mingwen.length(); i++) {
            res[i] = s.charAt(i) - 'a';
        }
        return res;
    }

    public static void int_to_string(int[] ints) {
        String res = "";
        for (int i = 0; i < ints.length; i++) {
            System.out.println((char) (ints[i] + 'a'));
        }
    }

    public static void encrypt(String mingwen,int[] miyao) {
        int[] ints = string_to_int(mingwen);
        int[] res = new int[ints.length];
        for (int i = 0; i < ints.length; i++) {
            res[i] = ((miyao[0]*ints[i]+miyao[1])%26+26)%26;
        }
        int_to_string(res);
    }
    public static void decode(String miwen,int[] miyao){
        int k = euclid_2(miyao[0],26);
        int[] ints = string_to_int(miwen);
        int[] res = new int[ints.length];
        for (int i = 0; i < ints.length; i++) {
            res[i]=(((ints[i]-miyao[1])*((k+26)%26))%26+26)%26;
        }
        int_to_string(res);
    }


}
