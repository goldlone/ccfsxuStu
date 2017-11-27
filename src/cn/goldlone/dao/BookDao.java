package cn.goldlone.dao;

import cn.goldlone.entity.BookType;
import cn.goldlone.entity.BorrowBook;
import cn.goldlone.model.BookInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 图书操纵
 * Created by CN on 2017/10/17.
 */
public class BookDao {

    /**
     * 获取图书类别
     * @return
     */
    public List<BookType> getBookType() {
        List<BookType> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
            conn = DBDao.getConnection();
            sql = "SELECT B_typeNo, B_typeName FROM BookType;";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                BookType book = new BookType();
                book.setNo(rs.getInt(1));
                book.setName(rs.getString(2));
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBDao.closeConnection(conn);
            DBDao.closePreparedStatement(pstmt);
            DBDao.closeResultSet(rs);
        }

        return list;
    }

    /**
     * 获取类别名
     * @param typeNo
     * @return
     */
    public String selectTypeName(int typeNo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
            conn = DBDao.getConnection();
            sql = "SELECT B_typeName FROM BookType WHERE B_typeNo = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, typeNo);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBDao.closeConnection(conn);
            DBDao.closePreparedStatement(pstmt);
            DBDao.closeResultSet(rs);
        }
        return "暂未分类";
    }

    /**
     * 根据ISBN编号查询数目信息
     * @param isbn
     * @return
     */
    public BookInfo selectBookByISBN(String isbn) {
        BookInfo info = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
            conn = DBDao.getConnection();
            sql = "SELECT B_name, B_author, B_publicer, B_price, B_inventory, B_typeNo " +
                    "FROM BookInfo " +
                    "WHERE B_bookNo = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, isbn);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                info = new BookInfo();
                info.setNo(String.valueOf(isbn));
                info.setName(rs.getString(1));
                info.setAuthor(rs.getString(2));
                info.setPublicer(rs.getString(3));
                info.setPrice(rs.getDouble(4));
                info.setInventory(rs.getInt(5));
                info.setType(this.selectTypeName(rs.getInt(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBDao.closeConnection(conn);
            DBDao.closePreparedStatement(pstmt);
            DBDao.closeResultSet(rs);
        }
        return info;
    }

    /**
     * 根据类别编号查询数目信息
     * @param typeNo
     * @return
     */
    public ArrayList<BookInfo> selectBookByType(int typeNo) {
        ArrayList<BookInfo> list = new ArrayList<BookInfo>();
        String typeName = this.selectTypeName(typeNo);
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
            conn = DBDao.getConnection();
            sql = "SELECT B_bookNo, B_name, B_author, B_publicer, B_price, B_inventory " +
                    "FROM BookInfo ";
            if(typeNo!=0)
                sql = sql + "WHERE B_typeNo = ?;";
            pstmt = conn.prepareStatement(sql);
            if(typeNo!=0)
                pstmt.setInt(1, typeNo);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                BookInfo info = new BookInfo();
                info.setNo(rs.getString(1));
                info.setName(rs.getString(2));
                info.setAuthor(rs.getString(3));
                info.setPublicer(rs.getString(4));
                info.setPrice(rs.getDouble(5));
                info.setInventory(rs.getInt(6));
                info.setType(typeName);
                list.add(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBDao.closeConnection(conn);
            DBDao.closePreparedStatement(pstmt);
            DBDao.closeResultSet(rs);
        }
        return list;
    }

    /**
     * 根据部分书名查询数目信息
     * @param name
     * @return
     */
    public ArrayList<BookInfo> selectBookByName(String name) {
        ArrayList<BookInfo> list = new ArrayList<BookInfo>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
            conn = DBDao.getConnection();
            sql = "SELECT B_bookNo, B_name, B_typeNo, B_author, B_publicer, B_price, B_inventory " +
                    "FROM BookInfo " +
                    "WHERE B_name LIKE ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%"+name+"%");
            rs = pstmt.executeQuery();
            while(rs.next()) {
                BookInfo info = new BookInfo();
                info.setNo(rs.getString(1));
                info.setName(rs.getString(2));
                info.setType(this.selectTypeName(rs.getInt(3)));
                info.setAuthor(rs.getString(4));
                info.setPublicer(rs.getString(5));
                info.setPrice(rs.getDouble(6));
                info.setInventory(rs.getInt(7));
                list.add(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBDao.closeConnection(conn);
            DBDao.closePreparedStatement(pstmt);
            DBDao.closeResultSet(rs);
        }
        return list;
    }

    /**
     * 借书
     * @param book
     * @return
     */
    public boolean borrowBook(BorrowBook book) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        try {
            conn = DBDao.getConnection();
            // 库存-1
            sql = "UPDATE BookInfo SET B_inventory = B_inventory-1 WHERE B_bookNo = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getBookNo());
            if(pstmt.executeUpdate()==0)
                return false;
            sql = "INSERT INTO BorrowBook(B_bookNo, B_memberNo, B_borrowTime) " +
                    "VALUES (?, ?, now()) ;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getBookNo());
            pstmt.setString(2, book.getMemberNo());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBDao.closeConnection(conn);
            DBDao.closePreparedStatement(pstmt);
        }
        return false;
    }

    /**
     * 还书
     * @param book
     * @return
     */
    public boolean backBook(BorrowBook book) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        try {
            conn = DBDao.getConnection();
            // 库存+1
            sql = "UPDATE BookInfo " +
                    "SET B_inventory = B_inventory+1 " +
                    "WHERE B_bookNo = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getBookNo());
            if(pstmt.executeUpdate()==0)
                return false;
            // 更新归还时间
            sql = "UPDATE BorrowBook " +
                    "SET B_backTime = now() " +
                    "WHERE B_borrowNo = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, book.getNo());
            if(pstmt.executeUpdate()>0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBDao.closeConnection(conn);
            DBDao.closePreparedStatement(pstmt);
        }
        return false;
    }

    /**
     * 查询库存
     * @param isbn
     * @return
     */
    public int selectInventory(String isbn) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
            conn = DBDao.getConnection();
            sql = "SELECT B_inventory " +
                    "FROM BookInfo " +
                    "WHERE B_bookNo = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, isbn);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            } else {
                return Integer.MAX_VALUE;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBDao.closeConnection(conn);
            DBDao.closePreparedStatement(pstmt);
            DBDao.closeResultSet(rs);
        }

        return 0;
    }

    /**
     * 查询未归还图书列表
     * @param isbn
     * @return
     */
    public ArrayList<BorrowBook> selectOrder(String isbn) {
        ArrayList<BorrowBook> list = new ArrayList<BorrowBook>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
            conn = DBDao.getConnection();
            sql = "SELECT B_borrowNo, B_memberNo FROM BorrowBook " +
                    "WHERE B_bookNo=? AND B_backTime IS NULL; ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, isbn);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                BorrowBook book = new BorrowBook();
                book.setNo(rs.getInt(1));
                book.setMemberNo(rs.getString(2));
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBDao.closeConnection(conn);
            DBDao.closePreparedStatement(pstmt);
            DBDao.closeResultSet(rs);
        }

        return list;
    }

    public static void main(String[] args) {
//        System.out.println(new BookDao().selectBookByType(0).get(0));
//        System.out.println(new BookDao().selectBookByName("数据库").get(0));
//        System.out.println(new BookDao().selectInventory("9787040406641"));
//        System.out.println(new BookDao().selectOrder("9787040406641").size());
//        BorrowBook book = new BorrowBook();
//        book.setBookNo("9787040406641");
//        book.setMemberNo("62151G");
//        book.setBorrowTime("2017-11-25");
//        book.setNo(3);
//        book.setBackTime("2017-11-29");

//        System.out.println(new BookDao().borrowBook(book));
//        System.out.println(new BookDao().backBook(book));

    }


}