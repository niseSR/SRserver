package com.ajou.nise.security.risk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.io.*;

//test 1차용 import
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;

//test 2차용 import
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;

//test 3차용 import
import com.hp.hpl.jena.query.* ;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;


import com.ajou.nise.security.common.RequestParameter;
import com.ajou.nise.security.common.Utils;
import com.ajou.nise.security.risk.RiskServiceImpl;
// 모델들 모음
import com.ajou.nise.security.model.*;

@Controller
public class RiskController {
	
	@Resource(name = "riskService")
	private RiskServiceImpl riskService;

	// Stakeholder 정보 입력 완료 후 DB로 저장하는 로직
	@RequestMapping("/risk/select_DomainAssetID.do")
	public ModelAndView select_DomainAssetID(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, String> param = new HashMap<String, String>();
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println("-------------select_DomainAssetID--------------");
		System.out.println("rp = " + rp);

		String domainasIDTmp = rp.get("domainasID").toString() + "_%";
		param.put("domainasID", domainasIDTmp);
		param.put("domainasUserID", rp.get("domainasUserID").toString());

		ArrayList<Domainas> DomainasList = (ArrayList<Domainas>) this.riskService.getDomainasList(param);
		System.out.println(DomainasList);
		if(DomainasList != null)
		{
			System.out.println("Success to bring the data!");
			map.put("success", DomainasList);
		
		}else
		{
			System.out.println("Fail to the registration");
			map.put("fail", "가져오기 실패했습니다..");		
		}	

		

		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}

	@RequestMapping("/risk/select_ThreatID.do")
	public ModelAndView select_ThreatID(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();

		System.out.println("-------------select_ThreatID--------------");
		System.out.println("rp = " + rp);
				
		param.put("threatName1", rp.get("threatName").toString());
		param.put("threatName2", rp.get("threatName").toString() + "%");
		param.put("threatName3", "%" + rp.get("threatName").toString());
		param.put("threatName3", "%" + rp.get("threatName").toString() + "%");
		
		param.put("threatID", rp.get("threatID").toString() + "%");
		
		
		ArrayList<Threat> threatList = (ArrayList<Threat>) this.riskService.getThreatList(param);
		System.out.println(threatList);
		
		
		if(threatList != null)
		{
			System.out.println("Success to bring the data!");
			map.put("success", threatList);
		}else
		{
			System.out.println("Fail to the registration");
			map.put("fail", "가져오기 실패했습니다..");		
		}	
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}
	
	
	@RequestMapping("/risk/check_RiskExists.do")
	public ModelAndView check_RiskExists(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, String> param = new HashMap<String, String>();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();

		System.out.println("-------------check_RiskExists--------------");
		System.out.println("rp = " + rp);

		ArrayList<Domainaspl> DomainasplList = (ArrayList<Domainaspl>) this.riskService.getDomainasplList(rp);
		ArrayList<Threatpl> ThreatplList = (ArrayList<Threatpl>) this.riskService.getThreatplList(rp);
		
		int k=0;
		for (int i=0; i<DomainasplList.size() ; i++){
			for(int j=0 ; j<ThreatplList.size() ; j++){
				if (DomainasplList.get(i).getDomainasplPlatformID().toString().equals(ThreatplList.get(j).getThreatplPlatformID().toString())){
					result.put("platformID"+k, DomainasplList.get(i).getDomainasplPlatformID().toString());
					System.out.println(result.get("platformID"+k));
					k++;
				}
			}
		}
		
		
		if(result != null)
		{
			System.out.println("Success to bring the data!");
			map.put("success", result);
		
		}else
		{
			System.out.println("Fail to the registration");
			map.put("fail", "가져오기 실패했습니다..");		
		}	

		

		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}

	
	
	
	
	
	
