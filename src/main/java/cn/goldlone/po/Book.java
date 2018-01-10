package cn.goldlone.po;

import cn.goldlone.model.BookInfo;

/**
 * Created by CN on 2017/10/17.
 */
public class Book {
    // 图书编号
    private String no;
    // 图书名
    private String name;
    // 类别编号
    private int typeNo;
    // 作者
    private String author;
    // 出版社
    private String publicer;
    // 出版时间
    private String publicDate;
    // 价格
    private double price;
    // 库存量
    private int inventory;

    public Book() {
    }

    public Book(String no, String name, int typeNo,
                String author, String publicer, String publicDate,
                double price, int inventory) {
        this.no = no;
        this.name = name;
        this.typeNo = typeNo;
        this.author = author;
        this.publicer = publicer;
        this.publicDate = publicDate;
        this.price = price;
        this.inventory = inventory;
    }

    public Book(BookInfo info, int typeNo) {
        this.no = info.getNo();
        this.name = info.getName();
        this.typeNo = typeNo;
        this.author = info.getAuthor();
        this.publicer = info.getPublicer();
        this.publicDate = info.getPublicDate();
        this.price = info.getPrice();
        this.inventory = info.getInventory();
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeNo() {
        return typeNo;
    }

    public void setTypeNo(int typeNo) {
        this.typeNo = typeNo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicer() {
        return publicer;
    }

    public void setPublicer(String publicer) {
        this.publicer = publicer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", typeNo=" + typeNo +
                ", author='" + author + '\'' +
                ", publicer='" + publicer + '\'' +
                ", publicDate='" + publicDate + '\'' +
                ", price=" + price +
                ", inventory=" + inventory +
                '}';
    }
}
