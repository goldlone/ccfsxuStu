package cn.goldlone.controller;

import cn.goldlone.model.Result;
import cn.goldlone.po.Book;
import cn.goldlone.service.impl.BookServiceImpl;
import cn.goldlone.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by CN on 2018/1/8.
 */
@RestController
public class BookController extends BaseController {

    @Autowired
    private BookServiceImpl bs;

    /**
     * 获取图书类别
     * @return
     */
    @PostMapping("/book/getBookType")
    public Result getBookType() {
        return bs.getBookType();
    }

    /**
     * 根据类别查询图书信息
     * @param typeNo
     * @return
     */
    @PostMapping("/book/selectBookByType")
    public Result selectBookByType(int typeNo) {
        return bs.selectBookByType(typeNo);
    }

    /**
     * 按照图书名查询图书信息
     * @param bookName
     * @return
     */
    @PostMapping("/book/selectBookByName")
    public Result selectBookByName(String bookName) {
        return bs.selectBookByName(bookName);
    }

    /**
     * 按照图书ISBN查询图书信息
     * @param isbn
     * @return
     */
    @PostMapping("/book/selectBookByISBN")
    public Result selectBookByISBN(String isbn) {
        return bs.selectBookByISBN(isbn);
    }


    /**
     * 借书
     * @param isbn
     * @param memberNo
     * @return
     */
    @PostMapping("/book/borrowBook")
    public Result borrowBook(String isbn, String memberNo) {
        return bs.borrowBook(isbn, memberNo);
    }

    /**
     * 查询借阅信息
     * @param isbn
     * @return
     */
    @PostMapping("/book/searchBorrowOrder")
    public Result searchBorrowOrder(String isbn) {
        return bs.searchBorrowOrder(isbn);
    }


    /**
     * 还书
     * @param no
     * @param isbn
     * @return
     */
    @PostMapping("/book/backBook")
    public Result backBook(int no, String isbn) {
        return bs.backBook(no, isbn);
    }


    /**
     * 添加图书
     * @param book
     * @param typeName
     * @return
     */
    @PostMapping("/book/add")
    public Result addBook(Book book, String typeName) {
        return bs.addBook(book, typeName);
    }

    /**
     * 文件导入图书信息
     * @param file
     * @return
     */
    @PostMapping("/book/addByFile")
    public Result addBookByFile(@RequestParam("file") MultipartFile file) {
        return ExcelUtils.importBook(file);
    }

}
