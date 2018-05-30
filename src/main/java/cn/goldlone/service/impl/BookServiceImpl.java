package cn.goldlone.service.impl;

import cn.goldlone.mapper.BookMapper;
import cn.goldlone.mapper.MemberMapper;
import cn.goldlone.model.BookInfo;
import cn.goldlone.model.BorrowInfo;
import cn.goldlone.model.Result;
import cn.goldlone.po.Book;
import cn.goldlone.po.BookType;
import cn.goldlone.po.BorrowBook;
import cn.goldlone.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by CN on 2018/1/6.
 */
@Service
public class BookServiceImpl {

    @Autowired
    private BookMapper bm;
    @Autowired
    private MemberMapper mm;


    /**
     * 获取图书类别
     * @return
     */
    public Result getBookType() {
        return ResultUtil.success(bm.getBookType(), "获取图书类别成功");
    }

    /**
     * 根据类别查询图书信息
     * @param typeNo
     * @return
     */
    public Result selectBookByType(int typeNo) {
        Result result = null;
        if (typeNo == 0) {
            result =  ResultUtil.success(bm.selectAllBook(), "获取全部图书信息");
        }
        else
            result = ResultUtil.success(bm.selectBookByType(typeNo), "按照图书类别查询成功");
        return result;
    }

    /**
     * 按照图书名查询图书信息
     * @param bookName
     * @return
     */
    public Result selectBookByName(String bookName) {
        return ResultUtil.success(bm.selectBookByName(bookName), "按照图书名查询成功");
    }

    /**
     * 按照图书ISBN查询图书信息
     * @param isbn
     * @return
     */
    public Result selectBookByISBN(String isbn) {
        BookInfo bookInfo = bm.selectBookByISBN(isbn);
        if (bookInfo == null)
            return ResultUtil.error(ResultUtil.CODE_NOT_EXIST, "该图书不存在");
        return ResultUtil.success(bookInfo, "按照图书ISBN查询成功");
    }


    /**
     * 借书
     * @param isbn
     * @param memberNo
     * @return
     */
    public Result borrowBook(String isbn, String memberNo) {
        Result result = null;
        if(mm.selectMemberByNo(memberNo) == null) {
            result = ResultUtil.error(ResultUtil.CODE_NOT_EXIST, "该会员不存在");
        } else {
            Integer num = null;
            BorrowBook book = new BorrowBook(isbn, memberNo, new Timestamp(System.currentTimeMillis()));
            num = bm.selectInventory(book.getBookNo());
            if (num != null && num > 0) {
                bm.borrowBook(book);
                bm.updateInventoryByBorrow(book.getBookNo());
                result = ResultUtil.success( "借书成功");
            } else if (num == null) {
                result = ResultUtil.error(ResultUtil.CODE_NOT_EXIST, "该图书不存在");
            } else {
                result = ResultUtil.error(ResultUtil.CODE_NOT_ENOUGH, "库存不足");
            }
        }
        return result;
    }

    /**
     * 查询借阅信息
     * @param isbn
     * @return
     */
    public Result searchBorrowOrder(String isbn) {
        BookInfo book = bm.selectBookByISBN(isbn);
        if(book == null)
            return ResultUtil.error(ResultUtil.CODE_NOT_EXIST, "该图书不存在");
        List<BorrowInfo> list = bm.selectNotBackBook(isbn);
        if(list.size()>0)
            return ResultUtil.success(list, "查询借阅信息成功("+book.getName()+")");
        else
            return ResultUtil.error(ResultUtil.CODE_NOT_EXIST, "图书("+book.getName()+")无借阅记录");
    }

    /**
     * 还书
     * @param no
     * @param isbn
     * @return
     */
    public Result backBook(int no, String isbn) {
        bm.backBook(no);
        bm.updateInventoryByBorrow(isbn);
        return ResultUtil.success( "还书成功");
    }

    /**
     * 添加图书
     * @return
     */
    public Result addBook(Book book, String typeName) {
        if(bm.selectBookByISBN(book.getNo())!=null)
            return ResultUtil.error(ResultUtil.CODE_HAD_EXIST, "该图书已存在");
        Integer typeNo = bm.selectTypeNoByName(typeName);
        if(typeNo==null) {
            BookType bt = new BookType(typeName);
            bm.addBookType(bt);
            typeNo = bt.getNo();
        }
        book.setTypeNo(typeNo);
        bm.addBook(book);
        return ResultUtil.success(null, "添加图书成功");
    }
}
