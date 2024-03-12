package test;

import java.util.Random;
import java.util.Scanner;
public class T3 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入房间的总数n：");
        int roomLong=sc.nextInt();
        int left=0;
        int right=roomLong;
        Random r=new Random();
        //这里随机数学书所在房间号
        int room=r.nextInt(right)+1;
        while (true){
            //先将mid设置为一半
          int mid= (right+left)/2;
            System.out.println("你要探索的房间号为");
            int num=sc.nextInt();
            if(num>room) {
                System.out.println("-----------------数学书在"+num+"号房间的左边----------------");
                //if mid<room,则改变min的值（改变左边界
                if (mid < room) {//11
                    left = mid + 1;
                }
                //但这里不改变右边界的值，让输入的探索房间号num作为右边界，更加直接
                System.out.println("------神奇海螺特别提醒你：" +
                        "书所在的房间在" + left + "-" + num + "号之间------");
            }
            else if(num<room){
                System.out.println("---------------数学书在"+num+"房间的右边-----------------");
                //if mid>room,则改变roomLong的值(改变右边界
                if (mid > room) {//20
                    right = mid - 1;
                }
                //但这里不改变左边界的值，让输入的探索房间号num作为左边界，更加直接可以找出room与num的距离
                System.out.println("-----神奇海螺特别提醒你：" +
                        "书所在的房间在" + num + "-" + right + "号之间-----");
            }
              if(num==room){
                 System.out.println("恭喜你，找到了.数学书在"+num+"号房间");
                  break;
             }
        }
    }
}
