package cn.goldlone.dao;

import cn.goldlone.entity.BookType;
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
                info.setNo(isbn);
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

    public static void main(String[] args) {
//        System.out.println(new BookDao().selectBookByType(0).get(0));
        System.out.println(new BookDao().selectBookByName("数据库").get(0));
    }


}