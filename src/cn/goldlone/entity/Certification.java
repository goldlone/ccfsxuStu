package cn.goldlone.entity;

/**
 * 认证实体类
 * Created by CN on 2017/10/17.
 */
public class Certification {
    private int no;
    private String name;
    private String startTime;
    private String endTime;
    private int memberFee;
    private int notMemberFee;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getMemberFee() {
        return memberFee;
    }

    public void setMemberFee(int memberFee) {
        this.memberFee = memberFee;
    }

    public int getNotMemberFee() {
        return notMemberFee;
    }

    public void setNotMemberFee(int notMemberFee) {
        this.notMemberFee = notMemberFee;
    }

    @Override
    public String toString() {
        return "Certification{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", memberFee=" + memberFee +
                ", notMemberFee=" + notMemberFee +
                '}';
    }
}
