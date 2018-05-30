package cn.goldlone.mapper;

import cn.goldlone.model.BookInfo;
import cn.goldlone.model.BorrowInfo;
import cn.goldlone.po.Book;
import cn.goldlone.po.BookType;
import cn.goldlone.po.BorrowBook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by CN on 2017/12/30.
 */
@Mapper
public interface BookMapper {
    // 添加图书类别
    public int addBookType(BookType bookType);
    // 获取图书类别
    public List<BookType> getBookType();
    // 获取类别名
    public String selectTypeName(int typeNo);
    // 查询图书类别编号
    public Integer selectTypeNoByName(String typeName);
    // 添加图书信息
    public int addBook(Book book);
    public List<BookInfo> selectAllBook();
    // 根据ISBN编号查询数目信息
    public BookInfo selectBookByISBN(String isbn);
    // 根据类别编号查询数目信息
    public List<BookInfo> selectBookByType(int typeNo);
    // 根据部分书名查询数目信息
    public List<BookInfo> selectBookByName(String name);
    // 查询库存
    public Integer selectInventory(String isbn);
    // 借书
    public int borrowBook(BorrowBook book);
    // 借书更新库存
    public int updateInventoryByBorrow(String isbn);
    // 查询未归还图书列表
    public List<BorrowInfo> selectNotBackBook(String isbn);
    // 还书
    public int backBook(int no);
    // 还书更新库存
    public int updateInventoryByBack(String isbn);

}
