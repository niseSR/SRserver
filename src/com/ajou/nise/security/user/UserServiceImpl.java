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
	
	@Override
	public Object getObject(Object obj) throws SQLException {
		return this.dao.select(obj);
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
	@Override
	public boolean save(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		dao.insert(obj);
		return true;
	}
	@Override
	public boolean edit(Object obj) throws SQLException {
		return true;
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean delete(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public Object getObjectForIdcheck(Object obj) throws SQLException {
		return this.dao.selectForIDCheck(obj);
	}
	
	public Object getObjectForCompanyCheck(Object obj) throws SQLException {
		return this.dao.selectForCompanyCheck(obj);
	}
	
	public void insertUserInfo(Object obj) throws SQLException {
		this.dao.insertUserInfo(obj);
	}
	
	public Object login(Object obj) throws SQLException {
		return this.dao.login(obj);
	}

}
