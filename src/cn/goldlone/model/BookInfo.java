package cn.goldlone.model;

/**
 * 图书信息
 * Created by CN on 2017/11/26.
 */
public class BookInfo {
    // 图书编号
    private String no;
    // 图书名
    private String name;
    // 类别
    private String type;
    // 作者
    private String author;
    // 出版社
    private String publicer;
    // 价格
    private double price;
    // 库存量
    private int inventory;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "BookInfo{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", author='" + author + '\'' +
                ", publicer='" + publicer + '\'' +
                ", price=" + price +
                ", inventory=" + inventory +
                '}';
    }
}
