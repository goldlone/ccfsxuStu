package cn.goldlone.model;

/**
 * Created by CN on 2018/1/10.
 */
public class SingleScore {
    // 总成绩
    private int all;
    // 第一题
    private int first;
    // 第二题
    private int second;
    // 第三题
    private int third;
    // 第四题
    private int forth;
    // 第五题
    private int fifth;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getThird() {
        return third;
    }

    public void setThird(int third) {
        this.third = third;
    }

    public int getForth() {
        return forth;
    }

    public void setForth(int forth) {
        this.forth = forth;
    }

    public int getFifth() {
        return fifth;
    }

    public void setFifth(int fifth) {
        this.fifth = fifth;
    }

    @Override
    public String toString() {
        return "SingleScore{" +
                "all=" + all +
                ", first=" + first +
                ", second=" + second +
                ", third=" + third +
                ", forth=" + forth +
                ", fifth=" + fifth +
                '}';
    }
}
