package br.unitins.acougue.report;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class GenerateReport {

	private String file;
	private Map<String, Object> parameters;
	private Connection connection;

	public GenerateReport(String file, Map<String, Object> parameters, Connection connection) {
		this.file = file;
		this.parameters = parameters;
		this.connection = connection;
	}

	public void generatePDF(OutputStream outputStream) {
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(this.file, this.parameters, this.connection);

			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
			exporter.exportReport();

		} catch (JRException e) {
			e.printStackTrace();
		}

	}

}