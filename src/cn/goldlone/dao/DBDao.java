package cn.goldlone.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mysql连接数据库
 * 
 * 当每次使用完成后，最好调用closeConnection关闭连接
 * 否则加大数据库的访问压力，消耗CPU资源
 * 
 * @author CN
 * 创建时间: 2017年6月10日
 */
public class DBDao {
	private static final String USER = "cn";
    private static final String PASSWORD = "cheng521";
    private static final String URL = "jdbc:mysql://123.207.159.214:3306/ccfsxu?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
//    private static final String URL = "jdbc:mysql://127.0.0.1:3306/ccfsxu?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    
    /**
     * 获取连接
     * @return 连接对象
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("数据库连接异常");
            e.printStackTrace();
        }
        return conn;
    }
    
    /**
     * 关闭连接
     * @param conn
     */
    public static void closeConnection(Connection conn){
        if(conn != null){
            try {
            	// 关闭数据库连接
            	conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 关闭预处理语句
     * @param pstmt
     */
    public static void closePreparedStatement(PreparedStatement pstmt){
        if(pstmt != null){
            try {
            	pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 关闭结果集对象
     * @param rs
     */
    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try {
            	rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

