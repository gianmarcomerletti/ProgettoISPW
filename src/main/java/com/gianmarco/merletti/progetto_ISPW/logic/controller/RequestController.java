package com.gianmarco.merletti.progetto_ISPW.logic.controller;

import com.gianmarco.merletti.progetto_ISPW.logic.bean.RequestBean;
import com.gianmarco.merletti.progetto_ISPW.logic.dao.RequestDAO;
import com.gianmarco.merletti.progetto_ISPW.logic.model.Request;

public class RequestController {

	public boolean creteRequest(RequestBean requestBean) {
		RequestDAO dao = new RequestDAO();
		Request request = dao.addRequest(requestBean);
		return (request != null);
	}

}
