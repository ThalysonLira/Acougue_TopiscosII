package br.unitins.acougue.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.acougue.application.Session;
import br.unitins.acougue.application.Util;
import br.unitins.acougue.model.User;

@Named @ViewScoped
public class TemplateController implements Serializable {

	private static final long serialVersionUID = 690957198281459215L;
	
	private User logUser = null;
	
	public void finishSession() {
		Session.getInstance().invalidateSession();
//		Util.redirect("/faces/login.xhtml");
	}
	
	public User getLogUser() {
		if (logUser == null)
			logUser = (User) Session.getInstance().getAttribute("logUser");
		return logUser;
	}
	
	public void setLogUser(User logUser) {
		this.logUser = logUser;
	}

}