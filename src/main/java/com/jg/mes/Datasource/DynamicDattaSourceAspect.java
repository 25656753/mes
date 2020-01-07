package com.jg.mes.Datasource;


import com.jg.mes.Anno.TargetDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @Author caiChaoqi
 * @Description 动态数据源通知
 * @Date 2018-06-23
 */



@Aspect
@Order(-1) // 保证在@Transactional之前执行
@Component
public class DynamicDattaSourceAspect {


	private Logger logger =LoggerFactory.getLogger(DynamicDattaSourceAspect.class);
	
	@Autowired
	private DataSoruceProxy datasourceproxy;

	// 改变数据源
	@Before("@annotation(targetDataSource)")
	public void changeDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
		
		//获取RequestAttributes  
         RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();  
        //从获取RequestAttributes中获取HttpServletRequest的信息  
       // HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);  
       // logger.info("==> 请求者的IP："+request.getRemoteAddr());
        //如果要获取Session信息的话，可以这样写：  
       
      //  HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);

       // String dbid= ((User) session.getAttribute("user")).getUsername();
		
		
		String dbid = targetDataSource.name();
        if (!DynamicDataSourceContextHolder.isContainsDataSource(dbid)) {
			// joinPoint.getSignature() ：获取连接点的方法签名对象
			datasourceproxy.addDataSource(
					"jdbc:mysql://localhost:3306/"+dbid+"?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=UTC",
					dbid);
			DynamicDataSourceContextHolder.setDataSourceType(dbid);
			logger.error("数据源 " + dbid + " 不存在，已加入到数据源 -> " + joinPoint.getSignature());
		} else {
			logger.debug("使用数据源：" + dbid);
			DynamicDataSourceContextHolder.setDataSourceType(dbid);
		}
	}

	@After("@annotation(targetDataSource)")
	public void clearDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
		logger.info("清除数据源 " + targetDataSource.name() + " !");
		DynamicDataSourceContextHolder.clearDataSourceType();
	}
	

	
}
