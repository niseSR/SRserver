package com.ajou.nise.security.arcinfo;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ajou.nise.security.common.BaseDao;
import com.ajou.nise.security.common.Dao;
import com.ajou.nise.security.model.*;

@Repository("arcinfoDao")
public class ArcinfoDaoImpl extends BaseDao implements Dao {

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
	
	public Object getUserSHcnt(Object obj) throws SQLException {
		return  getSqlMapClientTemplate().queryForObject("com.ajou.nise.security.arcinfo.getUserSHCount", obj);
	}
	
	public Object getUserAScnt(Object obj) throws SQLException {
		return  getSqlMapClientTemplate().queryForObject("com.ajou.nise.security.arcinfo.getUserASCount", obj);
	}
	
	public void insertStakeholderInfo(Object obj) throws SQLException {
		getSqlMapClientTemplate().insert("com.ajou.nise.security.arcinfo.InsertStakeholderInformation", obj);
	}
	
	public void updateUserSHCnt(Object obj) throws SQLException {
		getSqlMapClientTemplate().update("com.ajou.nise.security.arcinfo.updateUserSHCnt", obj);
	}
	
	public Object checkUniquePlatformInfo(Object obj) throws SQLException {
		return  getSqlMapClientTemplate().queryForObject("com.ajou.nise.security.arcinfo.checkPlatformInfo", obj);
	}

	public void insertPlatformInfo(Object obj) throws SQLException {
		getSqlMapClientTemplate().insert("com.ajou.nise.security.arcinfo.InsertPlatformInformation", obj);
	}
	
	public List<Asset> getAssetList(Object obj) throws SQLException {
		return (List<Asset>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.arcinfo.selectAssetID", obj);
	} 
	
	public List<Asset> getRelatedSHList(Object obj) throws SQLException {
		return (List<Asset>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.arcinfo.selectRelatedSHList", obj);
	} 
	
	public void insertDomainasInfo(Object obj) throws SQLException {
		getSqlMapClientTemplate().insert("com.ajou.nise.security.arcinfo.InsertDomainasInformation", obj);
	}
	
	public void updateUserASCnt(Object obj) throws SQLException {
		getSqlMapClientTemplate().update("com.ajou.nise.security.arcinfo.updateUserASCnt", obj);
	}
	
	public void insertDomainasplInfo(Object obj) throws SQLException {
		getSqlMapClientTemplate().insert("com.ajou.nise.security.arcinfo.InsertDomainasPlatformInformation", obj);
	}
	
	public void insertDomainasRelatedshInfo(Object obj) throws SQLException {
		getSqlMapClientTemplate().insert("com.ajou.nise.security.arcinfo.InsertDomainasRelatedshInformation", obj);
	}
	public List<CM> getCMList(Object obj) throws SQLException {
		return (List<CM>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.arcinfo.selectCMID", obj);
	} 
	
	public List<Implcm> getImplCMList(Object obj) throws SQLException {
		return (List<Implcm>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.arcinfo.selectImplCMID", obj);
	} 
	
	public void insertCurrentCMInfo(Object obj) throws SQLException {
		getSqlMapClientTemplate().insert("com.ajou.nise.security.arcinfo.InsertCurrentCMInformation", obj);
	}
}
