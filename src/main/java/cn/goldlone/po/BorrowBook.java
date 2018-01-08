package cn.goldlone.po;

import cn.goldlone.model.BorrowInfo;

import java.sql.Timestamp;

/**
 * Created by CN on 2017/10/17.
 */
public class BorrowBook {
    // 借阅编号
    private int no;
    // 图书编号
    private String bookNo;
    // 会员号
    private String memberNo;
    // 借阅时间
    private Timestamp borrowTime;
    // 归还时间
    private Timestamp backTime;

    public BorrowBook() {
    }

    public BorrowBook(String bookNo, String memberNo, Timestamp borrowTime) {
        this.bookNo = bookNo;
        this.memberNo = memberNo;
        this.borrowTime = borrowTime;
    }

    public BorrowBook(BorrowInfo info) {
        this.no = info.getNo();
        this.bookNo = info.getBookNo();
        this.memberNo = info.getMemberNo();
        this.borrowTime = info.getBorrowTime();
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
}
