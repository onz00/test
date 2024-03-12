package test.T5.class1;

import java.io.Serializable;

public class administratorMessage implements Serializable {
    private static final long serialVersionUID = -5173616326066511494L;
    private String name;
    private String qq;
    private String phoneNumber;
    private String password;

    public administratorMessage(String name, String qq, String phoneNumber, String password) {
        this.name = name;
        this.qq = qq;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }


    public administratorMessage() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return  "名字" + name + '\t'+'\t'+
                "QQ:" + qq + '\t'+'\t'+
                "手机号：" + phoneNumber + '\t'+'\t'+
                "密码：" + password + '\t'+'\t';
    }
}
