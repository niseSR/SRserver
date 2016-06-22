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
	
	public Object getUserThreatCnt(Object obj) throws SQLException {
		return  getSqlMapClientTemplate().queryForObject("com.ajou.nise.security.arcinfo.getUserThreatCount", obj);
	}
	
	public void insertStakeholderInfo(Object obj) throws SQLException {
		getSqlMapClientTemplate().insert("com.ajou.nise.security.arcinfo.InsertStakeholderInformation", obj);
	}
	
	public void updateUserSHCnt(Object obj) throws SQLException {
		getSqlMapClientTemplate().update("com.ajou.nise.security.arcinfo.updateUserSHCnt", obj);
	}
	
	public void updateUserThreatCnt(Object obj) throws SQLException {
		getSqlMapClientTemplate().update("com.ajou.nise.security.arcinfo.updateUserThreatCnt", obj);
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
	
	public List<Relatedsh> getRelatedsh(Object obj) throws SQLException {
		return (List<Relatedsh>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.arcinfo.selectRelatedsh", obj);
	}
	
	public List<Domainas> getDomainasList(Object obj) throws SQLException {
		return (List<Domainas>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.arcinfo.selectDomainasList", obj);
	}
	
	public List<Domainas> getDomainasListbyID(Object obj) throws SQLException {
		return (List<Domainas>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.arcinfo.selectDomainasListbyID", obj);
	}
	
	public List<Domainaspl> getDomainasplList(Object obj) throws SQLException {
		return (List<Domainaspl>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.arcinfo.selectDomainasplList", obj);
	}
	
	public List<Domainasrelatedsh> getDomainasrelatedshList(Object obj) throws SQLException {
		return (List<Domainasrelatedsh>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.arcinfo.selectDomainasrelatedshList", obj);
	}
	
	public List<Currentcm> getCurrentcmList(Object obj) throws SQLException {
		return (List<Currentcm>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.arcinfo.selectCurrentcmList", obj);
	}
	
	public void insertDomainasInfo(Object obj) throws SQLException {
		getSqlMapClientTemplate().insert("com.ajou.nise.security.arcinfo.InsertDomainasInformation", obj);
	}
	
	public void updateUserASCnt(Object obj) throws SQLException {
		getSqlMapClientTemplate().update("com.ajou.nise.security.arcinfo.updateUserASCnt", obj);
	}
	public void insertThreatInfo(Object obj) throws SQLException {
		getSqlMapClientTemplate().insert("com.ajou.nise.security.arcinfo.InsertThreatInformation", obj);
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
	
	public Object selectCAPECID(Object obj) throws SQLException {
		return getSqlMapClientTemplate().queryForObject("com.ajou.nise.security.arcinfo.selectCAPECID", obj);
	}
	
	public Object selectCVEID(Object obj) throws SQLException {
		return getSqlMapClientTemplate().queryForObject("com.ajou.nise.security.arcinfo.selectCVEID", obj);
	}
	
	public Object selectCWEID(Object obj) throws SQLException {
		return getSqlMapClientTemplate().queryForObject("com.ajou.nise.security.arcinfo.selectCWEID", obj);
	}
	public void insertThreatplInfo(Object obj) throws SQLException {
		getSqlMapClientTemplate().insert("com.ajou.nise.security.arcinfo.InsertThreatPlatformInformation", obj);
	}
	public void insertThreatactInfo(Object obj) throws SQLException {
		getSqlMapClientTemplate().insert("com.ajou.nise.security.arcinfo.InsertThreatactInformation", obj);
	}
	
	public Object getSHNamebyID(Object obj) throws SQLException {
		return  getSqlMapClientTemplate().queryForObject("com.ajou.nise.security.arcinfo.selectSHNamebyID", obj);
	}
	
	public List<Currentcm> getCurrentcmListbyDomainasID(Object obj) throws SQLException {
		return (List<Currentcm>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.arcinfo.getCurrentcmListbyDomainasID", obj);
	}
}
