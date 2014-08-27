package com.zcnation.zc.action;


import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcnation.zc.common.Result;
import com.zcnation.zc.common.ThreadLocalSession;
import com.zcnation.zc.context.ZcContext;
import com.zcnation.zc.domain.ZcUserInfo;
import com.zcnation.zc.service.ZcOrdersInfoService;
import com.zcnation.zc.service.ZcOrdesInfoNativeService;

@RequestMapping("/order")
@Controller
public class ZcOrderInfoAction {
	@Autowired private ZcOrdersInfoService zcOrdersInfoService;
	@Autowired private ZcOrdesInfoNativeService zcOrdesInfoNativeService;

	@RequestMapping("/order.xhtml")
	public String order(){
		return "order/order";
	}
	
	@RequestMapping("/putOrder.xhtml")
	@ResponseBody
	public String putorder(){
		Result r=new Result();
		zcOrdersInfoService.process();
		r.setSuccess(true);
		ZcUserInfo info=(ZcUserInfo)ThreadLocalSession.getLocal_session().getAttribute(ZcContext.LOGIN_USER_KEY);
		ThreadLocalSession.getLocal_session().removeAttribute(info.getUserCode()+"");
		return r.toJson();
	}
	
	
	@RequestMapping("/order_info.xhtml")
	public String to_publish(HttpServletRequest request, String  orderCode, String orderStatus) {
		System.out.println("orderCode"+orderCode);
		System.out.println("orderStatus"+orderStatus);
		//List<ZcProjectInfo> list=new ArrayList<ZcProjectInfo>();
		ZcUserInfo sezcUserInfo=(ZcUserInfo)request.getSession().getAttribute(ZcContext.LOGIN_USER_KEY);
		
		
		List list=zcOrdesInfoNativeService.queryByUserCodeAndOrderCodeAndOrderStatus(sezcUserInfo.getUserCode(), orderCode,orderStatus);
		//list=zcProjectInfoService.queryByUserCodeAndProNameLike(sezcUserInfo.getUserCode(),"%"+proName+"%");
		request.setAttribute("ordinfos", list);
		request.setAttribute("orderCode", orderCode);
		request.setAttribute("orderStatus", orderStatus);
		System.out.println(list.size());
		
//		if(list.size()>0){
//			for (int i = 0; i <list.size(); i++) {
//				System.out.println(list.get(i).getProCode());
//				System.out.println(list.get(i).getProName());
//			}
//			
//		}
		return "order/order_info";
	}
	
	
	@RequestMapping("/order_cart.xhtml")
	public String to_cart(HttpServletRequest request, String  orderCode, String orderStatus) {
		System.out.println("orderCode"+orderCode);
		System.out.println("orderStatus"+orderStatus);
		//List<ZcProjectInfo> list=new ArrayList<ZcProjectInfo>();
		ZcUserInfo sezcUserInfo=(ZcUserInfo)request.getSession().getAttribute(ZcContext.LOGIN_USER_KEY);
		
		
		List list=zcOrdesInfoNativeService.queryByUserCodeAndOrderCodeAndOrderStatus(sezcUserInfo.getUserCode(), orderCode,orderStatus);
		//list=zcProjectInfoService.queryByUserCodeAndProNameLike(sezcUserInfo.getUserCode(),"%"+proName+"%");
		request.setAttribute("ordinfos", list);
		request.setAttribute("orderCode", orderCode);
		request.setAttribute("orderStatus", orderStatus);
		System.out.println(list.size());
		
//		if(list.size()>0){
//			for (int i = 0; i <list.size(); i++) {
//				System.out.println(list.get(i).getProCode());
//				System.out.println(list.get(i).getProName());
//			}
//			
//		}
		return "order/order_cart";
	}
	
	
	
}
