package br.unitins.acougue.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unitins.acougue.application.Util;

public class ImgProductServlet extends HttpServlet {

	private static final long serialVersionUID = -3031813720759207659L;

	/*
     * @param request - recebe o parametro name (name da imagem do professor)
     * @param response - retorna a imagem
     * @throws ServletException
     * @throws IOException
     */
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        // recebendo o name da imagem 
        String name = request.getParameter("name");
        
        String directory = System.getProperty("user.home") + File.separator + Util.PATH_IMAGES_PRODUCT + File.separator;
        
        // montando a imagem com e endereco do servidor
        File image = new File(directory + name);
        
        // se o name da imagem for nulo ou se a imagem nao existir, enviar uma imagem padrao
        if ((name == null) || (!image.exists())) {
            File img = new File(directory + "user-image.png");
            // montando objeto de resposta
            response.reset();
            response.setContentType("image");
            response.setHeader("Content-Length", String.valueOf(img.length()));
            //atribuindo a imagem no obejto de resposta
            Files.copy(img.toPath(), response.getOutputStream());
            return;
        }
 
        // obtendo o tipo do arquivo (contentType) e verificando se eh realmente uma imagem
        // caso nao seja uma imagem, retorna um erro 404
        String contentType = getServletContext().getMimeType(image.getName());
        if ((contentType == null) || (!contentType.startsWith("image/"))) {
            response.sendError(404);
            return;
        }
        // montando objeto de resposta
        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(image.length()));
        //atribuindo a imagem no obejto de resposta
        Files.copy(image.toPath(), response.getOutputStream());
    }
    
}