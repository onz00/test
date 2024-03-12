package test.T5.class1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class userMessage implements Serializable {
    private static final long serialVersionUID = 1337870196970534354L;
    private String name;
    private String qq;
    private String phoneNumber;
    private String password;
    //储存物品的数量+名字
    private HashMap<String,Integer>hmGoods;
    //可根据是什么类型的道具去增加/减少数量
    private HashMap<String,Integer>hm;
    public userMessage(){}

    public HashMap<String, Integer> getHmGoods() {
        return hmGoods;
    }

    public void setHmGoods(HashMap<String, Integer> hmGoods) {
        this.hmGoods = hmGoods;
    }

    public userMessage(String name, String qq, String phoneNumber, String password, HashMap<String,Integer> hm, HashMap<String,Integer>hmGoods) {
        this.name = name;
        this.qq = qq;
        this.phoneNumber = phoneNumber;
        this.password=password;
        this.hm=hm;
        this.hmGoods=hmGoods;
    }
    public HashMap<String, Integer> getHm() {
        return hm;
    }

    public void setHm(HashMap<String, Integer> hm) {
        this.hm = hm;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return
                "名字" + name + '\t'+'\t'+
                "QQ:" + qq + '\t'+'\t'+
                "手机号：" + phoneNumber + '\t'+'\t'+
                "密码：" + password + '\t'+'\t'+
                "拥有的物品：" + hmGoods +'\t'+'\t'+
                "道具信息" + hm ;
    }
}
