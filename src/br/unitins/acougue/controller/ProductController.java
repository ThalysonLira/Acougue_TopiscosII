package br.unitins.acougue.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.file.UploadedFile;

import br.unitins.acougue.application.Util;
import br.unitins.acougue.controller.listener.ProductListener;
import br.unitins.acougue.model.Product;
import br.unitins.acougue.repository.ProductRepository;

@Named
@ViewScoped
public class ProductController extends Controller<Product> {

	private static final long serialVersionUID = 4570628857407841810L;

	private String search;
	private List<Product> listProduct;
	private InputStream pictureInputStream = null;
	private String pictureName = null;

	public void openProductListener() {
		ProductListener listener = new ProductListener();
		listener.open();
	}

	public void getProductListener(SelectEvent<?> event) {
		Product entity = (Product) event.getObject();
		setEntity(entity);
	}

	public void upload(FileUploadEvent event) {

		UploadedFile uploadFile = event.getFile();
		System.out.println("nome arquivo: " + uploadFile.getFileName());
		System.out.println("tipo: " + uploadFile.getContentType());
		System.out.println("tamanho: " + uploadFile.getSize());

		if (uploadFile.getContentType().equals("image/png")) {
			try {
				pictureInputStream = uploadFile.getInputStream();
				pictureName = uploadFile.getFileName();
				System.out.println("inputStream: " + uploadFile.getInputStream().toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			Util.addMessageInfo("Upload realizado com sucesso.");
		} else {
			Util.addMessageError("O tipo da image deve ser png.");
		}

	}

	@Override
	public void clear() {
		super.clear();
		pictureInputStream = null;
		pictureName = null;
	}

	@Override
	public void save() {
		// salvando no banco de dados
		if (safeSave()) {
			// caso nao tenha selecionado a imagem sair do metodo
			if (pictureInputStream == null) {
				clear();
				Util.addMessageInfo("Cadastro realizado com sucesso");
				return;
			}
			if (Util.saveImageProduct(pictureInputStream, "png", getEntity().getId())) {
				clear();
				Util.addMessageInfo("Cadastro realizado com sucesso");
				return;
			} else {
				clear();
				Util.addMessageWarn("Cadastro foi realizado com sucesso, porém a foto não foi salva.");
				return;
			}
		}
		Util.addMessageError("Erro ao efetuar o cadastro do professor.");
	}

	@Override
	public Product getEntity() {
		if (entity == null)
			entity = new Product();
		return entity;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<Product> getListProduct() {
		if (listProduct == null) {
			ProductRepository repository = new ProductRepository();
			listProduct = repository.findByAvailable("");
		}
		return listProduct;
	}

	public String getPictureName() {
		if (pictureName == null)
			return "Selecione uma foto ...";
		return "Arquivo: " + pictureName + " (Clique para alterar a foto...)";
	}

}