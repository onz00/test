package test.T5.class1;

import java.io.Serializable;
import java.time.LocalDateTime;

public class history implements Serializable {

    private static final long serialVersionUID = 4910559538078577968L;
    private   LocalDateTime time;
      private   String GoodsName;
    private String rank;
    private String quality;
    private String propsName;

    public history(LocalDateTime time, String goodsName, String rank, String quality,String propsName) {
        this.time = time;
        GoodsName = goodsName;
        this.rank = rank;
        this.quality = quality;
        this.propsName=propsName;
    }

    public String getPropsName() {
        return propsName;
    }

    public void setPropsName(String propsName) {
        this.propsName = propsName;
    }

    public history() {
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getGoodsName() {
        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }


    @Override
    public String toString() {
        return "时间：" + time +'\t'+
                "你的" +'\t'+propsName+"道具-1"+'\t'+'\t'+
                "获得物品：" + GoodsName  +'\t'+'\t'+
                "该物品等级为：" + rank + '\t' +
                "品质：" + quality ;
    }
}
