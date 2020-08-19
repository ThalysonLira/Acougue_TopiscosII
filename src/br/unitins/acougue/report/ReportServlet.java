package br.unitins.acougue.report;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unitins.acougue.factory.JDBCFactory;

public abstract class ReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public abstract String getJasperFile();

	public abstract HashMap<String, Class<?>> getParameters();

	public abstract Connection getConnection();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String name = request.getServletContext().getRealPath("/reports/" + getJasperFile());

			Connection connection = getConnection();

			// Adicionando os parameters
			Map<String, Object> parameters = new HashMap<String, Object>();
			HashMap<String, Class<?>> parametersList = getParameters();
			if (parametersList != null)
				for (String key : parametersList.keySet()) {
					// excecao para valores nullos
					if (request.getParameter(key) == null) {
						parameters.put(key, null);
						continue;
					}
					// excecao para valores vazios
					Object param = request.getParameter(key);
					if (param instanceof String) {
						if (((String) param).trim().equals("") || ((String) param).trim().equals("null")) {
							parameters.put(key, null);
							continue;
						}
					}

					if (parametersList.get(key).getName().equals("java.lang.Integer"))
						parameters.put(key, Integer.valueOf(request.getParameter(key)));
					else if (parametersList.get(key).toString().contains("java.lang.String")) {
						parameters.put(key, new String(request.getParameter(key)));
					} else if (parametersList.get(key).toString().contains("java.lang.Boolean"))
						parameters.put(key, Boolean.valueOf(request.getParameter(key)));
					else if (parametersList.get(key).toString().contains("java.lang.Float"))
						parameters.put(key, Float.valueOf(request.getParameter(key)));
					else if (parametersList.get(key).toString().contains("java.lang.Double"))
						parameters.put(key, Double.valueOf(request.getParameter(key)));
				}

			GenerateReport generate = new GenerateReport(name, parameters, connection);
			generate.generatePDF(response.getOutputStream());

			JDBCFactory.closeConnection(connection);
			if (!connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}