package cn.goldlone.po;

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
    private String borrowTime;
    // 归还时间
    private String backTime;

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

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getBackTime() {
        return backTime;
    }

    public void setBackTime(String backTime) {
        this.backTime = backTime;
    }

}
