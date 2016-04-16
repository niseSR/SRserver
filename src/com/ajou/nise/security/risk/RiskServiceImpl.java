package com.ajou.nise.security.risk;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ajou.nise.security.common.RequestParameter;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.util.FileManager;
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
	public List getCmsgoalList(Object obj) throws SQLException {
		return dao.getCmsgoalList(obj);
	}
	public Object getSecurityReqInfo(Object obj) throws SQLException {
		return this.dao.getSecurityReqInfo(obj);
	}
	public List getRelatedSecurityReqInfo(Object obj) throws SQLException {
		return dao.getRelatedSecurityReqInfo(obj);
	}
	
	
	
	
	
	public Object getCountermeasureInfo(Object obj) throws SQLException {
		return this.dao.getCountermeasureInfo(obj);
	}
	public List getImplCountermeasureInfo(Object obj) throws SQLException {
		return dao.getImplCountermeasureInfo(obj);
	}
	public Object getAssetInfo(Object obj) throws SQLException {
		return this.dao.getAssetInfo(obj);
	}
	public Object getDomainasInfo(Object obj) throws SQLException {
		return this.dao.getDomainasInfo(obj);
	}
	public Object getRelatedSHInfo(Object obj) throws SQLException {
		return this.dao.getRelatedSHInfo(obj);
	}
	public List getRelatedStakeholderInfo(Object obj) throws SQLException {
		return dao.getRelatedStakeholderInfo(obj);
	}
	public Object getThreatInfo(Object obj) throws SQLException {
		return this.dao.getThreatInfo(obj);
	}
	public Object getThreatactInfo(Object obj) throws SQLException {
		return this.dao.getThreatactInfo(obj);
	}
	public Object getCAPECInfo(Object obj) throws SQLException {
		return this.dao.getCAPECInfo(obj);
	}
	public Object getCWEInfo(Object obj) throws SQLException {
		return this.dao.getCWEInfo(obj);
	}
	public Object getCVEInfo(Object obj) throws SQLException {
		return this.dao.getCVEInfo(obj);
	}
	public List getBusinessOrganizationRecInfo(Object obj) throws SQLException {
		return dao.getBusinessOrganizationRecInfo(obj);
	}
	public List getBusinessRegulationRecInfo(Object obj) throws SQLException {
		return dao.getBusinessRegulationRecInfo(obj);
	}
	public List getBusinessFundRecInfo(Object obj) throws SQLException {
		return dao.getBusinessFundRecInfo(obj);
	}
	public List getBusinessTimeRecInfo(Object obj) throws SQLException {
		return dao.getBusinessTimeRecInfo(obj);
	}
	public List getBusinessBestPracticeRecInfo(Object obj) throws SQLException {
		return dao.getBusinessBestPracticeRecInfo(obj);
	}
	public List getBusinessLegalComplianceRecInfo(Object obj) throws SQLException {
		return dao.getBusinessLegalComplianceRecInfo(obj);
	}
	public List getSystemServiceRecInfo(Object obj) throws SQLException {
		return dao.getSystemServiceRecInfo(obj);
	}
	public List getSystemPlatformRecInfo(Object obj) throws SQLException {
		return dao.getSystemPlatformRecInfo(obj);
	}
	public List getTechnicalSecMechanismRecInfo(Object obj) throws SQLException {
		return dao.getTechnicalSecMechanismRecInfo(obj);
	}
	public List getTechnicalTrendRecInfo(Object obj) throws SQLException {
		return dao.getTechnicalTrendRecInfo(obj);
	}

}
