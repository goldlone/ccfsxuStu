package cn.goldlone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cn.goldlone.entity.Member;
import cn.goldlone.model.LoginInfo;
import cn.goldlone.model.RegistInfo;
import cn.goldlone.model.UserInfo;

/**
 * 会员信息表操作实现
 * @ClassName: MemberDao
 * @Description: TODO 
 * @author: CN
 * 创建时间: 2017年9月28日 下午4:32:28
 */
public class MemberDao {
	
	/**
	 * 用户登录
	 * @param email
	 * @return
	 */
	public ArrayList<String> login(String email) {
		ArrayList<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = DBDao.getConnection();
			sql = "select M_passwd, M_memberNo "
					+ "from Member "
					+ "where M_email = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list.add(rs.getString(1));
				list.add(rs.getString(2));
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
	 * 添加会员信息
	 * @param user
	 * @return
	 */
	public RegistInfo addMember(Member user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		RegistInfo info = new RegistInfo();
		try {
			conn = DBDao.getConnection();
			// 检查会员信息是否已存在
			sql = "SELECT * FROM Member WHERE M_memberNo = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getNo());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				// 用户已存在
				info.setSuccess(false);
				info.setCode(10002);
				info.setInfo("用户已存在，初始密码为会员号");
				return info;
			}
			// 向表中添加会员信息
			sql = "INSERT INTO Member VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getNo());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getStuNo());
			pstmt.setString(4, user.getPhone());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getDiscipline());
			pstmt.setInt(7, user.getGrade());
			pstmt.setInt(8, user.getClassNum());
			pstmt.setInt(9, user.getDegreeNo());
			pstmt.setString(10, user.getId());
			pstmt.setString(11, user.getStartTime());
			pstmt.setString(12, user.getEndTime());
			pstmt.setInt(13, user.getMemberTypeNo());
			pstmt.setString(14, user.getPasswd());
			pstmt.setInt(15, user.getPower());
			pstmt.setInt(16, user.getAddSocre());
			
			pstmt.execute();
			info.setSuccess(true);
			info.setCode(10001);
			info.setInfo("添加成功");
		} catch (SQLException e) {
			info.setSuccess(false);
			info.setCode(10003);
			info.setInfo("添加失败，写入数据库时错误");
			e.printStackTrace();
		} finally {
			DBDao.closeConnection(conn);
		}
		
