package com.ajou.nise.security.user;

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

//test 1차용 import
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;

//test 2차용 import
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;

//test 3차용 import
import com.hp.hpl.jena.query.* ;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;

import java.io.*;


import com.ajou.nise.security.common.RequestParameter;
import com.ajou.nise.security.common.Utils;
import com.ajou.nise.security.user.User;
import com.ajou.nise.security.user.UserServiceImpl;

@Controller
public class UserController {
	
	@Resource(name = "userService")
	private UserServiceImpl userService;
	
	@Resource(name = "shaEncoder")
	private ShaEncoder encoder;
	
	/* 테스트 1차 : import 시켜서 rdf 모델이 정상적으로 동작하는지 확인
	 * 결과 : 테스트 이상없으며, 대신 import시켜야하는 라이브러리는 Web App Libraries 안에 무조건 넣어야 함.
	 */ 	
 
	@RequestMapping("/test/test_jena1.do")
	public ModelAndView test_jena1(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		 
		System.out.println("rp = "+ rp);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Model m = ModelFactory.createDefaultModel();
		String NS = "http://example.com/test/";
		
		com.hp.hpl.jena.rdf.model.Resource r = m.createResource(NS + "r");
		Property p = m.createProperty(NS + "p");
		
		r.addProperty(p, "hello world", XSDDatatype.XSDstring);
		
		m.write(System.out, "Turtle");
				
		map.put("success", 1);
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		System.out.println("mnv = "+ mnv);
		return mnv;
	}

	/* 테스트 2차 : 모델 파일 전부다 긁어오기
	 * 결과 : 테스트 이상없으며, 전체 파일을 다 긁어온것 같지만, console에 찍히는 내용은 다소 차이가 있음.
	 */ 	
	
	@RequestMapping("/test/test_jena2.do")
	public ModelAndView test_jena2(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		 
		System.out.println("rp = "+ rp);
		
		Map<String, Object> map = new HashMap<String, Object>();
	
		String inputFileName = "test1st.owl";
		
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open("E:/workingontology/test1st.owl");
		
		if (in == null) {
		    throw new IllegalArgumentException(
		                                 "File: " + inputFileName + " not found");
		}

		model.read(in, null);
		
		model.write(System.out);
				
		map.put("success", 1);
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		System.out.println("mnv = "+ mnv);
		return mnv;
	}

	/* 테스트 3차 : SPARQL 만들어서 테스트 
	 * 결과 : 일단 soln 에서 SPARQL 구문에 대한 결과를 받을 수 있음 이제 이것을 파싱해야함.
	 */ 	
		
	@RequestMapping("/test/test_jena3.do")
	public ModelAndView test_jena3(HttpServletRequest req, HttpServletResponse res) throws Exception {
		RequestParameter rp = Utils.extractRequestParameters(req);	
		ModelAndView mnv = new ModelAndView("/common/json_result");
		 
		System.out.println("rp = "+ rp);
		Map<String, Object> map = new HashMap<String, Object>();
		
		// statement의 prefix를 생성
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
		
		// owl 파일 불러오는 로직		
		String inputFileName = "E:/workingontology/test1st.owl";
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open("E:/workingontology/test1st.owl");
		model.read(in, null);
		
		// statement 생성
		String queryString = prefix + "SELECT ?SR ?CM ?AS "
			+ "WHERE { ?SR SROnt:suggest ?CM."
			+ "?SR SROnt:apply_To ?AS.}";
		Query query = QueryFactory.create(queryString);	
		System.out.println("query : " + query);
		
		// 쿼리 실행
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		
		// 쿼리 실행 결과를 담는것 
		ResultSet results = qexec.execSelect() ;
		System.out.println("results : " + results);
		
		// 쿼리 실행 결과를 QuerySolution을 이용하여 각 추론 결과를 얻는 로직
		for ( ; results.hasNext() ; ){
			QuerySolution soln = results.nextSolution() ;
			System.out.println("soln : " + soln);
			
			// RDFnode는 soln에서 가져온 RDF데이터를 가지고 온다. 이게 아직 스트링 값이 아니어서 스트링으로 바꿔줄 필요 있음
		    RDFNode sr = soln.get("SR") ;
		    RDFNode as = soln.get("AS") ;
		    RDFNode cm = soln.get("CM") ;
		    
		    // 스트링 변환하고, 불필요한 부분 전체 제거 
		    String srToString = sr.toString().replace(SROnt, "");
		    System.out.println("SR : " + srToString);
		    
		    String asToString = as.toString().replace(SROnt, "");
		    System.out.println("AS : " + asToString);
		    
		    String cmToString = cm.toString().replace(SROnt, "");
		    System.out.println("CM : " + cmToString);
		}
		
		
		map.put("success", 1);
		mnv.addObject("map", map);
		mnv.addObject("callback", req.getParameter("callback"));
		
		System.out.println("mnv = "+ mnv);
		return mnv;
	}
	
}