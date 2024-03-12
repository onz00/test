package test;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.security.Key;
import java.sql.SQLOutput;
import java.util.*;

public class T2 {
    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
        System.out.println("请输入字符串1");
       String letter1=sc.next();//如AAB
        System.out.println("请输入字符串2");
       String letter2=sc.next();//ABBC
       //得到两个字符串对应的表  如letter1：A-2 B-1
        //                      letter2：A-1 B-2 C-1
       HashMap hm1=statistic(letter1);
       HashMap hm2=statistic(letter2);
       String addResult=add(hm1, hm2);
       String multiplyResult=multiply(hm1,hm2);
        System.out.println("和为"+addResult);
        System.out.println("乘积为"+multiplyResult);
    }
    //获得letter中字母出现的相应次数并储存在HashMap表中（如A-3  B-4)
    public static HashMap statistic(String letter){
        //将单词转为字符串数组
        //A-2,B-1  A-2,B-0;
        String []arr=new String[letter.length()];
        for (int i = 0; i < letter.length(); i++) {
             char a=letter.charAt(i);
            String ge=String.valueOf(a);
             arr[i]=ge;
        }
        HashMap<String,Integer>hm=new HashMap<>();
        for(String l:arr){
            if(hm.containsKey(l)){
                int count=hm.get(l);
                count++;
                hm.put(l,count);
            }else {
                hm.put(l,1);
            }
        }
        return hm;
    }

    //得到所有的集合中所有的键名如：letter为AABBC 则allKey为ABC
    public static String[] getAllKey(HashMap hm1){
        //a b
        //2 3
        String[] allKey =new String[hm1.size()];
        //  Set<String>keys=hm1.keySet();
        Set<Map.Entry<String,Integer>>entries= hm1.entrySet();
        int count=0;
        for(Map.Entry<String, Integer> entry:entries){
            allKey[count]=entry.getKey();
            count++;
        }
        return allKey;
    }
    //得到键对应的次数letter1:AABBC（这里的次数刚好对应allKey数组的对应 如allKey为ABC 则allValue为2，2，1
    public static int[] getAllValue(HashMap hm1){
        int[] allValue =new int[hm1.size()];
        Set<Map.Entry<String,Integer>>entries= hm1.entrySet();
        int count=0;
        for(Map.Entry<String, Integer> entry:entries){
            allValue[count]= entry.getValue();
            count++;
        }
        return allValue;
    }
    //这里进行加法
    public static String  add( HashMap hm1, HashMap hm2){
        int max=Math.max(hm1.size(),hm2.size());
        String result;
        if(max==hm1.size()) {
            //如果hm1的长度比较大，那么在嵌套循环中应该是外循环
            result=add1(hm1,hm2);
        }
        else{
            //如果hm2的长度比较大，那么在嵌套循环中应该是外循环
            result= add1(hm2,hm1);
        }
        return result;
    }
    //这里是对加法的运算
    public static String add1(HashMap hm1, HashMap hm2){
        //得到hm的键名和hm键名对应的次数数组
        String []arrKey1=getAllKey(hm1);
        String []arrKey2=getAllKey(hm2);
        int []arrValue1=getAllValue(hm1);
        int []arrValue2=getAllValue(hm2);
        StringBuilder sb=new StringBuilder();
        //计算是否有对应的元素（
        int count=0;
        for (int i = 0; i < hm1.size(); i++) {
            for (int i1 = 0; i1 < hm2.size(); i1++) {
                //如果两个键名相等，则去相加（利用对应的值去相加，因为值代表的是次数）
                if (arrKey1[i].equals(arrKey2[i1])) {
                    int sum = arrValue1[i] + arrValue2[i1];
                    //有几次就加几次
                    for (int i2 = 0; i2 < sum; i2++) {
                        sb.append(arrKey1[i]);
                        count++;
                    }
                }
            }
            //如果内循环结束后count=0则代表hm2没有该元素，自己进行计算
            if (count == 0) {
                for (int i1 = 0; i1 < arrValue1[i]; i1++) {
                    sb.append(arrKey1[i]);
                }
            }
            //方便下一循环
            count = 0;
        }
        return sb.toString();
    }
    //这里进行*法
    public static String multiply(HashMap hm1, HashMap hm2){
        int max=Math.max(hm1.size(),hm2.size());
        String result;
        if(max==hm1.size()){
            //理由同加法
        result= multiply1(hm1,hm2);
        } else{
            result=multiply1(hm2,hm1);
        }
        return result;
    }
    //*法的运算
    public static String multiply1(HashMap hm1, HashMap hm2){
        String []arrKey1=getAllKey(hm1);
        String []arrKey2=getAllKey(hm2);
        int []arrValue1=getAllValue(hm1);
        int []arrValue2=getAllValue(hm2);
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < hm1.size(); i++) {
            for (int i1 = 0; i1 < hm2.size(); i1++) {
                if (arrKey1[i].equals(arrKey2[i1])) {
                    int sum = arrValue1[i] * arrValue2[i1];
                    for (int i2 = 0; i2 < sum; i2++) {
                        sb.append(arrKey1[i]);
                    }
                }
            }
        }
        return sb.toString();
    }
}



