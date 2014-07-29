/**********************************************
 * 杭州枭雄网络科技有限公司 @2014 版权所有
 * 项目：zc
 * 时间：2014年7月29日 下午12:30:15
 * 作者:xiaoymin@foxmail.com 萧玉民
 * 说明：
 **********************************************/
package com.zcnation.zc.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {
	private Logger logger=Logger.getLogger(CustomSimpleMappingExceptionResolver.class);

	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		String viewName=determineViewName(ex, request);
		logger.info("viewName:"+viewName);
		if (viewName!=null) {
			if (StringUtils.indexOf(request.getHeader("Accept"), "application/json") > -1 || StringUtils.indexOf(request.getHeader("X-Requested-With"), "XMLHttpRequest") > -1) {// 如果不是异步请求
				return null;
			}else{
				Integer codeStatus=determineStatusCode(request, viewName);
				logger.info("jsp格式返回："+viewName+" code:"+codeStatus);
				if (codeStatus!=null) {
					applyStatusCodeIfPossible(request, response, codeStatus);
				}
				request.setAttribute("error", ex.getMessage());
				return getModelAndView(viewName, ex,request);
			}
		}else{ 
			return null;
		}
	}
}
