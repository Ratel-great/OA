/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.icss.hit.struts.action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.icss.hit.bean.MessageSaveBean;
import com.icss.hit.bean.interfaces.MessageDao;
import com.icss.hit.component.PageBean;
import com.icss.hit.hibernate.vo.Message;
import com.icss.hit.hibernate.vo.MessageReceivers;
import com.icss.hit.hibernate.vo.SysUser;

/** 
 * 用于显示草稿箱里面的内容
 * Creation date: 08-07-2009
 * @author 赵颖申
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="draftBox.successd" path="/message/draftBox.jsp"
 * @struts.action-forward name="draftBox.fail" path="/message/draftBox.jsp"
 */
public class DraftBoxAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		MessageDao draftMessage = new MessageSaveBean();
		//读取当前用户的ID
		long uid = 1;
		if( request.getSession().getAttribute("UserId") != null ){
			uid = Long.parseLong(request.getSession().getAttribute("UserId").toString());
		}else{
			return mapping.findForward("NullLogin");
		}
		// 所有人员的数量
		int count = 0;
		// 分页的数量
		int pageCount = 0;
		
		int pageNo = 1;
		try{
			pageNo = Integer.parseInt(request.getParameter("page"));
		}catch(Exception e){
			// 如果数据异常
			pageNo = 1;
		}
		// 得到总数和分页
		count = draftMessage.getDraftMessageCount(uid);
		pageCount = draftMessage.getPageConut(count, MessageSaveBean.PAGE_SIZE);
		
		// 设置页面的范围
		pageNo = pageNo < 1? 1:pageNo;
		pageNo = pageNo > count? count: pageNo;
		
		List<MessageReceivers> allDraftMessage;
		//List<String[]> receiverList = null;
		//获得草稿箱里面的所有的信息
		allDraftMessage = draftMessage.getAllDraftMessage(uid, pageNo);
		//设置到request里面去，以便显示
		request.setAttribute("draftMessage", allDraftMessage);
		
		// 生成前面的页面显示
		PageBean pagebean = new PageBean();
		pagebean.setLink("draftBox.do");
		pagebean.setTotal(pageCount);
		pagebean.setThispage(pageNo);
		
		request.setAttribute("pageString", pagebean.getPageString());
		return mapping.findForward("draftBox.successd");
		
	}
}