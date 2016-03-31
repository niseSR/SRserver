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

	// Stakeholder 정보 입력 완료 후 DB로 저장하는 로직
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

	// 플랫폼 정보를 DB로 입력하기 위한 로직
	@RequestMapping("/arcinfo/submit_PlatformInfo.do")
	public ModelAndView submit_PlatformInfo(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println("-------------submit_PlatformInfo--------------");
		System.out.println("rp = " + rp);

		Platform platform = new Platform();
		
		// 아이디를 CPE 형식으로 만들기 위한 데이터 핸들링을 위해 모델에 주입
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
				
		// 주입된 정보를 바탕으로 CPE 아이디 만들기
		String platformID = "cpe:2.3:"+ platform.getPlatformPart() + ":"
				+ platform.getPlatformVendor() + ":" + platform.getPlatformProduct() + ":"
				+ platform.getPlatformVersion() + ":" + platform.getPlatformUpdate() + ":"
				+ platform.getPlatformEdition() + ":" + platform.getPlatformLanguage() + ":"
				+ platform.getPlatformSoftwareEdition() + ":" + platform.getPlatformTargetSoftware() + ":"
				+ platform.getPlatformTargetHardware() + ":" + platform.getPlatformOther();
		platform.setPlatformID(platformID);

		// 등록되어 있는지에 대한 여부 확인(등록되어 있으면 재등록할 필요 없음)
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

	// Asset의 카테고리를 바탕으로 세부 카테고리 불러오는 로직 
	@RequestMapping("/arcinfo/select_AssetType.do")
	public ModelAndView select_AssetType(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
				
		System.out.println("-------------select_AssetType--------------");
		System.out.println("rp = " + rp);
		
		// SQL 쿼리문의 조건문을 만들기 위해 param이라는 Hash Map을 활용하여 쿼리 조건문 완성
		// 이부분은 ibatis에서는 계속 에러가 나므로 java에서 쿼리문을 완성하고, ibatis로 보내야 정신건강에 이로움
		String assetTypetmp = rp.get("assetType").toString() + "_%";
		param.put("assetType", assetTypetmp);
		
		// 결과값을 ArrayList 형식으로 Asset 모델을 각 key에 대한 value로 리턴받음
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
	
	// 사용자가 입력한 Platform 정보를 바탕으로 CPE 아이디를 가져오는 로직
	@RequestMapping("/arcinfo/select_PlatformInfo.do")
	public ModelAndView select_PlatformInfo(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println("-------------select_PlatformInfo--------------");
		System.out.println("rp = " + rp);

		Platform platform = new Platform();
		
		// 데이터 핸들링을 위해 모델에 주입(위와 동일)
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
				
		// 주입된 정보를 바탕으로 아이디 만들기(위와 동일)
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

	// 맨처음 페이지 로드할 때 session에 입력된 사용자 정보를 바탕으로 Stakeholder의 정보를 가져오고 이를 체크박스화 하는 로직
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

	// 완성된 도메인 Asset과 관련된 정보를 입력하는 곳
	// 여기서 Domain Asset 정보와 Platform 정보, 연관 Stakeholder 정보는 데이터베이스 정규화로 인해서 분리해서 저장됨 
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
		
		//user의 Domain Asset의 count number 가져오기(사용자 Usability를 위해 자동으로 ID를 부여하기 위한 작업임)
		User user = (User) this.arcinfoService.getUserAScnt(rp);
		int getuserAscountResult = user.getUserAscount();
		getuserAscountResult = getuserAscountResult +1;
		String userASCnt = String.valueOf(getuserAscountResult);
		
		//user의 Domain Asset의 Count Number를 바탕으로 Domain Asset의 아이디 생성  
		String tmpDomainasID = domainas.getDomainasID() + userASCnt;
		domainas.setDomainasID(tmpDomainasID);

		// 최종 insert가 완료되면 user의 Stakeholder counter를 1 올린다.(추후에 삭제시 1 빼는 것 로직 추가 예정)
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

	// Domain Asset의 정보입력 이후 Asset의 플랫폼 정보와 Stakeholder의 정보를 DB에 주입하는 과정
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

	// countermeasure 종류를 자동으로 분류에 맞춰서 가져오기 위한 로직
	@RequestMapping("/arcinfo/select_CountermeasureType.do")
	public ModelAndView select_CountermeasureType(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
				
		System.out.println("-------------select_CountermeasureType--------------");
		System.out.println("rp = " + rp);
		
		// SQL 쿼리문의 조건문을 만들기 위해 param이라는 Hash Map을 활용하여 쿼리 조건문 완성 
		param.put("cmType", rp.get("cmType").toString() + "%");
		
		ArrayList<CM> CMList = (ArrayList<CM>) this.arcinfoService.getCMList(param);
		System.out.println(CMList);
		
		if(CMList != null)
		{
			System.out.println("Success to the registration");
			map.put("success", CMList);
		
		}else
		{
			System.out.println("Fail to the registration");
			map.put("fail", "가져오기 실패했습니다..");		
		}
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}
	// 구현가능한 countermeasure 종류를 자동으로 분류에 맞춰서 가져오기 위한 로직
	@RequestMapping("/arcinfo/select_ImplCountermeasureType.do")
	public ModelAndView select_ImplCountermeasureType(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
				
		System.out.println("-------------select_ImplCountermeasureType--------------");
		System.out.println("rp = " + rp);
		
		// SQL 쿼리문의 조건문을 만들기 위해 param이라는 Hash Map을 활용하여 쿼리 조건문 완성 
		param.put("implcmType", rp.get("implcmType").toString() + "%");
		
		ArrayList<Implcm> CMList = (ArrayList<Implcm>) this.arcinfoService.getImplCMList(param);
		System.out.println(CMList);
		
		if(CMList != null)
		{
			System.out.println("Success to the registration");
			map.put("success", CMList);
		
		}else
		{
			System.out.println("Fail to the registration");
			map.put("fail", "가져오기 실패했습니다..");		
		}
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		return mnv;
	}
	
	// 최종적으로 도메인에서 현재 운용중인 Countermeasure의 정보를 가져오기 위한 로직 
	@RequestMapping("/arcinfo/submit_CurrentCMInfo.do")
	public ModelAndView submit_CurrentCMInfo(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();

		System.out.println("-------------submit_CurrentCMInfo--------------");
		System.out.println("rp = " + rp);

		if(arcinfoService.insertCurrentCMInfo(rp) == 1)
		{
			System.out.println("Success to the registration");
			map.put("success", "등록성공");
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