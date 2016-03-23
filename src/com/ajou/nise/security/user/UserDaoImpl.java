package com.ajou.nise.security.user;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ajou.nise.security.common.BaseDao;
import com.ajou.nise.security.common.Dao;


@Repository("userDao")
public class UserDaoImpl extends BaseDao implements Dao {

	@Override
	public Object select(Object obj) throws SQLException {
		return getSqlMapClientTemplate().queryForObject("com.ajou.secure.user.selectUserByUserID", obj);
	}
	@Override
	public void delete(Object obj) throws SQLException {
		// TODO Auto-generated method stub
	}
	
	@Override
	public List getList(Object obj) throws SQLException {
		return getSqlMapClientTemplate().queryForList("com.ajou.secure.user.selectUserByUserAuthority", obj);
	}
	
	@Override
	public int getRowCount(Object obj) throws SQLException {
		return 0;
	}
	
	@Override
	public void insert(Object obj) throws SQLException {
		getSqlMapClientTemplate().insert("com.ajou.secure.user.InsertUserInformation", obj);
	}
	
	@Override
	public void update(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("com.ajou.secure.user.updateUserInfo", obj);
	}
	
	// ID 중복 여부 확인을 위한 체크
	public Object selectForIDCheck(Object obj) throws SQLException {
		return  getSqlMapClientTemplate().queryForObject("com.ajou.nise.security.user.selectCheckUserIDByUserID", obj);
	}
	
	// 회사이름 중복 여부 확인을 위한 체크	
	public Object selectForCompanyCheck(Object obj) throws SQLException {
		return  getSqlMapClientTemplate().queryForObject("com.ajou.nise.security.user.selectCheckUserCompany", obj);
	}
	
	// 사용자 등록후 사용자 정보 입력을 위한 로직
	public void insertUserInfo(Object obj) throws SQLException {
		getSqlMapClientTemplate().insert("com.ajou.nise.security.user.InsertUserInformation", obj);
	}

	public Object login(Object obj) throws SQLException {
		return  getSqlMapClientTemplate().queryForObject("com.ajou.nise.security.user.selectForLogin", obj);
	}

}
