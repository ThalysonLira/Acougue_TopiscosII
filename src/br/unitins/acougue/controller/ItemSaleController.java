package br.unitins.acougue.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.acougue.model.ItemSale;

@Named
@ViewScoped
public class ItemSaleController extends Controller<ItemSale> {

	private static final long serialVersionUID = 4570571329338917143L;

	@Override
	public ItemSale getEntity() {
		if (entity == null)
			entity = new ItemSale();
		return entity;
	}

}
