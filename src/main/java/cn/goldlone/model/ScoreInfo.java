package cn.goldlone.model;

import java.util.Arrays;
import java.util.List;

public class ScoreInfo {
    // 认证编号
    private int certNo;
	// 认证名称
	private String certName;
	// 姓名
	private String memberName;
	// 会员号
	private String memberNo;
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
	// 该次最高分
    private SingleScore max;
    // 该次平均分
    private SingleScore average;
    // 该次最低分
    private SingleScore min;

    public int getCertNo() {
        return certNo;
    }

    public void setCertNo(int certNo) {
        this.certNo = certNo;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
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

    public SingleScore getMax() {
        return max;
    }

    public void setMax(SingleScore max) {
        this.max = max;
    }

    public SingleScore getAverage() {
        return average;
    }

    public void setAverage(SingleScore average) {
        this.average = average;
    }

    public SingleScore getMin() {
        return min;
    }

    public void setMin(SingleScore min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "ScoreInfo{" +
                "certNo=" + certNo +
                ", certName='" + certName + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberNo='" + memberNo + '\'' +
                ", all=" + all +
                ", first=" + first +
                ", second=" + second +
                ", third=" + third +
                ", forth=" + forth +
                ", fifth=" + fifth +
                ", max=" + max +
                ", average=" + average +
                ", min=" + min +
                '}';
    }
}
