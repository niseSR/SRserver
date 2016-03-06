package com.ajou.nise.security.user;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ajou.nise.security.common.RequestParameter;
import com.ajou.nise.security.user.UserDaoImpl;


@Service("userService")
public class UserServiceImpl implements com.ajou.nise.security.common.Service {
	
	@Resource(name = "userDao")
	UserDaoImpl dao;
	
	// 10.9 봉재 : (확인 필요) select가 선택되면 "com.ajou.secure.user.selectUserByUserID"만 선택되기 때문에,
	// select의 다른 로직을 사용하는 경우 조정이 필요함 
	@Override
	public Object getObject(Object obj) throws SQLException {
		return this.dao.select(obj);
	}
	
	//10.26. 봉재 : ID 중복 확인을 위해 SQL에서 데이터 가져오는 로직
	public Object getObjectForIdcheck(Object obj) throws SQLException {
		return this.dao.selectForCheck(obj);
	}
	
	
	@Override
	public boolean edit(Object obj) throws SQLException {
		return true;
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getList(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getList(obj);
	}

	@Override
	public int getRowCount(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void remove(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	// save 서비스에 insert 추가 
	@Override
	public boolean save(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		dao.insert(obj);
		return true;
	}
	// 10.9 봉재 : update로직 추가
	public boolean update(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		dao.update(obj);
		return true;
	}

	@Override
	public boolean delete(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Object login(Object obj) throws SQLException {
		return dao.login(obj);
	}

	public void updateUserAuthority(Object obj) {
		dao.updateUserAuthority(obj);
	}
	
	public void updateUserAuthorityByID(Object obj) {
		dao.updateUserAuthorityByID(obj);
	}

	public int getUserCount(Object obj) {
		// TODO Auto-generated method stub
		return dao.userCount(obj);
	}
}
