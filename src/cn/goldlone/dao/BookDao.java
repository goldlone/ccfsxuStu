package cn.goldlone.dao;

import cn.goldlone.entity.BookType;
import cn.goldlone.entity.Certification;

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

}
