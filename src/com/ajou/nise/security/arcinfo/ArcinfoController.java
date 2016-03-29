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
import com.ajou.nise.security.model.User;
import com.ajou.nise.security.model.Relatedsh;
import com.ajou.nise.security.model.Platform;

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




}