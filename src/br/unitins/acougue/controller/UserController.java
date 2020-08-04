package br.unitins.acougue.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.acougue.controller.listener.UserListener;
import br.unitins.acougue.model.Profile;
import br.unitins.acougue.model.User;

@Named @ViewScoped
public class UserController extends Controller<User>{

	private static final long serialVersionUID = 3092155194023413476L;
	
	private String search;
	private List<User> listUser;
	
	public void openUserListener() {
		UserListener listener = new UserListener();
		listener.open();
	}
	
	public void getUserListener(SelectEvent event) {
		User entity = (User) event.getObject();
		setEntity(entity);
	}
	
	@Override
	public User getEntity() {
		if (entity == null)
			entity = new User();
		return entity;
	}
	
	public Profile[] getListProfile() {
		return Profile.values();
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}

}