package cn.goldlone.entity;

/**
 * 认证实体类
 * Created by CN on 2017/10/17.
 */
public class Certification {
    private int no;
    private String name;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Certification{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
