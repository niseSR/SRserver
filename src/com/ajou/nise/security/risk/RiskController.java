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
	
	@RequestMapping("/risk/calculate_RiskFactor.do")
	public ModelAndView calculate_RiskFactor(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> result = new HashMap<String, String>();
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
				String tmp = rp.get("asID").toString().replace("AS_", "C_");
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
		}else
		{
			System.out.println("Fail to the registration");
			map.put("fail", "가져오기 실패했습니다..");		
		}	
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}


}