package cn.goldlone.po;

/**
 * Created by CN on 2017/10/17.
 */
public class BookType {
    // 类别编号
    private int no;
    // 类别名
    private String name;

    public BookType() {
    }

    public BookType(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public BookType(String name) {
        this.name = name;
    }

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
        return "BookType{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
