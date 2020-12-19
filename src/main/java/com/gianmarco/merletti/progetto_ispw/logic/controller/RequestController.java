package com.gianmarco.merletti.progetto_ispw.logic.controller;

import com.gianmarco.merletti.progetto_ispw.logic.bean.RequestBean;
import com.gianmarco.merletti.progetto_ispw.logic.dao.RequestDAO;
import com.gianmarco.merletti.progetto_ispw.logic.model.Request;

public class RequestController {

	public boolean creteRequest(RequestBean requestBean) {
		RequestDAO dao = new RequestDAO();
		Request request = dao.addRequest(requestBean);
		return (request != null);
	}

}
