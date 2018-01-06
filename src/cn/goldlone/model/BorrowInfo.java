package cn.goldlone.model;

import java.sql.Timestamp;

/**
 * 借阅图书信息
 * Created by CN on 2018/1/6.
 */
public class BorrowInfo {
    // 借阅编号
    private int no;
    // 图书编号
    private String bookNo;
    // 会员号
    private String memberNo;
    // 姓名
    private String memberName;
    // 借阅时间
    private Timestamp borrowTime;
    // 归还时间
    private Timestamp backTime;

    public BorrowInfo() {
    }

    public BorrowInfo(int no, String bookNo, String memberNo, String memberName,
                      Timestamp borrowTime, Timestamp backTime) {
        this.no = no;
        this.bookNo = bookNo;
        this.memberNo = memberNo;
        this.memberName = memberName;
        this.borrowTime = borrowTime;
        this.backTime = backTime;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Timestamp getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Timestamp borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Timestamp getBackTime() {
        return backTime;
    }

    public void setBackTime(Timestamp backTime) {
        this.backTime = backTime;
    }

    @Override
    public String toString() {
        return "BorrowInfo{" +
                "no=" + no +
                ", bookNo='" + bookNo + '\'' +
                ", memberNo='" + memberNo + '\'' +
                ", memberName='" + memberName + '\'' +
                ", borrowTime=" + borrowTime +
                ", backTime=" + backTime +
                '}';
    }
}
