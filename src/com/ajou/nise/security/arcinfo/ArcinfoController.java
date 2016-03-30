package com.ajou.nise.security.arcinfo;

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
import com.ajou.nise.security.arcinfo.ArcinfoServiceImpl;
// 모델들 모음
import com.ajou.nise.security.model.*;

@Controller
public class ArcinfoController {
	
	@Resource(name = "arcinfoService")
	private ArcinfoServiceImpl arcinfoService;

	@RequestMapping("/arcinfo/submit_StakeholderInfo.do")
	public ModelAndView submit_StakeholderInfo(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println("-------------submit_StakeholderInfo--------------");
		System.out.println("rp = " + rp);

		Relatedsh relatedsh = new Relatedsh();
		// 데이터 핸들링을 위해 모델에 주입
		relatedsh.setRelatedshUserID(rp.get("relatedshUserID").toString());
		relatedsh.setRelatedshID(rp.get("relatedshID").toString());
		relatedsh.setRelatedshName(rp.get("relatedshName").toString());
		relatedsh.setRelatedshDescription(rp.get("relatedshDescription").toString());
		relatedsh.setRelatedshCompany(rp.get("relatedshCompany").toString());
		
		//user의 Stakeholder의 countnumber 가져오기
		User user = (User) this.arcinfoService.getUserSHcnt(rp);
		int getuserShcountResult = user.getUserShcount();
		getuserShcountResult = getuserShcountResult +1;
		String userSHCnt = String.valueOf(getuserShcountResult);
		
		//user의 Stakeholder의 Count Number를 바탕으로 Stakeholder의 아이디 생성  
		String tmpRelatedshID = relatedsh.getRelatedshID() + userSHCnt;
		relatedsh.setRelatedshID(tmpRelatedshID);
		
		// 데이터 로그 찍기 
		System.out.println(relatedsh.getRelatedshName());
		System.out.println(relatedsh.getRelatedshID());
		System.out.println(relatedsh.getRelatedshUserID());
		System.out.println(relatedsh.getRelatedshDescription());
		System.out.println(relatedsh.getRelatedshCompany());
		
		// 최종 insertrk 완료되면 user의 Stakeholder counter를 1 올린다.(추후에 삭제시 1 빼는 것 로직 추가 예정)
		if(arcinfoService.insertStakeholderInfo(relatedsh) == 1)
		{
			user.setUserShcount(getuserShcountResult);
			arcinfoService.updateUserSHCnt(user);

			System.out.println("Success to the registration");
			map.put("success", "등록 완료");
		
		}else
		{
			System.out.println("Fail to the registration");
			map.put("fail", "등록 실패");		
		}

		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}

	@RequestMapping("/arcinfo/submit_PlatformInfo.do")
	public ModelAndView submit_PlatformInfo(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println("-------------submit_PlatformInfo--------------");
		System.out.println("rp = " + rp);

		Platform platform = new Platform();
		
		// 데이터 핸들링을 위해 모델에 주입
		platform.setPlatformPart(rp.get("platformPart").toString());
		platform.setPlatformVendor(rp.get("platformVendor").toString());
		platform.setPlatformProduct(rp.get("platformProduct").toString());
		platform.setPlatformVersion(rp.get("platformVersion").toString());
		platform.setPlatformUpdate(rp.get("platformUpdate").toString());
		platform.setPlatformEdition(rp.get("platformEdition").toString());
		platform.setPlatformLanguage(rp.get("platformLanguage").toString());
		platform.setPlatformSoftwareEdition(rp.get("platformSoftwareEdition").toString());
		platform.setPlatformTargetSoftware(rp.get("platformTargetSoftware").toString());
		platform.setPlatformTargetHardware(rp.get("platformTargetHardware").toString());
		platform.setPlatformOther(rp.get("platformOther").toString());
				
		// 주입된 정보를 바탕으로 아이디 만들기
		String platformID = "cpe:2.3:"+ platform.getPlatformPart() + ":"
				+ platform.getPlatformVendor() + ":" + platform.getPlatformProduct() + ":"
				+ platform.getPlatformVersion() + ":" + platform.getPlatformUpdate() + ":"
				+ platform.getPlatformEdition() + ":" + platform.getPlatformLanguage() + ":"
				+ platform.getPlatformSoftwareEdition() + ":" + platform.getPlatformTargetSoftware() + ":"
				+ platform.getPlatformTargetHardware() + ":" + platform.getPlatformOther();
		platform.setPlatformID(platformID);

		// 데이터 로그 찍기 
		System.out.println(platform.getPlatformID());
		
		//중복 여부 확인
		if(arcinfoService.checkUniquePlatformInfo(platform) == true)
		{
			// 중복되지 않으면 등록
			arcinfoService.insertPlatformInfo(platform);
			System.out.println("Success to the registration");
			map.put("success", "등록 완료");
		
		}else
		{
			// 중복되면 등록안하고, 빠져나가기
			System.out.println("Fail to the registration");
			map.put("fail", "중복되는 정보가 있습니다.");		
		}
		
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}

	
	@RequestMapping("/arcinfo/select_AssetType.do")
	public ModelAndView select_AssetType(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
				
		System.out.println("-------------select_AssetType--------------");
		System.out.println("rp = " + rp);
		
		// SQL 쿼리문의 조건문을 만들기 위해 param이라는 Hash Map을 활용하여 쿼리 조건문 완성 
		String assetTypetmp = rp.get("assetType").toString() + "_%";
		param.put("assetType", assetTypetmp);
		
		ArrayList<Asset> AssetList = (ArrayList<Asset>) this.arcinfoService.getAssetList(param);
		System.out.println(AssetList);
		
		if(AssetList != null)
		{
			System.out.println("Success to the registration");
			map.put("success", AssetList);
		
		}else
		{
			System.out.println("Fail to the registration");
			map.put("fail", "가져오기 실패했습니다..");		
		}
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}
	
