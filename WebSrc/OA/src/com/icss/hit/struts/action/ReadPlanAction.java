/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.icss.hit.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.icss.hit.bean.WorkListBean;
import com.icss.hit.bean.WorkPlanBean;
import com.icss.hit.bean.interfaces.WorkList;
import com.icss.hit.bean.interfaces.WorkPlanDao;
import com.icss.hit.component.PageBean;
import com.icss.hit.hibernate.vo.Schedule;

/**
 * �鿴����������ϸ��Ϣ
 * Creation date: 08-05-2009
 * @author ����
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 * @struts.action-forward name="readPlan.planList.succeed"
 *                        path="/work/planList.jsp"
 * @struts.action-forward name="readPlan.planListMore.succeed"
 *                        path="/work/planListMore.jsp"
 */
public class ReadPlanAction extends Action {
	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// �û�ID
		long userId = -1;
		if (request.getSession().getAttribute("UserId") != null) {
			userId = Long.parseLong(request.getSession().getAttribute("UserId")
					.toString());
		} else {
			return mapping.findForward("NullLogin");
		}
		// schedule��ID
		long schId = -1;
		// ҳ��
		int pageNo = 1;
		// ������Ա������
		String flag = null;
		// ������ת����
		String forwardString = null;
		try {
			schId = Long.parseLong(request.getParameter("schId"));
		} catch (Exception e) {
			schId = -1;
		}
		try {
			pageNo = Integer.parseInt(request.getParameter("page"));
			flag = request.getParameter("flag");
		} catch (Exception e) {
			pageNo = 1;
			flag = "planList";
		}
		if (schId != -1) {
			Schedule sch = new Schedule();
			WorkPlanDao wkDao = new WorkPlanBean();
			
			sch = wkDao.getWorkPlanByTo(schId, userId);
			request.setAttribute("planDetails", sch);
			// ��״̬���ó��Ѷ�
			sch.setSchRead("1");
			wkDao.update(sch);
			// ����ҳ��������ת����
			if (flag.equals("planList")) {
				forwardString = "planList.do?page=" + pageNo;
			} else if (flag.equals("planListMore")) {
				forwardString = "planListMore.do?page=" + pageNo;
			}
			request.setAttribute("forwardString", forwardString);
			return mapping.findForward("readPlan.succeed");
		}
		return mapping.findForward("readPlan.failed");

	}
}