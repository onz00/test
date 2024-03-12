package test.T5.mode;

import test.T5.class1.administratorMessage;
import test.T5.class1.goods;
import test.T5.class1.history;
import test.T5.class1.userMessage;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class storage {
    public static void main(String[] args) throws IOException, ClassNotFoundException {



     //这里是我存储信息方便调试的【因为要给文件搞的初始值，这里只需要运行一次即可）【可以不用看，不影响使用】






        //用于遍历信息:这里的goods是所有物品的（奇奇怪怪都可以/角色什么的
        //而Hash存的是道具卡与道具卡的数量
        HashMap<String,Integer> hm=new HashMap<>();
    //   //添加四种道具，对应上面的四种道具
       hm.put("a",10);
       hm.put("b",10);
       hm.put("c",10);
        //人拥有的物品+数量
        HashMap<String,Integer> hmGoods=new HashMap<>();
        hmGoods.put("苇海信标",1);
        hmGoods.put("若水",1);
        hmGoods.put("萤火",1);
        hmGoods.put("M9刺刀",1);
        hmGoods.put("千岩长枪",1);
        //定义用户信息
        userMessage u=new userMessage("a","11111111111","a","a",hm,hmGoods);
        //储存到集合并文件中
        ArrayList<userMessage>listu=new ArrayList<>();
        listu.add(u);
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("src/test/T5/txt/userStorage.txt"));
        oos.writeObject(listu);
        oos.close();
        //储存管理员信息
        administratorMessage a=new administratorMessage("a","11111111111","a","a");
        ObjectOutputStream oosAd=new ObjectOutputStream(new FileOutputStream("src/test/T5/txt/AdministratorMessage.txt"));
        ArrayList<administratorMessage>listAD=new ArrayList<>();
        listAD.add(a);
        oosAd.writeObject(listAD);
        oosAd.close();
        //奖品池A
        ArrayList<goods>listg1=new ArrayList<>();
        goods g1=new goods("龙狙纪念 ","A","锁头  ","红色","巨龙传说");
        goods g2=new goods("暗金爪子刀","A","一刀暴击","红色","aa");
        goods g3=new goods("蓝金爪子刀","D","一刀999","红色","aa");
        goods g4=new goods("淬火AK47 ","A","攻击max","红色","aa");
        goods g5=new goods("全息P印花 ","A","好看","红色","aa");
        goods g6=new goods("深红刺刀 ","D","攻击力++","红色","aa");
        goods g7=new goods("M9刺刀 ","D","爆    ","红色","aa");
        goods g8=new goods("暗影双匕 ","A","锁头  ","红色","aa");
        listg1.add(g1);
        listg1.add(g2);
        listg1.add(g3);
        listg1.add(g4);
        listg1.add(g5);
        listg1.add(g6);
        listg1.add(g7);
        listg1.add(g8);
        ObjectOutputStream oosg=new ObjectOutputStream(new FileOutputStream("src/test/T5/txt/goodsA.txt"));
        oosg.writeObject(listg1);
        oosg.close();
        //卡池B
        ArrayList<goods>listgB=new ArrayList<>();
        goods gb1=new goods("魈    ","C","尸鬼封尽","紫色","a");
        goods gb2=new goods("钟离  ","A","飞雷神之术","紫色","a");
        goods gb3=new goods("八神重子","A","地爆天星","紫色","a");
        goods gb4=new goods("达达利亚","A","召唤六道","紫色","a");
        goods gb5=new goods("坎蒂丝 ","C","神罗天征","紫色","a");
        goods gb6=new goods("妮露  ","C","灵魂吞噬","紫色","a");
        goods gb7=new goods("云堇  ","A","万象天引","紫色","a");
        goods gb8=new goods("绮良良  ","A","地爆天星","紫色","a");
        goods gb9=new goods("萤火  ","C","通灵·外道魔像","紫色","a");
        goods gb10=new goods("阿贝多  ","A","雷遁·麒麟","紫色","a");
        listgB.add(gb1);
        listgB.add(gb2);
        listgB.add(gb3);
        listgB.add(gb4);
        listgB.add(gb5);
        listgB.add(gb6);
        listgB.add(gb7);
        listgB.add(gb8);
        listgB.add(gb9);
        listgB.add(gb10);
        ObjectOutputStream oosgb=new ObjectOutputStream(new FileOutputStream("src/test/T5/txt/goodsB.txt"));
        oosgb.writeObject(listgB);
        oosgb.close();
        //卡池C
        ArrayList<goods>listgC=new ArrayList<>();
        goods gc1=new goods("迷雾之回光","A","大玉螺旋丸","紫色","a");
        goods gc2=new goods("神乐之真意","B","烟玉   ","紫色","a");
        goods gc3=new goods("护摩之杖","S","多重影分身","紫色","a");
        goods gc4=new goods("阿莫斯之弓","S","雷切   ","紫色","a");
        goods gc5=new goods("千岩长枪","S","雷盾影分身","紫色","a");
        goods gc6=new goods("昭心   ","A","瞳术   ","紫色","a");
        goods gc7=new goods("绝弦   ","B","医疗忍术 ","紫色","a");
        goods gc8=new goods("流月针  ","A","豪火球之术","紫色","a");
        listgC.add(gc1);
        listgC.add(gc2);
        listgC.add(gc3);
        listgC.add(gc4);
        listgC.add(gc5);
        listgC.add(gc6);
        listgC.add(gc7);
        listgC.add(gc8);
        ObjectOutputStream oosgc=new ObjectOutputStream(new FileOutputStream("src/test/T5/txt/goodsC.txt"));
        oosgc.writeObject(listgC);
        oosgc.close();
        //保底系统1
        BufferedWriter bw1=new BufferedWriter(new FileWriter("src/test/T5/txt/countA.txt"));
        bw1.write(0);
        bw1.close();
        //保底系统2B
        BufferedWriter bw2=new BufferedWriter(new FileWriter("src/test/T5/txt/countB.txt"));
        bw2.write(0);
        bw2.close();
        //C
        BufferedWriter bw3=new BufferedWriter(new FileWriter("src/test/T5/txt/countC.txt"));
        bw3.write(0);
        bw3.close();
        //给历史记录初始化
     ObjectOutputStream oosHistory = new ObjectOutputStream(new FileOutputStream("src/test/T5/txt/historyStorage.txt"));
     LocalDateTime time=LocalDateTime.now();
     history history=new history(time,"null","null","null","null");
     ArrayList<history>listHistory=new ArrayList<>();
     listHistory.add(history);
     oosHistory.writeObject(listHistory);
     oosHistory.flush();
     oosHistory.close();
    }
    }