	@RequestMapping("/arcinfo/select_PlatformInfo.do")
	public ModelAndView select_PlatformInfo(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println("-------------select_PlatformInfo--------------");
		System.out.println("rp = " + rp);

		Platform platform = new Platform();
		
		// 데이터 핸들링을 위해 모델에 주입
		platform.setPlatformPart(rp.get("platformPart").toString());
		platform.setPlatformVendor(rp.get("platformVendor").toString());
		platform.setPlatformProduct(rp.get("platformProduct").toString());
		platform.setPlatformVersion(rp.get("platformVersion").toString());
		platform.setPlatformUpdate(rp.get("platformUpdate").toString());
		platform.setPlatformEdition(rp.get("platformEdition").toString());
		platform.setPlatformLanguage(rp.get("platformLanguage").toString());
		platform.setPlatformSoftwareEdition(rp.get("platformSoftwareEdition").toString());
		platform.setPlatformTargetSoftware(rp.get("platformTargetSoftware").toString());
		platform.setPlatformTargetHardware(rp.get("platformTargetHardware").toString());
		platform.setPlatformOther(rp.get("platformOther").toString());
				
		// 주입된 정보를 바탕으로 아이디 만들기
		String platformID = "cpe:2.3:"+ platform.getPlatformPart() + ":"
				+ platform.getPlatformVendor() + ":" + platform.getPlatformProduct() + ":"
				+ platform.getPlatformVersion() + ":" + platform.getPlatformUpdate() + ":"
				+ platform.getPlatformEdition() + ":" + platform.getPlatformLanguage() + ":"
				+ platform.getPlatformSoftwareEdition() + ":" + platform.getPlatformTargetSoftware() + ":"
				+ platform.getPlatformTargetHardware() + ":" + platform.getPlatformOther();
		platform.setPlatformID(platformID);

		// 데이터 로그 찍기 
		System.out.println(platform.getPlatformID());
		
		//등록 여부 확인 있으면 위에서 사용한 아이디 그대로 가져 나오기
		if(arcinfoService.checkUniquePlatformInfo(platform) == false)
		{
			System.out.println("Success to bring the data");
			map.put("success", platformID);
		
		}else
		{
			System.out.println("Fail to bring the data.");
			map.put("fail", "등록된 정보가 없습니다..");		
		}
		
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}

	@RequestMapping("/arcinfo/bring_StakeholderInfo.do")
	public ModelAndView bring_StakeholderInfo(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println("-------------bring_StakeholderInfo--------------");
		System.out.println("rp = " + rp);

		ArrayList<Relatedsh> RelatedSHList = (ArrayList<Relatedsh>) this.arcinfoService.getRelatedSHList(rp);
		
		if(RelatedSHList != null)
		{
			System.out.println("Success to bring the data");
			map.put("success", RelatedSHList);
		
		}else
		{
			System.out.println("Fail to bring the data");
			map.put("fail", "등록 실패");		
		}

		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}