		return info;
	}
	
	/**
	 * 查询所有会员信息
	 * @return
	 */
	public ArrayList<UserInfo> selectAllMember() {
		ArrayList<UserInfo> list = new ArrayList<UserInfo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBDao.getConnection();
			sql = "select M_memberNo, M_name, M_stuNo, M_phone, M_email, "
					+ "M_discipline, M_grade, M_class, D_degreeName, M_id, "
					+ "M_startTime, M_endTime, M_typeName, M_passwd, M_power, M_addScore "
					+ "from Member as a "
					+ "left join MemberType as b on a.M_typeNo=b.M_typeNo "
					+ "left join DegreeInfo as c on a.M_degreeNo=c.D_degreeNo;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UserInfo user = new UserInfo();
				user.setNo(rs.getString(1));
				user.setName(rs.getString(2));
				user.setStuNo(rs.getString(3));
				user.setPhone(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setDiscipline(rs.getString(6));
				user.setGrade(rs.getInt(7));
				user.setClassNum(rs.getInt(8));
				user.setDegree(rs.getString(9));
				user.setId(rs.getString(10));
				user.setStartTime(rs.getDate(11).toString());
				user.setEndTime(rs.getDate(12).toString());
				user.setMemberType(rs.getString(13));
				user.setPasswd(rs.getString(14));
				user.setPower(rs.getInt(15));
				user.setAddScore(rs.getInt(16));
				list.add(user);
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
	 * 根据会员号查询会员信息
	 * @param no
	 * @return
	 */
	public ArrayList<UserInfo> selectMemberByNo(String no) {
		ArrayList<UserInfo> list = new ArrayList<UserInfo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = DBDao.getConnection();
			sql = "select M_memberNo, M_name, M_stuNo, M_phone, M_email, "
					+ "M_discipline, M_grade, M_class, D_degreeName, M_id, "
					+ "M_startTime, M_endTime, M_typeName, M_passwd, M_power, M_addScore "
					+ "from Member as a "
					+ "left join MemberType as b on a.M_typeNo=b.M_typeNo "
					+ "left join DegreeInfo as c on a.M_degreeNo=c.D_degreeNo "
					+ "where M_memberNo = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UserInfo user = new UserInfo();
				user.setNo(rs.getString(1));
				user.setName(rs.getString(2));
				user.setStuNo(rs.getString(3));
				user.setPhone(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setDiscipline(rs.getString(6));
				user.setGrade(rs.getInt(7));
				user.setClassNum(rs.getInt(8));
				user.setDegree(rs.getString(9));
				user.setId(rs.getString(10));
				user.setStartTime(rs.getDate(11).toString());
				user.setEndTime(rs.getDate(12).toString());
				user.setMemberType(rs.getString(13));
				user.setPasswd(rs.getString(14));
				user.setPower(rs.getInt(15));
				user.setAddScore(rs.getInt(16));
				list.add(user);
			}
			sql = "SELECT * FROM Member WHERE M_memberNo = ? AND now()>M_endTime";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.get(0).setExpired(true);
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
	 * 根据手机号查询会员信息
	 * @param phone
	 * @return
	 */
	public ArrayList<UserInfo> selectMemberByPhone(String phone) {
		ArrayList<UserInfo> list = new ArrayList<UserInfo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBDao.getConnection();
			sql = "select M_memberNo, M_name, M_stuNo, M_phone, M_email, "
					+ "M_discipline, M_grade, M_class, D_degreeName, M_id, "
					+ "M_startTime, M_endTime, M_typeName, M_passwd, M_power, M_addScore "
					+ "from Member as a "
					+ "left join MemberType as b on a.M_typeNo=b.M_typeNo "
					+ "left join DegreeInfo as c on a.M_degreeNo=c.D_degreeNo "
					+ "where M_phone = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UserInfo user = new UserInfo();
				user.setNo(rs.getString(1));
				user.setName(rs.getString(2));
				user.setStuNo(rs.getString(3));
				user.setPhone(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setDiscipline(rs.getString(6));
				user.setGrade(rs.getInt(7));
				user.setClassNum(rs.getInt(8));
				user.setDegree(rs.getString(9));
				user.setId(rs.getString(10));
				user.setStartTime(rs.getDate(11).toString());
				user.setEndTime(rs.getDate(12).toString());
				user.setMemberType(rs.getString(13));
				user.setPasswd(rs.getString(14));
				user.setPower(rs.getInt(15));
				user.setAddScore(rs.getInt(16));
				list.add(user);
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
	 * 查询所有过期会员或未过期会员
	 * @param expired	true:过期, false:未过期
	 * @return
	 */
	public ArrayList<UserInfo> selectExpiredMember(boolean expired){
		ArrayList<UserInfo> list = new ArrayList<UserInfo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBDao.getConnection();
			if(expired){
				// 过期查询
				sql = "select M_memberNo, M_name, M_stuNo, M_phone, M_email, "
						+ "M_discipline, M_grade, M_class, D_degreeName, M_id, "
						+ "M_startTime, M_endTime, M_typeName, M_passwd, M_power, M_addScore "
						+ "from Member as a "
						+ "left join MemberType as b on a.M_typeNo=b.M_typeNo "
						+ "left join DegreeInfo as c on a.M_degreeNo=c.D_degreeNo "
						+ "where not now() between M_startTime and M_endTime;";
			} else {
				// 非过期查询
				sql = "select M_memberNo, M_name, M_stuNo, M_phone, M_email, "
						+ "M_discipline, M_grade, M_class, D_degreeName, M_id, "
						+ "M_startTime, M_endTime, M_typeName, M_passwd, M_power "
						+ "from Member as a "
						+ "left join MemberType as b on a.M_typeNo=b.M_typeNo "
						+ "left join DegreeInfo as c on a.M_degreeNo=c.D_degreeNo "
						+ "where now() between M_startTime and M_endTime;";
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UserInfo user = new UserInfo();
				user.setNo(rs.getString(1));
				user.setName(rs.getString(2));
				user.setStuNo(rs.getString(3));
				user.setPhone(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setDiscipline(rs.getString(6));
				user.setGrade(rs.getInt(7));
				user.setClassNum(rs.getInt(8));
				user.setDegree(rs.getString(9));
				user.setId(rs.getString(10));
				user.setStartTime(rs.getDate(11).toString());
				user.setEndTime(rs.getDate(12).toString());
				user.setMemberType(rs.getString(13));
				user.setPasswd(rs.getString(14));
				user.setPower(rs.getInt(15));
				user.setAddScore(rs.getInt(16));
				list.add(user);
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
	 * 获取年级集合
	 * @return
	 */
	public ArrayList<Integer> selectGradeSet() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBDao.getConnection();
			sql = "select distinct M_grade from Member;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt(1));
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
	 * 筛选查询会员信息
	 * @param member
	 * @return
	 */
	public ArrayList<UserInfo> queryMember(Member member) {
		ArrayList<UserInfo> list = new ArrayList<UserInfo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		try {
			conn = DBDao.getConnection();
			sb.append("select M_memberNo, M_name, M_stuNo, M_phone, M_email, "
				+ "M_discipline, M_grade, M_class, D_degreeName, M_id, "
				+ "M_startTime, M_endTime, M_typeName, M_passwd, M_power, M_addScore "
				+ "from Member as a "
				+ "left join MemberType as b on a.M_typeNo=b.M_typeNo "
				+ "left join DegreeInfo as c on a.M_degreeNo=c.D_degreeNo where ");
			// 年级
			if(member.getGrade() != 0){
				sb.append(" M_grade="+member.getGrade()+" and ");
				System.out.println(1);
			}
			// 过期
			if(member.getEndTime()!=null && !member.getEndTime().equals("all")) {
				if(member.getEndTime().equals("1")) {
					sb.append(" not now() between M_startTime and M_endTime and ");
					System.out.println(2);
				} else if(member.getEndTime().equals("0")) {
					sb.append(" now() between M_startTime and M_endTime and ");
					System.out.println(3);
				}
			}
			if(member.getNo()!=null && !member.getNo().equals("")) {
				sb.append(" M_memberNo = '");
				sb.append(member.getNo());
				sb.append("' and ");
				System.out.println(4);
			}
			
			sb.append(" 1;");
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UserInfo user = new UserInfo();
				user.setNo(rs.getString(1));
				user.setName(rs.getString(2));
				user.setStuNo(rs.getString(3));
				user.setPhone(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setDiscipline(rs.getString(6));
				user.setGrade(rs.getInt(7));
				user.setClassNum(rs.getInt(8));
				user.setDegree(rs.getString(9));
				user.setId(rs.getString(10));
				user.setStartTime(rs.getDate(11).toString());
				user.setEndTime(rs.getDate(12).toString());
				user.setMemberType(rs.getString(13));
				user.setPasswd(rs.getString(14));
				user.setPower(rs.getInt(15));
				user.setAddScore(rs.getInt(16));
				list.add(user);
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
	 * 查询权限
	 * @param memeberNo
	 * @return
	 */
	public int selectPower(String memeberNo) {
		int power = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBDao.getConnection();
			sql = "select M_power from Member WHERE M_memberNo = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memeberNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				power = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBDao.closeConnection(conn);
			DBDao.closePreparedStatement(pstmt);
			DBDao.closeResultSet(rs);
		}

		return power;
	}

    /**
     * 根据邮箱查询会员号
     * @param email
     * @return
     */
	public String selectNoByEmail(String email) {
	    String no = null;
	    Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        try {
            conn = DBDao.getConnection();
            sql = "select M_memberNo from Member WHERE M_email = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                no = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBDao.closeConnection(conn);
            DBDao.closePreparedStatement(pstmt);
            DBDao.closeResultSet(rs);
        }

	    return no;
    }

    /**
     * 根据会员号修改手机号
     * @param no
     * @param phone
     * @return
     */
    public boolean updatePhoneByNo(String no, String phone) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        try {
            conn = DBDao.getConnection();
            sql = "UPDATE Member SET M_phone = ?  WHERE M_memberNo = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phone);
            pstmt.setString(2, no);

            int  rs = pstmt.executeUpdate();
            if(rs == 0)
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBDao.closeConnection(conn);
            DBDao.closePreparedStatement(pstmt);
        }
	    return true;
    }

    /**
     * 更新失效日期
     * @param no
     * @param endTime
     * @return
     */
    public boolean updateEndTimeByNo(String no, String endTime) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        try {
            conn = DBDao.getConnection();
            sql = "UPDATE Member SET M_endTime = ?  WHERE M_memberNo = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, endTime);
            pstmt.setString(2, no);

            int  rs = pstmt.executeUpdate();
            if(rs == 0)
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBDao.closeConnection(conn);
            DBDao.closePreparedStatement(pstmt);
        }
        return true;
    }

    /**
     * 查询学历编号
     * @param degree
     * @return
     */
    public int selectDegreeNo(String degree) {
        int degreeNo = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
            conn = DBDao.getConnection();
            sql = "SELECT D_degreeNo FROM DegreeInfo WHERE D_degreeName=?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, degree);
            rs = pstmt.executeQuery();
            if(rs.next())
                degreeNo = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBDao.closeConnection(conn);
            DBDao.closePreparedStatement(pstmt);
            DBDao.closeResultSet(rs);
        }

        return degreeNo;
    }

	
	public static void main(String[] args) {
//        System.out.println(new MemberDao().selectDegreeNo("本科"));

//		ArrayList<UserInfo> list = (new MemberDao()).selectAllMember();
//		for(int i=0; i<list.size(); i++){
//			UserInfo user = list.get(i);
//			System.out.println(user);
//		}
//		ArrayList<String> list = (new MemberDao()).select();
//		for(int i=0; i<list.size(); i++){
//			System.out.println(list.get(i));
//		}
//		ArrayList<Integer> list = (new MemberDao()).selectGradeSet();
//		System.out.println(list.size());
	}
}
