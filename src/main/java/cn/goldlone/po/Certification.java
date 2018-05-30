package cn.goldlone.po;

import java.sql.Timestamp;

/**
 * 认证实体类
 * Created by CN on 2017/10/17.
 */
public class Certification {
    // 认证编号
    private int no;
    // 认证名称
    private String name;
    // 认证开始时间
    private Timestamp startTime;
    // 认证结束时间
    private Timestamp endTime;
    // 会员报名费用
    private int memberFee;
    // 非会员报名费用
    private int notMemberFee;

    public Certification() {
    }

    public Certification(String name, Timestamp startTime, Timestamp endTime,
                         int memberFee, int notMemberFee) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.memberFee = memberFee;
        this.notMemberFee = notMemberFee;
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

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
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
