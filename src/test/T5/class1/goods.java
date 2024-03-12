package test.T5.class1;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class goods implements Serializable {

    private static final long serialVersionUID = -3331290286417519454L;
    //卡池物品名字
    private String name;
    //等级：abc
    private String rank;
    //功能
    private String function;
    //品质【紫色
    private String quality;
    //介绍
    private String introduce;
    //哈希存储

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        goods goods = (goods) o;
        return Objects.equals(name, goods.name) && Objects.equals(rank, goods.rank) && Objects.equals(function, goods.function) && Objects.equals(quality, goods.quality) && Objects.equals(introduce, goods.introduce);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rank, function, quality, introduce);
    }

    public goods(String name, String rank, String function, String quality, String introduce) {
        this.name = name;
        this.rank = rank;
        this.function = function;
        this.quality = quality;
        this.introduce = introduce;
    }
    public goods(){};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "物品" +
                "名称:" + name + '\t' +'\t' +'\t' +'\t' +'\t' +'\t' +'\t' +'\t' +
                " 等级:" + rank + '\t' +
                " 功能:" + function + '\t' +'\t' +'\t' +'\t' +'\t' +'\t' +
                " 品质:" + quality + '\t' +'\t' +
                " 介绍:" + introduce
                ;
    }
}
