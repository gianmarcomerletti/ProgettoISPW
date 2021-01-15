package com.gianmarco.merletti.progetto_ispw.logic.controller;

import java.util.ArrayList;
import java.util.List;

import com.gianmarco.merletti.progetto_ispw.logic.bean.RequestBean;
import com.gianmarco.merletti.progetto_ispw.logic.dao.EventDAO;
import com.gianmarco.merletti.progetto_ispw.logic.dao.RequestDAO;
import com.gianmarco.merletti.progetto_ispw.logic.exception.RequestException;
import com.gianmarco.merletti.progetto_ispw.logic.model.Request;

public class RequestController {

	public Integer createRequest(RequestBean requestBean) throws RequestException {
		RequestDAO dao = new RequestDAO();
		if (new EventDAO().checkParticipation(requestBean.getRequestUser(), requestBean.getRequestEvent().getEventId()) ||
				new RequestDAO().checkRequest(requestBean)) {
			throw new RequestException();
		}
		Request request = dao.addRequest(requestBean);
		return request.getIdRequest();
	}

	public List<RequestBean> loadRequests(String username) {
		List<Request> requests = new RequestDAO().findByUser(username);
		List<RequestBean> reqBeanList = new ArrayList<>();


		requests.stream().forEach(req -> {
			RequestBean reqBean = new RequestBean();
			reqBean.setRequestCreationDate(req.getCreationDate());
			reqBean.setRequestEvent(new LoadEventsController().getEventBeanFromEvent(new EventDAO().findById(req.getEvent())));
			reqBean.setRequestId(req.getIdRequest());
			reqBean.setRequestMessage(req.getMessage());
			reqBean.setRequestStatus(req.getStatus());
			reqBean.setRequestUser(req.getUser());
			reqBeanList.add(reqBean);
		});

		return reqBeanList;
	}

	public boolean acceptRequest(RequestBean bean) {
		RequestDAO dao = new RequestDAO();
		Request request = dao.activateRequest(bean.getRequestId());
		if (request != null)
			return new EventDAO().joinEvent(request.getUser(), request.getEvent());
		return false;
	}

	public boolean rejectRequest(RequestBean bean) {
		RequestDAO dao = new RequestDAO();
		Request request = dao.refuseRequest(bean.getRequestId());
		return (request != null);
	}

}