	@RequestMapping("/arcinfo/submit_DomainAssetInfo.do")
	public ModelAndView submit_DomainAssetInfo(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println("-------------submit_DomainAssetInfo--------------");
		System.out.println("rp = " + rp);

		Domainas domainas = new Domainas();
		// 데이터 핸들링을 위해 모델에 주입
		domainas.setDomainasID(rp.get("domainasID").toString());
		domainas.setDomainasName(rp.get("domainasName").toString());
		domainas.setDomainasUserID(rp.get("domainasUserID").toString());
		domainas.setDomainasCriticality(rp.get("domainasCriticality").toString());
		domainas.setDomainasDescription(rp.get("domainasDescription").toString());
		domainas.setDomainasSGoalC(rp.get("domainasSGoalC").toString());
		domainas.setDomainasSGoalI(rp.get("domainasSGoalI").toString());
		domainas.setDomainasSGoalA(rp.get("domainasSGoalA").toString());
		
		//user의 Domain Asset의 count number 가져오기
		User user = (User) this.arcinfoService.getUserAScnt(rp);
		int getuserAscountResult = user.getUserAscount();
		getuserAscountResult = getuserAscountResult +1;
		String userASCnt = String.valueOf(getuserAscountResult);
		
		//user의 Domain Asset의 Count Number를 바탕으로 Domain Asset의 아이디 생성  
		String tmpDomainasID = domainas.getDomainasID() + userASCnt;
		domainas.setDomainasID(tmpDomainasID);
		
		// 데이터 로그 찍기 
		System.out.println(domainas.getDomainasName());
		System.out.println(domainas.getDomainasID());
		System.out.println(domainas.getDomainasUserID());
		System.out.println(domainas.getDomainasDescription());
		System.out.println(domainas.getDomainasCriticality());
		System.out.println(domainas.getDomainasSGoalC());
		System.out.println(domainas.getDomainasSGoalI());
		System.out.println(domainas.getDomainasSGoalA());

		// 최종 insertrk 완료되면 user의 Stakeholder counter를 1 올린다.(추후에 삭제시 1 빼는 것 로직 추가 예정)
		if(arcinfoService.insertDomainasInfo(domainas) == 1)
		{
			user.setUserAscount(getuserAscountResult);
			arcinfoService.updateUserASCnt(user);

			System.out.println("Success to the registration");
			map.put("success", tmpDomainasID);
		
		}else
		{
			System.out.println("Fail to the registration");
			map.put("fail", "등록 실패");		
		}
		 
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}

	@RequestMapping("/arcinfo/submit_DomainAssetPLSHInfo.do")
	public ModelAndView submit_DomainAssetPLSHInfo(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		
		System.out.println("-------------submit_DomainAssetPLSHInfo--------------");
		System.out.println("rp = " + rp);

		// request로 온 데이터를 분리
		String tmpdomainasplPlatformID=rp.get("domainasplPlatformID").toString();
		String tmpdomainasrelatedshRelatedshID=rp.get("domainasrelatedshRelatedshID").toString();

		String [] resultdomainasplPlatformID =tmpdomainasplPlatformID.split(",");
		String [] resultdomainasrelatedshRelatedshID =tmpdomainasrelatedshRelatedshID.split(",");

		// 데이터를 hash map에 집어넣고 반복적으로 table에 입력(Domain Asset 과 Platform 정보 종합)
		for( int i = 0; i < resultdomainasplPlatformID.length; i++ ){
			System.out.println( "문자(열) " + (i+1) + " : " + resultdomainasplPlatformID[i] );
			Map<String, String> param = new HashMap<String, String>();
		    param.put("domainasplDomainasID", rp.get("domainasplDomainasID").toString());
		    param.put("domainasplPlatformID", resultdomainasplPlatformID[i]);
		    arcinfoService.insertDomainasplInfo(param);
		}
		// 데이터를 hash map에 집어넣고 반복적으로 table에 입력(Domain Asset 과 관련 Stakeholder 정보 종합)		
		for( int j=0; j<resultdomainasrelatedshRelatedshID.length; j++){
			System.out.println( "문자(열) " + (j+1) + " : " + resultdomainasrelatedshRelatedshID[j] );
			Map<String, String> param1 = new HashMap<String, String>();
			param1.put("domainasrelatedshDomainasID", rp.get("domainasrelatedshDomainasID").toString());
			param1.put("domainasrelatedshRelatedshID", resultdomainasrelatedshRelatedshID[j]);
			arcinfoService.insertDomainasRelatedshInfo(param1);
		}
		// 여기까지 도달한 것 확인을 위해 첨가
		int k=1;

		if(k == 1)
		{

			System.out.println("Success to the registration");
			map.put("success", "Success to the registration");
		
		}else
		{
			System.out.println("Fail to the registration");
			map.put("fail", "등록 실패");		
		}

		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}




}