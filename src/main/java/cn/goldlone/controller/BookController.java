package cn.goldlone.controller;

import cn.goldlone.mapper.BookMapper;
import cn.goldlone.model.BookInfo;
import cn.goldlone.model.Result;
import cn.goldlone.po.BookType;
import cn.goldlone.utils.MybatisUtils;
import cn.goldlone.utils.ResultUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by CN on 2018/1/8.
 */
@RestController
public class BookController {
    private SqlSession sqlSession = MybatisUtils.openSqlSession();
    private BookMapper bm = sqlSession.getMapper(BookMapper.class);

    /**
     * 获取图书类别
     * @return
     */
    @PostMapping("/book/getBookType")
    public Result<List<BookType>> getBookType() {
        return ResultUtils.success(bm.getBookType(), "获取图书类别成功");
    }

    /**
     * 根据类别查询图书信息
     * @param typeNo
     * @return
     */
    @PostMapping("/book/selectBookByType")
    public Result<List<BookInfo>> selectBookByType(int typeNo) {
        Result<List<BookInfo>> result = null;
        if(typeNo==0)
            result =  ResultUtils.success(bm.selectAllBook(), "获取全部图书信息");
        else
            result =  ResultUtils.success(bm.selectBookByType(typeNo), "按照图书类别查询成功");
        return result;
    }

    /**
     * 按照图书名查询图书信息
     * @param bookName
     * @return
     */
    @PostMapping("/book/selectBookByName")
    public Result<List<BookInfo>> selectBookByName(String bookName) {
        Result<List<BookInfo>> result = null;
        result = ResultUtils.success(bm.selectBookByName(bookName), "按照图书名查询成功");
        return result;
    }

    /**
     * 按照图书ISBN查询图书信息
     * @param isbn
     * @return
     */
    @PostMapping("/book/selectBookByISBN")
    public Result<List<BookInfo>> selectBookByISBN(String isbn) {
        Result<List<BookInfo>> result = null;
        result = ResultUtils.success(bm.selectBookByISBN(isbn), "按照图书ISBN查询成功");
        return result;
    }


}