	@RequestMapping("/risk/calculate_RiskFactor.do")
	public ModelAndView calculate_RiskFactor(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		System.out.println("-------------calculate_RiskFactor--------------");
		System.out.println("rp = " + rp);

		ArrayList<Domainas> domainasList = (ArrayList<Domainas>) this.riskService.getDomainasListByID(rp);
		ArrayList<Threatact> threatactList = (ArrayList<Threatact>) this.riskService.getThreatactListByID(rp);
		ArrayList<Risktemplate> risktemplateList = new ArrayList<Risktemplate>();
		System.out.println(domainasList.size());
		for (int i=0; i<domainasList.size() ; i++){
			for(int j=0 ; j<threatactList.size() ; j++){
				Risktemplate risktemplate = new Risktemplate();
				String tmp = rp.get("asID").toString().replace("AS_", "CM_");
				param.put("currentcmID", "%" + rp.get("domainCompany").toString());
				param.put("currentcmCMIDMC", tmp + "_MC%");
				param.put("currentcmCMIDPC", tmp + "_PC%");
				param.put("currentcmCMIDRC", tmp + "_RC%");
				param.put("currentcmCMIDMI", tmp + "_MI%");
				param.put("currentcmCMIDPI", tmp + "_PI%");
				param.put("currentcmCMIDRI", tmp + "_RI%");
				param.put("currentcmCMIDMA", tmp + "_MA%");
				param.put("currentcmCMIDPA", tmp + "_PA%");
				param.put("currentcmCMIDRA", tmp + "_RA%");
				String currentcmMCResult = "";
				String currentcmPCResult = "";
				String currentcmRCResult = "";
				String currentcmMIResult = "";
				String currentcmPIResult = "";
				String currentcmRIResult = "";
				String currentcmMAResult = "";
				String currentcmPAResult = "";
				String currentcmRAResult = "";

				ArrayList<Currentcm> currentcmListMC = (ArrayList<Currentcm>) this.riskService.getCurrentcmListByMC(param);
				for (int k=0 ; k<currentcmListMC.size();k++){
					if (k==0){
						currentcmMCResult = currentcmListMC.get(k).getCurrentcmImplcmName();  
					} else {
						currentcmMCResult = currentcmMCResult + "," + currentcmListMC.get(k).getCurrentcmImplcmName();
					}
				}
				
				ArrayList<Currentcm> currentcmListPC = (ArrayList<Currentcm>) this.riskService.getCurrentcmListByPC(param);
				for (int k=0 ; k<currentcmListPC.size();k++){
					if (k==0){
						currentcmPCResult = currentcmListPC.get(k).getCurrentcmImplcmName();  
					} else {
						currentcmPCResult = currentcmPCResult + "," + currentcmListPC.get(k).getCurrentcmImplcmName();
					}
				}
				
				ArrayList<Currentcm> currentcmListRC = (ArrayList<Currentcm>) this.riskService.getCurrentcmListByRC(param);
				for (int k=0 ; k<currentcmListRC.size();k++){
					if (k==0){
						currentcmRCResult = currentcmListRC.get(k).getCurrentcmImplcmName();  
					} else {
						currentcmRCResult = currentcmRCResult + "," + currentcmListRC.get(k).getCurrentcmImplcmName();
					}
				}
				
				ArrayList<Currentcm> currentcmListMI = (ArrayList<Currentcm>) this.riskService.getCurrentcmListByMI(param);
				for (int k=0 ; k<currentcmListMI.size();k++){
					if (k==0){
						currentcmMIResult = currentcmListMI.get(k).getCurrentcmImplcmName();  
					} else {
						currentcmMIResult = currentcmMIResult + "," + currentcmListMI.get(k).getCurrentcmImplcmName();
					}
				}
				
				ArrayList<Currentcm> currentcmListPI = (ArrayList<Currentcm>) this.riskService.getCurrentcmListByPI(param);
				for (int k=0 ; k<currentcmListPI.size();k++){
					if (k==0){
						currentcmPIResult = currentcmListPI.get(k).getCurrentcmImplcmName();  
					} else {
						currentcmPIResult = currentcmPIResult + ", " + currentcmListPI.get(k).getCurrentcmImplcmName();
					}
				}
				
				ArrayList<Currentcm> currentcmListRI = (ArrayList<Currentcm>) this.riskService.getCurrentcmListByRI(param);
				for (int k=0 ; k<currentcmListRI.size();k++){
					if (k==0){
						currentcmRIResult = currentcmListRI.get(k).getCurrentcmImplcmName();  
					} else {
						currentcmRIResult = currentcmRIResult + ", " + currentcmListRI.get(k).getCurrentcmImplcmName();
					}
				}
				
				ArrayList<Currentcm> currentcmListMA = (ArrayList<Currentcm>) this.riskService.getCurrentcmListByMA(param);
				for (int k=0 ; k<currentcmListMA.size();k++){
					if (k==0){
						currentcmMAResult = currentcmListMA.get(k).getCurrentcmImplcmName();  
					} else {
						currentcmMAResult = currentcmMAResult + ", " + currentcmListMA.get(k).getCurrentcmImplcmName();
					}
				}
				
				ArrayList<Currentcm> currentcmListPA = (ArrayList<Currentcm>) this.riskService.getCurrentcmListByPA(param);
				for (int k=0 ; k<currentcmListPA.size();k++){
					if (k==0){
						currentcmPAResult = currentcmListPA.get(k).getCurrentcmImplcmName();  
					} else {
						currentcmPAResult = currentcmPAResult + ", " + currentcmListPA.get(k).getCurrentcmImplcmName();
					}
				}
				
				ArrayList<Currentcm> currentcmListRA = (ArrayList<Currentcm>) this.riskService.getCurrentcmListByRA(param);
				for (int k=0 ; k<currentcmListRA.size();k++){
					if (k==0){
						currentcmRAResult = currentcmListRA.get(k).getCurrentcmImplcmName();  
					} else {
						currentcmRAResult = currentcmRAResult + ", " + currentcmListRA.get(k).getCurrentcmImplcmName();
					}
				}
					
				risktemplate.setRisktemplateDomainasID(domainasList.get(i).getDomainasID());
				risktemplate.setRisktemplateDomainasName(domainasList.get(i).getDomainasName());
				risktemplate.setRisktemplateDomainasSGoalC(domainasList.get(i).getDomainasSGoalC());
				risktemplate.setRisktemplateDomainasSGoalI(domainasList.get(i).getDomainasSGoalI());
				risktemplate.setRisktemplateDomainasSGoalA(domainasList.get(i).getDomainasSGoalA());
				risktemplate.setRisktemplateDomainasCriticality(domainasList.get(i).getDomainasCriticality());
				risktemplate.setRisktemplateThreatID(threatactList.get(j).getThreatactThreatID());
				risktemplate.setRisktemplateThreatCAPEC(threatactList.get(j).getThreatactCAPECID());
				risktemplate.setRisktemplateThreatCVE(threatactList.get(j).getThreatactCVEID());
				risktemplate.setRisktemplateThreatCWE(threatactList.get(j).getThreatactCWEID());
				risktemplate.setRisktemplateThreatMGoal(threatactList.get(j).getThreatactMGoal());
				risktemplate.setRisktemplateCMstatesCMID(currentcmMCResult);
				risktemplate.setRisktemplateCMstatesCPID(currentcmPCResult);
				risktemplate.setRisktemplateCMstatesCRID(currentcmRCResult);
				risktemplate.setRisktemplateCMstatesIMID(currentcmMIResult);
				risktemplate.setRisktemplateCMstatesIPID(currentcmPIResult);
				risktemplate.setRisktemplateCMstatesIRID(currentcmRIResult);
				risktemplate.setRisktemplateCMstatesAMID(currentcmMAResult);
				risktemplate.setRisktemplateCMstatesAPID(currentcmPAResult);
				risktemplate.setRisktemplateCMstatesARID(currentcmRAResult);
				risktemplateList.add(risktemplate);
			}
		}
		if(risktemplateList != null)
		{
			System.out.println("Success to bring the data!");
			map.put("success", risktemplateList);
		}else{
			System.out.println("Fail to the registration");
			map.put("fail", "가져오기 실패했습니다.");
		}	
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}
	
