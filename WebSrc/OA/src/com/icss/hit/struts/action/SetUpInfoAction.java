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

import com.icss.hit.bean.CheckPower;
import com.icss.hit.bean.DepartmentBean;
import com.icss.hit.bean.SysPositionBean;
import com.icss.hit.bean.UserInfoBean;
import com.icss.hit.bean.interfaces.Department;
import com.icss.hit.bean.interfaces.SysPositionDao;
import com.icss.hit.bean.interfaces.UserInfo;
import com.icss.hit.hibernate.vo.SysDept;
import com.icss.hit.hibernate.vo.SysPosition;
import com.icss.hit.hibernate.vo.SysUser;

/** 
 * �õ���Ҫ������Ϣ���û�
 * Creation date: 08-12-2009
 * @author ����
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="setUpInfo" path="/role/setUpInfo.jsp"
 */
public class SetUpInfoAction extends Action {
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
		// ��ǰ��¼�û�ID
		long userId = 1;
		if( request.getSession().getAttribute("UserId") != null ){
			userId = Long.parseLong(request.getSession().getAttribute("UserId").toString());
		}else{
			return mapping.findForward("NullLogin");
		}
		// �ж��û�Ȩ��
		CheckPower check = new CheckPower(userId);
		check.getPower();
		if( !check.check(CheckPower.ADMIN_STUFF_INFO)){
			return mapping.findForward("NullLogin");
		}
		
		
		long workerId = -1;//�洢��ѯ�ַ����е��û�ID����
		long dept = 0; // ���ź�
		int pageNo = 1; // ҳ��

		String id = request.getParameter("suId");//����String�� ��ת����Long
		try{
			if( id != null){
				workerId = Long.parseLong(id);
			}
		}
		catch (Exception e){
			e.printStackTrace();
			return mapping.findForward("setUpInfo.succeed");
		}
		try{
			dept = Integer.parseInt(request.getParameter("dept"));
			pageNo = Integer.parseInt(request.getParameter("page"));
		}catch(Exception e){
			// ��������쳣
			dept = 0;
			pageNo = 1;
		}
		
		Department deptm = new DepartmentBean();
		// �õ����еĲ�����Ϣ
		List<SysDept> deptList = deptm.getAllDept();
		request.setAttribute("deptList",deptList);
		
		SysPositionDao pos = new SysPositionBean();
		// �õ�����ְλ�б�
		List<SysPosition> posList = pos.getAllPosition();
		request.setAttribute("posList",posList);
		
		UserInfo info = new UserInfoBean();
		SysUser user = info.getUserInfo(workerId);
		request.setAttribute("userInfo",user);
		user.setPath(request.getRealPath("/headphoto/")+"\\");
		request.setAttribute("dept",dept);
		request.setAttribute("page", pageNo);
		return mapping.findForward("setUpInfo.succeed");
	}
}