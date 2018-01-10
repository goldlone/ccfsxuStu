package cn.goldlone.controller;

import cn.goldlone.mapper.BookMapper;
import cn.goldlone.mapper.MemberMapper;
import cn.goldlone.model.BookInfo;
import cn.goldlone.model.BorrowInfo;
import cn.goldlone.model.Result;
import cn.goldlone.po.Book;
import cn.goldlone.po.BookType;
import cn.goldlone.po.BorrowBook;
import cn.goldlone.utils.ExcelUtils;
import cn.goldlone.utils.MybatisUtils;
import cn.goldlone.utils.ResultUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by CN on 2018/1/8.
 */
@RestController
public class BookController {
    private SqlSession sqlSession = null;
    private BookMapper bm = null;

    /**
     * 获取图书类别
     * @return
     */
    @PostMapping("/book/getBookType")
    public Result getBookType() {
        sqlSession = MybatisUtils.openSqlSession();
        bm = sqlSession.getMapper(BookMapper.class);
        try {
            return ResultUtils.success(bm.getBookType(), "获取图书类别成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.error(1, "异常："+e.getMessage());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 根据类别查询图书信息
     * @param typeNo
     * @return
     */
    @PostMapping("/book/selectBookByType")
    public Result selectBookByType(int typeNo) {
        sqlSession = MybatisUtils.openSqlSession();
        bm = sqlSession.getMapper(BookMapper.class);
        Result result = null;
        try {
            if (typeNo == 0)
                result = ResultUtils.success(bm.selectAllBook(), "获取全部图书信息");
            else
                result = ResultUtils.success(bm.selectBookByType(typeNo), "按照图书类别查询成功");
        } catch (Exception e) {
            result = ResultUtils.error(1, "异常："+e.getMessage());
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    /**
     * 按照图书名查询图书信息
     * @param bookName
     * @return
     */
    @PostMapping("/book/selectBookByName")
    public Result selectBookByName(String bookName) {
        sqlSession = MybatisUtils.openSqlSession();
        bm = sqlSession.getMapper(BookMapper.class);
        Result result = null;
        try {
            result = ResultUtils.success(bm.selectBookByName(bookName), "按照图书名查询成功");
        } catch (Exception e) {
            result =  ResultUtils.error(1, "异常："+e.getMessage());
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    /**
     * 按照图书ISBN查询图书信息
     * @param isbn
     * @return
     */
    @PostMapping("/book/selectBookByISBN")
    public Result selectBookByISBN(String isbn) {
        sqlSession = MybatisUtils.openSqlSession();
        bm = sqlSession.getMapper(BookMapper.class);
        Result result = null;
        try {
            result = ResultUtils.success(bm.selectBookByISBN(isbn), "按照图书ISBN查询成功");
        } catch (Exception e) {
            result =  ResultUtils.error(1, "异常："+e.getMessage());
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }


    /**
     * 借书
     * @return
     */
    @PostMapping("/book/borrowBook")
    public Result borrowBook(String isbn, String memberNo) {
        sqlSession = MybatisUtils.openSqlSession();
        bm = sqlSession.getMapper(BookMapper.class);
        MemberMapper mm = sqlSession.getMapper(MemberMapper.class);
        Result result = null;
        try {
            if(mm.selectMemberByNo(memberNo) == null) {
                result = ResultUtils.error(3, "该会员不存在");
            } else {
                Integer num = null;
                BorrowBook book = new BorrowBook(isbn, memberNo, new Timestamp(System.currentTimeMillis()));
                num = bm.selectInventory(book.getBookNo());
                if (num != null && num > 0) {
                    bm.borrowBook(book);
                    bm.updateInventoryByBorrow(book.getBookNo());
                    sqlSession.commit();
                    result = ResultUtils.success(null, "借书成功");
                } else if (num == null) {
                    result = ResultUtils.error(1, "该图书不存在");
                } else {
                    result = ResultUtils.error(1, "库存不足");
                }
            }
        } catch (Exception e) {
            result = ResultUtils.error(2, "异常："+e.getMessage());
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    /**
     *
     * @param isbn
     * @return
     */
    @PostMapping("/book/searchBorrowOrder")
    public Result searchBorrowOrder(String isbn) {
        sqlSession = MybatisUtils.openSqlSession();
        bm = sqlSession.getMapper(BookMapper.class);
        Result result = null;
        try {
            result = ResultUtils.success(bm.selectNotBackBook(isbn), "查询借阅信息成功");
        } catch (Exception e) {
            result = ResultUtils.error(2, "异常："+e.getMessage());
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    /**
     * 还书
     * @param no
     * @return
     */
    @PostMapping("/book/backBook")
    public Result backBook(int no, String isbn) {
        sqlSession = MybatisUtils.openSqlSession();
        bm = sqlSession.getMapper(BookMapper.class);
        Result result = null;
        try {
            bm.backBook(no);
            bm.updateInventoryByBorrow(isbn);
            sqlSession.commit();
            result = ResultUtils.success(null, "还书成功");
        } catch (Exception e) {
            result = ResultUtils.error(2, "异常："+e.getMessage());
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    /**
     * 添加图书
     * @return
     */
    @PostMapping("/book/add")
    public Result addBook(Book book, String typeName) {
        sqlSession = MybatisUtils.openSqlSession();
        bm = sqlSession.getMapper(BookMapper.class);
        Result result = null;
        try {
            Integer typeNo = bm.selectTypeNoByName(typeName);
            if(typeNo==null) {
                BookType bt = new BookType(typeName);
                bm.addBookType(bt);
                typeNo = bt.getNo();
            }
            book.setTypeNo(typeNo);
            bm.addBook(book);
            sqlSession.commit();
            result = ResultUtils.success(null, "添加图书成功");
        } catch (Exception e) {
            result = ResultUtils.error(2, "异常："+e.getMessage());
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    /**
     * 文件导入图书信息
     * @param file
     * @return
     */
    @PostMapping("/book/addByFile")
    public Result addBookByFile(@RequestParam("file") MultipartFile file) {
        Result result = ExcelUtils.importBook(file);
        return result;
    }

}
