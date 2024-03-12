package test.T5.mode;

import java.io.IOException;
import java.util.Scanner;

public class system {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        System.out.println("                                          这里输出相关信息（方便登录）                ");
        System.out.println("============用户信息===========");
        System.out.println(user.listMessage);
        System.out.println("============管理员信息===========");
        System.out.println(administrator.listAdministrator);
        System.out.println("其他信息可在用户里面的查看信息查看");
        leap:
        while (true) {
            System.out.println("----------------欢迎来到抽卡空间----------------");
            Scanner sc = new Scanner(System.in);
            System.out.println("请选择  1:我是用户 2:我是管理员 3:退出");
            String select = sc.next();
            switch (select) {
                case "1":
                    //调用用户入口
                user u=new user();
                u.user();
                    break;
                case "2":
                    //管理员入口
                administrator ad=new administrator();
                ad.administrator();
                    break;
                case "3":
                    break leap;
                default:
                    System.out.println("请重新输入");
                    break;
            }
        }
    }
}
