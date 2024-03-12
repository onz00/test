package test.T1;

import java.util.ArrayList;

//  - 类和变量的命名要做到“见名知意”，注释记得写！
public class T1 {
    public static void main(String[] args) {
        //利用字符串来储存资产，这样多少资产都可以存（超过字符串的大小就真的离谱了）
        rich r1=new rich("10000000","a");
        rich r2=new rich("180020000","b");
        rich r3=new rich("1000000230","b");
        rich r4=new rich("16000000","d");
        rich r5=new rich("1700002300","e");
        rich r6=new rich("19000000","李");
        rich r7=new rich("1600000","李");
        ArrayList<rich> list=new ArrayList<>();
        //将这些东西放在集合里面
        list.add(r1);
        list.add(r2);
        list.add(r3);
        list.add(r4);
        list.add(r5);
        list.add(r6);
        list.add(r7);
        //这里利用了冒泡排序（虽然可能慢一点，但主打好玩！！！）
        for (int i = 0; i < list.size()-1; i++) {
            for (int j = 0; j < list.size()-i-1; j++) {
                //创建前索引和后索引的rich对象
                rich beforeRich=list.get(j);
                rich afterRich=list.get(j+1);
                //得到两个对象的资产
                String asset2=afterRich.getAsset();
                String asset1=beforeRich.getAsset();
                //自己写了个方法，这里将会得到那个资产大的字符串
               String bigAsset= compare(asset1,asset2);
                //得到那个资产大的字符串 进行判断，如果前面的大，那么放后面，
               if(bigAsset.equals(asset1)){
                   list.set(j,afterRich);
                   list.set(j+1,beforeRich);
               }
               //如果两个字符串一样大，进行名字比较（靠前的排后面，比如b排在a前面，（叛逆）
               if(bigAsset.equals("equation")){
                   //如果返回负数，返回before的姓氏靠前，排后面
                  int num= beforeRich.getName().compareTo(afterRich.getName());
                  if(num>=0){
                      list.set(j,beforeRich);
                      list.set(i,afterRich);
                  }
               }
            }
        }
       //遍历数组（从大到小输出）
        System.out.println("------------福布斯排行榜入榜名单!!!----------");
        for (int i = list.size()-1; i>=0; i--) {
            System.out.println(list.get(i));
        }
    }
   //返回那个大的字符串资产
    public static String compare(String asset1,String asset2){
        //先对比字符串的长度，如果长度不一样可以直接判断大小
        if(asset1.length()>asset2.length()){
        return asset1;
        }if(asset1.length()<asset2.length()){
            return asset2;
        }
        //两个长度一样则要去比较他们的大小
       char[] arrAsset1= asset1.toCharArray();
      //  Integer.parseInt()
       char[] arrAsset2= asset2.toCharArray();
       //利用字符去比较大小
        for (int i = 0; i < arrAsset1.length; i++) {
            //从123457，从1开始比，只要前面的是大的那么后面就不用比了，肯定大
            if(arrAsset1[i]>arrAsset2[i]){
                return asset1;
            }
            if(arrAsset1[i]<arrAsset2[i]) {
                return asset2;
            }
        }
        //如果执行到这还没停止证明两个字符串一样大，返回equation相等
        return "equation";
    }
}
