package test.T1;

import java.util.Objects;

//  - 类和变量的命名要做到“见名知意”，注释记得写！
public class rich {
    private String asset;
    private String name;
    public rich(){
    }
    public rich(String asset, String name) {
        this.asset = asset;
        this.name = name;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        rich rich = (rich) o;
        return Objects.equals(asset, rich.asset) && Objects.equals(name, rich.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asset, name);
    }

    @Override
    public String toString() {
        return "rich{" +
                "老总name:   " + name + '\t' +'\t'+
                "金库：   " + asset + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
