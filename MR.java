package calculation;


import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

public class MR {
    public static boolean miller_rabin(BigInteger n){
        if (n.equals(BigInteger.valueOf(0))==true||n.equals(BigInteger.valueOf(1))==true) return false;
        if (n.equals(BigInteger.valueOf(2))==true) return true;
        int s=10;
        BigInteger k=n.subtract(BigInteger.valueOf(1));
        int t=0;
        //k.mod(BigInteger.valueOf(2)).equals(BigInteger.valueOf(1))!=true    取模判1
        while (k.getLowestSetBit()!=0){
            t++;
            k=k.divide(BigInteger.valueOf(2));
        }
        Random ran = new Random();
        while (s-->0){
            BigInteger a=new BigInteger(100,ran).mod( n.subtract(BigInteger.valueOf(2))).add(BigInteger.valueOf(2) );
            BigInteger[] x= new BigInteger[105];
            x[0]=a.modPow(k,n);
            //x[0]=qpow(a,k,n);
            for (int i=1;i<=t;i++){
                x[i]=x[i-1].modPow(BigInteger.valueOf(2),n);
                //x[i]=multi(x[i-1],x[i-1],n);
                if (x[i].equals(BigInteger.valueOf(1))==true&&x[i-1].equals(BigInteger.valueOf(1))!=true&&x[i-1].equals(n.subtract(BigInteger.valueOf(1)))!=true) return false;
            }
            if (x[t].equals(BigInteger.valueOf(1))!=true) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Scanner cin= new Scanner(System.in);
        byte[] arr = new byte[512];//512比特是64字节
        new SecureRandom().nextBytes(arr);

        System.out.println(convertBytesToHex(arr));
        BigInteger  n=new BigInteger(arr);
            if (miller_rabin(n)==true)
                System.out.println("Yes");
            else System.out.println("No");

        cin.close();
        long endTime = System.currentTimeMillis();
        System.out.println("程序执行耗时"+(endTime-startTime));
    }
    private static String convertBytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte temp : bytes) {
            result.append(String.format("%02x", temp));
        }
        return result.toString();
    }


}

