package com.ajou.nise.security.aop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.servlet.ModelAndView;

import com.ajou.nise.security.common.RequestParameter;
import com.ajou.nise.security.common.Utils;

@Aspect
public class ProfilingAspect {
	
	@Pointcut("execution(public * com.ajou.secure.board.BoardController..*(..))")
	private void profileTarget(){}
	

	@Around("profileTarget()")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable{
		String signatureString = joinPoint.getSignature().toShortString();
		//System.out.println(signatureString + "시작");
		long start = System.currentTimeMillis();
		
		Object[] obj = joinPoint.getArgs();
		HttpServletRequest req;
		HttpServletResponse res;
		req = (HttpServletRequest) obj[0];
		res = (HttpServletResponse) obj[1];
		
		RequestParameter rp = Utils.extractRequestParameters(req);
		System.out.println(signatureString + "rp : "+ rp);
		
		ModelAndView mnv = new ModelAndView("/common/json_result");
		Map<String, Object> map = new HashMap<String, Object>();
		
		int seq = 0;
		
		if(rp.get("boardNumSeq") != null)
			seq = Integer.parseInt(rp.get("boardNumSeq").toString());
		
		if(seq == 104){	
			map.put("fail", "로그인 실패");
			mnv.addObject("map", map);
			mnv.addObject("callback", req.getParameter("callback"));
			
			System.out.println("mnv = "+ mnv);
			return mnv;
		}
		
		
		
		try{
			Object result = joinPoint.proceed();
			return result;
		}finally{
			long finish = System.currentTimeMillis();
		//	System.out.println(signatureString + "종료");
		//	System.out.println(signatureString + "실행시간 : " +(finish-start)+"ms");
		}
		
	}

	
	/*
	@AfterReturning(pointcut = "profileTarget()", returning="ret")
	public void trace(JoinPoint joinPoint, HashMap ret) throws Throwable{
		String signatureString = joinPoint.getSignature().toShortString();
		System.out.println(signatureString + "시작");
		long start = System.currentTimeMillis();
		
		ret.put("aaa", "bbb");
		
		

	}
	*/
	
	/*
	@Before("profileTarget()")
	public Object beforeTrace(ProceedingJoinPoint joinPoint) throws Throwable{
		String signatureString = joinPoint.getSignature().toShortString();
		System.out.println(signatureString + "test!!!!!!!!!!!!!!!!!!!!!");
		try{
			Object result = joinPoint.proceed();
			return result;
		}finally{
			long finish = System.currentTimeMillis();
		}
	}
	*/

}
