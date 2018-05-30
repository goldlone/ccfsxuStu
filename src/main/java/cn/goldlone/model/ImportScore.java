package cn.goldlone.model;

/**
 * Created by CN on 2018/1/6.
 */
public class ImportScore {
    // CSP认证名
    private String certName;
    // 会员姓名
    private String name;
    // 邮箱
    private String email;
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

    public ImportScore() {
    }

    public ImportScore(String certName, String name, String email, int all,
                       int first, int second, int third, int forth, int fifth) {
        this.certName = certName;
        this.name = name;
        this.email = email;
        this.all = all;
        this.first = first;
        this.second = second;
        this.third = third;
        this.forth = forth;
        this.fifth = fifth;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
}