	@RequestMapping("/risk/select_RecommendingCMID.do")
	public ModelAndView select_RecommendingCMID(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println("-------------select_RecommendingCMID--------------");
		System.out.println("rp = " + rp);
		
		String[] arrayCmsgoalSgoal = rp.get("cmsgoalSgoal").toString().split(",");
		ArrayList<Cmsgoal> cmsgoalList = new ArrayList<Cmsgoal>();

		for (int i=0; i<arrayCmsgoalSgoal.length ; i++){
			Map<String, String> param = new HashMap<String, String>();
			param.put("cmsgoalSgoal", arrayCmsgoalSgoal[i]);
			param.put("cmsgoalASID", rp.get("cmsgoalASID").toString());
			cmsgoalList.addAll(this.riskService.getCmsgoalList(param));
		}

		if(cmsgoalList != null)
		{
			System.out.println("Success to bring the data!");
			map.put("success", cmsgoalList);
		
		}else{
			System.out.println("Fail to the registration");
			map.put("fail", "가져오기 실패했습니다..");		
		}	
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}

	@RequestMapping("/risk/select_RecommendingSR.do")
	public ModelAndView select_RecommendingSR(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println("-------------select_RecommendingSR--------------");
		System.out.println("rp = " + rp);

		// 각종 입력 변수 / 출력 변수 다 선언(param / ontresult / result)
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		
		param.put("domainasID", rp.get("domainasID").toString());
		param.put("CMID", rp.get("CMID").toString());
		param.put("implcmID", rp.get("CMID").toString()+"%");
		param.put("CMrecID", "%" + rp.get("CMID").toString().replace("CM_", "") + "%");
		param.put("ASID", rp.get("ASID").toString());
		param.put("threatID", rp.get("threatID").toString());
		param.put("CAPECID", rp.get("CAPECID").toString());
		if (rp.get("CVEID") != null){
			param.put("CVEID", rp.get("CVEID").toString());
		}
		if (rp.get("CWEID") != null){
			param.put("CWEID", rp.get("CWEID").toString());
		}

		// 1차 : 온톨로지를 통해서 Security Requirements를 가져오고, 이를 param에 집어넣기
		String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
		String rdfs = "http://www.w3.org/2000/01/rdf-schema#";
		String owl = "http://www.w3.org/2002/07/owl#";
		String xsd = "http://www.w3.org/2001/XMLSchema#";
		String SROnt = "http://www.semanticweb.org/ajou/ontologies/2016/1/securityOntologyTest#";
		String prefix = "prefix rdf: <" + rdf + ">\n" +
				"prefix rdfs: <" + rdfs+">\n" +
				"prefix owl: <" + owl+">\n" +
				"prefix xsd: <" + xsd+">\n" +
				"prefix SROnt: <" + SROnt+">\n";
		
		// 1-1 : owl 파일 불러오는 로직		
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open("C:/workingontology/test1st.owl");
		model.read(in, null);
		
		// 1-2 : statement 생성
		String queryString = prefix + "SELECT ?SR WHERE { ?SR SROnt:suggest SROnt:"+ param.get("CMID").toString() +"."
				+ "?SR SROnt:apply_To SROnt:"+ param.get("ASID").toString() +"."
				+ "?SR SROnt:prevent SROnt:"+ param.get("CWEID").toString() + "."
				+ "?SR SROnt:driven_By SROnt:"+param.get("CAPECID").toString() +"}";
		Query query = QueryFactory.create(queryString);	
		System.out.println("query : " + query);
		
		// 1-3 : 쿼리 실행
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		
		// 1-4 : 쿼리 실행 결과를 담는것 
		ResultSet results = qexec.execSelect() ;
		System.out.println("results : " + results);
						
		// 1-5 : 쿼리 실행 결과를 QuerySolution을 이용하여 각 추론 결과를 얻는 로직
		for ( ; results.hasNext() ; ){
			QuerySolution soln = results.nextSolution() ;
			System.out.println("soln : " + soln);
			
			// RDFnode는 soln에서 가져온 RDF데이터를 가지고 온다. 이게 아직 스트링 값이 아니어서 스트링으로 바꿔줄 필요 있음
		    RDFNode sr = soln.get("SR") ;

		    
		    // 스트링 변환하고, 불필요한 부분 전체 제거 
		    String srToString = sr.toString().replace(SROnt, "");
		    param.put("SRID", srToString);
		    param.put("SrrecID", srToString+"%");
		    System.out.println("SR : " + param.get("SRID"));
		}

		// 2차 : 온톨로지에 대한 결과를 바탕으로 모든 DB 정보 다 담아오기

		SR sr = (SR) this.riskService.getSecurityReqInfo(param);
		ArrayList<Relatedsr> relatedsrList = (ArrayList<Relatedsr>) this.riskService.getRelatedSecurityReqInfo(param);
		
		CM cm = (CM) this.riskService.getCountermeasureInfo(param);
		ArrayList<Implcm> implcmList = (ArrayList<Implcm>) this.riskService.getImplCountermeasureInfo(param);
		
		Asset as = (Asset) this.riskService.getAssetInfo(param);
		Domainas domainas = (Domainas) this.riskService.getDomainasInfo(param);
		ArrayList<Domainasrelatedsh> domainasrelatedshList = (ArrayList<Domainasrelatedsh>) this.riskService.getRelatedStakeholderInfo(param);
		
		Threat threat = (Threat) this.riskService.getThreatInfo(param);
		Threatact threatact = (Threatact) this.riskService.getThreatactInfo(param);
		CVE cve = new CVE();
		CWE cwe = new CWE();
		
		CAPEC capec = (CAPEC) this.riskService.getCAPECInfo(param);
		if (rp.get("CVEID") != null){
			cve = (CVE) this.riskService.getCVEInfo(param);
		}
		if (rp.get("CWEID") != null){
			cwe = (CWE) this.riskService.getCWEInfo(param);
		}
		
		ArrayList<Srrec> srrecBORList = (ArrayList<Srrec>) this.riskService.getBusinessOrganizationRecInfo(param);
		ArrayList<Srrec> srrecBRPList = (ArrayList<Srrec>) this.riskService.getBusinessRegulationRecInfo(param);
		ArrayList<Srrec> srrecBRFList = (ArrayList<Srrec>) this.riskService.getBusinessFundRecInfo(param);
		ArrayList<Srrec> srrecBTIList = (ArrayList<Srrec>) this.riskService.getBusinessTimeRecInfo(param);
		ArrayList<Srrec> srrecBBPList = (ArrayList<Srrec>) this.riskService.getBusinessBestPracticeRecInfo(param);
		ArrayList<Srrec> srrecBLCList = (ArrayList<Srrec>) this.riskService.getBusinessLegalComplianceRecInfo(param);
		ArrayList<Srrec> srrecSSEList = (ArrayList<Srrec>) this.riskService.getSystemServiceRecInfo(param);
		ArrayList<Srrec> srrecSPLList = (ArrayList<Srrec>) this.riskService.getSystemPlatformRecInfo(param);
		ArrayList<Srrec> srrecTSMList = (ArrayList<Srrec>) this.riskService.getTechnicalSecMechanismRecInfo(param);
		ArrayList<Srrec> srrecTTTList = (ArrayList<Srrec>) this.riskService.getTechnicalTrendRecInfo(param);
		
		// 3차 : DB 정보를 Result Hashmap에 모두 담기  
		
		
		// 3-1 : 보안 요구사항 관련
		result.put("rsrtSRNameID", sr.getSrID().toString());
		result.put("rsrtSRNameContent", sr.getSrName().toString());
		
		result.put("rsrtSRDescriptionID", sr.getSrID().toString());
		result.put("rsrtSRDescriptionContent", sr.getSrDescription().toString());

		for (int k=0 ; k<relatedsrList.size();k++){
			result.put("rsrtSRSubReqID"+k, relatedsrList.get(k).getRelatedsrRelatedSRID().toString());
			Map<String, Object> tmpparam = new HashMap<String, Object>();
			tmpparam.put("SRID", relatedsrList.get(k).getRelatedsrRelatedSRID().toString());
			SR tmpsr = (SR) this.riskService.getSecurityReqInfo(tmpparam);
			result.put("rsrtSRSubReqName"+k, tmpsr.getSrName().toString());
		}
		int SRRowCount = relatedsrList.size() + 2;
		result.put("rsrtSRRowCount", SRRowCount);
		result.put("rsrtSRSubReqRowCount", relatedsrList.size());
		
		
		// 3-2 : Countermeasure 관련
		result.put("rsrtCMNameID", cm.getCmID().toString());
		result.put("rsrtCMNameContent", cm.getCmName().toString());
		
		result.put("rsrtCMDescriptionID", cm.getCmID().toString());
		result.put("rsrtCMDescriptionContent", cm.getCmDescription().toString());
		
		for (int k=0 ; k<implcmList.size();k++){
			result.put("rsrtCMImplID"+k, implcmList.get(k).getImplcmID().toString());
			result.put("rsrtCMImplContent"+k, implcmList.get(k).getImplcmName().toString());
		}
		int CMRowCount = implcmList.size() + 2;
		result.put("rsrtCMRowCount", CMRowCount);
		result.put("rsrtCMImplRowCount", implcmList.size());
		
		
		// 3-3 : Asset 관련
		result.put("rsrtASTypeID", as.getAssetID().toString());
		result.put("rsrtASTypeContent", as.getAssetName().toString());
		
		result.put("rsrtASProtectDomainasID", domainas.getDomainasID().toString());
		result.put("rsrtASProtectDomainasContent", domainas.getDomainasName().toString() + " ("+domainas.getDomainasCriticality().toString() +")");
		result.put("rsrtASAssetSGoalCID", domainas.getDomainasID().toString() + "_Confidentiality");
		result.put("rsrtASAssetSGoalCContent", domainas.getDomainasSGoalC().toString()); 
		result.put("rsrtASAssetSGoalIID", domainas.getDomainasID().toString() + "_Integrity");
		result.put("rsrtASAssetSGoalIContent", domainas.getDomainasSGoalI().toString());
		result.put("rsrtASAssetSGoalAID", domainas.getDomainasID().toString() + "_Availability");
		result.put("rsrtASAssetSGoalAContent", domainas.getDomainasSGoalA().toString());
		
		for (int k=0 ; k<domainasrelatedshList.size();k++){
			result.put("rsrtASRelatedSHID"+k, domainasrelatedshList.get(k).getDomainasrelatedshRelatedshID().toString());
			Map<String, Object> tmpparam = new HashMap<String, Object>();
			tmpparam.put("SHID", domainasrelatedshList.get(k).getDomainasrelatedshRelatedshID().toString());
			Relatedsh relatedsh = (Relatedsh) this.riskService.getRelatedSHInfo(tmpparam);
			result.put("rsrtASRelatedSHName"+k, relatedsh.getRelatedshName().toString());
		}
		int ASRowCount = domainasrelatedshList.size() + 5;
		result.put("rsrtASRowCount", ASRowCount);
		result.put("rsrtASRelatedSHRowCount", domainasrelatedshList.size());
		
		
		
		// 3-4 : Risk 관련
		result.put("rsrtRFThreatID", threat.getThreatID().toString());
		result.put("rsrtRFThreatContent", threat.getThreatName().toString());
		result.put("rsrtRFThreatDescriptionContent", threat.getThreatDescription().toString());
		result.put("rsrtRFThreatactDescriptionContent", threatact.getThreatactActDescription().toString());
		result.put("rsrtRFThreatMGoalID", threatact.getThreatactThreatID().toString());
		result.put("rsrtRFThreatMGoalContent", threatact.getThreatactMGoal().toString());
		result.put("rsrtRFAttackVectorID", capec.getCapecID().toString());
		result.put("rsrtRFAttackVectorContent", capec.getCapecName().toString());
		
		if (cve != null){
		
			result.put("rsrtRFVulnerabilityID", cve.getCveID().toString());
			result.put("rsrtRFVulnerabilityContent", cve.getCveName().toString());
		
		}
		if (rp.get("CWEID") != null){
			result.put("rsrtRFWeaknessID", cwe.getCweID().toString());
			result.put("rsrtRFWeaknessContent", cwe.getCweName().toString());
		}
		int RFRowCount = 7;
		result.put("rsrtRFRowCount", RFRowCount);
		

		
		
		
		// 3-5 : Business Perspective 관련
		for (int k=0 ; k<srrecBORList.size();k++){
			result.put("rsrtRBOrganizationID"+k, srrecBORList.get(k).getSrrecID().toString());
			result.put("rsrtRBOrganizationContent"+k, srrecBORList.get(k).getSrrecDescription().toString());
		}
		result.put("rsrtRBOrganizationRowCount", srrecBORList.size());
		
		
		for (int k=0 ; k<srrecBRPList.size();k++){
			result.put("rsrtRBRegulationID"+k, srrecBRPList.get(k).getSrrecID().toString());
			result.put("rsrtRBRegulationContent"+k, srrecBRPList.get(k).getSrrecDescription().toString());
		}
		result.put("rsrtRBRegulationRowCount", srrecBRPList.size());
		
		
		for (int k=0 ; k<srrecBRFList.size();k++){
			result.put("rsrtRBFundID"+k, srrecBRFList.get(k).getSrrecID().toString());
			result.put("rsrtRBFundContent"+k, srrecBRFList.get(k).getSrrecDescription().toString());
		}
		result.put("rsrtRBFundRowCount", srrecBRFList.size());
		
		
		for (int k=0 ; k<srrecBTIList.size();k++){
			result.put("rsrtRBTimeID"+k, srrecBTIList.get(k).getSrrecID().toString());
			result.put("rsrtRBTimeContent"+k, srrecBTIList.get(k).getSrrecDescription().toString());
		}
		result.put("rsrtRBTimeRowCount", srrecBTIList.size());
		
		
		for (int k=0 ; k<srrecBBPList.size();k++){
			result.put("rsrtRBBestPracticeID"+k, srrecBBPList.get(k).getSrrecID().toString());
			result.put("rsrtRBBestPracticeContent"+k, srrecBBPList.get(k).getSrrecDescription().toString());
		}
		result.put("rsrtRBBestPracticeRowCount", srrecBBPList.size());
		
		
		for (int k=0 ; k<srrecBLCList.size();k++){
			result.put("rsrtRBLegalComplianceID"+k, srrecBLCList.get(k).getSrrecID().toString());
			result.put("rsrtRBLegalComplianceContent"+k, srrecBLCList.get(k).getSrrecDescription().toString());
		}
		result.put("rsrtRBLegalComplianceRowCount", srrecBLCList.size());
		
		int RBRowCount = srrecBORList.size()+ srrecBRPList.size() +srrecBRFList.size()+srrecBTIList.size()+srrecBBPList.size()+srrecBLCList.size();
		result.put("rsrtRBRowCount", RBRowCount);
		
		
		
		for (int k=0 ; k<srrecSSEList.size();k++){
			result.put("rsrtRSServiceID"+k, srrecSSEList.get(k).getSrrecID().toString());
			result.put("rsrtRSServiceContent"+k, srrecSSEList.get(k).getSrrecDescription().toString());
		}
		result.put("rsrtRSServiceRowCount", srrecSSEList.size());
		
		
		for (int k=0 ; k<srrecSPLList.size();k++){
			result.put("rsrtRSPlatformID"+k, srrecSPLList.get(k).getSrrecID().toString());
			result.put("rsrtRSPlatformContent"+k, srrecSPLList.get(k).getSrrecDescription().toString());
		}
		result.put("rsrtRSPlatformRowCount", srrecSPLList.size());
		
		int RSRowCount = srrecSSEList.size()+ srrecSPLList.size();
		result.put("rsrtRSRowCount", RSRowCount);
		
		
		for (int k=0 ; k<srrecTSMList.size();k++){
			result.put("rsrtRTMechanismID"+k, srrecTSMList.get(k).getSrrecID().toString());
			result.put("rsrtRTMechanismContent"+k, srrecTSMList.get(k).getSrrecDescription().toString());
		}
		result.put("rsrtRTMechanismRowCount", srrecTSMList.size());
		
		
		for (int k=0 ; k<srrecTTTList.size();k++){
			result.put("rsrtRTTrendID"+k, srrecTTTList.get(k).getSrrecID().toString());
			result.put("rsrtRTTrendContent"+k, srrecTTTList.get(k).getSrrecDescription().toString());
		}
		result.put("rsrtRTTrendRowCount", srrecTTTList.size());
		
		int RTRowCount = srrecTSMList.size()+ srrecTTTList.size();
		result.put("rsrtRTRowCount", RTRowCount);
		
		
		// 마지막 View Resolving을 위한 리턴
		
		System.out.println("Success to bring the data!");
		map.put("success", result);

	
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}
}