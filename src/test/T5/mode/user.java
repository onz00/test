package test.T5.mode;

import test.T5.Exception.PhoneNumberFormatException;
import test.T5.Exception.QQFormatException;
import test.T5.class1.goods;
import test.T5.class1.history;
import test.T5.class1.userMessage;

import java.io.*;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.*;

public class user {
    //这里是获取对应的集合，在本循环中是对集合进行修改并存在文件中，但其他循环又是搞出集合
    static ArrayList<userMessage> listMessage;

    static {
        try {
            listMessage = getListUserMessage();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //卡池1
    static ArrayList<goods> listGoodsA;

    static {
        try {
            listGoodsA = getListGoodsA();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //卡池B
    static ArrayList<goods> listGoodsB;

    static {
        try {
            listGoodsB = getListGoodsB();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //卡池C
    static ArrayList<goods> listGoodsC;

    static {
        try {
            listGoodsC = getListGoodsC();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //利用了：io流的反序列流等储存信息，所有的东西都是放到文件中的，方便程序再次运行时继续上一次的结果
    //定义了两个自定义异常，而且设置了异常的捕获方便查看错误信息。。。
    //管理员。。三个卡池。。三种道具
    //验证码，一些算法小游戏，
    //其他的必备的也都写了（好玩！）
    public static void user() throws IOException, ClassNotFoundException, InterruptedException {
        leap:
        while (true) {
            System.out.println("=====================欢迎进入用户界面===================");
            Scanner sc = new Scanner(System.in);
            System.out.println("请选择 1:登录  2：找回密码 3:注册用户 4；退出");
            String select = sc.next();
            switch (select) {
             case "1":
                 int index = log();
                 //只有登录才可以执行下面的
                 if (index >= 0) {
                     //得到该用户的信息
                     userMessage u = listMessage.get(index);
                     leap1:
                     while (true) {
                         Scanner sc2 = new Scanner(System.in);
                         //一个用户可以有多个道具（储存在Hash中，多个物品也是储存到hash中，方便统计数量
                         //而卡池是相对独立的，即上面的三个静态：listlistGoodsCB。。。
                         System.out.println("============欢迎进入游戏界面===============");
                         System.out.println("请选择：a:抽卡 b:获得道具 c:查看道具信息(所有物品/用户物品/按xx查询，分类输出）" +
                                 " d:历史记录 e:修改信息f:退出");
                         String select2 = sc2.nextLine();
                         switch (select2) {
                             case "a":
                                 //大概：有三个卡池（三个文件）互相独立，保底系统也不相同，抽奖动画，累计到
                                 //一定次数后抽奖概率翻倍等等，随着抽奖次数的叠加概率上升。。。【抽奖次数会放到文件中】
                                 drawCard(u, index);
                                 break;
                             case "b":
                                 //大概：有根据+-*（随机的数and随机运算）运算，字符的运算（咳咳绝对没不是想跟T2一样的意思）【
                                 //猜拳游戏，判断奇偶数游戏。。。汉诺塔。。【运用了随机数，这样每次的游戏都是随机的
                                 getProps(u, index);
                                 break;
                             case "c":
                                 //分类查找，（根据rank/品质分类利用重写，所有信息。。）排序。。自己的信息。。
                                 inquireProps(u);
                                 break;
                             case "d":
                                 //历史记录：创建一个方法，记录每次抽奖得到的值/失去的卡，时间类
                                 getAllHistoryMessage();
                                 break;
                             case "e":
                                 //可以修改用户名（唯一性判断），密码，手机号（格式判断，异常）。。。
                                 remove(u, index);
                                 break;
                             //     f //查看历史记录：在抽卡时加上时间类，（将时间，抽到了什么，消耗了了什么储存到文件中，然后读取即可
                             case "f":
                                 break leap1;
                             default:
                                 System.out.println("输入违规，请重新输入");
                                 break;
                         }
                     }
                 } else {
                     System.out.println("登录失败");
                 }
                 break;
             case "2":
                 //找回密码
                 lookForPassword();
                 break;
             case "3":
                 //用户名，qq唯一性判断，异常捕获，随机验证码。。。
                 register();
                 break;
             case "4":
                 break leap;
             default:
                 System.out.println("输入不符合规则，请重新输入");
                 break;
        }
     }
}

    public static void register() throws IOException {
        Scanner sc = new Scanner(System.in);
        String qq = "";
        leap:
        while (true) {
            boolean flag1 = true;
            while (flag1) {
                try {
                    System.out.println("请输入你要注册的qq号");
                    qq = sc.nextLine();
                    flag1 = QQAbnormal(qq);
                } catch (QQFormatException I) {
                    I.printStackTrace();
                    System.out.println("qq格式错误，只能为11位数字，请重新输入");
                }
            }
            int index = judgeQQ(qq);
            if (index < 0) {
                System.out.println("请输入密码");
                String password = sc.nextLine();
                System.out.println("请再次确认密码");
                String newpassword = sc.nextLine();
                String name = "";
                if (password.equals(newpassword)) {
                    while (true) {
                        System.out.println("请创建用户名");
                        name = sc.nextLine();
                        int index2 = judgeName(name);
                        if (index2 < 0) {
                            break;
                        } else {
                            System.out.println("该用户名已经存在，请重新创建");
                        }
                    }
                    while (true) {
                        //获取验证码
                        byte identify[] = identify();
                        String identify2 = new String(identify);
                        System.out.println("验证码：" + identify2);
                        String judgeIdentify2 = sc.nextLine();
                        if (judgeIdentify2.equals(identify2)) {
                            System.out.println("验证码正确");
                            break;
                        } else {
                            System.out.println("验证码输入有误，请重新输入");
                        }
                    }
                    String phoneNumber = "";

                    while (true) {
                        try {
                            System.out.println("请绑定你的手机号");
                            phoneNumber = sc.nextLine();
                            PhoneNumberAbnormal(phoneNumber);
                            break;
                        } catch (PhoneNumberFormatException I) {
                            I.printStackTrace();
                            System.out.println("手机号格式错误，只能为11位数字，请重新输入");
                        }
                    }
                    HashMap<String, Integer> hm = new HashMap<>();
                    hm.put("a", 0);
                    hm.put("b", 0);
                    hm.put("c", 0);
                    hm.put("d", 0);
                    //物品
                    HashMap<String, Integer> hmGoods = new HashMap<>();
                    hmGoods.put("a", 1);
                    userMessage u = new userMessage(name, qq, phoneNumber, password, hm, hmGoods);
                    System.out.println("当当当当，注册成功！！！");
                    listMessage.add(u);
                    setFile(listMessage);
                    break leap;
                } else {
                    System.out.println("两次密码不相同，请再次注册");
                    break;
                }
            } else {
                System.out.println(qq + "已经存在，无需注册");
                System.out.println("是否还想继续注册：0：不要登录  其他字母：都是继续登录");
                String select = sc.nextLine();
                if (select.equals("0")) {
                    break leap;
                }
            }
        }
    }

    public static void remove(userMessage u, int index) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean flag0 = true;
        leap:
        while (true) {
            System.out.println("请选择要修改的信息  1：用户名  2：密码  3：手机号 4：exit");
            String select = sc.nextLine();
            switch (select) {
                case "1":
                    reName(u, index);
                    break;
                case "2":
                    rePassword(u, index);
                    break;
                case "3":
                    rePhoneNumber(u, index);
                    break;
                case "4":
                    break leap;
                default:
                    System.out.println("请正确输入");
                    break;
            }
        }
    }

    public static void reName(userMessage u, int index) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要更改的用户名");
        String name = sc.nextLine();
        int i = judgeName(name);
        if (i < 0) {
            System.out.println("修改成功");
            u.setName(name);
            listMessage.set(index, u);
            setFile(listMessage);
        } else {
            System.out.println("该用户名已经存在，修改失败");
        }
    }

    public static void rePassword(userMessage u, int index) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入原密码");
        String oldPassword = sc.nextLine();
        if (oldPassword.equals(u.getPassword())) {
            System.out.println("密码正确");
            System.out.println("请输入你要更改的密码");
            String password = sc.nextLine();
            System.out.println("修改成功");
            u.setPassword(password);
            listMessage.set(index, u);
            setFile(listMessage);
        } else {
            System.out.println("密码错误，你到底是谁");
        }
    }

    public static void rePhoneNumber(userMessage u, int index) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入原手机号");
        String oldPhone = sc.nextLine();
        if (oldPhone.equals(u.getPhoneNumber())) {
            System.out.println("正在发送验证码ing....");
            byte identify[] = identify();
            String identify2 = new String(identify);
            System.out.println("验证码：" + identify2);
            String judgeIdentify2 = sc.nextLine();
            if (judgeIdentify2.equals(identify2)) {
                System.out.println("验证码正确");
                String phoneNumber = "";
                while (true) {
                    try {
                        System.out.println("请输入要更改的手机号");
                        phoneNumber = sc.nextLine();
                        PhoneNumberAbnormal(phoneNumber);
                        break;
                    } catch (PhoneNumberFormatException I) {
                        I.printStackTrace();
                        System.out.println("手机号格式错误，只能为11位数字，请重新输入");
                    }
                }
                System.out.println("修改成功");
                u.setPhoneNumber(phoneNumber);
                listMessage.set(index, u);
                setFile(listMessage);
            } else {
                System.out.println("验证码输入有误，你不是她");
            }
        } else {
            System.out.println("手机号错误，你究竟是who");
        }
    }
     /*   注意已经直到是拿个学生了
        在储存东西后记得放进集合并且放进文件中
    三件套[储存文件]            u.setName(name);
                listMessage.set(index,u);
              setFile(listMessage);
   抽卡三件套：
    HashMap<String,Integer>hm=u.getHm();
    Integer count = hm.get("a");
    count-=1;
                hm.put("a",count);
                抽到的东西三件套：将抽到的东西放在用户里面【东西的次数？哈希？
    goods g = listGoodsA.get(num);
                        System.out.println("恭喜你抽到:" + g);
                        listProps.add(g);
                        u.setListProps(listProps);
                        保底系统：
    int countA=getCountA();
    //利用三元运算符进行判断。看是否满足保底判断
    int num=countA==10?r.nextInt(listGoodsA.size()):r.nextInt(listGoodsA.size()+10
            记得在没抽中时count+1；
          保底：   if(countA==10){
        int countNewA=0;
        setCountA(countNewA);*/


    //抽卡思路：利用相应的卡去抽，让他的值+/-1；
    public static void drawCard(userMessage u, int index) throws IOException, ClassNotFoundException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        //抽卡之前要有道具，所以要先判断有无道具（可先遍历自己的道具）【注意保底】、
        //如何确保不同的概率：利用随机数的范围去弄集合：【3，23，，5，4，64，3，好东西，范德萨，23，23，好东西】
        //不同的卡池用不同的道具
        //抽到的物品放在user的props集合中
        leap:
        while (true) {
            System.out.println("a:csgo池 b:角色池 c:原生武器武器池 d:退出抽卡");
            String select2 = sc.nextLine();
            switch (select2) {
                case "a":
                    //利用三元运算符和抽奖次数实现不同概率
                    drawA(u, index, listGoodsA, "a");
                    //抽卡：将用户的goods搞出来，然后根据不同的道具名去减少该道具的数量【集合里面有各种各样的
                    break;
                case "b":
                    drawA(u, index, listGoodsB, "b");
                    //【4-6星角色
                    // 抽卡消耗纠缠之缘，1-90抽保底5星角色，50%几率歪，遵循大小保底规则。
                    //    exitClass();
                    break;
                case "c":
                    drawA(u, index, listGoodsC, "c");
                    //【1-5星武器】
                    //抽卡消耗纠缠之缘，1-80抽保底5星武器，25%几率歪，遵循大小保底规则。
                    //     inquireClass();
                    break;
                case "d":
                    break leap;
                default:
                    System.out.println("输入违规，请重新输入");
                    break;
            }
        }
    }

    //抽奖界面：是否跳过
    //抽卡卡池1：
    //消耗的是a道具
    //将抽到的物品放在hmGoods（值+1），将道具hm—1，
    public static void drawA(userMessage u, int index, ArrayList<goods> listGoods, String selectCount) throws IOException, ClassNotFoundException, InterruptedException {
        //根据传输不同的selectCount值利用不同的文件/方法
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        if (selectCount.equals("a")) {
            System.out.println("------欢迎来到新手池------");
        } else if (selectCount.equals("b")) {
            System.out.println("------欢迎来到up角色池------");
        } else {
            System.out.println("------欢迎来到武器池------");
        }
        //输出抽卡道具的数量
        while (true) {
            //输出道具
            getHmProp(u);
            System.out.println("----------------------------------------------");
            //输出物品
            getHmGoods(u);
            System.out.println("是否选择抽奖：0：否 1/其他：是");
            String select = sc.nextLine();
            if (!select.equals("0")) {
                //这里是去对道具-1
                HashMap<String, Integer> hm = u.getHm();
                int count = 0;
                if (selectCount.equals("a")) {
                    count = hm.get("a");
                    ;
                } else if (selectCount.equals("b")) {
                    count = hm.get("b");
                } else {
                    count = hm.get("c");
                }
                //  Integer count = hm.get("a");
                //对数量进行判断
                if (count > 0) {
                    System.out.println("抽奖ing......");
                    Shape();
                    count -= 1;
                    //对道具-1
                    if (selectCount.equals("a")) {
                        hm.put("a", count);
                    } else if (selectCount.equals("b")) {
                        hm.put("b", count);
                        ;
                    } else {
                        hm.put("c", count);
                        ;
                    }
                    //这里判断是否保底
                    int countA = 0;
                    if (selectCount.equals("a")) {
                        countA = getCountA();
                    } else if (selectCount.equals("b")) {
                        countA = getCountB();
                    } else {
                        countA = getCountC();
                    }
                    //利用三元运算符进行判断。看是否满足保底判断
                    //概率是30%
                    int num = countA == 3 ? r.nextInt(listGoods.size() + 4) : r.nextInt(listGoods.size() + 5);
                    //概率是60%
                    num = countA == 5 ? r.nextInt(listGoods.size() + 2) : num;
                    //概率是100%
                    num = countA == 7 ? r.nextInt(listGoods.size()) : num;
                    //   int num = r.nextInt(listGoodsA.size() + 100);
                    if (num < listGoods.size()) {
                        countA += 1;
                        if (selectCount.equals("a")) {
                            setCountA(countA);
                        } else if (selectCount.equals("b")) {
                            setCountB(countA);
                        } else {
                            setCountC(countA);
                        }
                        goods g = listGoods.get(num);
                        System.out.println("恭喜你抽到:" + g);
                        //抽到的话，要放进历史记录里面去
                        setHistory(g, selectCount);
                        String name = g.getName();
                        HashMap<String, Integer> hmGoods = u.getHmGoods();
                        //将物品放到u对象里面去
                        if (hmGoods.containsKey(name)) {
                            int countGoods = hmGoods.get(name);
                            countGoods += 1;
                            hmGoods.put(name, countGoods);
                        } else {
                            hmGoods.put(name, 1);
                        }
                        u.setHmGoods(hmGoods);
                    } else {
                        //这里对保底次数+1；
                        countA += 1;
                        if (selectCount.equals("a")) {
                            setCountA(countA);
                        } else if (selectCount.equals("b")) {
                            setCountB(countA);
                        } else {
                            setCountC(countA);
                        }
                        goods nullG = new goods("很遗憾，你没有抽中", "无", "无", "无", "无");
                        setHistory(nullG, selectCount);
                        System.out.println("很遗憾，你没有抽中");
                    }
                    System.out.println("==========你的保底" + selectCount + "奖池次数" + "已经累计到" + countA + "    特别提醒：当countA到3（概率+20%）/5（概率+50%）/8（概率100%）有福利噢======");
                    if(countA==7){
                        System.out.println("--------下一次必中噢-------");
                    }
                    //如果是最终保底了，那么积攒的次数清0
                    if (countA == 8) {
                        System.out.println("-----------------------累计次数已达到8次，清空count--------------------");
                        if (selectCount.equals("a")) {
                            setCountA(0);
                        } else if (selectCount.equals("b")) {
                            setCountB(0);
                        } else {
                            setCountC(0);
                        }
                    }
                } else {
                    System.out.println("你的道具" + selectCount + "的数量为" + count + ",无法抽奖，请先去获取道具");
                    break;
                }
            } else {
                System.out.println("你已选择退出抽奖");
                break;
            }
        }
        //抽卡的最后记得写放进去
        listMessage.set(index, u);
        setFile(listMessage);
    }

    //将抽到的东西放进历史记录里面去
    public static void getAllHistoryMessage() throws IOException, ClassNotFoundException {
        System.out.println("以下是你的抽奖记录（注意：抽奖记录！=你拥有的物品）");
        ArrayList<history> histories = getFileHistory();
        int count=0;
        for (int i = 0; i < histories.size(); i++) {
            System.out.println();
            if(!histories.get(i).getGoodsName().equals("null")) {
                if (!histories.get(i).getGoodsName().equals("很遗憾，你没有抽中")) {
                    System.out.println(histories.get(i));
                } else {
                    count++;
                    System.out.println("时间：" + histories.get(i).getTime() + '\t' + "你的道具" + histories.get(i).getPropsName() + "-1      很遗憾，你没有抽中");
                }
            }
        }
        if(count==0){
            System.out.println("你还没有抽奖记录噢");
        }
    }

    public static void setHistory(goods g, String selectCount) throws IOException, ClassNotFoundException {
        ArrayList<history> histories = getFileHistory();
        String name = g.getName();
        LocalDateTime time = LocalDateTime.now();
        String rank = g.getRank();
        String quality = g.getQuality();
        history history = new history(time, name, rank, quality, selectCount);
        histories.add(history);
        setFileHistory(histories);
    }

    //输出本人的道具信息
    public static void getHmProp(userMessage u) {
        HashMap<String, Integer> hm = u.getHm();
        Set<Map.Entry<String, Integer>> entrySet = hm.entrySet();
        for (Map.Entry<String, Integer> m : entrySet) {
            String key = m.getKey();
            Integer value = m.getValue();
            System.out.println("你拥有的抽卡道具为：" + key + "---" + value);
        }
    }

    //这里对物品进行了排序
    public static void getHmGoods(userMessage u) {
        HashMap<String, Integer> hm = u.getHmGoods();
        TreeMap<String, Integer> tm = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        tm.putAll(hm);
        Set<Map.Entry<String, Integer>> entrySet = tm.entrySet();
        for (Map.Entry<String, Integer> m : entrySet) {
            String key = m.getKey();
            Integer value = m.getValue();
            System.out.println("你的拥有的物品为：" + key + "---" + value);
        }
    }


    //获得道具：利用做任务，获得goods的name，再去找该学生对应的键，然后数量+1；
    //任务：+-*/，做对了则+道具，错了道具-1
    public static void getProps(userMessage u, int index) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("欢迎来到做任务环节");
        leap:
        while (true) {
            System.out.println("请选择要做的任务  a：获得抽csgo的a道具 b：获得抽原神角色道具 c：获得抽原神武器道具 e:退出");
            String select = sc.nextLine();
            switch (select) {
                case "a":
                    //+-*随机
                    assignmentA(u, index);
                    break;
                case "b":
                    //猜拳/猜数
                    assignmentB(u, index);
                    break;
                case "c":
                    //汉诺塔游戏
                    assignmentC(u, index);
                    break;
                case "e":
                    break leap;
                default:
                    System.out.println("别闹");
            }
        }
    }

    public static void assignmentA(userMessage u, int index) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("欢迎进入知识的海洋，在玩游戏的同时相信你也对没有学习感到非常心虚");
        System.out.println("所以让我们带着你进入知识的海洋吧！");
        leap:
        while (true) {
            System.out.println("这里是a道具的获得点：");
            System.out.println("请选择难度 1：地狱级 2：魔鬼级 3：地狱魔鬼级 4:不玩了妈妈我害怕");
            String select = sc.nextLine();
            switch (select) {
                case "1":
                    assignmentA1(u, index, false);
                    break;
                case "2":
                    assignmentA2(u, index, false);
                    break;
                case "3":
                    assignmentA3(u, index);
                    break;
                case "4":
                    break leap;
                default:
                    System.out.println("别闹");
                    break;
            }
        }
    }

    //+-*/
    public static void assignmentA1(userMessage u, int index, boolean judge) throws IOException {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        System.out.println("声明：随机+-*法运算");
        boolean flag = false;
        //judge的作用是用来判断是否是地狱魔鬼级的
        boolean flagJudge = false;
        while (true) {
            int select = r.nextInt(4) + 1;
            int num1 = r.nextInt(100);
            int num2 = r.nextInt(1000);
            if (select == 1) {
                System.out.println("欢迎选到加法运算");
                System.out.print("请计算两数的结果：" + num1 + "+" + num2 + "=");
                int sum = num1 + num2;
                int result = judgeResultNum();
                if (result == sum) {
                    System.out.println("呀！计算正确，你的道具a+1");
                    flag = true;
                } else {
                    System.out.println("计算错误噢，结果是" + sum);
                    if (judge) {
                        flagJudge = true;
                    }
                }
            } else if (select == 2) {
                System.out.println("欢迎选到乘法运算");
                System.out.print("请计算两数的结果：" + num1 + "*" + num2 + "=");
                int sum = num1 * num2;
                int result = judgeResultNum();
                if (result == sum) {
                    System.out.println("呀！计算正确，你的道具a+1");
                    flag = true;
                } else {
                    System.out.println("计算错误噢，结果是" + sum);
                    if (judge) {
                        flagJudge = true;
                    }
                }
            } else if (select == 3) {
                System.out.println("欢迎选到减法运算");
                System.out.print("请计算两数的结果：" + num1 + "-" + num2 + "=");
                int sum = num1 - num2;
                int result = judgeResultNum();
                if (result == sum) {
                    System.out.println("呀！计算正确，你的道具a+1");
                    flag = true;
                } else {
                    System.out.println("计算错误噢，结果是" + sum);
                    if (judge) {
                        flagJudge = true;
                    }
                }
            }
            //计算正确，道具+1
            if (flag) {
                HashMap<String, Integer> hm = u.getHm();
                int count = hm.get("a");
                count += 1;
                hm.put("a", count);
                //    记得三步  u.setHmGoods(hm);
                listMessage.add(u);
                setFile(listMessage);
            }
            if (flagJudge) {
                System.out.println("嘻嘻，你的a道具-1");
                HashMap<String, Integer> hm = u.getHm();
                int count = hm.get("a");
                count -= 1;
                hm.put("a", count);
                u.setHmGoods(hm);
                listMessage.add(u);
                setFile(listMessage);
            }
            //如果是3过来的，执行一次就够了
            if (judge) {
                break;
            }
            flag = false;
            flagJudge = false;
            System.out.println("是否要计算快乐玩耍？其他：我要快乐 000：再见了");
            String s = sc.next();
            if (s.equals("000")) {
                break;
            }
        }
    }

    //对输入的数进行判断
    public static int judgeResultNum() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String result = sc.nextLine();
            String regex = "(-?)\\d*";
            if (result.matches(regex)) {
                return Integer.parseInt(result);
            } else {
                System.out.println("格式错误，请重新输入");
            }
        }
    }

    public static void assignmentA2(userMessage u, int index, boolean judge) throws IOException {
        System.out.println("欢迎来到运算符的运算");
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        System.out.println("这里的A代表1，B=2....依次类推");
        String arr[] = {"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"};
        int arrnum[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        while (true) {
            int select = r.nextInt(14) + 1;
            int select2 = r.nextInt(14) + 1;
            System.out.print("请计算" + arr[select] + "+" + arr[select2] + "=");
            int result = judgeResultNum();
            int sum = arrnum[select2] + arrnum[select];
            if (result == sum) {
                System.out.println("计算正确！！你的a道具+1");
                HashMap<String, Integer> hm = u.getHm();
                int count = hm.get("a");
                count += 1;
                hm.put("a", count);
                u.setHmGoods(hm);
                listMessage.add(u);
                setFile(listMessage);
            } else {
                System.out.println("计算错误噢，答案是" + sum);
                if (judge) {
                    System.out.println("嘻嘻，你的a道具-1");
                    HashMap<String, Integer> hm = u.getHm();
                    int count = hm.get("a");
                    count -= 1;
                    hm.put("a", count);
                    u.setHmGoods(hm);
                    listMessage.add(u);
                    setFile(listMessage);
                }
            }
            //如果是从3过来的，调用一次就够了
            if (judge) {
                break;
            }
            System.out.println("是否还要快乐玩耍？其他：我要快乐 000：再见了");
            String s = sc.next();
            if (s.equals("000")) {
                break;
            }
        }
    }

    public static void assignmentA3(userMessage u, int index) throws IOException {
        System.out.println("特别说明，这个会随机调取前面两个任务，但唯一不同的是如果回答错误那么你的道具数量会减少噢！！");
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        while (true) {
            int num = r.nextInt(2);
            if (num == 0) {
                assignmentA1(u, index, true);
            } else {
                assignmentA2(u, index, true);
            }
            System.out.println("是否还要快乐玩耍？其他：我要快乐 000：再见了");
            String s = sc.next();
            if (s.equals("000")) {
                break;
            }
        }
    }

    public static void assignmentB(userMessage u, int index) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("欢迎来到判断环节");
        leap:
        while (true) {
            System.out.println("请选择：1：猜拳 2：猜质数/偶数 3：退出");
            String select = sc.nextLine();
            switch (select) {
                case "1":
                    assignmentB1(u, index);
                    break;
                case "2":
                    assignmentB2(u, index);
                    break;
                case "3":
                    break leap;
                default:
                    System.out.println("别闹");
                    break;
            }
        }
    }

    public static void assignmentB1(userMessage u, int index) throws IOException {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        boolean flag = false;
        String arr[] = {"石头", "剪刀", "布"};
        String result;
        while (true) {
            System.out.println("请输入你要出的：石头/剪刀/布");
            result = sc.nextLine();
            if (result.equals("石头") || result.equals("剪刀") || result.equals("布")) {
                break;
            } else {
                System.out.println("请正确输入，不要耍赖！！");
            }
        }
        int select = r.nextInt(3);
        if ((result.equals("石头") && select == 1) || (result.equals("剪刀") && select == 2) || (result.equals("布") && select == 0)) {
            System.out.println("恭喜你赢了：" + result + "win" + arr[select]);
            HashMap<String, Integer> hm = u.getHm();
            System.out.println("你的b道具+1");
            int count = hm.get("b");
            count += 1;
            hm.put("b", count);
            u.setHmGoods(hm);
            listMessage.add(u);
            setFile(listMessage);
        } else if ((result.equals("石头") && select == 0) || (result.equals("剪刀") && select == 1) || (result.equals("布") && select == 2)) {
            System.out.println("平局！！" + result + "the same" + arr[select]);
        } else {
            System.out.println("哈哈，你输了！" + result + " be defeated" + arr[select]);
        }
    }

    public static void assignmentB2(userMessage u, int index) throws IOException {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        System.out.println("请判断下面的随机数是奇数/偶数   1:偶数 其他：奇数");
        int num = r.nextInt(100) + 1;
        String judge;
        while (true) {
            judge = sc.nextLine();
            String regex = "\\d+";
            if (judge.matches(regex)) {
                break;
            } else {
                System.out.println("只能为数字噢，重新输入");
            }
        }
        if (num % 2 == 0) {
            if (judge.equals("1")) {
                System.out.println("恭喜你猜对了" + num + "是偶数");
                flag = true;
            } else {
                System.out.println("猜错啦！！" + num + "是偶数");
            }
        } else {
            if (!judge.equals("1")) {
                System.out.println("恭喜你猜对了" + num + "是奇数");
                flag = true;
            } else {
                System.out.println("猜错啦！！" + num + "是奇数");
            }
        }
        if (flag) {
            System.out.println("你的b道具+1");
            HashMap<String, Integer> hm = u.getHm();
            int count = hm.get("b");
            count += 1;
            hm.put("b", count);
            u.setHmGoods(hm);
            listMessage.add(u);
            setFile(listMessage);
        }
    }

    public static void assignmentC(userMessage u, int index) throws IOException, InterruptedException {
        System.out.println("---------欢迎来到汉诺塔宇宙---------");
        System.out.println("规则：1有三根邻近的柱子，标号为A,B,C。 2.A柱子上从下向上按金字塔状叠起来着n个不同大小的圆盘，");
        System.out.println(" 3.现在把所有的盘子一个一个挪动到柱子B上，而且每一次挪动同一根柱子上都不可以出现大盘子在小盘子上方");
        Random r = new Random();
        int num = r.nextInt(5) + 1;
        System.out.println("这次的盘子数为：" + num);
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        int chance = 0;
        int step = 0;
        while (chance <= 2) {
            System.out.print("请计算需要移动多少次:");
            int result = judgeResultNum();
            step = move(num, 'A', 'B', 'C', 0, false);
            if (result == step) {
                System.out.println("恭喜你答对了！！");
                System.out.println("你的c道具+1");
                HashMap<String, Integer> hm = u.getHm();
                int count = hm.get("b");
                count += 1;
                hm.put("b", count);
                u.setHmGoods(hm);
                listMessage.add(u);
                setFile(listMessage);
                flag = false;
                break;
            } else {
                chance++;
                int chance2 = 3 - chance;
                if (chance2 >= 0) {
                    System.out.println("很遗憾，你回答错了，你还剩下" + chance2 + "次机会");
                } else {
                    System.out.println("少年，你已经没用机会了");
                }
            }
        }
        System.out.println("具体步骤为：");
        step = move(num, 'A', 'B', 'C', 0, true);
        if (flag) {
            System.out.println("步数应为" + step);
            System.out.println("好吧这次确实有点难，但作为良心的游戏官方肯定要为我们的用户着想，所以你只需要回答一个" +
                    "常识即可");
            System.out.println("问题：光之国有几个凹凸曼");
            String answer = sc.nextLine();
            System.out.println("咳咳你对了（俺也不知道）");
            System.out.println("你的b道具+1");
            HashMap<String, Integer> hm = u.getHm();
            int count = hm.get("b");
            count += 1;
            hm.put("b", count);
            u.setHmGoods(hm);
            listMessage.add(u);
            setFile(listMessage);
        }
    }

    //汉诺塔规则：
    public static int move(int n, char from, char to, char auxiliary, int count, boolean flag) throws InterruptedException {
        if (n == 1) {
            if (flag) {
                System.out.println("将盘子1从" + from + "移动到" + to);
                Thread.sleep(500);
            }
            count++;
            return count;
        }
        count = move(n - 1, from, auxiliary, to, count, flag);
        if (flag) {
            System.out.println("将盘子" + n + "从" + from + "移动到" + to);
            Thread.sleep(500);
        }
        count++;
        count = move(n - 1, auxiliary, to, from, count, flag);
        return count;
    }

    //1:输出所有的goods【要再去创建一个goods类的集合去输出他】
    //2：查看用户的信息（抽卡道具剩余量，获得了什么东西）
    //根据货物名称查找他的功能
    public static void inquireProps(userMessage u) {
        Scanner sc = new Scanner(System.in);
        leap:
        while (true) {
            System.out.println("===================欢迎来到信息总站====================");
            System.out.println("请选择：  1：输出奖品池的所有物品（abc） 2：查看用户的获得物品 3：根据货物名称查找他的功能 4：分类输出 5:退出");
            String select = sc.nextLine();
            switch (select) {
                case "1":
                    inquireProps1();
                    break;
                //排序
                case "2":
                    //重写了compareTo方法进行排序
                    inquireProps2(u);
                    break;
                case "3":
                    inquireProps3();
                    break;
                case "4":
                    //输出奖品池的，但是按rank/品质分类
                    inquireProps4();
                    break;
                case "5":
                    break leap;
                default:
                    System.out.println("别闹");
                    break;
            }
        }
    }

    public static void inquireProps1() {
        Scanner sc = new Scanner(System.in);
        leap:
        while (true) {
            System.out.println("请选择要输出的奖品池信息：a：csgo奖品池 b: 原神角色奖品池 c: 元神武器奖品池 d:退出");
            String select = sc.nextLine();
            switch (select) {
                case "a":
                    inquireProps11(listGoodsA);
                    break;
                case "b":
                    inquireProps11(listGoodsB);
                    break;
                case "c":
                    inquireProps11(listGoodsC);
                    break;
                case "d":
                    break leap;
                default:
                    break;
            }
        }
    }

    //用于遍历奖品池所有信息
    public static void inquireProps11(ArrayList<goods> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    //根据不同用户输出其拥有的物品
    public static void inquireProps2(userMessage u) {
        System.out.println("用户" + u.getName());
        getHmProp(u);
        System.out.println("--------------------------");
        getHmGoods(u);
    }

    //根据名字去在三个奖品池里面找
    public static void inquireProps3() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要查找的物品信息（三个池都行）");
        String name = sc.nextLine();
        for (int i = 0; i < listGoodsA.size(); i++) {
            if (name.equals(listGoodsA.get(i).getName())) {
                System.out.println(listGoodsA.get(i));
                return;
            }
        }
        for (int i = 0; i < listGoodsB.size(); i++) {
            if (name.equals(listGoodsB.get(i).getName())) {
                System.out.println(listGoodsB.get(i));
                return;
            }
        }
        for (int i = 0; i < listGoodsC.size(); i++) {
            if (name.equals(listGoodsC.get(i).getName())) {
                System.out.println(listGoodsC.get(i));
                return;
            }
        }
        System.out.println("暂无名称为" + name + "的物品，敬请期待.....");
    }

    public static void inquireProps4() {
        Scanner sc = new Scanner(System.in);
        //按照rank分abc/按照品质分
        leap:
        while (true) {
            System.out.println("请选择按什么分类：1：按等级进行分类 2:按品质进行分类 3:退出");
            String select = sc.nextLine();
            switch (select) {
                case "1":
                    inquireProps412(true);
                    break;
                case "2":
                    //根据flag的不同去利用不用的语句进行排序
                    inquireProps412(false);
                    break;
                case "3":
                    break leap;
                default:
                    System.out.println("别闹");
                    break;
            }
        }
    }

    public static void inquireProps412(boolean flag) {
        TreeMap<String, Integer> tm = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        String prop;
        String prop2;
        if (flag) {
            prop = " 等级";
            prop2 = "按等级分：";
        } else {
            prop = " 品质";
            prop2 = "按品质分：";
        }
        //将三个奖池的东西全放在集合中（根据rank分类
        TreeMap<String, Integer> tm1 = getAllClassifyTm(tm, listGoodsA, flag);
        TreeMap<String, Integer> tm2 = getAllClassifyTm(tm1, listGoodsB, flag);
        TreeMap<String, Integer> tm3 = getAllClassifyTm(tm2, listGoodsC, flag);
        Set<Map.Entry<String, Integer>> entrySet = tm3.entrySet();
        //按照rank去分类
        for (Map.Entry<String, Integer> m : entrySet) {
            System.out.println("===================在所有奖池中，" + prop2 + m.getKey() + prop + "的总数为" + m.getValue() + "================");
            getAllClassifyMessage(listGoodsA, m.getKey(), flag);
            getAllClassifyMessage(listGoodsB, m.getKey(), flag);
            getAllClassifyMessage(listGoodsC, m.getKey(), flag);
        }
    }

    //将集合里面的东西根据rank/品质去排序
    public static TreeMap<String, Integer> getAllClassifyTm(TreeMap<String, Integer> tm, ArrayList<goods> list, boolean flag) {
        for (int i = 0; i < list.size(); i++) {
            if (flag) {
                if (tm.containsKey(list.get(i).getRank())) {
                    int count = tm.get(list.get(i).getRank());
                    count++;
                    tm.put(list.get(i).getRank(), count);
                } else {
                    tm.put(list.get(i).getRank(), 1);
                }
            } else {
                if (tm.containsKey(list.get(i).getQuality())) {
                    int count = tm.get(list.get(i).getQuality());
                    count++;
                    tm.put(list.get(i).getQuality(), count);
                } else {
                    tm.put(list.get(i).getQuality(), 1);
                }
            }
        }
        return tm;
    }

    //根据rank/品质的不同去输出
    public static void getAllClassifyMessage(ArrayList<goods> list, String key, boolean flag) {
        for (int i = 0; i < list.size(); i++) {
            if (flag) {
                if (list.get(i).getRank().equals(key)) {
                    System.out.println(list.get(i));
                }
            } else {
                if (list.get(i).getQuality().equals(key)) {
                    System.out.println(list.get(i));
                }
            }
        }
    }

    //登录功能，如果登录成功则放回对应用户的qq索引，失败则放回-1
    public static int log() throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            boolean flag1 = true;
            String qq = "";
            while (flag1) {
                try {
                    System.out.println("请输入你的qq");
                    qq = sc.nextLine();
                    flag1 = QQAbnormal(qq);
                } catch (QQFormatException I) {
                    I.printStackTrace();
                    System.out.println("qq格式错误，只能为11位数字，请重新输入");
                }
            }
            int index = judgeQQ(qq);
            if (index >= 0) {
                boolean flagname = true;
                while (flagname) {
                    System.out.println("请输入游戏名字");
                    String name = sc.nextLine();
                    if (name.equals(listMessage.get(index).getName())) {
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
                        byte identify[] = identify();
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
                    boolean flag2 = password.equals(listMessage.get(index).getPassword());
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

    public static void lookForPassword() throws IOException, ClassNotFoundException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String name = sc.nextLine();
        int index1 = judgeName(name);
        if (index1 >= 0) {
            System.out.println("请输入手机号加以进行验证");
            String number = sc.nextLine();
            int index = judgeNumPhone(number, index1);
            if (index >= 0) {
                while (true) {
                    //获取验证码
                    byte identify[] = identify();
                    String identify2 = new String(identify);
                    System.out.print("正在发送验证码中，请注意查收");
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(1000);
                        System.out.print(".");
                    }
                    System.out.println("验证码：" + identify2);
                    String judgeIdentify2 = sc.nextLine();
                    if (judgeIdentify2.equals(identify2)) {
                        System.out.println("验证码正确");
                        break;
                    } else {
                        System.out.println("验证码输入有误，请重新输入");
                    }
                }
                userMessage u = listMessage.get(index);
                System.out.println("输入正确");
                System.out.println("请输入修改后的密码");
                String password = sc.nextLine();
                System.out.println("修改成功！");
                u.setPassword(password);
                listMessage.set(index1, u);
                setFile(listMessage);
            } else {
                System.out.println("用户名与手机号不匹配，修改失败");
            }
        } else {
            System.out.println("查无此人");
        }
    }

    //判断集合中是否有对应的名字，有则放回索引
    public static int judgeName(String name) {
        for (int i = 0; i < listMessage.size(); i++) {
            if (name.equals(listMessage.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

    //对该索引的手机号进行判断是否相同
    public static int judgeNumPhone(String number, int index) {
        if (number.equals(listMessage.get(index).getPhoneNumber())) {
            return index;
        }
        return -1;
    }

    //对qq的格式进行判断
    public static boolean QQAbnormal(String id) {
        String regex1 = "\\d{11}";
        if (!id.matches(regex1)) {
            throw new QQFormatException();
        }
        return false;
    }

    public static boolean PhoneNumberAbnormal(String phone) {
        String regex1 = "\\d{11}";
        if (!phone.matches(regex1)) {
            throw new PhoneNumberFormatException();
        }
        return false;
    }

    //判断是否有存在(有则返回索引，无则返回-1）
    public static int judgeQQ(String qq) {
        for (int i = 0; i < listMessage.size(); i++) {
            if (qq.equals(listMessage.get(i).getQq())) {
                return i;
            }
        }
        return -1;
    }

    //获取验证码
    public static byte[] identify() throws IOException {
        //利用file储存数据字母
        RandomAccessFile file = new RandomAccessFile("src/test/T5/txt/letter.txt", "r");
        Random r = new Random();
        byte[] arr = new byte[4];
        for (int i = 0; i < 4; i++) {
            int ran = r.nextInt((int) file.length());
            file.seek(ran);
            byte b = file.readByte();
            arr[i] = b;
        }
        return arr;
    }

    //利用这里去读取
    public static ArrayList<userMessage> getListUserMessage() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/test/T5/txt/userStorage.txt"));
        ArrayList<userMessage> list = (ArrayList<userMessage>) ois.readObject();
        return list;
    }

    //抽奖动画
    public static void Shape() throws InterruptedException {
        int rows = 4;
        for (int i = 1; i <= rows; i++) {
            // 打印空格
            for (int j = rows; j > i; j--) {
                System.out.print(" ");
            }

            // 打印星号
            for (int k = 1; k <= (i * 2) - 1; k++) {
                Thread.sleep(200);
                System.out.print("*");
            }
            // 换行
            System.out.println();
        }
    }

    //这里是获取奖池A
    public static ArrayList<goods> getListGoodsA() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/test/T5/txt/goodsA.txt"));
        ArrayList<goods> list = (ArrayList<goods>) ois.readObject();
        ois.close();
        return list;
    }

    //奖池B
    public static ArrayList<goods> getListGoodsB() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/test/T5/txt/goodsB.txt"));
        ArrayList<goods> list = (ArrayList<goods>) ois.readObject();
        ois.close();
        return list;
    }

    //卡池c
    public static ArrayList<goods> getListGoodsC() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/test/T5/txt/goodsC.txt"));
        ArrayList<goods> list = (ArrayList<goods>) ois.readObject();
        ois.close();
        return list;
    }

    //这里是去将每次修改完的人的集合去放到文件中
    public static void setFile(ArrayList<userMessage> list) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/test/T5/txt/userStorage.txt"));
        oos.writeObject(list);
        oos.flush();
        oos.close();
    }

    //这里利用buffer去读取和存储次数
    public static void setCountA(int count) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/test/T5/txt/countA.txt"));
        bw.write(count);
        bw.close();
    }

    public static int getCountA() throws IOException, ClassNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader("src/test/T5/txt/countA.txt"));
        int count = br.read();
        br.close();
        return count;
    }

    public static int getCountB() throws IOException, ClassNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader("src/test/T5/txt/countB.txt"));
        int count = br.read();
        br.close();
        return count;
    }

    public static void setCountB(int count) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/test/T5/txt/countB.txt"));
        bw.write(count);
        bw.close();
    }

    public static int getCountC() throws IOException, ClassNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader("src/test/T5/txt/countC.txt"));
        int count = br.read();
        br.close();
        return count;
    }

    public static void setCountC(int count) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/test/T5/txt/countC.txt"));
        bw.write(count);
        bw.close();
    }

    //储存历史记录
    public static void setFileHistory(ArrayList<history> list) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/test/T5/txt/historyStorage.txt"));
        oos.writeObject(list);
        oos.flush();
        oos.close();
    }

    //读取历史记录
    public static ArrayList<history> getFileHistory() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/test/T5/txt/historyStorage.txt"));
        ArrayList<history> list = (ArrayList<history>) ois.readObject();
        return list;
    }
}