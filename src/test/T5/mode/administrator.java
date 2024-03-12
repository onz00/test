package test.T5.mode;

import test.T5.Exception.QQFormatException;
import test.T5.class1.administratorMessage;
import test.T5.class1.goods;
import test.T5.class1.userMessage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class administrator {
    static ArrayList<administratorMessage> listAdministrator;

    static {
        try {
            listAdministrator = getListAdministratorMessage();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static ArrayList<goods> listGoods;

    public static void administrator() throws IOException, ClassNotFoundException {
        System.out.println("欢迎来到管理员界面");
        Scanner sc = new Scanner(System.in);
        leap:
        while (true) {
            System.out.println("1:登录  2 退出");
            String select = sc.nextLine();
            switch (select) {
                case "1":
                    int index = log();
                    if (index >= 0) {
                        leap2:
                        while (true) {
                            //作用：让其调用不同的写入路径进行保存
                            String selectGoods;
                            while (true) {
                                System.out.println("请选择要修改的奖池：a：csgo奖品池 b: 原神角色奖品池 c: 元神武器奖品池 e:不玩了");
                                String prop = sc.nextLine();
                                if (prop.equals("a")) {
                                    listGoods = user.getListGoodsA();
                                    selectGoods = "a";
                                    break;
                                } else if (prop.equals("b")) {
                                    listGoods = user.getListGoodsB();
                                    selectGoods = "b";
                                    break;
                                } else if (prop.equals("c")) {
                                    listGoods = user.getListGoodsC();
                                    selectGoods = "c";
                                    break;
                                }
                                if(prop.equals("e")){
                                    break leap;
                                }
                                System.out.println("输入有误，请重新输入");
                            }
                            System.out.println("欢迎进入内测：  1：修改物品信息 2：增加物品 3：删除物品 4：退出");
                            String select2 = sc.nextLine();
                            switch (select2) {
                                case "1":
                                    remove(selectGoods);
                                    break;
                                case "2":
                                    add(selectGoods);
                                    break;
                                case "3":
                                    delete(selectGoods);
                                    break;
                                case "4":
                                    break leap2;
                                default:
                                    System.out.println("输入有误");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("登录失败");
                    }
                    break;
                case "2":
                    break leap;
                default:
                    break;
            }
        }
    }

    public static void remove(String selectGoods) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
       leap: while (true) {
            //输出奖池的信息
            System.out.println("奖品池"+selectGoods);
            user.inquireProps11(listGoods);
            System.out.println("请选择要修改的奖池物品信息 1：物品等级 2：物品功能 3：品质 4：介绍 5:退出");
            String select = sc.nextLine();
            switch (select) {
                //根据不同的judge去修改不同的属性
                case "1":
                    remove1(1, selectGoods);
                    break;
                case "2":
                    remove1(2, selectGoods);
                    break;
                case "3":
                    remove1(3, selectGoods);
                    break;
                case "4":
                    remove1(4, selectGoods);
                    break;
                case "5":
                    break leap;
                default:
                    System.out.println("输入有误");
                    break;
            }
        }
    }
//修改信息
    public static void remove1(int judge, String selectGoods) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改的物品的名称");
        String name = sc.nextLine();
        int index = judgeIfName(name);
        if (index >= 0) {
            goods g = listGoods.get(index);
            if (judge == 1) {
                System.out.println(name + "原等级为" + g.getRank());
                String rank;
                while (true) {
                    System.out.print("你要将等级改为(只能为S/A/B/C/D)：");
                    rank = sc.nextLine();
                    if (rank.equals("A") || rank.equals("B") || rank.equals("C") || rank.equals("D") || rank.equals("S")) {
                        break;
                    } else {
                        System.out.println("等级不规范，请重新输入");
                    }
                }
                g.setRank(rank);
                listGoods.set(index, g);
                setListGoods(selectGoods, listGoods);
            } else if (judge == 2) {
                System.out.println(name + "原品质为" + g.getQuality());
                System.out.print("你要将品质改为：");
                String quality = sc.nextLine();
                g.setQuality(quality);
                listGoods.set(index, g);
                setListGoods(selectGoods, listGoods);
            } else if (judge == 3) {
                System.out.println(name + "原功能为" + g.getFunction());
                System.out.print("你要将功能改为：");
                String function = sc.nextLine();
                g.setFunction(function);
                listGoods.set(index, g);
                setListGoods(selectGoods, listGoods);
            } else if (judge == 4) {
                System.out.println(name + "原介绍为" + g.getFunction());
                System.out.print("你要将介绍/背景改为：");
                String introduce = sc.nextLine();
                g.setIntroduce(introduce);
                listGoods.set(index, g);
                setListGoods(selectGoods, listGoods);
            }
        } else {
            System.out.println("该奖品池暂无此物品，敬请期待....");
        }
    }
//添加物品信息
    public static void add(String selectGoods) throws IOException, ClassNotFoundException {
        System.out.println("请输入要添加的物品名称");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        int index = judgeIfName(name);
        if (index < 0) {
            String rank;
            while (true) {
                System.out.print("请输入该物品等级(只能为S/A/B/C/D)：");
                rank = sc.nextLine();
                if (rank.equals("A") || rank.equals("B") || rank.equals("C") || rank.equals("D") || rank.equals("S")) {
                    break;
                } else {
                    System.out.println("等级不规范，请重新输入");
                }
            }
            System.out.println("请输入该物品功能");
            String function = sc.nextLine();
            System.out.println("请输入该物品的品质（红黑紫...）");
            String quality = sc.nextLine();
            System.out.println("请输入该物品的介绍");
            String introduce = sc.nextLine();
            goods g = new goods(name, rank, function, quality, introduce);
            System.out.println("添加成功！！");
            listGoods.add(g);
            setListGoods(selectGoods, listGoods);
            user.inquireProps11(listGoods);
        } else {
            System.out.println("该物品名已经存在，请勿重复添加");
        }
    }
//删除
    public static void delete(String selectGoods) throws IOException, ClassNotFoundException {
        System.out.println("奖品池"+selectGoods);
        user.inquireProps11(listGoods);
        System.out.println("请输入要删除的物品名称");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        int index = judgeIfName(name);
        if (index >= 0) {
            listGoods.remove(index);
            System.out.println("删除成功");
            setListGoods(selectGoods, listGoods);
            user.inquireProps11(listGoods);
        } else {
            System.out.println("该物品名不存在");
        }
    }

    //根据名字得到对应索引
    public static int judgeIfName(String name) {
        for (int i = 0; i < listGoods.size(); i++) {
            if (listGoods.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    //登录
    public static int log() throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            boolean flag1 = true;
            String qq = "";
            while (flag1) {
                try {
                    System.out.println("请输入你的qq");
                    qq = sc.nextLine();
                    flag1 = user.QQAbnormal(qq);
                } catch (QQFormatException I) {
                    I.printStackTrace();
                    System.out.println("qq格式错误，只能为11位数字，请重新输入");
                }
            }
            int index = user.judgeQQ(qq);
            if (index >= 0) {
                boolean flagname = true;
                while (flagname) {
                    System.out.println("请输入游戏名字");
                    String name = sc.nextLine();
                    if (name.equals(listAdministrator.get(index).getName())) {
                        flagname = false;
                    } else {
                        System.out.println("用户名与qq输入不对应,请重新输入");
                    }
                }
                while (true) {
                    System.out.println("请输入密码");
                    String password = sc.nextLine();
                    boolean flag4 = true;
                    while (flag4) {
                        //获取验证码
                        byte identify[] = user.identify();
                        String identify2 = new String(identify);
                        System.out.println("验证码：" + identify2);
                        String judgeIdentify2 = sc.nextLine();
                        if (judgeIdentify2.equals(identify2)) {
                            System.out.println("验证码正确");
                            flag4 = false;
                        } else {
                            System.out.println("验证码输入有误，请重新输入");
                        }
                    }
                    boolean flag2 = password.equals(listAdministrator.get(index).getPassword());
                    if (flag2) {
                        System.out.println("密码正确，登录成功");
                        return index;
                    } else {
                        System.out.println("密码错误，请重新输入");
                    }
                }
            } else {
                System.out.println(qq + "不存在，请先注册");
                System.out.println("是否还想继续登录：0：不要登录  其他字母：都是继续登录");
                String select = sc.nextLine();
                if (select.equals("0")) {
                    return -1;
                }
            }
        }
    }

    public static ArrayList<administratorMessage> getListAdministratorMessage() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/test/T5/txt/AdministratorMessage.txt"));
        ArrayList<administratorMessage> list = (ArrayList<administratorMessage>) ois.readObject();
        return list;
    }
//根据不同的seletGoods调不同的文件地址
    public static void setListGoods(String selectGoods, ArrayList<goods> list) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos;
        if (selectGoods.equals("a")) {
            oos = new ObjectOutputStream(new FileOutputStream("src/test/T5/txt/goodsA.txt"));
        } else if (selectGoods.equals("b")) {
            oos = new ObjectOutputStream(new FileOutputStream("src/test/T5/txt/goodsB.txt"));
        } else {
            oos = new ObjectOutputStream(new FileOutputStream("src/test/T5/txt/goodsC.txt"));
        }
        oos.writeObject(list);
        oos.close();
    }
}

