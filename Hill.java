package calculation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hill {
    public static void main(String[] args) {
        int[] encrypt_array = encrypt_array();
        int[] decode_array = decode_array(encrypt_array);
        String mingwen = "iloveu";
        StringBuilder miwen = new StringBuilder();
        int[] ints = string_to_int(mingwen);
        System.out.println("明文是"+mingwen);

        for (int i = 0; i < ints.length; i++) {
           miwen.append((char)(encrypt_array[ints[i]]+'a'));
        }
        System.out.println("密文为："+miwen);
        System.out.println("解密后为：");
        int[] ints1 = string_to_int(miwen.toString());
        for (int i = 0; i < ints1.length; i++) {
            System.out.println((char)(decode_array[ints1[i]]+'a'));
        }

    }

    public static int[] encrypt_array(){
        int MAXNUM=25;
         List<Integer> integers = new ArrayList<>();//创建个集合用来存储
        for (int i = 0; i <= MAXNUM; i++) {
            integers.add(i);
        }
        Collections.shuffle(integers);//打乱集合,这样就相当于随机生成了一组0-25的随机数
            Integer[] t = new Integer[integers.size()];
            integers.toArray(t);
            int[] res = new int[26];
        for (int i = 0; i <=MAXNUM; i++) {
            res[i] = t[i];
        }
        return res;
    }
    public static int[] decode_array(int[] encry){
        int[] res = new int[26];
        for (int i =0;i<=25;i++){
            res[encry[i]] = i;
        }
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
    @Test
    public void test(){
        String str = "Per zlrracm, vxmcs r qipqlczhs. Qs fcv rihw sxx\n" +
                "hblrxh sm nkidhvzphw. Ixxvn qsn, lysh sifecs uui\n" +
                "jrrfyg, mk xj suvc kd ss wbrzrrz uqh jpp zyw qv\n" +
                "ylgn osfz fin isi bpgyoj, fg dm zdqzap, cl sifecs\n" +
                "qks cdfy iu xyxey iu tipp zcni dt. Sin lj nt rfy jszcx\n" +
                "hi jik iyfixky iysmh hzuwwwxpk izayv; mw lv olh\n" +
                "kfxeu nr gitrhy d afgcr qkiit vjyucsdum bdw kwv\n" +
                "cjssiilbcwc kd wwhg e ads, ohg ewuffx fscavuy; lj\n" +
                "nt rfy jszcx hi vemt kvy hrmxichpiei rbx giwtrh\n" +
                "zxxlgv duqhvbzqm, wlvc ns uui xdzba ws ypms\n" +
                "nr hf xk hijikwvf.";
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isLetter(chars[i])){
                char c = Character.toUpperCase(chars[i]);
                sb.append(c);
            }
        }
        System.out.println(sb);
    }
}
