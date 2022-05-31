package calculation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Cracker implements Runnable {

    private int start;
    private int end;
    private final MessageDigest digest = MessageDigest.getInstance("SHA-256");
    private static boolean DONE = false;
    private String found;

    public Cracker(int s, int e) throws NoSuchAlgorithmException {
        start = s;
        end = e;
    }

    public void generate(StringBuilder sb, int n) {
        if (DONE)
            return;

        if (n == sb.length()) {
            String candidate = sb.toString();
            // check password
            byte[] bytes = digest.digest(candidate.getBytes());

            if (Arrays.equals(bytes, SHA256.BYTES_SHA_256_TO_FIND)) {
                found = candidate;
                DONE = true;
            }

            return;
        }

        for (int i = 0; i < SHA256.ALPHABET.length && !DONE; i++) {
            char letter = SHA256.ALPHABET[i];
            sb.setCharAt(n, letter);
            generate(sb, n + 1);
        }

    }

    @Override
    public void run() {

        for (int length = start; length <= end && !DONE; length++) {
            StringBuilder sb = new StringBuilder();
            sb.setLength(length);
            generate(sb, 0);
        }

        if (DONE) {
            long duration = System.currentTimeMillis() - SHA256.START_TIME;
            if(found != null)
                System.out.println("\n[*] SUCCESS!\n[*] Password cracked in " + TimeUnit.MILLISECONDS.toSeconds(duration) + "." + TimeUnit.MILLISECONDS.toMillis(duration) + " sec. \n[*] Password is = " + found);
        } else {
            //System.out.println("[*] Password not found in task subset [" + start + " , " + end + "]");
        }
    }

}

public class SHA256 {

    public static char[] ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();


    public static final int PASSWORD_MAX_LENGTH = 12;
    //public static final String PASSWORD_SHA_256_TO_FIND = "8c9713d70976a2d1ea0c07a6d70998dc7da3ebca901f5b55cce470191bc86332";
    public static  String PASSWORD_SHA_256_TO_FIND = "2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824";


    public static  byte[] BYTES_SHA_256_TO_FIND = SHA256.hexStringToByteArray(PASSWORD_SHA_256_TO_FIND);
    public static long START_TIME;

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) +
                    Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {


        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("\n##### Brute Force SHA256 Cracker #####\n");
        System.out.println("[*]Enter SHA-256 Hash: ");
        Scanner cin = new Scanner(System.in);
        String hsh = cin.next();
        if(hsh.length() == 0){

            System.out.println("INVALID HASH");
            System.exit(-1);

        }
        else{
            PASSWORD_SHA_256_TO_FIND = hsh;
            BYTES_SHA_256_TO_FIND = SHA256.hexStringToByteArray(PASSWORD_SHA_256_TO_FIND);
        }
        System.out.println("\n[*] Starting with "+cores+" cores\n");
        System.out.println("[*] Cracking Hash "+ PASSWORD_SHA_256_TO_FIND+ " ...");

        int lengthSet = PASSWORD_MAX_LENGTH / cores;
        int end = 0;

        START_TIME = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(cores);

        while (end < PASSWORD_MAX_LENGTH) {
            int start = end + 1;
            end = start + lengthSet;

            if (end > PASSWORD_MAX_LENGTH)
                end = PASSWORD_MAX_LENGTH;

            executorService.submit(new Cracker(start, end));
            //System.out.println("[*] Submitting Task ["+start+" , "+end+"]");

        }
        System.out.println();

        executorService.shutdown();

    }

}