/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.icss.hit.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/** 
 * MyEclipse Struts
 * Creation date: 08-09-2009
 * 
 * XDoclet definition:
 * @struts.form name="addSysPowerTypeForm"
 */
public class AddSysPowerTypeForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** sp_name property */
	private String sp_name;

	/** spt_name property */
	private String spt_name;

	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		ActionErrors errors = new ActionErrors();
		//Ȩ�ޱ�Ϊ��
		if(sp_name==null||sp_name.length()==0)
		{
			errors.add("sp_name", new ActionMessage("SpAdd.SpNameIsNull"));
		}
		request.setAttribute("form", this);
		return errors;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	/** 
	 * Returns the sp_name.
	 * @return String
	 */
	public String getSp_name() {
		return sp_name;
	}

	/** 
	 * Set the sp_name.
	 * @param sp_name The sp_name to set
	 */
	public void setSp_name(String sp_name) {
		this.sp_name = sp_name;
	}

	/** 
	 * Returns the spt_name.
	 * @return String
	 */
	public String getSpt_name() {
		return spt_name;
	}

	/** 
	 * Set the spt_name.
	 * @param spt_name The spt_name to set
	 */
	public void setSpt_name(String spt_name) {
		this.spt_name = spt_name;
	}
}