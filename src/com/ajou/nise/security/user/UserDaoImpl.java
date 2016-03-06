package com.ajou.nise.security.user;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ajou.nise.security.common.BaseDao;
import com.ajou.nise.security.common.Dao;


@Repository("userDao")
public class UserDaoImpl extends BaseDao implements Dao {

	
	// 10.9 봉재 : (확인 필요) select가 선택되면 반드시 여기로만 오기 때문에, select의 다른 로직을 사용하는 경우 조정이 필요함  
	@Override
	public Object select(Object obj) throws SQLException {
		return getSqlMapClientTemplate().queryForObject("com.ajou.secure.user.selectUserByUserID", obj);
	}
	
	
	// 10.26. 봉재 : ID 중복 여부 확인을 위한 체크
	public Object selectForCheck(Object obj) throws SQLException {
		return  getSqlMapClientTemplate().queryForObject("com.ajou.secure.user.selectCheckUserIDByUserID", obj);
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
		// spring 프레임워크의 SqlMapClientTemplate 호출후 MySQL insert 구문 입력을 위해 User.xml의 InsertUserInformation 호출
		// obj는 user 모델에서 가져옴
		getSqlMapClientTemplate().insert("com.ajou.secure.user.InsertUserInformation", obj);
	}

	// 10.9 봉재 : 업데이트 로직 추가
	@Override
	public void update(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("com.ajou.secure.user.updateUserInfo", obj);
	}
	
	public Object login(Object obj) throws SQLException {
		return getSqlMapClientTemplate().queryForObject("com.ajou.secure.user.selectUserLogin", obj);
	}


	public void updateUserAuthority(Object obj) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("com.ajou.secure.user.updateUserAuthority", obj);
		
	}


	public int userCount(Object obj) {
		// TODO Auto-generated method stub
		return (int) getSqlMapClientTemplate().queryForObject("com.ajou.secure.user.selectRowCount", obj);
	}
	
	public void updateUserAuthorityByID(Object obj) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("com.ajou.secure.user.updateUserAuthorityByUserId", obj);
		
	}

}
