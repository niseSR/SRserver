package com.ajou.nise.security.risk;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ajou.nise.security.common.RequestParameter;
import com.ajou.nise.security.arcinfo.ArcinfoDaoImpl;


@Service("riskService")
public class RiskServiceImpl implements com.ajou.nise.security.common.Service {
	
	@Resource(name = "riskDao")
	RiskDaoImpl dao;
	
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
		return false;
	}
	
	public List getDomainasList(Object obj) throws SQLException {
		return dao.getDomainasList(obj);
	}
	
	public List getThreatList(Object obj) throws SQLException {
		return dao.getThreatList(obj);
	}	
	
	public List getDomainasListByID(Object obj) throws SQLException {
		return dao.getDomainasListByID(obj);
	}
	
	public List getThreatactListByID(Object obj) throws SQLException {
		return dao.getThreatactListByID(obj);
	}
	
	public List getCurrentcmListByMC(Object obj) throws SQLException {
		return dao.getCurrentcmListByMC(obj);
	}
	public List getCurrentcmListByPC(Object obj) throws SQLException {
		return dao.getCurrentcmListByPC(obj);
	}
	public List getCurrentcmListByRC(Object obj) throws SQLException {
		return dao.getCurrentcmListByRC(obj);
	}
	public List getCurrentcmListByMI(Object obj) throws SQLException {
		return dao.getCurrentcmListByMI(obj);
	}
	public List getCurrentcmListByPI(Object obj) throws SQLException {
		return dao.getCurrentcmListByPI(obj);
	}
	public List getCurrentcmListByRI(Object obj) throws SQLException {
		return dao.getCurrentcmListByRI(obj);
	}
	public List getCurrentcmListByMA(Object obj) throws SQLException {
		return dao.getCurrentcmListByMA(obj);
	}
	public List getCurrentcmListByPA(Object obj) throws SQLException {
		return dao.getCurrentcmListByPA(obj);
	}
	public List getCurrentcmListByRA(Object obj) throws SQLException {
		return dao.getCurrentcmListByRA(obj);
	}

}
