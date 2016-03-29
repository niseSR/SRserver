package com.ajou.nise.security.arcinfo;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ajou.nise.security.common.RequestParameter;
import com.ajou.nise.security.arcinfo.ArcinfoDaoImpl;


@Service("arcinfoService")
public class ArcinfoServiceImpl implements com.ajou.nise.security.common.Service {
	
	@Resource(name = "arcinfoDao")
	ArcinfoDaoImpl dao;
	
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

	public Object getUserSHcnt(Object obj) throws SQLException {
		return this.dao.getUserSHcnt(obj);
	}
	
	public int insertStakeholderInfo(Object obj) throws SQLException {
		this.dao.insertStakeholderInfo(obj);
		return 1;
	}
	
	public boolean updateUserSHCnt(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		dao.updateUserSHCnt(obj);
		return true;
	}
	
	public boolean checkUniquePlatformInfo(Object obj) throws SQLException {
		if (dao.checkUniquePlatformInfo(obj)==null) return true;
		else return false;
	}
	
	public int insertPlatformInfo(Object obj) throws SQLException {
		this.dao.insertPlatformInfo(obj);
		return 1;
	}
}
