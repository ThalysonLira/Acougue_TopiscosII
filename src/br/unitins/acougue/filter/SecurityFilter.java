package br.unitins.acougue.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.unitins.acougue.model.User;

@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/faces/pages/*"})
public class SecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest servletRequest = (HttpServletRequest) request;
		// imprime o endereco da pagina
		String address = servletRequest.getRequestURI();
		System.out.println(servletRequest.getRequestURL());
		System.out.println(address);
		
		// filtrando o nome da pagina
		if (address != null) {
			int start = address.lastIndexOf("/faces/") + 7;
			int end = address.length();
			address = address.substring(start, end);
		}
		System.out.println(address);
		
		// caso seja a pagina de login .. nao sera feita nenhuma restricao
		// deixo o fluxo seguir
		if (address.equals("login.xhtml")|| 
				servletRequest.getRequestURI().matches(".*(css|jpg|png|gif|js)")) {
			chain.doFilter(request, response);
			return;
		}
		
		// retorna a sessao corrente (false - para nao criar uma nova sessao)
		HttpSession session = servletRequest.getSession(false);
		
		User user = null;
		if (session != null)
			user = (User) session.getAttribute("logUser");
		
		if (user == null) {
			((HttpServletResponse) response).sendRedirect("/Acougue_TopicosII/faces/login.xhtml");
		}  else {
			// nesse local podemos trabalhar as permissoes por pagina
			if (user.getProfile().getAccess().contains(address)) {
				// segue o fluxo 
				chain.doFilter(request, response);
				return;
			} else {
				for (String pages : user.getProfile().getAccess()) {
					System.out.println(pages);
				}
				// seria melhor redirecionar para uma pagina dizendo que nao tem permissao
				((HttpServletResponse) response).sendRedirect("/Acougue_TopicosII/faces/acessonegado.xhtml");
			}

		}
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("SecurityFilter Iniciado.");
	}

	@Override
	public void destroy() {
		
	}

}