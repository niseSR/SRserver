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
	
	public List getAssetList(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getAssetList(obj);
	}	
	
	public List getRelatedSHList(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getRelatedSHList(obj);
	}
	public List getRelatedsh(Object obj) throws SQLException {
		return dao.getRelatedsh(obj);
	}
	public List getDomainasList(Object obj) throws SQLException {
		return dao.getDomainasList(obj);
	}
	public List getCurrentcmList(Object obj) throws SQLException {
		return dao.getCurrentcmList(obj);
	}

	public Object getUserAScnt(Object obj) throws SQLException {
		return this.dao.getUserAScnt(obj);
	}
	
	public int insertDomainasInfo(Object obj) throws SQLException {
		this.dao.insertDomainasInfo(obj);
		return 1;
	}
	
	public boolean updateUserASCnt(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		dao.updateUserASCnt(obj);
		return true;
	}
	public boolean updateUserThreatCnt(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		dao.updateUserThreatCnt(obj);
		return true;
	}
	
	public int insertDomainasplInfo(Object obj) throws SQLException {
		this.dao.insertDomainasplInfo(obj);
		return 1;
	}
	
	public int insertDomainasRelatedshInfo(Object obj) throws SQLException {
		this.dao.insertDomainasRelatedshInfo(obj);
		return 1;
	}
	
	public List getCMList(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getCMList(obj);
	}	
	
	
	public List getImplCMList(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return dao.getImplCMList(obj);
	}
	
	public int insertCurrentCMInfo(Object obj) throws SQLException {
		this.dao.insertCurrentCMInfo(obj);
		return 1;
	}
	
	public Object selectCAPECID(Object obj) throws SQLException {
		return this.dao.selectCAPECID(obj);
	}
	
	public Object selectCVEID(Object obj) throws SQLException {
		return this.dao.selectCVEID(obj);
	}

	public Object selectCWEID(Object obj) throws SQLException {
		return this.dao.selectCWEID(obj);
	}
	
	public Object getUserThreatCnt(Object obj) throws SQLException {
		return this.dao.getUserThreatCnt(obj);
	}
	public int insertThreatInfo(Object obj) throws SQLException {
		this.dao.insertThreatInfo(obj);
		return 1;
	}
	public int insertThreatplInfo(Object obj) throws SQLException {
		this.dao.insertThreatplInfo(obj);
		return 1;
	}
	public int insertThreatactInfo(Object obj) throws SQLException {
		this.dao.insertThreatactInfo(obj);
		return 1;
	}
	
	
	
}
