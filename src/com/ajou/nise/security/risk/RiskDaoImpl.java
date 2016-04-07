package com.ajou.nise.security.risk;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ajou.nise.security.common.BaseDao;
import com.ajou.nise.security.common.Dao;
import com.ajou.nise.security.model.*;

@Repository("riskDao")
public class RiskDaoImpl extends BaseDao implements Dao {

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
	public List<Domainas> getDomainasList(Object obj) throws SQLException {
		return (List<Domainas>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.risk.selectDomainasList", obj);
	}
	
	public List<Threat> getThreatList(Object obj) throws SQLException {
		return (List<Threat>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.risk.selectThreatListByName", obj);
	} 
	
	public List<Domainas> getDomainasListByID(Object obj) throws SQLException {
		return (List<Domainas>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.risk.selectDomainasListByID", obj);
	}
	
	public List<Threatact> getThreatactListByID(Object obj) throws SQLException {
		return (List<Threatact>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.risk.selectThreatactListByID", obj);
	} 
	
	public List<Currentcm> getCurrentcmListByMC(Object obj) throws SQLException {
		return (List<Currentcm>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.risk.getCurrentcmListByMC", obj);
	} 
	public List<Currentcm> getCurrentcmListByPC(Object obj) throws SQLException {
		return (List<Currentcm>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.risk.getCurrentcmListByPC", obj);
	} 
	public List<Currentcm> getCurrentcmListByRC(Object obj) throws SQLException {
		return (List<Currentcm>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.risk.getCurrentcmListByRC", obj);
	} 
	public List<Currentcm> getCurrentcmListByMI(Object obj) throws SQLException {
		return (List<Currentcm>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.risk.getCurrentcmListByMI", obj);
	} 
	public List<Currentcm> getCurrentcmListByPI(Object obj) throws SQLException {
		return (List<Currentcm>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.risk.getCurrentcmListByPI", obj);
	} 
	public List<Currentcm> getCurrentcmListByRI(Object obj) throws SQLException {
		return (List<Currentcm>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.risk.getCurrentcmListByRI", obj);
	} 
	public List<Currentcm> getCurrentcmListByMA(Object obj) throws SQLException {
		return (List<Currentcm>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.risk.getCurrentcmListByMA", obj);
	} 
	public List<Currentcm> getCurrentcmListByPA(Object obj) throws SQLException {
		return (List<Currentcm>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.risk.getCurrentcmListByPA", obj);
	} 
	public List<Currentcm> getCurrentcmListByRA(Object obj) throws SQLException {
		return (List<Currentcm>)getSqlMapClientTemplate().queryForList("com.ajou.nise.security.risk.getCurrentcmListByRA", obj);
	} 


}
